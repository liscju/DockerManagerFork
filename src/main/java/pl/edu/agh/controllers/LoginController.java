package pl.edu.agh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.agh.dao.UserDAO;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    UserDAO userDAO;

    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String login(ModelMap model) {
        return "authentication/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String show_register(ModelMap modelMap) {
        return "authentication/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String do_register(@RequestParam("username") String username, @RequestParam("password") String password,
                              @RequestParam("password_confirm") String password_confirm, ModelMap modelMap) {

        if (!password.equals(password_confirm)) {
            modelMap.addAttribute("bad_registration_info", "Password and confirmed password not equals");
            return "authentication/register";
        } else if (userDAO.existUser(username)) {
            modelMap.addAttribute("bad_registration_info", "User already exist");
            return "authentication/register";
        } else {
            userDAO.insertUser(username,password,"user",true);
            modelMap.addAttribute("login_message", "You have succesfully registered account,you can now login with your credentials");
            return "authentication/login";
        }
    }
}
