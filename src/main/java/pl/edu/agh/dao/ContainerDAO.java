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
    
    
    
    
}
