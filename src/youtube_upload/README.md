# In-Memory "YouTube-Lite" Video Management System (VMS)

This project is a high-performance, in-memory video management system built with **Core Java (17+)** and the standard Java Collections Framework. It is designed for high concurrency and thread safety without relying on any external libraries, application servers, or databases.

## System Architecture

The system is designed around a few key components that work together to provide a scalable and thread-safe environment for managing video metadata.

### 1. Core Data Structures

- **Video Metadata Storage (`videoStore`)**:
  - **Data Structure:** `java.util.concurrent.ConcurrentHashMap<String, Video>`
  - **Reasoning:** `ConcurrentHashMap` is the cornerstone of the system's concurrency model. It provides excellent performance for a high number of read operations (like `getVideo` and `searchVideos`) and a moderate number of write operations (`uploadVideo`). It achieves this through fine-grained locking (locking on segments or nodes rather than the entire map), which allows multiple threads to read and write concurrently without blocking each other, thus avoiding a major performance bottleneck.

- **Search Index (`InvertedIndex`)**:
  - **Data Structure:** `ConcurrentHashMap<String, Set<String>>`
  - **Reasoning:** To achieve fast, O(1)-like search performance, we avoid linear scans (O(N)) at all costs. An **Inverted Index** is the ideal data structure for this. It works like the index at the back of a book: it maps keywords (tokens) to a list (or `Set`) of video IDs that contain those keywords.
  - The outer map `ConcurrentHashMap<String, ...>` allows multiple threads to safely index or de-index different tokens at the same time.
  - The inner `Set<String>` (instantiated as `ConcurrentHashMap.newKeySet()` for thread-safety) stores the video IDs for a given token. A `Set` is used to automatically handle duplicate video IDs for a single token.

### 2. Concurrency Strategy

- **Read Operations (`getVideo`, `searchVideos`)**: These operations are highly concurrent. `ConcurrentHashMap` allows lock-free reads. A `ReentrantReadWriteLock` is used in `searchVideos` primarily to ensure memory visibility and consistency when reading from the `videoStore` after getting results from the search index, protecting against rare race conditions with `deleteVideo`.

- **Write Operations (`uploadVideo`, `deleteVideo`)**:
  - `uploadVideo`: This operation is mostly non-blocking. `ConcurrentHashMap.putIfAbsent()` is an atomic operation, ensuring that a video is only inserted if its ID isn't already present, which gracefully handles duplicate upload attempts.
  - `deleteVideo`: This is the most critical write operation. It must remove the video from *both* the `videoStore` and the `InvertedIndex` in an **atomic** fashion. If we only removed it from the store, the search index would contain stale references (a memory leak). If we only removed it from the index, the video would be unsearchable but still in memory. To solve this, a `ReentrantReadWriteLock.writeLock()` is used. An exclusive write lock ensures that no other thread can read or write while a deletion is in progress, guaranteeing atomicity and preventing inconsistent states.

## Complexity Analysis

| Operation      | Time Complexity                                     | Space Complexity                                 |
|----------------|-----------------------------------------------------|--------------------------------------------------|
| **Upload Video** | `O(T + G)`                                          | `O(T + G)`                                       |
| **Search Video** | `O(Q + M*log(M) + R)`                               | `O(R)`                                           |
| **Delete Video** | `O(T + G)`                                          | `O(1)`                                           |

**Where:**
- `T`: Number of words (tokens) in the video's title.
- `G`: Number of tags for the video.
- `Q`: Number of words (tokens) in the search query.
- `M`: The size of the smallest set of video IDs found for a token in the query.
- `R`: The number of videos in the final result set.

## Getting Started

### Prerequisites
- Java JDK 17 or higher.

### Compilation
Navigate to the `src` directory and compile the Java files:
```bash
# Navigate to the source directory
cd /path/to/your/project/src

# Compile all java files into the parent directory
javac -d . youtube_upload/model/*.java youtube_upload/exception/*.java youtube_upload/search/*.java youtube_upload/service/*.java youtube_upload/Main.java
```

### Execution
From the `src` directory, run the `Main` class:
```bash
java youtube_upload.Main
```

## Usage Examples

The `Main.java` class provides a clear demonstration of the system's functionality.

```java
import youtube_upload.exception.DuplicateVideoException;
import youtube_upload.exception.VideoNotFoundException;
import youtube_upload.model.Video;
import youtube_upload.service.VideoManager;

import java.time.Instant;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        VideoManager manager = new VideoManager();

        // 1. Upload a video
        try {
            manager.uploadVideo(new Video(
                "v001",
                "Core Java Tutorial: Concurrency",
                "A deep dive into Java's concurrent utilities.",
                "UploaderA",
                Set.of("java", "tutorial", "concurrency"),
                Instant.now(),
                500 * 1024 * 1024
            ));
            System.out.println("Upload successful.");
        } catch (DuplicateVideoException e) {
            System.err.println("Error: " + e.getMessage());
        }

        // 2. Search for the video
        System.out.println("\nSearching for 'java concurrency':");
        Set<Video> searchResults = manager.searchVideos("java concurrency");
        searchResults.forEach(v -> System.out.println("  - Found: " + v.title()));

        // 3. Delete the video
        try {
            manager.deleteVideo("v001");
            System.out.println("\nDeletion successful.");
        } catch (VideoNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }

        // 4. Verify deletion
        Set<Video> postDeleteResults = manager.searchVideos("java concurrency");
        if (postDeleteResults.isEmpty()) {
            System.out.println("Video correctly removed from search index.");
        }
    }
}
```
