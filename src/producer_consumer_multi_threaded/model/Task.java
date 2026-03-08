package producer_consumer_multi_threaded.model;

public class Task {
    private final String taskId;
    private final String payload;

    public Task(String taskId, String payload) {
        this.taskId = taskId;
        this.payload = payload;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        return "Task [taskId=" + taskId + ", payload=" + payload + "]";
    }
}
