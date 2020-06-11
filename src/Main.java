import model.Task;
import schedule.IScheduler;
import schedule.Scheduler;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Task> tasks = Arrays.asList(
                new Task("A", Arrays.asList("B")),
                new Task("B", Arrays.asList("D", "T", "C", "K")),
                new Task("D", Arrays.asList()),
                new Task("T", Arrays.asList("G")),
                new Task("C", Arrays.asList("T")),
                new Task("K", Arrays.asList("G", "C")),
                new Task("G", Arrays.asList())
        );

        IScheduler scheduler = new Scheduler();
        List<Task> sortedTasks = scheduler.schedule(tasks);
        for (Task t : sortedTasks) {
            System.out.println(t.getName());
        }
    }
}