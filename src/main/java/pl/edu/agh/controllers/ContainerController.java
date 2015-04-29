package pl.edu.agh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.agh.dao.ContainerDAO;
import pl.edu.agh.model.Container;

import java.util.List;

@Controller
@RequestMapping("/")
public class ContainerController {

    @Autowired
    ContainerDAO containerDAO;

    @RequestMapping(value="/home/containers",method = RequestMethod.GET)
    public String containers(ModelMap model) {
        List<Container> containers = containerDAO.getUserContainers();
        model.addAttribute("containers",containers);
        return "home/containers";
    }

    @RequestMapping(value="/home/containers",method = RequestMethod.POST)
    public String add_container(ModelMap model,@RequestParam("containerImage") String containerImageToAdd) {
        containerDAO.insertContainer(containerImageToAdd);
        return "redirect:/home/containers";
    }
}
