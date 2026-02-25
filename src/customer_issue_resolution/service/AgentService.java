package customer_issue_resolution.service;

import java.util.HashSet;
import java.util.List;

import customer_issue_resolution.enums.IssueType;
import customer_issue_resolution.model.Agent;
import customer_issue_resolution.repo.AgentRepo;

public class AgentService {
    private final AgentRepo agentRepo;

    public AgentService(AgentRepo agentRepo) {
        this.agentRepo = agentRepo;
    }

    public void addAgent(String email, String name, List<IssueType> issueTypes) {
        Agent agent = new Agent(email, new HashSet<>(issueTypes), name);
        agentRepo.save(agent);
        System.out.println(">>> Agent " + agent.getAgentId() + " created");
    }

    public void viewAgentsWorkHistory() {
        for(Agent agent: agentRepo.getAll()) {
            System.out.println(agent.getAgentId() + " -> " + agent.getHistory());
        }
    }
}
