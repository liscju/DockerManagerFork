package pl.edu.agh.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.dao.TaskDAO;
import pl.edu.agh.model.Task;
import pl.edu.agh.model.TaskStatus;

@Service
public class TaskRunner {

    @Autowired
    TaskDAO taskDAO;

    public void runSimpleTask(final Task task,final Runnable runnable) {
        Runnable stopJob = new Runnable() {
            public void run() {
                Task inprogressStopContainerTask = new Task(task.getId(),
                        task.getProperties(),
                        TaskStatus.INPROGRESS);
                taskDAO.updateTask(inprogressStopContainerTask);

                try {
                    runnable.run();

                    Task closedStopContainerTask = new Task(task.getId(),
                            task.getProperties(),
                            TaskStatus.SUCCESS_END);
                    taskDAO.updateTask(closedStopContainerTask);
                } catch (Exception e) {
                    Task closedStopContainerTask = new Task(task.getId(),
                            task.getProperties(),
                            TaskStatus.FAILURE_END);
                    taskDAO.updateTask(closedStopContainerTask);
                }
            }
        };

        Thread commandThread = new Thread(stopJob);
        commandThread.start();
    }
}
