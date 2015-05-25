package pl.edu.agh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.agh.dao.ImageDAO;
import pl.edu.agh.model.Image;
import pl.edu.agh.model.Task;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class ImageController {

    @Autowired
    ImageDAO imageDAO;

    public ImageController() {}

    public ImageController(ImageDAO imageDAO) {
        this.imageDAO = imageDAO;
    }

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

        Task task = imageDAO.addImageFromDockerfile(image_name, dockerfile);
        return "redirect:/home/tasks/" + task.getId();
    }

    @RequestMapping(value = "/home/images/quick_command_run", method=RequestMethod.POST)
    public String quickImageRun(ModelMap model,
                                @RequestParam("imageId") String imageId,
                                @RequestParam("command") String command){
        String output = imageDAO.runQuickCommandInImage(imageId, command);
        model.addAttribute("imageId", imageId);
        model.addAttribute("command", command);
        model.addAttribute("output", output);
        return "home/image_run";
    }

    @RequestMapping(value = "/home/images/run_image_in_container", method=RequestMethod.POST)
    public String runImageInContainer(ModelMap model,
                                      @RequestParam("imageId") String imageId){

        String containerId = imageDAO.runImageInContainer(imageId);
        return "redirect:/home/containers";
    }

    @RequestMapping(value = "/home/images/create_image_for_war",method = RequestMethod.POST)
    public String createImageForWar(ModelMap modelMap,
                                    @RequestParam("image_name") String image_name,
                                    @RequestParam("war_file") MultipartFile war_file) {
        try {
            imageDAO.createImageForWar(image_name,war_file.getOriginalFilename(),war_file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "home/images";
    }

    @RequestMapping(value = "/home/images/find_image", method=RequestMethod.GET)
    public @ResponseBody Map<String, String> findImage(@RequestParam("image_to_find") String imageToFind){
        Map<String, String> foundImages = imageDAO.searchForImageByName(imageToFind);
        return foundImages;
    }

    @RequestMapping(value = "/home/images/pull_image", method=RequestMethod.POST)
    public String pullImage(ModelMap model,
                            @RequestParam("image_to_pull") String imageToPull){

        imageDAO.pullImage(imageToPull);
        return "redirect:/home/images";
    }
}
