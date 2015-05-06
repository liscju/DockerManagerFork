package pl.edu.agh.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.edu.agh.model.DockerServer;
import javax.print.Doc;
import javax.sql.DataSource;
import java.util.List;

@Repository
public class DockerServerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public List<DockerServer> getDockerServers() {
        Session currentSession = sessionFactory.openSession();
        List<DockerServer> docker_servers = currentSession.createCriteria(DockerServer.class).list();
        currentSession.close();

        return docker_servers;
    }
    
    
    public DockerServer getDockerServer(int id){
        Session currentSession = sessionFactory.openSession();
        DockerServer dockerServer = (DockerServer) currentSession.get(DockerServer.class, id);
        currentSession.close();

        return dockerServer;
    }
    
    public void deleteServer(int id){
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();

        DockerServer serverToDelete = getDockerServer(id);
        currentSession.delete(serverToDelete);

        transaction.commit();
        currentSession.close();
    }
    
    public void insertServer(String name,String address){
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();

        DockerServer serverToInsert = new DockerServer(0,name,address);
        currentSession.save(serverToInsert);

        transaction.commit();
        currentSession.close();
    }
    
}
