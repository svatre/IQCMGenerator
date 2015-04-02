package qcm.application;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qcm.actions.Action;
import qcm.exceptions.ExpiredSessionException;
import qcm.exceptions.UnauthorizedActionException;
import qcm.exceptions.UnknownUserException;
import qcm.router.Router;

public class Application extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String forward = "/index.jsp";
        String uri = "";
        try {

            uri = request.getRequestURI();
            uri = uri.substring(request.getContextPath().length(), uri.length());
            Action action = getActionByUri(uri);
            if (action != null) {
                action.setRequestAndCheckAuthorization(request);
                action.execute();
                request.setAttribute("view", action.getView());
                forward = "/view.jsp";
            } else {
                request.setAttribute("errorMessage", "Cette page n'existe pas : " + uri);
                forward = "/error.jsp";
            }



        } catch (ExpiredSessionException e) {
            request.setAttribute("errorMessage", e.getMessage());
            e.printStackTrace();
        } catch (UnknownUserException e) {
            request.setAttribute("errorMessage", "Unknown user : " + e.getMessage());
            forward = "/error.jsp";
            e.printStackTrace();
        } catch (UnauthorizedActionException e) {
            request.setAttribute("errorMessage", "Access denied : " + e.getMessage());
            forward = "/error.jsp";
            e.printStackTrace();
        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            forward = "/error.jsp";
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(forward).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private Action getActionByUri(String uri) {
        Action action = null;
        try {
            Class classe = Router.getActionByUri(uri);
            if (classe != null) {
                action = (Action) classe.newInstance();
            }
        } catch (InstantiationException ex) {
            Logger.getLogger(Router.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Router.class.getName()).log(Level.SEVERE, null, ex);
        }
        return action;
    }
}
