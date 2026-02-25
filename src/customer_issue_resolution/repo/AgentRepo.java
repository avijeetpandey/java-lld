package customer_issue_resolution.repo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import customer_issue_resolution.model.Agent;

public class AgentRepo {
    private final Map<String, Agent> agents = new HashMap<>();
    
    public void save(Agent agent) {
        agents.put(agent.getAgentId(), agent);
    }

    public Agent getById(String id){
        return agents.get(id);
    }

    public Collection<Agent> getAll() {
        return agents.values();
    }
}
