package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoHibernate.dropUsersTable();
        userDaoHibernate.createUsersTable();
        userDaoJDBC.saveUser("Michael", "Jackson", (byte) 51);
        userDaoJDBC.saveUser("Warren", "Buffet", (byte) 91);
        userDaoJDBC.saveUser("Bill", "Gates", (byte) 66);
        userDaoJDBC.saveUser("Winston", "Churchill", (byte) 91);
        userDaoJDBC.removeUserById(2);
        userDaoJDBC.getAllUsers();
        userDaoJDBC.cleanUsersTable();
    }
}
