package pl.edu.agh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.agh.dao.ContainerDAO;
import pl.edu.agh.controllers.api.CustomController;
import pl.edu.agh.model.Container;

import java.util.List;

@Controller
@RequestMapping("/")
public class ContainerController extends CustomController {

    @Autowired
    ContainerDAO containerDAO;

    @RequestMapping(value="/home/containers",method = RequestMethod.GET)
    public String getAllContainers(ModelMap model) {
        List<Container> allContainers = containerDAO.getAllContainers();
        model.addAttribute("containers",allContainers);
        return "home/containers";
    }

    @RequestMapping(value = "/home/containers/{containerId}", method=RequestMethod.GET)
    public String getContainer(@PathVariable String containerId, ModelMap model){
        Container container = containerDAO.getContainer(containerId);

        model.addAttribute("container",container);
        return "home/container_details";
    }

    @RequestMapping(value = "/home/containers/stop_container", method=RequestMethod.POST)
    public String stopContainer(ModelMap model, @RequestParam("containerId") String containerId){
        containerDAO.stopContainer(containerId);
        return "redirect:/home/containers";
    }

    @RequestMapping(value = "/home/containers/delete_container", method=RequestMethod.POST)
    public String deleteContainer(ModelMap model, @RequestParam("containerId") String containerId){
        containerDAO.deleteContainer(containerId);
        return "redirect:/home/containers";
    }

}
