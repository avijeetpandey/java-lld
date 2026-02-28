package design_patterns.ProxyDesignPattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProxyDesignPattern {
    interface Internet {
        void connect(String internetUrl);
    }

    static class RealInternet implements Internet {
        @Override
        public void connect(String internetUrl) {
            System.out.println("Connected to URL " + internetUrl);
        }
    }


    static class Firewall implements Internet {
        private final List<String> bannedSites;
        Internet internet;

        public Firewall(List<String> bannedWebsites) {
            this.bannedSites = bannedWebsites;
            this.internet = new RealInternet();
        }

        @Override
        public void connect(String internetUrl) {
            if(bannedSites.contains(internetUrl)) {
                System.out.println("Unable to connect to blocked domain");
            } else {
                internet.connect(internetUrl);
            }
        }
    }

    public static void main(String[] args) {
        List<String> bannedWebsites = Arrays.asList("https://banned.com","https://instagram.com");
        Firewall firewall = new Firewall(bannedWebsites);
        firewall.connect("https://banned.com");
        firewall.connect("https://google.com");
    }
}
