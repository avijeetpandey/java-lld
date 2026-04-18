package youtube_upload.search;

import youtube_upload.model.Video;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InvertedIndex {

    private final ConcurrentHashMap<String, Set<String>> index = new ConcurrentHashMap<>();

    public void indexVideo(Video video) {
        Stream<String> tokens = Stream.concat(
            tokenize(video.title()),
            video.tags().stream().map(String::toLowerCase)
        );

        String videoId = video.videoId();
        tokens.forEach(token -> {
            index.computeIfAbsent(token, k -> ConcurrentHashMap.newKeySet()).add(videoId);
        });
    }

    public void removeVideo(Video video) {
        Stream<String> tokens = Stream.concat(
            tokenize(video.title()),
            video.tags().stream().map(String::toLowerCase)
        );

        String videoId = video.videoId();
        tokens.forEach(token ->
            index.computeIfPresent(token, (key, videoIds) -> {
                videoIds.remove(videoId);
                return videoIds.isEmpty() ? null : videoIds;
            })
        );
    }

    public Set<String> search(String query) {
        if (query == null || query.isBlank()) {
            return Collections.emptySet();
        }

        String[] tokens = tokenize(query).toArray(String[]::new);
        if (tokens.length == 0) {
            return Collections.emptySet();
        }

        Set<String> resultSet = index.getOrDefault(tokens[0], Collections.emptySet());
        if (resultSet.isEmpty()) {
            return Collections.emptySet();
        }
        
        resultSet = ConcurrentHashMap.newKeySet();
        resultSet.addAll(index.getOrDefault(tokens[0], Collections.emptySet()));

        for (int i = 1; i < tokens.length; i++) {
            Set<String> otherSet = index.getOrDefault(tokens[i], Collections.emptySet());
            resultSet.retainAll(otherSet);
            if (resultSet.isEmpty()) {
                break;
            }
        }

        return resultSet;
    }

    private Stream<String> tokenize(String text) {
        if (text == null || text.isBlank()) {
            return Stream.empty();
        }
        return Arrays.stream(text.toLowerCase().split("[^a-zA-Z0-9]+"))
                     .filter(s -> !s.isEmpty());
    }
}
