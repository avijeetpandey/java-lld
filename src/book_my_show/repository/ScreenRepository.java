package book_my_show.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import book_my_show.models.Screen;

public class ScreenRepository {
    private final Map<String, Screen> screens = new HashMap<>();

    public void addScreen() {
        Screen screen = new Screen();
        screens.put(screen.getId(), screen);
    }

    public Screen get(String id) {
        if(screens.containsKey(id)) {
            return screens.get(id);
        }

        return null;
    }

    public List<Screen> getAll() {
        return new ArrayList<>(screens.values());
    }
}
