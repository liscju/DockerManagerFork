package pl.edu.agh.dao;

import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import pl.edu.agh.configuration.HibernateUtil;
import pl.edu.agh.model.Task;
import pl.edu.agh.model.TaskStatus;

public class TaskDAOTest {

    @Test
    public void testSaveTask() throws Exception {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        TaskDAO taskDAO = new TaskDAO();
        taskDAO.setSessionFactory(sessionFactory);
        Task ohoho = taskDAO.saveTask(new Task("Ohoho", TaskStatus.SUCCESS_END));
        Assert.assertEquals("Ohoho", ohoho.getProperties());
        Assert.assertEquals(TaskStatus.SUCCESS_END, ohoho.getStatus());
    }
}