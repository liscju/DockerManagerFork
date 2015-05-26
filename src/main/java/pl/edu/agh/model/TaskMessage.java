package pl.edu.agh.model;

import javax.persistence.*;

@Entity
public class TaskMessage {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Task task;

    private String details;

    public TaskMessage() {
    }

    public TaskMessage(Task task, String details) {
        this.task = task;
        this.details = details;
    }

    public TaskMessage(int id,Task task, String details) {
        this.task = task;
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public Task getTask() {
        return task;
    }

    public String getDetails() {
        return details;
    }
}
