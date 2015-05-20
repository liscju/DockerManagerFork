package pl.edu.agh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.agh.dao.ImageDAO;
import pl.edu.agh.docker.DockerManager;
import pl.edu.agh.model.DockerServer;
import pl.edu.agh.model.Image;
import pl.edu.agh.model.User;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class ImageController {

    @Autowired
    ImageDAO imageDAO;

    @RequestMapping(value="/home/images",method = RequestMethod.GET)
    public String getAllImages(ModelMap model) {
        List<Image> allImages = imageDAO.getAllImages();
        model.addAttribute("images",allImages);
        return "home/images";
    }

    @RequestMapping(value = "/home/images/{imageId}", method=RequestMethod.GET)
    public String getImage(@PathVariable String imageId, ModelMap model){
        Image image = imageDAO.getImage(imageId);

        model.addAttribute("image",image);
        return "home/image_details";
    }

    @RequestMapping(value = "/home/images/add_image_from_dockerfile",method = RequestMethod.POST)
    public String addImageFromDockerfile(ModelMap modelMap,
                                         @RequestParam("image_name") String image_name,
                                         @RequestParam("dockerfile") String dockerfile) {

        imageDAO.addImageFromDockerfile(image_name, dockerfile);
        return "redirect:/home/images";
    }

    @RequestMapping(value = "/home/images/run", method=RequestMethod.POST)
    public String quickImageRun(ModelMap model,
                                @RequestParam("imageId") String imageId,
                                @RequestParam("command") String command){
        String output = imageDAO.runQuickCommandInImage(imageId, command);
        model.addAttribute("imageId", imageId);
        model.addAttribute("command", command);
        model.addAttribute("output", output);
        return "home/image_run";
    }

    @RequestMapping(value = "/home/images/create_image_for_war",method = RequestMethod.POST)
    public String createImageForWar(ModelMap modelMap,
                                    @RequestParam("image_name") String image_name,
                                    @RequestParam("war_file") MultipartFile war_file) {
        try {
            imageDAO.createImageForWar(image_name,war_file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "home/containers";
    }

    @RequestMapping(value = "/home/images/find_image", method=RequestMethod.POST)
    public String findImage(ModelMap model,
                            @RequestParam("image_to_find") String imageToFind){

        List<Image> allImages = imageDAO.getAllImages();
        Map<String, String> foundImages = imageDAO.searchForImageByName(imageToFind);
        model.addAttribute("images",allImages);
        model.addAttribute("found_images",foundImages);
        return "home/images";
    }

    @RequestMapping(value = "/home/images/pull_image", method=RequestMethod.POST)
    public String pullImage(ModelMap model,
                            @RequestParam("image_to_pull") String imageToPull){

        imageDAO.pullImage(imageToPull);
        return "redirect:home/images";
    }
}
