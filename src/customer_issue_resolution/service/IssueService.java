package customer_issue_resolution.service;

import java.util.List;
import java.util.Map;

import customer_issue_resolution.enums.IssueStatus;
import customer_issue_resolution.enums.IssueType;
import customer_issue_resolution.model.Agent;
import customer_issue_resolution.model.Issue;
import customer_issue_resolution.repo.AgentRepo;
import customer_issue_resolution.repo.IssueRepo;

public class IssueService {
    private final IssueRepo issueRepo;
    private final AgentRepo agentRepo;

    public IssueService(IssueRepo issueRepo, AgentRepo agentRepo) {
        this.issueRepo = issueRepo;
        this.agentRepo = agentRepo;
    }

    public Issue createIssue(String transactionId, IssueType issueType, String subject, String description, String email) {
        Issue issue = new Issue(transactionId, issueType, subject, description, email);
        issueRepo.save(issue);
        System.out.println(">>> Issue " + issue.getIssueId() + " created");
        return issue;
    }

    public List<Issue> getIssues(Map<String, String> filter) {
        return issueRepo.getAll().stream().filter(issue -> {
            if(filter.containsKey("email") && !issue.getEmail().equalsIgnoreCase(filter.get("email"))) {
                return false;
            }

            if(filter.containsKey("type") && !issue.getType().name().equalsIgnoreCase(filter.get("type"))) {
                return false;
            }

            if(filter.containsKey("status") && !issue.getStatus().name().equalsIgnoreCase(filter.get("status"))) {
                return false;
            }

            return true;
        }).toList();
    }

    public void updateStatus(String issueId, IssueStatus status, String resolution) {
        Issue issue = issueRepo.getById(issueId);
        if(issue == null) {
            throw new IllegalArgumentException("Issue not found");
        }

        issue.setStatus(status);
        issue.setResolution(resolution);

        System.out.println(">>> " + issueId + " status updated to " + issue.getEmail());
    }

    public void resolveIssue(String issueId, String resolution) {
                Issue issue = issueRepo.getById(issueId);
        if(issue == null) {
            throw new IllegalArgumentException("Issue not found");
        }

        issue.setStatus(IssueStatus.RESOLVED);
        issue.setResolution(resolution);

        if(issue.getAssignedAgentId() != null) {
            Agent agent = agentRepo.getById(issue.getAssignedAgentId());
            if(agent != null) {
                agent.getHistory().add(issue.getIssueId());
            }
        }

        System.out.println(">>> " + issueId + " resolved");
    }
}
