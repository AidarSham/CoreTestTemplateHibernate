package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Util util = new Util();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try {
            Session session = util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS Users " +
                    "(id BIGINT NOT NULL AUTO_INCREMENT," +
                    " name CHAR(30) NOT NULL," +
                    " lastName CHAR(30) NOT NULL," +
                    " age CHAR(30) NOT NULL," +
                    " primary key (id));";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            Session session = util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS Users";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
