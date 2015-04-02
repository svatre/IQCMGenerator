package qcm.actions.admin;

import java.sql.SQLException;
import java.util.HashMap;

import qcm.actions.AdminAction;
import qcm.models.User;
import qcm.persistences.UserDAO;
import qcm.services.AdminActionHelper;

public class AdminUsersEditAction extends AdminAction {

    public void execute() throws SQLException {
        Integer idUser = Integer.parseInt(request.getParameter("id"));
        if (idUser != null && idUser >= 0) {
            if (request.getMethod().equals("GET")) {
                AdminActionHelper.setAttributeUser(idUser, request);
                setView("/admin/editUser.jsp");
            } else if (request.getMethod().equals("POST")) {
                boolean ok = AdminActionHelper.isEmail(request.getParameter("email"), request);
                System.out.println(ok);
                if (!ok) {
                    AdminActionHelper.setAttributeUser(idUser, request);
                    setView("/admin/editUser.jsp");
                } else {
                    if (idUser != null && idUser >= 0) {
                        String login = request.getParameter("login");
                        String nom = request.getParameter("nom");
                        String prenom = request.getParameter("prenom");
                        String email = request.getParameter("email");
                        String password = request.getParameter("password");
                        User user = UserDAO.getById(idUser);
                        user.setLogin(login);
                        user.setNom(nom);
                        user.setPrenom(prenom);
                        user.setEmail(email);
                        user.setPassword(password);
                        UserDAO.update(user);
                        request.setAttribute("message", "La modification de l'utilisateur s'est déroulée correctement.");
                    }
                    HashMap<Integer, User> users = UserDAO.getAll();
                    request.setAttribute("users", users);
                    setView("/admin/gererUtilisateurs.jsp");
                }
            }
        } else {
            request.setAttribute("message", "User inexistant");
        }
    }
}
