package pl.edu.agh.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pl.edu.agh.mapper.DockerServerMapper;
import pl.edu.agh.model.DockerServer;

import javax.sql.DataSource;

import java.util.List;

@Repository
public class DockerServerDAO {

    @Autowired
    private DataSource dataSource;

    private JdbcTemplate getTemplate() {
        return new JdbcTemplate(dataSource);
    }

    public List<DockerServer> getDockerServers() {
        List<DockerServer> servers = getTemplate().query("SELECT SERVER_ID,SERVER_NAME,SERVER_ADDRESS FROM DOCKER_SERVERS",
                new DockerServerMapper());
        return servers;
    }
    
    
    public DockerServer getDockerServer(int id){
        DockerServer server = getTemplate().queryForObject("SELECT SERVER_ID,SERVER_NAME,SERVER_ADDRESS from DOCKER_SERVERS WHERE SERVER_ID=?",
        		new Object[]{id},
                new DockerServerMapper());
        return server;
    }
    
    public void deleteServer(int id){
    	getTemplate().update("DELETE FROM DOCKER_SERVERS WHERE SERVER_ID=?",id);
    }
    
    public void insertServer(String name,String address){
    	getTemplate().update("INSERT INTO DOCKER_SERVERS (SERVER_NAME,SERVER_ADDRESS)  VALUES (?,?);",name,address);
    }
    
    public void updateServer(int id ,String name,String address){
    	getTemplate().update("UPDATE DOCKER_SERVERS SET SERVER_NAME=?,SERVER_ADDRESS=?  where SERVER_ID=?;",name,address,id);
    }

    
    
}
