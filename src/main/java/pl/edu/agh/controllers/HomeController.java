package pl.edu.agh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.agh.configuration.Configurator;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    Configurator configurator;

    public HomeController() {
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String getConfiguration(ModelMap model) {
        return "configuration/start";
    }

    @RequestMapping(value = "/configure", method = RequestMethod.POST)
    public String setServerAddress(@RequestParam("address") String address, ModelMap model) {
        configurator.setAddress(address);
        return "redirect:/home";
    }

    @RequestMapping(value="/home",method = RequestMethod.GET)
    public String getHome(ModelMap model) {
        model.addAttribute("username", "DEFAULT_USER");
        model.addAttribute("greeting", "Welcome to DockerManager");
        return "home/index";
    }

    @RequestMapping(value="/home/about",method = RequestMethod.GET)
    public String getAbout(ModelMap model) {
        return "home/about";
    }

    @RequestMapping(value="/home/contact",method = RequestMethod.GET)
    public String getContact(ModelMap model) {
        return "home/contact";
    }

}
