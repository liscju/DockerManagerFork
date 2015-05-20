package pl.edu.agh.controllers;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import pl.edu.agh.dao.ContainerDAO;
import pl.edu.agh.dao.UserDAO;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LoginControllerTest {

    private UserDAO userDAO;
    private LoginController loginController;
    private ModelMap modelMap;

    @Before
    public void setUp() throws Exception {
        userDAO = mock(UserDAO.class);
        loginController = new LoginController(userDAO);
        modelMap = mock(ModelMap.class);
    }

    @Test
    public void testLogin() throws Exception {
        String nextView = loginController.showLoginSite(modelMap);
        assertEquals("authentication/login",nextView);
    }

    @Test
    public void testShowRegisterSite() throws Exception {
        String nextView = loginController.showRegisterSite(modelMap);
        assertEquals("authentication/register",nextView);
    }

    @Test
    public void testTryRegisterUser() throws Exception {
        String username = "zbycho";
        String password = "zbycho";
        String confirm_password = "zbychu";

        String nextView = loginController.tryRegisterUser(username, password, confirm_password, modelMap);
        assertEquals("authentication/register",nextView);
        verify(modelMap).addAttribute("bad_registration_info", "Password and confirmed password not equals");
        
    }
}