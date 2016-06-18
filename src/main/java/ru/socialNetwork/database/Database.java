package ru.socialNetwork.database;

import ru.socialNetwork.models.Post;
import ru.socialNetwork.models.User;

import java.sql.*;

public class Database {
    private Connection connection;

    public Database() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");


        String jdbcString = "jdbc:mysql://localhost/socialnetwork?characterEncoding=utf-8";
        connection = DriverManager.getConnection(jdbcString, "root", "");
    }

    public User saveUserToDatabase(User user) throws SQLException {
        String query = "INSERT INTO users (login, password) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getPassword());

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }

        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
        return user;
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

    public void savePostForUser(Post post, User user) {
        String query = "INSERT INTO post (name, post, id_user) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            preparedStatement.setString(1, post.getName());
            preparedStatement.setString(2, post.getPost());
            preparedStatement.setInt(3, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
