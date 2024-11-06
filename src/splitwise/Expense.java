package splitwise;

import java.util.Date;

public class Expense {
    private Long id;
    private String description;
    private Double amount;
    private Date date;
    private boolean isSettled;
    private Long groupId;

    public Expense(Long id, String description, Double amount, Date date, Long groupId) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.isSettled = false;
        this.groupId = groupId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSettled() {
        return isSettled;
    }

    public void setSettled(boolean settled) {
        isSettled = settled;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
