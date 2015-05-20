package pl.edu.agh.controllers;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.ModelMap;
import pl.edu.agh.dao.ContainerDAO;
import pl.edu.agh.model.Container;

import java.util.LinkedList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ContainerControllerTest {

    @Test
    public void testGetAllContainers() throws Exception {
        ContainerDAO containerDAO = mock(ContainerDAO.class);
        ContainerController containerController = new ContainerController(containerDAO);
        ModelMap modelMap = mock(ModelMap.class);
        LinkedList<Container> allContainers = new LinkedList<Container>();

        when(containerDAO.getAllContainers()).thenReturn(allContainers);

        String nextView = containerController.getAllContainers(modelMap);

        assertEquals("home/containers",nextView);
        verify(modelMap).addAttribute("containers", allContainers);
    }

    @Test
    public void testGetContainer() throws Exception {
        ContainerDAO containerDAO = mock(ContainerDAO.class);
        ContainerController containerController = new ContainerController(containerDAO);
        ModelMap modelMap = mock(ModelMap.class);
        Container container = new Container("15",null,null,null,null);
    }
}