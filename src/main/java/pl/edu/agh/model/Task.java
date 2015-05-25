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

    private TaskStatus status;

    public Task() {
    }

    public Task(String properties) {
        this.properties = properties;
        this.status = TaskStatus.BEGGINING;
    }

    public Task(int id,String properties, TaskStatus status) {
        this.id = id;
        this.properties = properties;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getProperties() {
        return properties;
    }

    public TaskStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != task.id) return false;
        if (properties != null ? !properties.equals(task.properties) : task.properties != null) return false;
        if (status != task.status) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (properties != null ? properties.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
