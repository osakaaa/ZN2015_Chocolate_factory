package ZN_Chocolate.Controller;

import ZN_Chocolate.DAO.SessionDao;
import ZN_Chocolate.DAO.UserDao;
import ZN_Chocolate.Model.Session;
import ZN_Chocolate.Model.User;
import ZN_Chocolate.ZNExceptions;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by aplastunov on 28.10.15.
 */
public class BaseController extends HttpServlet {
    protected User getCurrentUser(Session session) throws ZNExceptions{
        User user = null;
        UserDao dao = new UserDao();

        user = dao.getUserByName(session.getuserID());
        if (user.getUsername()==null){throw new ZNExceptions("User Not found");}
        return user;
    }

    protected Session getCurrentSession(HttpServletRequest request) throws ZNExceptions{
        Cookie[] cookies = request.getCookies();
        Cookie userCookie = null;
        Cookie sessCookie = null;
        SessionDao dao = new SessionDao();
        Session session = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("ChocoUser")) {
                    userCookie = cookie;
                }
                if (cookie.getName().equals("ChocoSess")) {
                    sessCookie = cookie;
                }
                if (sessCookie != null && userCookie != null) {
                    break;
                }
            }
            if (sessCookie != null && userCookie != null) {
                session = dao.getSessionByUser(userCookie.getValue());
            }else{throw new ZNExceptions("Session Not found");}
        }
    return session;
    }

    protected boolean chechAuth(HttpServletRequest request){
        try {
            Session session = getCurrentSession(request);
            User user = getCurrentUser(session);
        } catch (Exception e){
            return false;
        }
        return true;
    }

}