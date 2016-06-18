package ru.socialNetwork.controllers;

import ru.socialNetwork.database.Database;
import ru.socialNetwork.models.Post;
import ru.socialNetwork.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet (urlPatterns = "/add")
public class AddServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String postName = request.getParameter("postname");
        String  postText = request.getParameter("text");
        User user = (User) request.getSession().getAttribute("user");
        Post post = new Post();
        post.setName(postName);
        post.setPost(postText);
        try {
            Database database = new Database();
            database.savePostForUser(post, user);
        } catch (ClassNotFoundException|SQLException  e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }
}
