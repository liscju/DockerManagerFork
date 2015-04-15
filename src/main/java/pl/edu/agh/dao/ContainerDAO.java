package pl.edu.agh.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pl.edu.agh.model.Container;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                new RowMapper<Container>() {

                    public Container mapRow(ResultSet resultSet, int i) throws SQLException {
                        int container_id = resultSet.getInt("CONTAINER_ID");
                        String container_image = resultSet.getString("CONTAINER_IMAGE");
                        return new Container(container_id, container_image);
                    }
                });
        return containers;
    }
}
