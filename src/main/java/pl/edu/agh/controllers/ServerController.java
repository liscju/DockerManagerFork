package pl.edu.agh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.edu.agh.dao.DockerServerDAO;
import pl.edu.agh.docker.DockerManager;
import pl.edu.agh.docker.InfoItem;
import pl.edu.agh.model.DockerServer;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class ServerController {

    @Autowired
    DockerServerDAO dockerServerDAO;
    DockerManager dockerManager;

    @RequestMapping(value="/home/servers",method = RequestMethod.GET)
    public String servers(ModelMap model) {
        List<DockerServer> servers = dockerServerDAO.getDockerServers();
        model.addAttribute("servers",servers);
        return "home/servers";
    }

    @RequestMapping(value="/home/servers",method = RequestMethod.POST)
    public String add_server(ModelMap model,@RequestParam("name") String name,@RequestParam("address") String address) {
    	dockerServerDAO.insertServer(name, address);
        return "redirect:/home/servers";
    }
    

    @RequestMapping(value = "/home/servers/{serverID}",method = RequestMethod.POST)
    public String perform_action(ModelMap model,@PathVariable String serverID,@RequestParam("action") String action) {
    	if (action.equals("delete")){
    		dockerServerDAO.deleteServer(Integer.parseInt(serverID));
            return "redirect:/home/servers";
    	}
        return "/home/server_details";
    }

    @RequestMapping(value = "/home/servers/{serverID}", method=RequestMethod.GET)
    public String getOrder(@PathVariable String serverID,ModelMap model){
    	List<InfoItem> items=null;
        DockerServer server = dockerServerDAO.getDockerServer(Integer.parseInt(serverID));
        model.addAttribute("serverID",server.getServerID());
        model.addAttribute("name", server.getName());
        model.addAttribute("address", server.getAddress());
        try{
        dockerManager = new DockerManager(server.getAddress());
        items=dockerManager.getInfoAsList();
        model.addAttribute("items",items);       
        }catch (Exception e){
            model.addAttribute("error","Cannot connect to server");       
        }
        return "home/server_details";
    }
    
    
    
}
