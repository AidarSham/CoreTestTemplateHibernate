package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.dropUsersTable();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Michael", "Jackson", (byte) 51);
        userDaoJDBC.removeUserById(2);
        userDaoJDBC.getAllUsers();
        userDaoJDBC.cleanUsersTable();
    }
}
