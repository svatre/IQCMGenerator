package qcm.actions.admin;

import java.sql.SQLException;

import qcm.actions.AdminAction;
import qcm.models.Theme;
import qcm.persistences.ThemeDAO;
import qcm.services.ActionHelper;

public class AdminThemesEditAction extends AdminAction {

    public void execute() throws SQLException {
        Integer idTheme = Integer.parseInt(request.getParameter("id"));
        if (idTheme != null && idTheme >= 0) {
            if (request.getMethod().equals("GET")) {
                ActionHelper.setAttributeTheme(idTheme, request);
                System.out.println("APRES");
                setView("/admin/editTheme.jsp");
            } else if (request.getMethod().equals("POST")) {
                if (idTheme != null && idTheme >= 0) {
                    String libelle = request.getParameter("libelle");
                    Theme theme = ThemeDAO.getById(idTheme);
                    theme.setLibelle(libelle);
                    ThemeDAO.update(theme);
                    request.setAttribute("message", "La modification du th√®me s'est déroulée correctement.");
                }
                ActionHelper.setAttributeThemes(request);
                setView("/admin/gererThemes.jsp");
            }
        } else {
            request.setAttribute("message", "Th√®me inexistant");
        }
    }
}
