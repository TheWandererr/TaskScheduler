package schedule;

import model.Task;
import schedule.exception.NoSuchTaskException;
import schedule.exception.TasksTotalityException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class Scheduler implements IScheduler {

    private List<Task> sorted;
    private List<Task> tasks;

    private Set<Task> proceedTasks;
    private Set<String> proceedNames;

    private Set<String> requiredPrev;

    public Scheduler() {
        this.sorted = new ArrayList<>();
        this.proceedTasks = new HashSet<>();
        this.proceedNames = new HashSet<>();
        this.requiredPrev = new HashSet<>();
    }

    @Override
    public List<Task> schedule(List<Task> tasks) {
        if (isEmpty(tasks)) {
            return tasks;
        }
        processTasks(tasks);
        return sorted;
    }

    private void processTasks(List<Task> all) {
        checkNullNames(all);
        // no need to check the list for uniqueness of tasks
        tasks = all;
        for (Task t : tasks) {
            processOne(t);
            requiredPrev.clear();
        }
    }

    private void processOne(Task current) {
        if (isCompleted(current)) {
            return;
        }
        List<String> prevs = new ArrayList<>(current.getPredecessors());
        removeCompleted(prevs);
        if (!isEmpty(prevs)) {
            requiredPrev.add(current.getName());
            processPrevs(prevs);
        }
        sorted.add(current);
        proceedTasks.add(current);
        proceedNames.add(current.getName());
    }

    private void processPrevs(List<String> prevs) {
        checkLooping(prevs);
        prevs.forEach(prevTaskName -> {
            Task prev = getPrevByName(prevTaskName);
            processOne(prev);
        });
    }

    private boolean isEmpty(List<?> elems) {
        return elems.size() == 0;
    }

    private void checkNullNames(List<Task> tasks) {
        if (tasks.stream().anyMatch(task -> task.getName() == null)) {
            throw new TasksTotalityException("task's name cannot be NULL");
        }
    }

    private void removeCompleted(List<String> prevs) {
        prevs.removeAll(proceedNames);
    }

    private Task getPrevByName(String name) {
        List<Task> existing = tasks.stream()
                .filter(task -> task.getName().equals(name))
                .collect(Collectors.toList());
        if (isEmpty(existing)) {
            throw new NoSuchTaskException(name);
        }
        return existing.get(0);
    }

    private boolean isCompleted(Task t) {
        return proceedTasks.contains(t);
    }

    private void checkLooping(List<String> prevs) {
        if (prevs.stream().anyMatch(taskName -> requiredPrev.contains(taskName))) {
            throw new TasksTotalityException("looping");
        }
    }
}
