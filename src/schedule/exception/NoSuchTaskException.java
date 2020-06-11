package schedule.exception;

public class NoSuchTaskException extends TasksTotalityException {

    private static final String template = "tasks don't contain required one for sorting: %s";

    public NoSuchTaskException(String taskName) {
        super(String.format(template, taskName));
    }
}
