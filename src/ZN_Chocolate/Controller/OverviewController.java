package ZN_Chocolate.Controller;
/**
 * Created by aplastunov on 24.10.15.
 */


import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OverviewController extends BaseController{

    public OverviewController() {
        super();
    }

    public void doGet( HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {
        if (!chechAuth(request)){response.sendRedirect("index.html");
        }else {
            RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/overview.jsp");
            view.forward(request, response);
        }
    }

    public void doPost( HttpServletRequest request,
                        HttpServletResponse response)
            throws IOException, ServletException {

        if (!chechAuth(request)){response.sendRedirect("index.html");
        }else {
            RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/overview.jsp");
            view.forward(request, response);
        }
    }
}
