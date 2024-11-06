package splitwise.models;

import java.util.List;

public class Group {
    private final String name;
    private final String desc;
    private final String imageUrl;
    private final List<String> userList;

    public Group(String name, String desc, String imageUrl, List<String> userList) {
        this.name = name;
        this.desc = desc;
        this.imageUrl = imageUrl;
        this.userList = userList;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<String> getUserList() {
        return userList;
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", userList=" + userList +
                '}';
    }
}
