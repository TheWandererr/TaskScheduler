package schedule;

import model.Task;

import java.util.List;

public interface IScheduler {
    List<Task> schedule(List<Task> tasks);
}
