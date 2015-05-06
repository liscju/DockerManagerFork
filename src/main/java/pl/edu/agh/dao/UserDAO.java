package pl.edu.agh.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pl.edu.agh.model.User;

import javax.sql.DataSource;

import java.util.LinkedList;
import java.util.List;

@Repository
public class UserDAO  {

    @Autowired
    private SessionFactory sessionFactory;

    public List<User> getUsers() {
        Session currentSession = sessionFactory.openSession();
        Query query = currentSession.createQuery("from User");
        List<User> all_users = query.list();
        currentSession.close();
        return all_users;
    }
    
    public User getUser(String name){
        Session session = sessionFactory.openSession();
        List<User> users = new LinkedList<User>();

        users = session
                .createQuery("from User where name=?")
                .setParameter(0, name)
                .list();

        session.close();
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }
    
    public void insertUser(String name,String password,String role,boolean enabled){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(new User(name, password,role, enabled));
        tx.commit();
        session.close();
    }

    public boolean existUser(String username) {
        return getUser(username) != null;
    }
}
