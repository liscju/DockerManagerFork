package pl.edu.agh.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pl.edu.agh.mapper.ContainerMapper;
import pl.edu.agh.model.Container;

import javax.sql.DataSource;

import java.util.List;

@Repository
public class ContainerDAO {

    @Autowired
    private DataSource dataSource;

    private JdbcTemplate getTemplate() {
        return new JdbcTemplate(dataSource);
    }

    public List<Container> getUserContainers() {
        List<Container> containers = getTemplate().query("SELECT CONTAINER_ID,CONTAINER_IMAGE FROM CONTAINERS",
                new ContainerMapper());
        return containers;
    }
    
    
    public Container getContainer(int container_id){
        Container container = getTemplate().queryForObject("SELECT CONTAINER_ID,CONTAINER_IMAGE FROM CONTAINERS WHERE CONTAINER_ID=?",
        		new Object[]{container_id},
                new ContainerMapper());
        return container;
    }
    
    public void deleteContainer(int container_id){
    	getTemplate().update("DELETE FROM CONTAINERS WHERE CONTAINER_ID=?",container_id);
    }
    
    public void insertContainer(int container_id,String image){
    	getTemplate().update("INSERT INTO CONTAINERS (CONTAINER_ID, CONTAINER_IMAGE)  VALUES (?,?);",container_id,image);
    }
    
    public void updateContainer(int container_id,String image){
    	getTemplate().update("UPDATE CONTAINERS SET CONTAINER_IMAGE=?  where CONTAINER_ID=?;",image,container_id);
    }

    
    
}
