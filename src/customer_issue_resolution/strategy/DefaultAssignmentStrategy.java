package customer_issue_resolution.strategy;

import java.util.List;

import customer_issue_resolution.model.Agent;
import customer_issue_resolution.model.Issue;

public class DefaultAssignmentStrategy implements AssigmentStrategy {

    @Override
    public Agent assign(List<Agent> agents, Issue issue) {

        for(Agent agent: agents) {
            if(agent.isAvailable() && agent.getAgentExpertise().contains(issue.getType())) {
                return agent;
            }
        }
        
        return null;
    }
    
}
