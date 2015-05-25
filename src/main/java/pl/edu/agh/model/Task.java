package pl.edu.agh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String properties;

    private TaskStatus taskStatus;

    public Task() {
    }

    public Task(String properties, TaskStatus taskStatus) {
        this.properties = properties;
        this.taskStatus = taskStatus;
    }

    public int getId() {
        return id;
    }

    public String getProperties() {
        return properties;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != task.id) return false;
        if (properties != null ? !properties.equals(task.properties) : task.properties != null) return false;
        if (taskStatus != task.taskStatus) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (properties != null ? properties.hashCode() : 0);
        result = 31 * result + (taskStatus != null ? taskStatus.hashCode() : 0);
        return result;
    }
}
