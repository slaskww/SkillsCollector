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
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/skills")
public class UserSkillsServlet extends HttpServlet {

    private UserDao userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {

        userDao = new UserDao((SessionFactory)config.getServletContext().getAttribute("session_factory"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        List<String> skills = userDao.getAllSkillsForUser(user.getUsername(), user.getPassword());

        Map<String, Long> groupedSkills = skills.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        groupedSkills = groupedSkills.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

       // groupedSkills.entrySet().stream().forEach(stringLongEntry -> System.out.println(stringLongEntry.getKey() + ": " + stringLongEntry.getValue()));

        req.setAttribute("skillMap", groupedSkills);
        req.getRequestDispatcher("WEB-INF/views/user-skills.jsp").forward(req, resp);

    }

}
