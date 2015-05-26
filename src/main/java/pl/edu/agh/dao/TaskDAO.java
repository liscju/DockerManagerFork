package pl.edu.agh.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import pl.edu.agh.configuration.Configurator;
import pl.edu.agh.docker.DockerConnector;
import pl.edu.agh.model.Task;

import java.io.Serializable;

@Repository
public class TaskDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Task saveTask(Task task) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Integer id = (Integer) session.save(task);
        transaction.commit();
        session.close();
        return getTask(id);
    }

    public Task getTask(Integer id) {
        Session session = sessionFactory.openSession();
        Task task = (Task) session.get(Task.class, id);
        session.close();
        return task;
    }

    public void updateTask(Task task) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(task);
        transaction.commit();
        session.close();
    }
}
