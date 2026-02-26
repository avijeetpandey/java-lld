package book_my_show.models;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Theater {
    private final String id;
    private final Map<String, Screen> screens = new HashMap<>();
    private final String name;

    public Theater(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public Screen getScreen(String screenId) {
        return screens.get(screenId);
    }

    public void addScreen(Screen screen) {
        screens.put(screen.getId(), screen);
    }

    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
}
