package ru.socialNetwork.database;

import ru.socialNetwork.models.User;

import java.sql.*;

public class Database {
    private Connection connection;

    public Database() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");


        String jdbcString = "jdbc:mysql://localhost/socialnetwork";
        connection = DriverManager.getConnection(jdbcString, "root", "");
    }

    public void saveUserToDatabase(String login, String password) throws SQLException {
        Statement preparedStatement = connection.createStatement();
        String query = "INSERT INTO users (login, password) VALUES ('" + login + " ', '" + password + "')";
        preparedStatement.executeUpdate(query);
    }

    public User getUserByLogin(String login) {
        String query = "SELECT * FROM users WHERE login = ?";
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, login);
            try (ResultSet result = preparedStatement.executeQuery();) {

                while (result.next()) {
                    user = new User();
                    user.setId(result.getInt(1));
                    user.setLogin(result.getString(2));
                    user.setPassword(result.getString(3));
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
