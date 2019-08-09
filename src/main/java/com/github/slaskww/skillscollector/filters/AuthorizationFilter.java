package com.github.slaskww.skillscollector.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class AuthorizationFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        String requestedPath = req.getServletPath();

        switch (requestedPath) {

            case "/register":
            case "/login":
            case "/logout":
                chain.doFilter(req, res);
                break;
            case "/skills":
            case "/sources":
            case "/unknown-sources":
            case "/confirm":
            case "" :
                if (req.getSession().getAttribute("user") == null) {
                    res.sendRedirect("/login");
                } else {
                    chain.doFilter(req, res);
                }
                break;
            default:
                res.sendError(500);
        }
    }
}
