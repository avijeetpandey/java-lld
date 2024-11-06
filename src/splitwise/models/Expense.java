package splitwise.models;

public class Expense {
    private final BalanceMap userBalances;
    private final String title;
    private final String description;
    private final String imageUrl;
    private final String groupId;

    public Expense(BalanceMap userBalances, String title, String description, String imageUrl, String groupId) {
        this.userBalances = userBalances;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.groupId = groupId;
    }

    public BalanceMap getUserBalances() {
        return userBalances;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getGroupId() {
        return groupId;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "userBalances=" + userBalances +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", groupId='" + groupId + '\'' +
                '}';
    }
}
