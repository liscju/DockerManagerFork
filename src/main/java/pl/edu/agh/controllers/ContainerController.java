package pl.edu.agh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.edu.agh.dao.ContainerDAO;
import pl.edu.agh.docker.DockerManager;
import pl.edu.agh.controllers.api.CustomController;
import pl.edu.agh.model.Container;

import java.util.List;

@Controller
@RequestMapping("/")
public class ContainerController extends CustomController {

    @Autowired
    ContainerDAO containerDAO;

    @RequestMapping(value="/home/containers",method = RequestMethod.GET)
    public String containers(ModelMap model) {
        List<Container> allContainers = containerDAO.getAllContainers();
        model.addAttribute("containers",allContainers);
        return "home/containers";
    }

}
