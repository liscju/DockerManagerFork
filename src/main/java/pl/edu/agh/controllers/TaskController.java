package pl.edu.agh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.agh.dao.TaskDAO;
import pl.edu.agh.model.Container;
import pl.edu.agh.model.Task;

@Controller
@RequestMapping("/")
public class TaskController {

    @Autowired
    TaskDAO taskDAO;

    @RequestMapping(value = "/home/tasks/{taskId}", method= RequestMethod.GET)
    public String getTask(@PathVariable String taskId, ModelMap model){
        Task task = taskDAO.getTask(new Integer(taskId));
        model.addAttribute("task",task);
        return "home/task_details";
    }
}
