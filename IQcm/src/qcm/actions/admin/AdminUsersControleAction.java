package qcm.actions.admin;

import java.sql.SQLException;
import java.util.HashMap;

import qcm.actions.AdminAction;
import qcm.exceptions.UnauthorizedActionException;
import qcm.models.User;
import qcm.persistences.UserDAO;
import qcm.services.ActionHelper;

public class AdminUsersControleAction extends AdminAction {

    public void execute() throws SQLException, UnauthorizedActionException {
        Integer idUser = Integer.parseInt(request.getParameter("id"));
        if (idUser != null && idUser >= 0) {
            if (ActionHelper.getIdUser(request) == idUser) {
                throw new UnauthorizedActionException("Vous n'avez pas le droit de désactiver votre propre compte.");
            }

            User user = UserDAO.getById(idUser);
            Boolean estActif = Boolean.parseBoolean(request.getParameter("est_actif"));
            user.setEstActif(estActif);
            UserDAO.update(user);
            String message = "Le compte utilisateur de <strong>" + user.getNom() + " " + user.getPrenom() + "</strong> a été ";
            if (estActif) {
                message += "activé";
            } else {
                message += "désactivé";
            }
            request.setAttribute("message", message);
        } else {
            request.setAttribute("message", "Utilisateur inexistant");
        }
        HashMap<Integer, User> users = UserDAO.getAll();
        request.setAttribute("users", users);
        setView("/admin/gererUtilisateurs.jsp");
    }
}
