package schedule.exception;

public class TasksTotalityException extends IllegalArgumentException {

    private static final String template = "Impossible to sort this list of tasks: %s";

    public TasksTotalityException(String reason) {
        super(String.format(template, reason));
    }
}
