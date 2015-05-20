package pl.edu.agh.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.agh.controllers.api.CustomController;

@Controller
@RequestMapping("/")
public class HomeController extends CustomController {

    @RequestMapping(value="/home",method = RequestMethod.GET)
    public String home(ModelMap model) {
        String username = getCurrentUser().getName();

        model.addAttribute("username", username);
        model.addAttribute("greeting", "Welcome to DockerManager");
        return "home/index";
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
