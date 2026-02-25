package customer_issue_resolution.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;

import customer_issue_resolution.enums.IssueType;

public class Agent {
    private final String agentId;
    private final String name;
    private final String email;
    private final Set<IssueType> expertise;

    private String assignedIssueId;
    private final Queue<String> waitlist = new LinkedList<>();
    private final List<String> history = new ArrayList<>();

    public Agent(String email, Set<IssueType> expertise, String name) {
        this.agentId = UUID.randomUUID().toString();
        this.email = email;
        this.expertise = expertise;
        this.name = name;
    }

    public String getAgentId() {
        return agentId;
    }

    public String getEmail() {
        return email;
    }

    public Set<IssueType> getType() {
        return expertise;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return assignedIssueId == null;
    }

    public List<String> getHistory() {
        return history;
    }

    public Queue<String> getWaitlist() {
        return this.waitlist;
    }

    public Set<IssueType> getAgentExpertise() {
        return expertise;
    }

    public String setAssignedId(String issueId) {
        return this.assignedIssueId = issueId;
    }
}
