package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    //private final UserDao userDao = new UserDaoHibernateImpl(Util.getSessionFactory());
    private final UserDao userDao = new UserDaoJDBCImpl(Util.getMysqlConnection());

    public void createUsersTable() throws SQLException {
        try {
            userDao.createUsersTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            userDao.dropUsersTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            userDao.saveUser(name, lastName, age);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            userDao.removeUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        try {
            return userDao.getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void cleanUsersTable() {
        try {
            userDao.cleanUsersTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
