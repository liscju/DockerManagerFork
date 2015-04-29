package pl.edu.agh.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pl.edu.agh.mapper.UserMapper;
import pl.edu.agh.model.User;

import javax.sql.DataSource;

import java.util.List;

@Repository
public class UserDAO  {

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
    
    public void deleteUser(String name){
    	getTemplate().update("DELETE FROM USERS WHERE NAME=?",name);
    }
    
    public void insertUser(String name,String password,String role,int enabled){
    	getTemplate().update("INSERT INTO USERS (NAME,PASSWORD,ROLE,ENABLED)  VALUES (?,?,?,?);",name,password,role,enabled);
    }
    
    public void updateUser(String name,String password,String role,int enabled){
    	getTemplate().update("UPDATE USERS SET password=?,role=?,enabled=?  where name=?;",password,role,enabled,name);
    }
    
    public void updateUserPassword(String name,String password){
    	getTemplate().update("UPDATE USERS SET password=? where name=?;",password,name);
    }
    
    public void updateUserRole(String name,String role){
    	getTemplate().update("UPDATE USERS SET role=? where name=?;",role,name);
    }
    public void updateUserEnabled(String name,int enabled){
    	getTemplate().update("UPDATE USERS SET enabled=? where name=?;",enabled,name);
    }


    
    
    
}
