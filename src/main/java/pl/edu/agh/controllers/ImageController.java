package pl.edu.agh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.agh.dao.ImageDAO;
import pl.edu.agh.docker.DockerManager;
import pl.edu.agh.model.Container;
import pl.edu.agh.model.DockerServer;
import pl.edu.agh.model.Image;
import pl.edu.agh.model.User;

import java.io.IOException;
import java.util.List;

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
        model.addAttribute("imageId",imageId);
        return "home/image_details";
    }

    @RequestMapping(value = "/home/images/add_image_from_dockerfile",method = RequestMethod.POST)
    public String addImageFromDockerfile(ModelMap modelMap,
                                         @RequestParam("image_name") String image_name,
                                         @RequestParam("dockerfile") String dockerfile) {

        imageDAO.addImageFromDockerfile(image_name,dockerfile);
        return "redirect:/home/images";
    }
}
