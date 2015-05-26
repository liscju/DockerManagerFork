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

    private String errResult;

    public Task() {
    }

    public Task(String properties) {
        this.properties = properties;
        this.status = TaskStatus.BEGGINING;
        this.errResult = "";
    }

    public Task(int id,String properties, TaskStatus status) {
        this.id = id;
        this.properties = properties;
        this.status = status;
        this.errResult = "";
    }

    public Task(int id,String properties, TaskStatus status,String errResult) {
        this.id = id;
        this.properties = properties;
        this.status = status;
        this.errResult = errResult;
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

    public String getErrResult() {
        return errResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != task.id) return false;
        if (properties != null ? !properties.equals(task.properties) : task.properties != null) return false;
        if (status != task.status) return false;
        if (errResult != null ? !errResult.equals(task.errResult) : task.errResult != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result1 = id;
        result1 = 31 * result1 + (properties != null ? properties.hashCode() : 0);
        result1 = 31 * result1 + (status != null ? status.hashCode() : 0);
        result1 = 31 * result1 + (errResult != null ? errResult.hashCode() : 0);
        return result1;
    }
}
