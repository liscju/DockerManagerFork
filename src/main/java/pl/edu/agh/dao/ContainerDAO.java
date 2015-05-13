package pl.edu.agh.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pl.edu.agh.model.Container;
import pl.edu.agh.model.User;

import javax.sql.DataSource;

import java.util.List;

@Repository
public class ContainerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Container> getUserContainers(User user) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("SELECT c FROM Container c WHERE c.owner.name =:userName");
        query.setParameter("userName",user.getName());
        List<Container> containers = query.list();
        session.close();
        return containers;
    }

    public Container getContainer(int container_id){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("SELECT c FROM Container c WHERE c.id =:containerId");
        query.setParameter("containerId", container_id);
        List<Container> containers = query.list();
        session.close();

        if (containers.size() == 0)
            return null;
        else
            return containers.get(0);
    }
    
    public void insertContainer(String image,User owner, String onServer){
    	Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Container containerToInsert = new Container(0,image,owner,onServer);
        session.save(containerToInsert);

        transaction.commit();
        session.close();
    }

    public void deleteContainer(int id) {
        Container containerToDelete = getContainer(id);
        if (containerToDelete != null) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.delete(containerToDelete);

            transaction.commit();
            session.close();
        }
    }
}












