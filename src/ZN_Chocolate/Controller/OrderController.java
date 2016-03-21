package ZN_Chocolate.Controller;
/**
 * Created by aplastunov on 24.10.15.
 */

import ZN_Chocolate.Util.JspChecker;
import ZN_Chocolate.Model.Session;
import ZN_Chocolate.Model.User;
import ZN_Chocolate.ZNExceptions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderController extends BaseController{

    public void doGet( HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {

        if (!chechAuth(request)){
            RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/index.jsp");
            view.forward(request, response);
        }else {
            RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/order.jsp");
            view.forward(request, response);
        }
    }
    public void doPost( HttpServletRequest request,
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
            String BaseUploadPath = "upload/" + session.getsessionID() + "/";


            String order = request. getParameter("order");
            JspChecker checker = new JspChecker();
            String orderName = request.getParameter("orderName").replaceAll("[^A-Za-z0-9.]", "");
            ;
            if (checker.checkJSP(order)) {
                orderName = "error.txt";
                order = "The order is in UNACCAPTABLE CONDITION!!!";
            }
            File order_file;
            System.out.println(this.getServletContext().getServerInfo());
            System.out.println(this.getServletContext().getResource("/"));
            String result = "";
            try {
                order_file = new File(this.getServletContext().getRealPath("/") + BaseUploadPath + orderName);
                if (!order_file.getCanonicalPath().startsWith(this.getServletContext().getRealPath("/") + BaseUploadPath)) {
                    result = "Looks like there is some serious problems with the order's name";
                    request.setAttribute("Error", "true");
                } else {
                    Path path = Paths.get(this.getServletContext().getRealPath("/") + BaseUploadPath);
                    //if (!Files.isDirectory(path)) {
                    //    Files.createDirectories(path);
                    //}
                    new File(path.toString()).mkdirs();
                    if (!Files.exists(path)) {
                        Files.createFile(path);
                    }
                    FileWriter writer = new FileWriter(order_file);
                    writer.write(order);
                    writer.close();
                    result = BaseUploadPath + orderName;
                }
            } catch (Exception e) {
                // if any error occurs
                e.printStackTrace();
                result = "We won't accept this order";
                request.setAttribute("Error", "true");
            }
            request.setAttribute("result", result);
            RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/order.jsp");
            view.forward(request, response);
        }
    }

}
