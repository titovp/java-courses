package ru.socialNetwork.controllers;

import ru.socialNetwork.database.Database;
import ru.socialNetwork.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/reg")
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = new User(login, password);
        try {
            Database database = new Database();
            database.saveUserToDatabase(login, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        request.setAttribute("user", user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("profile.jsp");
        requestDispatcher.forward(request, response);



    }


}
