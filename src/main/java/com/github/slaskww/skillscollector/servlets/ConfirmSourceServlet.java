package com.github.slaskww.skillscollector.servlets;

import com.github.slaskww.skillscollector.dao.SourceDao;
import com.github.slaskww.skillscollector.dao.UserDao;
import com.github.slaskww.skillscollector.dto.Source;
import com.github.slaskww.skillscollector.dto.User;
import org.hibernate.SessionFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@WebServlet("/confirm")
public class ConfirmSourceServlet extends HttpServlet {


    private SourceDao sourceDao;
    private UserDao userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {

        sourceDao = new SourceDao((SessionFactory) config.getServletContext().getAttribute("session_factory"));
        userDao = new UserDao((SessionFactory) config.getServletContext().getAttribute("session_factory"));

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        Source sourceToConfirm = sourceDao.get(Long.parseLong(id));
        req.setAttribute("sourceToConfirm", sourceToConfirm);
        req.getRequestDispatcher("WEB-INF/views/confirm-source.jsp").forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String sourceId = req.getParameter("id");
        Source newAddedSource = sourceDao.get(Long.parseLong(sourceId));
        User user = (User) req.getSession().getAttribute("user");

        System.out.println("nowy source: " + newAddedSource.getName() + "dla " + user.getUsername());
    //    System.out.println("source w userze: " + user.getSources().size());
    //    System.out.println("userzy w source: " + newAddedSource.getUsers().size());

/*        user.getSources().add(newAddedSource);
        newAddedSource.getUsers().add(user);
        userDao.update(user);
        sourceDao.update(newAddedSource);*/
    userDao.addNewSource(user, newAddedSource);

        resp.sendRedirect("/sources");
    }
}
