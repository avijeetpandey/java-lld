package customer_issue_resolution.model;

import customer_issue_resolution.enums.IssueType;

import java.util.UUID;

import customer_issue_resolution.enums.IssueStatus;

public class Issue {
    private final String issueId;
    private final String transactionId;
    private final IssueType type;
    private final String subject;
    private final String description;
    private IssueStatus status = IssueStatus.OPEN;
    private final String email;
    private String resolution;
    private String assignedAgentId;

    public Issue(String transactionId,
            IssueType type,
            String subject,
            String description,
            String email) {
        this.issueId = UUID.randomUUID().toString();
        this.transactionId = transactionId;
        this.type = type;
        this.subject = subject;
        this.description = description;
        this.email = email;
        this.resolution = null;
        this.assignedAgentId = null;
    }

    public String getIssueId() {
        return issueId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public IssueType getType() {
        return type;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public String getEmail() {
        return email;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getAssignedAgentId() {
        return assignedAgentId;
    }

    public void setAssignedAgentId(String agentId) {
        this.assignedAgentId = agentId;
    }

    @Override
    public String toString() {
        return "Issue [issueId=" + issueId + ", transactionId=" + transactionId + ", type=" + type + ", subject="
                + subject + ", description=" + description + ", status=" + status + ", email=" + email + ", resolution="
                + resolution + ", assignedAgentId=" + assignedAgentId + ", getIssueId()=" + getIssueId()
                + ", getTransactionId()=" + getTransactionId() + ", getType()=" + getType() + ", getSubject()="
                + getSubject() + ", getDescription()=" + getDescription() + ", getStatus()=" + getStatus()
                + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
                + "]";
    }
}
