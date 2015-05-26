package pl.edu.agh.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.agh.model.Task;
import pl.edu.agh.model.TaskMessage;

import java.util.List;

@Repository
public class TaskMessageDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void saveTaskMessage(TaskMessage taskMessage) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(taskMessage);
        transaction.commit();
        session.close();
    }

    public List<TaskMessage> getMessageForTask(Task task) {
        Session session = sessionFactory.openSession();
        Criteria msgForTaskCriteria = session.createCriteria(TaskMessage.class)
                .createAlias("task", "t")
                .add(Restrictions.eq("t.id", task.getId()));
        List<TaskMessage> listOfMessages = msgForTaskCriteria.list();
        session.close();
        return listOfMessages;
    }
}
