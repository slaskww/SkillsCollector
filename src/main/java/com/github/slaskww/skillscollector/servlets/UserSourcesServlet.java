package com.github.slaskww.skillscollector.servlets;

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
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@WebServlet("/sources")
public class UserSourcesServlet extends HttpServlet {

    private UserDao userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {

        userDao = new UserDao((SessionFactory)config.getServletContext().getAttribute("session_factory"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");

        List<Source> sources = userDao.getAllSourcesForUser(user.getUsername(), user.getPassword());


       // sources.stream().forEach(source -> source.getSkills().stream().map(skill -> skill.getName()).forEach(s -> System.out.println(s)));
      /*  sources.stream().forEach(source -> System.out.println(source.getName() + ", " + source.getDescription() + " {" + source.getSkills()
                .stream().map(skill -> skill.getName()).reduce((s, s2) -> s.concat(", ").concat(s2)).orElse("") + "}"));*/

        Map<Source, String> sourcesMap = sources.stream().collect(Collectors.toMap(source -> source, source -> source.getSkills().stream().map(skill -> skill.getName()).reduce((s, s2) -> s.concat(", ").concat(s2)).orElse("")));

        sourcesMap.entrySet().stream().forEach(entry -> System.out.println(entry.getKey().getName() + ", " + entry.getKey().getDescription() + ", " + entry.getValue()));

        req.setAttribute("sourcesMap", sourcesMap);
        req.getRequestDispatcher("WEB-INF/views/user-sources.jsp").forward(req, resp);

    }
}
