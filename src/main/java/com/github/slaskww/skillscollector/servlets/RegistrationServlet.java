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
import java.util.Map;
import java.util.TreeMap;

@WebServlet(urlPatterns = "/register")
public class RegistrationServlet extends HttpServlet {

    private  UserDao userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
       SessionFactory sessionFactory = (SessionFactory) config.getServletContext().getAttribute("session_factory");
        userDao = new UserDao(sessionFactory);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, String[]> params  = req.getParameterMap();
        Map<String, String> errors = new TreeMap<>();
        User newUser;

        validateParams(params,  errors);

        if (errors.isEmpty()){
            newUser = createUser(params);
            userDao.save(newUser);
            resp.sendRedirect("/login");
        } else{
            req.setAttribute("errorMap", errors);
          req.getRequestDispatcher("WEB-INF/views/register.jsp").forward(req, resp);
        }

    }

    private User createUser(Map<String, String[]> params){
        User user = new User();
        user.setFirstName(params.get("firstName")[0].toUpperCase());
        user.setLastName(params.get("lastName")[0].toUpperCase());
        user.setUsername(params.get("username")[0]);
        user.setPassword(params.get("password")[0]);

        return user;
    }

    private void validateParams( Map<String, String[]> params,  Map<String, String>  errors){

        if (!nameValidation(params.get("firstName"))){
            errors.put("firstName", "Pole 'Imię' jest za krótkie. Powinno składać się z co najmniej 2 znaków w tym wyłącznie z liter.");
        }

        if (!nameValidation(params.get("lastName"))){
            errors.put("lastName", "Pole 'Nazwisko' jest za krótkie. Powinno składać się z co najmniej 2 znaków w tym wyłącznie z liter.");
        }

        if (!usernameValidation(params.get("username"))){
            errors.put("username", "Pole 'Nazwa użytkownika' jest za krótkie. Powinno składać się z co najmniej 6 znaków.");
        }

        if (!passwordValidation(params.get("password"))){
            errors.put("password", "Pole 'Hasło' powinno posiadać co najmniej 8 znaków.");
        }

        if (!confirmedPasswordValidation(params.get("password"), params.get("confirmedPassword"))){
            errors.put("confirmedPassword", "Podane hasła różnią się. Pola powinny mieć taką samą wartość.");
        }

    }

    private boolean  nameValidation(String[] nameToCheck){
        String name = nameToCheck[0];

        if (name.equals("")) {
            return false;
        }

        if (name.length() < 2){
            return false;
        }

        name = name.toLowerCase();

        if (name.charAt(0) < 'a' || name.charAt(0) > 'z'){
            return false;
        }


        byte[] stringInBytes = name.getBytes();

        for (int j = 0; j < stringInBytes.length; j++) {
            if (stringInBytes[j] < 97 || stringInBytes[j] > 122) {
                return false;
            }
        }
        return true;
    }


    private boolean usernameValidation(String[] usernameToCheck){
        String username = usernameToCheck[0];

        if (username.equals("")) {
            return false;
        }

        if (username.length() < 6){
            return false;
        }

        if (!userDao.isUsernameAvailable(username)){
            return false;
        }

        return true;
    }

    private boolean passwordValidation(String[] passwordToCheck){
        String password = passwordToCheck[0];

        if (password.equals("")) {
            return false;
        }

        if (password.length() < 8){
            return false;
        }

        return true;

    }

    private boolean confirmedPasswordValidation(String[] passwordToCheck , String[] confirmedPasswordToCheck){
        String password = passwordToCheck[0];
        String confirmedPassword = confirmedPasswordToCheck[0];

        if (confirmedPassword.equals("")) {
            return false;
        }

        if (!password.equals(confirmedPassword)) {
            return false;
        }
        return true;
    }
}
