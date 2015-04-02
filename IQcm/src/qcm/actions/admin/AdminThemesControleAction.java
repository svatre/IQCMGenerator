package qcm.actions.admin;

import java.sql.SQLException;

import qcm.actions.AdminAction;
import qcm.models.Theme;
import qcm.persistences.ThemeDAO;

public class AdminThemesControleAction extends AdminAction {

    public void execute() throws SQLException {
        Integer idTheme = Integer.parseInt(request.getParameter("id"));
        if (idTheme != null && idTheme >= 0) {
            Boolean estActif = Boolean.parseBoolean(request.getParameter("est_actif"));
            Theme theme = ThemeDAO.getById(idTheme);
            theme.setEstActif(estActif);
            ThemeDAO.update(theme);
            String message = "Le th√®me <strong>" + theme.getLibelle() + "</strong> a été ";
            if (estActif) {
                message += "activé";
            } else {
                message += "désactivé";
            }
            request.setAttribute("message", message);
        } else {
            request.setAttribute("message", "Th√®me inexistant");
        }
        request.setAttribute("themes", ThemeDAO.getAll());
        setView("/admin/gererThemes.jsp");
    }
}
