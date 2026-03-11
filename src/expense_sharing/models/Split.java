package expense_sharing.models;

public abstract class Split {
    private final String userId;
    protected double amount;

    public Split(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
