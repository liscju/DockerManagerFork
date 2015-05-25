package pl.edu.agh.model;

public enum TaskStatus {
    BEGGINING("BEGGINING"),
    INPROGRESS("INPROGRESS"),
    SUCCESS_END("SUCCESS_END"),
    FAILURE_END("FAILURE_END");

    private String name;

    TaskStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
