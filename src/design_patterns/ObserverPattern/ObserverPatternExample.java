package design_patterns.ObserverPattern;

import java.util.ArrayList;
import java.util.List;

public class ObserverPatternExample {
    interface Subscriber {
        void notify(String videoTitle);
    }

    static class YoutubeChannel {
        private final List<Subscriber> subscribers = new ArrayList<>();

        public void subscribe(Subscriber sub) {
            subscribers.add(sub);
        }

        public void unsubscribe(Subscriber sub) {
            subscribers.remove(sub);
        }

        public void uploadVideo(String title) {
            System.out.println("Video uploaded with " + title);
            // notify all the subscribers
            for(Subscriber sub : subscribers) {
                sub.notify(title);
            }
        }
    }


    static class User implements Subscriber {
        private final String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        public void notify(String videoTitle) {
            System.out.println("Hi " + name + " new video is out " + videoTitle);
        }
    }

    public static void main(String[] args) {
        YoutubeChannel channel = new YoutubeChannel();
        Subscriber aman = new User("Aman");
        Subscriber ajay = new User("ajay");

        channel.subscribe(ajay);
        channel.subscribe(aman);

        channel.uploadVideo("Aiko's video");
    }
}
