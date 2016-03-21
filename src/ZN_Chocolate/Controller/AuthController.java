package ZN_Chocolate.Controller;

/**
 * Created by aplastunov on 24.10.15.
 */
import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ZN_Chocolate.DAO.SessionDao;
import ZN_Chocolate.DAO.UserDao;
import ZN_Chocolate.Model.Session;
import ZN_Chocolate.Model.User;
import ZN_Chocolate.Util.VerifyRecaptcha;
public class AuthController extends HttpServlet{
    private static String LOGIN = "/login.jsp";
    private static String SIGN_UP = "/sign_up.jsp";
    private UserDao userdao;
    private SessionDao sessiondao;

    public AuthController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = "";
        try {
            action = request.getParameter("action");
        } catch (Exception e){
            action = "lol";
        }
        if (action == null) {action="lol";}
        if (action.equalsIgnoreCase("signup")) {
            RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/signup.jsp");
            view.forward(request, response);

        }
        else if (action.equalsIgnoreCase("logout")){
            Cookie[] cookies = request.getCookies();
            Cookie userCookie = null;
            Cookie sessCookie = null;
            sessiondao = new SessionDao();
            try {
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
                        userCookie.setMaxAge(0);
                        sessCookie.setMaxAge(0);
                        sessiondao.deleteSession(userCookie.getValue());
                        response.addCookie(userCookie);
                        response.addCookie(sessCookie);
                    }
                    RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/index.jsp");
                    view.forward(request, response);

                }
            }catch(Exception e){
                RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/index.jsp");
                view.forward(request, response);
            }
        } else {
            RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/index.jsp");
            view.forward(request, response);
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        userdao = new UserDao();
        String forward = "";

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("login")) {
            if (action == null || username == null || password == null){
                request.setAttribute("Error", "Alert! Something went wrong");
                response.sendRedirect("index.html");
            }
            User dbUser = userdao.getUserByName(username);
            String password1 = "";
            boolean authError = false;
            if (dbUser == null){
                authError=true;
            }else {
                password1 = dbUser.getPassword();
                if (password1 == null) {
                    authError = true;
                }
            }
            String password2 = User.getHashString(password);
            if (!authError && password1.equals(password2)) {
                Session session = new Session();
                session.setuserID(username);
                session.setsessionID(dbUser.getGuid());
                sessiondao = new SessionDao();
                sessiondao.deleteSession(request.getParameter("username"));
                sessiondao.addSession(session);
                Cookie usercookie = new Cookie("ChocoUser",username);
                usercookie.setMaxAge(60 * 60);
                response.addCookie(usercookie);
                Cookie sesscookie = new Cookie("ChocoSess",dbUser.getGuid());
                sesscookie.setMaxAge(60 * 60);
                response.addCookie(sesscookie);
                response.sendRedirect("overview");

            }else{
                request.setAttribute("Error", "We cannot find you, sorry");
                RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/index.jsp");
                view.forward(request, response);
            }


        }

        if (action.equalsIgnoreCase("signup")) {
            String gRecaptchaResponse = request
                    .getParameter("g-recaptcha-response");
            boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
            if (action == null || username == null || password == null || !verify){
                request.setAttribute("Error", "Alert! Something went wrong");
                RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/signup.jsp");
                view.forward(request, response);
            }else {
                if (request.getParameter("username").matches("(\\d|\\w)+")) {
                    User dbUser = new User(request.getParameter("username"), request.getParameter("password"));
                    if (userdao.getUserByName(request.getParameter("username")).getUsername() == null) {
                        userdao.addUser(dbUser);
                        Session session = new Session();
                        session.setuserID(username);
                        session.setsessionID(dbUser.getGuid());
                        sessiondao = new SessionDao();
                        sessiondao.addSession(session);
                        Cookie usercookie = new Cookie("ChocoUser", username);
                        usercookie.setMaxAge(60 * 60);
                        response.addCookie(usercookie);
                        Cookie sesscookie = new Cookie("ChocoSess", dbUser.getGuid());
                        sesscookie.setMaxAge(60 * 60);
                        response.addCookie(sesscookie);
                        response.setStatus(0);
                        response.sendRedirect("overview");
                    } else {
                        request.setAttribute("Error", "You already registered here. Login please");
                        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/signup.jsp");
                        view.forward(request, response);
                    }
                } else {
                    request.setAttribute("Error", "Your name doesn't look like a real one. sorry");
                    RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/signup.jsp");
                    view.forward(request, response);
                }
            }

        }


    }
}
