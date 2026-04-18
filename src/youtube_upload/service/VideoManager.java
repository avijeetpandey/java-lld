package youtube_upload.service;

import youtube_upload.cache.LRUCache;
import youtube_upload.exception.DuplicateVideoException;
import youtube_upload.exception.VideoNotFoundException;
import youtube_upload.model.Video;
import youtube_upload.search.InvertedIndex;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

public class VideoManager {

    private final ConcurrentHashMap<String, Video> videoStore = new ConcurrentHashMap<>();
    private final InvertedIndex searchIndex = new InvertedIndex();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final LRUCache<String, Set<Video>> searchCache = new LRUCache<>(100);

    public void uploadVideo(Video video) throws DuplicateVideoException {
        if (videoStore.putIfAbsent(video.videoId(), video) != null) {
            throw new DuplicateVideoException("Video with ID '" + video.videoId() + "' already exists.");
        }
        searchIndex.indexVideo(video);
        searchCache.clear();
    }

    public Video getVideo(String videoId) throws VideoNotFoundException {
        Video video = videoStore.get(videoId);
        if (video == null) {
            throw new VideoNotFoundException("Video with ID '" + videoId + "' not found.");
        }
        return video;
    }

    public void deleteVideo(String videoId) throws VideoNotFoundException {
        lock.writeLock().lock();
        try {
            Video video = videoStore.remove(videoId);
            if (video == null) {
                throw new VideoNotFoundException("Cannot delete. Video with ID '" + videoId + "' not found.");
            }
            searchIndex.removeVideo(video);
            searchCache.clear();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Set<Video> searchVideos(String query) {
        Set<Video> cachedResult = searchCache.get(query);
        if (cachedResult != null) {
            System.out.println("(Serving from cache for query: '" + query + "')");
            return cachedResult;
        }

        System.out.println("(Performing full search for query: '" + query + "')");
        Set<String> videoIds = searchIndex.search(query);

        Set<Video> results;
        lock.readLock().lock();
        try {
            results = videoIds.stream()
                           .map(videoStore::get)
                           .filter(java.util.Objects::nonNull)
                           .collect(Collectors.toSet());
        } finally {
            lock.readLock().unlock();
        }

        searchCache.put(query, results);

        return results;
    }
}
