package pl.edu.agh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.dockerjava.api.DockerClient;

import pl.edu.agh.configuration.WebMVCConfigurator;
import pl.edu.agh.dao.ContainerDAO;
import pl.edu.agh.docker.DockerManager;
import pl.edu.agh.docker.DockerManagerRepository;
import pl.edu.agh.docker.InfoItem;
import pl.edu.agh.model.Container;
import pl.edu.agh.model.DockerServer;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

@Controller
@RequestMapping("/")
//@Import({ DockerManagerRepository.class })
public class ContainerController {

    @Autowired
    ContainerDAO containerDAO;
    DockerManager dockerManager;
    
//	@Resource(name="dockerServers")
//    HashMap<Integer,DockerManager> dockerServers;
//    

    @RequestMapping(value="/home/containers",method = RequestMethod.GET)
    public String containers(ModelMap model) {
        List<Container> containers = containerDAO.getUserContainers();
        model.addAttribute("containers",containers);
        return "home/containers";
    }

    @RequestMapping(value="/home/containers",method = RequestMethod.POST)
    public String search_container(ModelMap model,@RequestParam(value="containerImage",required=false) String containerImageToAdd, 
    		@RequestParam(value="to_pull",required=false)  String container) {
    	dockerManager=new DockerManager("http://127.0.0.1:2375");
    	if(containerImageToAdd!=null){
	        model.addAttribute("sItems",dockerManager.searchForImage(containerImageToAdd));
    	}
    	if(container!=null){
        	dockerManager.pullContainer(container);
        	containerDAO.insertContainer(container);
            return "redirect:/home/containers";

    	}
        //containerDAO.insertContainer(containerImageToAdd);
        //return "redirect:/home/containers";
        return "/home/containers";
    }
    
//    @RequestMapping(value="/home/containers/",method = RequestMethod.POST)
//    public String pull_container(ModelMap model, @RequestParam(value="to_pull",required=false)  String container) {
//    	dockerManager=new DockerManager("http://127.0.0.1:2375");
//    	dockerManager.pullContainer(container);
//    	containerDAO.insertContainer(container);
//
//        return "/home/containers";
//    }

    @RequestMapping(value = "/home/containers/{containerId}", method=RequestMethod.GET)
    public String getOrder(@PathVariable String containerId,ModelMap model){
        Container container = containerDAO.getContainer(Integer.parseInt(containerId));
        model.addAttribute("code",container.hashCode());
        model.addAttribute("id",container.getId());
        model.addAttribute("image", container.getImage());
        return "home/container_details";
    }
    

}
