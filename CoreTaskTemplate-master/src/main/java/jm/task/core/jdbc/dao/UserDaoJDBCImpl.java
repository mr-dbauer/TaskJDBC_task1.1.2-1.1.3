package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection;

    public UserDaoJDBCImpl(Connection connection) {
        this.connection = connection;
    }

    public void createUsersTable() throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("create table user_client (id bigint auto_increment, name varchar(256), lastName varchar(256), age TINYINT,  primary key (id))");
        stmt.execute();
        stmt.close();
    }

    public void dropUsersTable() throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("DROP TABLE user_client");
        stmt.execute();
        stmt.close();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(" insert into user_client (name, lastName, age) values (?, ?, ?)");
        stmt.setString(1, name);
        stmt.setString(2, lastName);
        stmt.setByte(3, age);
        stmt.execute();
        stmt.close();
    }

    //sql инъекция, исправленно
    public void removeUserById(long id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("delete from user_client where id = ?");
        stmt.setLong(1, id);
        stmt.execute();
        stmt.close();
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> list = new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery("select * from user_client");
        while (resultSet.next()) {
            String name = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            byte age = resultSet.getByte(4);
            list.add(new User(name, lastName, age));
        }
        resultSet.close();
        stmt.close();
        return list;
    }

    public void cleanUsersTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("delete from user_client");
        ResultSet result = stmt.getResultSet();
        result.next();
        result.close();
        stmt.close();
    }
}
