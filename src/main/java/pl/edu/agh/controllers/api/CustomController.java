package pl.edu.agh.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.edu.agh.dao.ContainerDAO;
import pl.edu.agh.dao.UserDAO;
import pl.edu.agh.model.User;

public class CustomController {

    @Autowired
    protected UserDAO userDAO;

    @Autowired
    protected ContainerDAO containerDAO;

    protected User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return userDAO.getUser(username);
    }
}
