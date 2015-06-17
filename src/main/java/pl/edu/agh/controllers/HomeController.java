package pl.edu.agh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;
import pl.edu.agh.configuration.Configurator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

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

    @RequestMapping(value = "/set_language_eng",method = RequestMethod.POST)
    public String setLanguageEng(ModelMap model,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        localeResolver.setLocale(request,response,new Locale("en"));
        return "redirect:/";
    }

    @RequestMapping(value = "/set_language_pl",method = RequestMethod.POST)
    public String setLanguagePl(ModelMap model,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        localeResolver.setLocale(request,response,new Locale("pl"));
        return "redirect:/";
    }

    @RequestMapping(value = "/configure", method = RequestMethod.POST)
    public String setServerAddress(@RequestParam("address") String address, ModelMap model) {
        configurator.setAddress(address);
        return "redirect:/home";
    }

    @RequestMapping(value="/home",method = RequestMethod.GET)
    public String getHome(ModelMap model) {
        return "home/index";
    }

    @RequestMapping(value="/home/about",method = RequestMethod.GET)
    public String getAbout(ModelMap model) {
        return "home/about";
    }

}
