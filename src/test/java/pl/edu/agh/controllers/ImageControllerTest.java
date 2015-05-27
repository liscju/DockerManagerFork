package pl.edu.agh.controllers;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.agh.dao.ImageDAO;
import pl.edu.agh.model.Image;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class ImageControllerTest {

    private ImageDAO imageDAO;
    private ImageController imageController;
    private ModelMap modelMap;

    @Before
    public void setUp() throws Exception {
        imageDAO = mock(ImageDAO.class);
        imageController = new ImageController(imageDAO);
        modelMap = mock(ModelMap.class);
    }

    @Test
    public void testGetAllContainers() throws Exception {
        LinkedList<Image> allImages = new LinkedList<Image>();
        when(imageDAO.getAllImages()).thenReturn(allImages);
        String nextView = imageController.getAllImages(modelMap);
        assertEquals("home/images", nextView);
        verify(modelMap).addAttribute("images", allImages);
    }

    @Test
    public void testGetImage() throws Exception {
        Image img = new Image("15","HELLO");
        when(imageDAO.getImage("15")).thenReturn(img);
        String nextView = imageController.getImage("15",modelMap);
        assertEquals("home/image_details", nextView);
        verify(modelMap).addAttribute("image",img);
    }

    @Test
    public void testAddImageFromDockerfile() throws Exception {
        String nextView = imageController.addImageFromDockerfile(modelMap, "doc", "FROM /");
        assertEquals("redirect:/home/images", nextView);
        verify(imageDAO).addImageFromDockerfile("doc", "FROM /");
    }

    @Test
    public void testQuickImageRun() throws Exception {
        String imageId = "15";
        String command = "PWD";
        String output = "OK";
        when(imageDAO.runQuickCommandInImage(imageId, command)).thenReturn(output);
        String nextView = imageController.quickImageRun(modelMap, imageId, command);
        verify(modelMap).addAttribute("imageId", imageId);
        verify(modelMap).addAttribute("command", command);
        verify(modelMap).addAttribute("output", output);
    }


    @Test
    public void testPullImage() throws Exception {
        String imageToPull = "fedora 5";
        String nextView = imageController.pullImage(modelMap,imageToPull);
        assertEquals("redirect:home/images",nextView);
        verify(imageDAO).pullImage(imageToPull);
    }
}