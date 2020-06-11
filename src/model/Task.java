package model;

import java.util.List;
import java.util.Objects;

public class Task {

    /**
     * Unique name of the activity
     */
    private String name;
    private List<String> predecessors;

    public Task(String name, List<String> predecessors) {
        this.name = name;
        this.predecessors = predecessors;
    }

    public String getName() {
        return name;
    }

    public List<String> getPredecessors() {
        return predecessors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return this.name.equals(task.getName()) && this.predecessors.containsAll(task.getPredecessors());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, getPredecessors());
    }
}