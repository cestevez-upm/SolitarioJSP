package upm.miw.solitaire.controllers.beans;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ump.miw.solitaire.models.entities.Gender;
import ump.miw.solitaire.models.entities.User;

/**
 * Servlet implementation class FrontController
 */
@WebServlet({"/", "/*/*"})
public class FrontController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String view = "error";

        if (action == null || action.equals("home"))
            view = "home";

        else if (action.equals("login") | action.equals("register")) {
            User user = new User();
            LoginBean loginB = new LoginBean();
            loginB.setUser(user);
            request.setAttribute("loginB", loginB);
            if (action.equals("login"))
                view = "login";
            else if (action.equals("register"))
                view = "register";
        }
        getServletContext().getRequestDispatcher("/" + view + ".jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String view = "error";

        if (action.equals("login") | action.equals("register")) {
            User user = new User();
            LoginBean loginB = new LoginBean();
            loginB.setUser(user);
            request.setAttribute("loginB", loginB);
            user.setNick(request.getParameter("nick"));
            loginB.setPassword(request.getParameter("password2"));
            if (action.equals("login"))
                view = loginB.login(request.getSession(true));
            else if (action.equals("register")) {
                user.setPassword(request.getParameter("password"));
                user.setFirstName(request.getParameter("firstName"));
                user.setLastName(request.getParameter("lastName"));
                user.setCountry(request.getParameter("country"));
                user.setGender(Gender.valueOf(request.getParameter("gender")));
                try {
                    user.setAge(Integer.parseInt(request.getParameter("age")));
                } catch (NumberFormatException nfe) {
                    loginB.addFieldError("Edad incorrecta");
                }
                view = loginB.register();
            }
        }
        getServletContext().getRequestDispatcher("/" + view + ".jsp").forward(request, response);
    }
}
