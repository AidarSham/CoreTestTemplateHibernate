package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    Util util = new Util();
    Connection connection = util.getConnection();

    public void createUsersTable() {
        try (Statement statement =  connection.createStatement()){
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users " +
                    "(id BIGINT NOT NULL AUTO_INCREMENT," +
                    " name CHAR(30) NOT NULL," +
                    " lastName CHAR(30) NOT NULL," +
                    " age CHAR(30) NOT NULL," +
                    " primary key (id));");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS Users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Statement statement = connection.createStatement()) {

            String sql = "SELECT * FROM users";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);

            statement.executeUpdate("INSERT INTO Users (name, lastName, age) VALUES ('Jordan', 'Belfort', 59)");
            statement.executeUpdate("INSERT INTO Users (name, lastName, age) VALUES ('Warren', 'Buffet', 91)");
            statement.executeUpdate("INSERT INTO Users (name, lastName, age) VALUES ('Bill', 'Gates', 66)");
            statement.executeUpdate("INSERT INTO Users (name, lastName, age) VALUES ('Winston', 'Churchill', 91)");

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getInt("age"));
                System.out.println("User с именем – " + user.getName() + " добавлен в базу данных");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
//        Statement statement =  connection.createStatement();
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Users WHERE id=?;");){
            preparedStatement.setInt(1, (int) id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        statement.executeUpdate("DELETE FROM Users WHERE id=2");
    }

    public List<User> getAllUsers() {

        Statement statement = null;
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String sql = "SELECT * FROM users";

            ResultSet resultSet = statement.executeQuery(sql);
            List<User> result = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getInt("age"));
//                System.out.println("User с именем – " + user.getName() + " добавлен в базу данных");
                System.out.println(user);

                result.add(user);
            }
            connection.commit();
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return null;
    }

    public void cleanUsersTable() {
        try {
            Statement statement =  connection.createStatement();
            statement.executeUpdate("TRUNCATE TABLE Users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}