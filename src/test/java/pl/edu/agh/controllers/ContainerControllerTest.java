package pl.edu.agh.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.ModelMap;
import pl.edu.agh.dao.ContainerDAO;
import pl.edu.agh.model.Container;

import java.util.LinkedList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ContainerControllerTest {

    private ContainerDAO containerDAO;
    private ContainerController containerController;
    private ModelMap modelMap;

    @Before
    public void setUp() throws Exception {
        containerDAO = mock(ContainerDAO.class);
        containerController = new ContainerController(containerDAO);
        modelMap = mock(ModelMap.class);
    }

    @Test
    public void testGetAllContainers() throws Exception {
        LinkedList<Container> allContainers = new LinkedList<Container>();

        when(containerDAO.getAllContainers()).thenReturn(allContainers);

        String nextView = containerController.getAllContainers(modelMap);

        assertEquals("home/containers",nextView);
        verify(modelMap).addAttribute("containers", allContainers);
    }

    @Test
    public void testGetContainer() throws Exception {
        Container container = new Container("15",null,null,null,null);

        when(containerDAO.getContainer("15")).thenReturn(container);

        String nextView = containerController.getContainer("15",modelMap);

        assertEquals("home/container_details",nextView);
        verify(modelMap).addAttribute("container",container);
    }

    @Test
    public void testStopContainer() throws Exception {
        String nextView = containerController.stopContainer(modelMap,"20");
        assertEquals("redirect:/home/containers",nextView);
        verify(containerDAO).stopContainer("20");
    }

    @Test
    public void testDeleteContainer() throws Exception {
        String nextView = containerController.deleteContainer(modelMap, "20");
        assertEquals("redirect:/home/containers",nextView);
        verify(containerDAO).deleteContainer("20");
    }

}











