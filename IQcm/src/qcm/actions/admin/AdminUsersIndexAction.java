package qcm.actions.admin;

import java.sql.SQLException;
import java.util.HashMap;

import qcm.actions.AdminAction;
import qcm.exceptions.ExpiredSessionException;
import qcm.models.User;
import qcm.persistences.UserDAO;

public class AdminUsersIndexAction extends AdminAction {

    public void execute() throws SQLException, ExpiredSessionException {
        HashMap<Integer, User> users = UserDAO.getAll();
        request.setAttribute("users", users);
        setView("/admin/gererUtilisateurs.jsp");
    }
}
