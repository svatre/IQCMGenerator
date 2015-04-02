package qcm.actions;

import java.sql.SQLException;

import qcm.exceptions.UnknownUserException;
import qcm.models.User;
import qcm.services.ActionHelper;

public class IndexAction extends AbstractAction {

    public void execute() throws SQLException, UnknownUserException {

        String page = "/scripts/login.jsp";

        if (!isUserAuthenticated()) {
            String login = request.getParameter("login").toString();
            String password = request.getParameter("password").toString();
            if (login == null || login.trim().isEmpty()) {
                throw new UnknownUserException("Veuillez renseigner votre login");
            } else if (password == null || password.trim().isEmpty()) {
                throw new UnknownUserException("Veuillez renseigner votre mot de passe");
            } else {
                User user = ActionHelper.checkUserByLoginAndPassWord(login, password);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                    page = "/user/accueil.jsp";
                } else {
                    throw new UnknownUserException("Utilisateur inconnu : " + login);
                }
            }
        } else {
            page = "/user/accueil.jsp";
        }
        setView(page);


    }
}
