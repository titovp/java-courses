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

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String login = request.getParameter("login");
        RequestDispatcher requestDispatcher = null;
        String password = request.getParameter("password");
        try {
            Database database = new Database();
            User user = database.getUserByLogin(login);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    request.getSession().setAttribute("user", user);
                    requestDispatcher = request.getRequestDispatcher("profile.jsp");

                    requestDispatcher.forward(request, response);
                }
                else {
                    requestDispatcher = request.getRequestDispatcher("index.jsp");
                    requestDispatcher.forward(request, response);
                }
            } else {
                requestDispatcher = request.getRequestDispatcher("index.jsp");
                requestDispatcher.forward(request, response);
            }

        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }

    }


}
