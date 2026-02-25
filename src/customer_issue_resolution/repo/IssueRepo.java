package customer_issue_resolution.repo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import customer_issue_resolution.model.Issue;

public class IssueRepo {
    private final Map<String, Issue> issues = new HashMap<>();

    public void save(Issue issue) {
        issues.put(issue.getIssueId(), issue);
    }

    public Issue getById(String id) {
        return issues.get(id);
    }

    public Collection<Issue> getAll() {
        return issues.values();
    }
}
