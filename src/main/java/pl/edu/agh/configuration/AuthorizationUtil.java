package pl.edu.agh.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.agh.dao.UserDAO;

import java.util.Collections;

@Service
public class AuthorizationUtil implements UserDetailsService {

    @Autowired
    UserDAO userDAO;

    public UserDetails loadUserByUsername(final String name)
            throws UsernameNotFoundException {
        pl.edu.agh.model.User user = userDAO.getUser(name);
        return buildUserForAuthentication(user);
    }

    private User buildUserForAuthentication(pl.edu.agh.model.User user) {
        return new User(user.getName(), user.getPassword(),
                user.getEnabled(), true, true, true, Collections.EMPTY_LIST);
    }
}
