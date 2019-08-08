package com.github.slaskww.skillscollector.servlets;

import com.github.slaskww.skillscollector.dao.UserDao;
import com.github.slaskww.skillscollector.dto.User;
import org.hibernate.SessionFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserDao userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userDao = new UserDao((SessionFactory) config.getServletContext().getAttribute("session_factory"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> params = req.getParameterMap();
        String paramsError;

        if (areParamsCorrect(params)) {
            User user = getUser(params);
            req.getSession().invalidate(); //uniewaznienie bieżącej sesji
            req.getSession(true).setAttribute("user", user); //utworzenie nowej sesji z atrybutem 'user'
            resp.sendRedirect("/skills");
            //req.getRequestDispatcher("WEB-INF/foo.jsp").forward(req, resp);


        } else {
            paramsError = "Błędna nazwa użytkownika lub hasło";
            req.setAttribute("errorMsg", paramsError);
            req.getRequestDispatcher("WEB-INF/views/login.jsp").forward(req, resp);
        }
    }

    private boolean areParamsCorrect(Map<String, String[]> params) {
        String username = params.get("username")[0];
        String password = params.get("password")[0];
        List<User> matchingUsers = userDao.getAllByUsernameAndPassword(username, password);

        if (matchingUsers.isEmpty()) {
            return false;
        }
        return true;
    }

    private User getUser(Map<String, String[]> params) {
        String username = params.get("username")[0];
        String password = params.get("password")[0];
        List<User> matchingUsers = userDao.getAllByUsernameAndPassword(username, password);
        return matchingUsers.get(0);
    }
}
