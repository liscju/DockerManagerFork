package pl.edu.agh.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pl.edu.agh.model.DockerServer;

public class DockerServerMapper implements RowMapper<DockerServer>{

	@Override
    public DockerServer mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("SERVER_ID");
        String name = resultSet.getString("SERVER_NAME");
        String address = resultSet.getString("SERVER_ADDRESS");
        return new DockerServer(id, name,address);
    }
	

}
