package qcm.actions.admin;

import java.sql.SQLException;

import qcm.actions.AdminAction;
import qcm.persistences.ThemeDAO;

public class AdminThemesIndexAction extends AdminAction {

    public void execute() throws SQLException {
        request.setAttribute("themes", ThemeDAO.getAll());
        setView("/admin/gererThemes.jsp");
    }
}
