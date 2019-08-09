package com.github.slaskww.skillscollector.servlets;

import com.github.slaskww.skillscollector.dao.SourceDao;
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
import java.util.stream.Collectors;

@WebServlet("/unknown-sources")
public class UnknownSourcesServlet extends HttpServlet {


    private SourceDao sourceDao;

    @Override
    public void init(ServletConfig config) throws ServletException {

        sourceDao = new SourceDao((SessionFactory) config.getServletContext().getAttribute("session_factory"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");

        List<Source> unknownSources = sourceDao.getAllUnknownSources(user);

        Map<Source,String> unknownSourcesMap = unknownSources
                .stream()
                .collect(Collectors
                        .toMap(source -> source, source -> source.getSkills()
                                .stream()
                                .map(skill -> skill.getName())
                                .reduce((s, s2) -> s.concat(", ").concat(s2)).orElse("")));


        req.setAttribute("unknownSourcesMap", unknownSourcesMap);
        req.getRequestDispatcher("WEB-INF/views/user-unknown-sources.jsp").forward(req, resp);
    }
}
