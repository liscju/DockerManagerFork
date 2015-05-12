package pl.edu.agh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Image;

import pl.edu.agh.configuration.WebMVCConfigurator;
import pl.edu.agh.dao.ContainerDAO;
import pl.edu.agh.docker.DockerManager;
import pl.edu.agh.docker.DockerManagerRepository;
import pl.edu.agh.docker.InfoItem;
import pl.edu.agh.model.Container;
import pl.edu.agh.model.DockerServer;
import pl.edu.agh.controllers.api.CustomController;
import pl.edu.agh.model.Container;
import pl.edu.agh.model.User;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

@Controller
@RequestMapping("/")
public class ContainerController extends CustomController {

    @Value("${docker.server.address}")
    private String dockerServerAddress;

    @RequestMapping(value="/home/containers",method = RequestMethod.GET)
    public String containers(ModelMap model) {
        User user = getCurrentUser();
        List<Container> containers = containerDAO.getUserContainers(user);
        model.addAttribute("containers",containers);
        return "home/containers";
    }

    @RequestMapping(value="/home/containers",method = RequestMethod.POST)
    public String search_container(ModelMap model,@RequestParam(value="containerImage",required=false) String containerImageToAdd, 
    		@RequestParam(value="to_pull",required=false)  String container) {
        DockerManager dockerManager=new DockerManager(dockerServerAddress);
        User user = getCurrentUser();
        List<Container> containers = containerDAO.getUserContainers(user);
        model.addAttribute("containers",containers);
    	if(containerImageToAdd!=null){
	        model.addAttribute("sItems",dockerManager.searchForImage(containerImageToAdd));
    	}
    	if(container!=null){
        	dockerManager.pullContainer(container);

        	containerDAO.insertContainer(container,getCurrentUser());
            return "redirect:/home/containers";

    	}
        return "/home/containers";
    }

    @RequestMapping(value = "/home/containers/{containerId}", method=RequestMethod.GET)
    public String getOrder(@PathVariable String containerId,ModelMap model){
        Container container = containerDAO.getContainer(Integer.parseInt(containerId));
        DockerManager dockerManager=new DockerManager(dockerServerAddress);
        Image i =dockerManager.listImages().get(Integer.parseInt(containerId)-1);
        model.addAttribute("imageS",i);
        java.util.Date created=new java.util.Date((long)i.getCreated()*1000);
        model.addAttribute("created",created);

        model.addAttribute("code",container.hashCode());
        model.addAttribute("id",container.getId());
        model.addAttribute("image", container.getImage());
        return "home/container_details";
    }
    

}
