package pl.edu.agh.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pl.edu.agh.model.User;

public class UserMapper implements RowMapper<User>{

	@Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        String name = resultSet.getString("NAME");
        String password = resultSet.getString("PASSWORD");
        String role = resultSet.getString("ROLE");
       	int enabled = resultSet.getInt("ENABLED");
        return new User(name, password, role, enabled);
    }

}
