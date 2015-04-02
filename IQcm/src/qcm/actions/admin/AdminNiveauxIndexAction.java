package qcm.actions.admin;

import java.sql.SQLException;

import qcm.actions.AdminAction;
import qcm.persistences.NiveauDAO;

public class AdminNiveauxIndexAction extends AdminAction {

    public void execute() throws SQLException {
        request.setAttribute("niveaux", NiveauDAO.getAll());
        setView("/admin/gererNiveaux.jsp");
    }
}
