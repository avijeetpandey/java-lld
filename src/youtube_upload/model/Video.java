package youtube_upload.model;

import java.time.Instant;
import java.util.Set;

public record Video(
    String videoId,
    String title,
    String description,
    String uploader,
    Set<String> tags,
    Instant uploadTime,
    long sizeInBytes
) {
    public Video {
        if (videoId == null || videoId.isBlank()) {
            throw new IllegalArgumentException("Video ID cannot be null or empty.");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Video title cannot be null or empty.");
        }
    }
}
