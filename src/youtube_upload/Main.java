package youtube_upload;

import youtube_upload.exception.DuplicateVideoException;
import youtube_upload.exception.VideoNotFoundException;
import youtube_upload.model.Video;
import youtube_upload.service.VideoManager;

import java.time.Instant;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- YouTube-Lite Video Management System with Caching ---");
        VideoManager manager = new VideoManager();

        System.out.println("\n[1] Uploading videos...");
        try {
            manager.uploadVideo(new Video("v001", "Core Java Tutorial: Concurrency", "A deep dive into Java's concurrent utilities.", "UploaderA", Set.of("java", "tutorial", "concurrency"), Instant.now(), 500 * 1024 * 1024));
            manager.uploadVideo(new Video("v002", "Advanced Python Programming", "Exploring advanced topics in Python.", "UploaderB", Set.of("python", "programming", "advanced"), Instant.now(), 750 * 1024 * 1024));
            manager.uploadVideo(new Video("v003", "Java vs. Python: A Speed Test", "Comparing the performance of Java and Python.", "UploaderA", Set.of("java", "python", "performance", "test"), Instant.now(), 300 * 1024 * 1024));
            System.out.println("Uploads successful. Search cache has been cleared.");
        } catch (DuplicateVideoException e) {
            System.err.println("Error during upload: " + e.getMessage());
        }

        System.out.println("\n[2] Demonstrating search with caching...");
        System.out.println("\n--- First Search (Cache Miss) ---");
        Set<Video> javaVideos = manager.searchVideos("java");
        javaVideos.forEach(v -> System.out.println("  - Found: " + v.title()));

        System.out.println("\n--- Second Search (Cache Hit) ---");
        Set<Video> javaVideosFromCache = manager.searchVideos("java");
        javaVideosFromCache.forEach(v -> System.out.println("  - Found: " + v.title()));

        System.out.println("\n[3] Deleting a video...");
        try {
            manager.deleteVideo("v003");
            System.out.println("Deleted video with ID 'v003'. Search cache has been cleared.");
        } catch (VideoNotFoundException e) {
            System.err.println("Error during deletion: " + e.getMessage());
        }

        System.out.println("\n[4] Verifying deletion and cache invalidation...");
        System.out.println("\n--- Third Search for 'java' (Cache Miss) ---");
        Set<Video> javaVideosAfterDelete = manager.searchVideos("java");
        javaVideosAfterDelete.forEach(v -> System.out.println("  - Found: " + v.title()));
        if (javaVideosAfterDelete.stream().noneMatch(v -> v.videoId().equals("v003"))) {
            System.out.println("  - Video 'Java vs. Python: A Speed Test' correctly removed from new search results.");
        }

        System.out.println("\n[5] Simulating concurrent uploads (will clear cache)...");
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 100; i < 200; i++) {
            int videoNum = i;
            executor.submit(() -> {
                try {
                    manager.uploadVideo(new Video("v" + videoNum, "Concurrent Video " + videoNum, "Desc...", "UploaderC", Set.of("concurrent", "test"), Instant.now(), 1024));
                } catch (DuplicateVideoException e) {
                    System.err.println(e.getMessage());
                }
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
            System.out.println("Concurrent uploads finished.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
