package pl.edu.agh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.agh.dao.ContainerDAO;
import pl.edu.agh.model.Container;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    ContainerDAO containerDAO;

    @RequestMapping(value="/home",method = RequestMethod.GET)
    public String home(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        model.addAttribute("username", username);
        model.addAttribute("greeting", "Welcome to DockerManager");
        return "home/index";
    }

    @RequestMapping(value="/home/containers",method = RequestMethod.GET)
    public String containers(ModelMap model) {
        List<Container> containers = containerDAO.getUserContainers();
        model.addAttribute("containers",containers);
        return "home/containers";
    }

    @RequestMapping(value="/home/about",method = RequestMethod.GET)
    public String about(ModelMap model) {
        return "home/about";
    }

    @RequestMapping(value="/home/contact",method = RequestMethod.GET)
    public String contact(ModelMap model) {
        return "home/contact";
    }

}
