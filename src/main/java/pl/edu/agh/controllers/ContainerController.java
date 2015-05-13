package pl.edu.agh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.Image;

import pl.edu.agh.dao.ContainerDAO;
import pl.edu.agh.dao.DockerServerDAO;
import pl.edu.agh.docker.DockerManager;
import pl.edu.agh.model.Container;
import pl.edu.agh.model.DockerServer;
import pl.edu.agh.controllers.api.CustomController;
import pl.edu.agh.model.User;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/")
public class ContainerController extends CustomController {

    @Value("${docker.server.address}")
    private String dockerServerAddress;
    
    @Autowired
    ContainerDAO containerDAO;
    @Autowired
    DockerServerDAO dockerServerDAO;
    DockerManager dockerManager;

    @RequestMapping(value="/home/containers",method = RequestMethod.GET)
    public String containers(ModelMap model) {
        User user = getCurrentUser();
        List<Container> containers = containerDAO.getUserContainers(user);
        List<DockerServer> servers = dockerServerDAO.getDockerServers();
        model.addAttribute("servers",servers);
        model.addAttribute("containers",containers);
        return "home/containers";
    }

    @RequestMapping(value="/home/containers",method = RequestMethod.POST)
    public String search_container(ModelMap model,@RequestParam(value="containerImage",required=false) String containerImageToAdd,
    		@RequestParam(value="to_pull",required=false)  String container, @RequestParam(value="serverToUse",required=false) String saddress) {
    	if (saddress!=null){
    		dockerManager=new DockerManager(saddress);
    	}else{
            dockerManager=new DockerManager(dockerServerAddress);
    	}
        User user = getCurrentUser();
        List<Container> containers = containerDAO.getUserContainers(user);
        model.addAttribute("containers",containers);
    	if(containerImageToAdd!=null){
	        model.addAttribute("sItems",dockerManager.searchForImage(containerImageToAdd));
	        model.addAttribute("sToPull",saddress);

    	}
    	if(container!=null){
        	dockerManager.pullContainer(container);

        	containerDAO.insertContainer(container,getCurrentUser(), saddress);
            return "redirect:/home/containers";

    	}
        return "/home/containers";
    }
    
    @RequestMapping(value = "/home/containers/{containerId}", method=RequestMethod.POST)
    public String manupulateContainer(@PathVariable String containerId,ModelMap model,@RequestParam("action") String action){
        dockerManager=new DockerManager(dockerServerAddress);
    	if (action.equals("delete")){
    		containerDAO.deleteContainer(Integer.parseInt(containerId)-1);
            dockerManager.deleteContainer(containerId);

            return "redirect:/home/containers";
    	}
            else if (action.equals("create")){
    		CreateContainerResponse image = dockerManager.createContainer(containerDAO.getContainer(Integer.parseInt(containerId)-1).getImage(), null, null);
    		dockerManager.startContainer(image);
    	}
    	
    	
		return "home/container_details";

    }

    
    

    @RequestMapping(value = "/home/containers/{containerId}", method=RequestMethod.GET)
    public String getOrder(@PathVariable String containerId,ModelMap model){
        Container container = containerDAO.getContainer(Integer.parseInt(containerId));
        DockerManager dockerManager=new DockerManager(container.getOnServer());
        Image i =dockerManager.listImages().get(Integer.parseInt(containerId)-1);
        model.addAttribute("imageS",i);
        java.util.Date created=new java.util.Date((long)i.getCreated()*1000);
        model.addAttribute("created",created);

        model.addAttribute("code",container.hashCode());
        model.addAttribute("id",container.getId());
        model.addAttribute("image", container.getImage());
        return "home/container_details";
    }

    @RequestMapping(value = "/home/containers/run",method = RequestMethod.POST)
    public String run_container(ModelMap modelMap,
                                @RequestParam("container_image") String container_image,
                                @RequestParam("command") String command) {

        DockerManager dockerManager=new DockerManager(dockerServerAddress);
        String output = null;
        try {
            output = dockerManager.runImageCommand(container_image, command);
        } catch (IOException e) {
            e.printStackTrace();
        }

        modelMap.addAttribute("container_image",container_image);
        modelMap.addAttribute("output", output);
        return "home/container_run";
    }

    @RequestMapping(value = "/home/containers/add_image_from_dockerfile",method = RequestMethod.POST)
    public String add_image_from_dockerfile(ModelMap modelMap,
                                            @RequestParam("image_name") String image_name,
                                            @RequestParam("dockerfile") String dockerfile) {

        DockerManager dockerManager = new DockerManager(dockerServerAddress);
        try {
            dockerManager.createImageFromDockerFile(image_name,dockerfile);
            containerDAO.insertContainer(image_name,getCurrentUser(),dockerServerAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/home/containers";
    }
}
