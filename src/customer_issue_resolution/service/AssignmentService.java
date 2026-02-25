package customer_issue_resolution.service;

import java.util.ArrayList;
import java.util.List;

import customer_issue_resolution.enums.IssueStatus;
import customer_issue_resolution.model.Agent;
import customer_issue_resolution.model.Issue;
import customer_issue_resolution.repo.AgentRepo;
import customer_issue_resolution.repo.IssueRepo;
import customer_issue_resolution.strategy.AssigmentStrategy;

public class AssignmentService {
    private final AgentRepo agentRepo;
    private final IssueRepo issueRepo;
    private final AssigmentStrategy strategy;

    public AssignmentService(AgentRepo agentRepo, IssueRepo issueRepo, AssigmentStrategy strategy) {
        this.agentRepo = agentRepo;
        this.issueRepo = issueRepo;
        this.strategy = strategy;
    }

    public void assignIssues(String issueId) {
        Issue issue = issueRepo.getById(issueId);

        if(issue == null) {
            throw new IllegalArgumentException("Issue not found");
        }

        List<Agent> agents = new ArrayList<>(agentRepo.getAll());

        Agent assigned = strategy.assign(agents, issue);

        if(assigned != null) {
            assigned.setAssignedId(issue.getIssueId());
            issue.setAssignedAgentId(issue.getIssueId());
        } else {
            for(Agent agent: agents) {
                if(agent.isAvailable() && agent.getAgentExpertise().contains(issue.getType())) {
                    agent.getWaitlist().add(issue.getIssueId());
                    issue.setStatus(IssueStatus.WAITING);
                    System.out.println(">>> Issue " + issueId + " added to waitlist");
                    return;
                }
            }

            System.out.println("No agent found");
        }

    }
}
