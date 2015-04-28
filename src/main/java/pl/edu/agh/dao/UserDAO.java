package pl.edu.agh.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.edu.agh.mapper.UserMapper;
import pl.edu.agh.model.User;
import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDAO {

    @Autowired
    private DataSource dataSource;

    private JdbcTemplate getTemplate() {
        return new JdbcTemplate(dataSource);
    }

    public List<User> getUsers() {
        List<User> users = getTemplate().query("SELECT NAME,PASSWORD,ROLE,ENABLED FROM USERS",
                new UserMapper());
        return users;
    }
    
    public User getUser(String name){
        User user = getTemplate().queryForObject("SELECT NAME,PASSWORD,ROLE,ENABLED FROM USERS WHERE USERNAME=?",
        		new Object[]{name},
                new UserMapper());
        return user;
    	
    }
}
