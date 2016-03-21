package ZN_Chocolate.Controller;
/**
 * Created by aplastunov on 24.10.15.
 */

import java.io.*;

import ZN_Chocolate.CRYPTO.Secret;
import ZN_Chocolate.DAO.UserDao;
import ZN_Chocolate.Model.Session;
import ZN_Chocolate.Model.User;
import ZN_Chocolate.ZNExceptions;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SettingsController extends BaseController{

    public void doGet( HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {
        Session session = null;
        User user = null;
        if (!chechAuth(request)) {
            RequestDispatcher view = request.getRequestDispatcher("/index.html");
            view.forward(request, response);
        } else {
            try {
                session = getCurrentSession(request);
                user = getCurrentUser(session);
            } catch (ZNExceptions znExceptions) {
                response.sendRedirect("index.html");
            }
            if (user != null) {
                request.setAttribute("token", user.getToken());
                String seed = request.getParameter("seed");
                if (seed != null) {
                    Secret s = new Secret();
                    String token = "";
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    try {
                        token = s.generateUserkey(seed);
                        user.setToken(token);
                        UserDao dao = new UserDao();
                        dao.updateUser(user);
                        request.setAttribute("token", token);
                    } catch (Exception e) {
                        e.printStackTrace(pw);
                        request.setAttribute("Error", sw.toString());
                    }
                }
                request.getRequestDispatcher("/WEB-INF/settings.jsp").forward(request, response);
            }
        }
    }
    public void doPost( HttpServletRequest request,
                        HttpServletResponse response)
            throws IOException, ServletException {
        Session session = null;
        User user = null;
        try {
            session = getCurrentSession(request);
            user = getCurrentUser(session);
        } catch (ZNExceptions znExceptions) {
            response.sendRedirect("index.html");
        }
        request.getRequestDispatcher("/WEB-INF/settings.jsp").forward(request, response);
    }
}
