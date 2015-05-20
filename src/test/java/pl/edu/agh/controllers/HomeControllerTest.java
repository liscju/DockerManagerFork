package pl.edu.agh.controllers;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import pl.edu.agh.controllers.api.UserAuthorizator;
import pl.edu.agh.dao.UserDAO;
import pl.edu.agh.model.User;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class HomeControllerTest {

    private UserDAO userDAO;
    private UserAuthorizator userAuthorizator;
    private HomeController homeController;
    private ModelMap modelMap;

    @Before
    public void setUp() throws Exception {
        userDAO = mock(UserDAO.class);
        userAuthorizator = mock(UserAuthorizator.class);
        homeController = new HomeController(userDAO, userAuthorizator);
        modelMap = mock(ModelMap.class);
    }

    @Test
    public void testGetHome() throws Exception {
        User user = new User("Piotr","***","admin",true);
        when(userAuthorizator.getCurrentUser()).thenReturn(user);

        String nextView = homeController.getHome(modelMap);
        assertEquals(nextView,"home/index");
        modelMap.addAttribute("username", "Piotr");
        modelMap.addAttribute("greeting","Welcome to DockerManager");
    }

    @Test
    public void testGetAbout() throws Exception {
        String nextView = homeController.getAbout(modelMap);
        assertEquals("home/about",nextView);
    }

    @Test
    public void testGetContact() throws Exception {
        String nextView = homeController.getContact(modelMap);
        assertEquals("home/contact",nextView);
    }

}