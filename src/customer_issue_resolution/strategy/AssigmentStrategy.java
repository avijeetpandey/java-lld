package customer_issue_resolution.strategy;

import java.util.List;

import customer_issue_resolution.model.Agent;
import customer_issue_resolution.model.Issue;

public interface AssigmentStrategy {
    Agent assign(List<Agent> agents, Issue issue);    
}