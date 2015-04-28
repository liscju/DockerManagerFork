package pl.edu.agh.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pl.edu.agh.model.Container;

public class ContainerMapper implements RowMapper<Container>{

	@Override
    public Container mapRow(ResultSet resultSet, int i) throws SQLException {
        int container_id = resultSet.getInt("CONTAINER_ID");
        String container_image = resultSet.getString("CONTAINER_IMAGE");
        return new Container(container_id, container_image);
    }
	

}
