package qcm.actions.admin;

import java.sql.SQLException;

import qcm.actions.AdminAction;
import qcm.models.Niveau;
import qcm.persistences.NiveauDAO;
import qcm.services.ActionHelper;

public class AdminNiveauxEditAction extends AdminAction {

    public void execute() throws SQLException {
        Integer idNiveau = Integer.parseInt(request.getParameter("id"));
        if (idNiveau != null && idNiveau >= 0) {
            if (request.getMethod().equals("GET")) {
                ActionHelper.setAttributeNiveau(idNiveau, request);
                System.out.println("APRES");
                setView("/admin/editNiveau.jsp");
            } else if (request.getMethod().equals("POST")) {
                if (idNiveau != null && idNiveau >= 0) {
                    String libelle = request.getParameter("libelle");
                    Niveau theme = NiveauDAO.getById(idNiveau);
                    theme.setLibelle(libelle);
                    NiveauDAO.update(theme);
                    request.setAttribute("message", "La modification du niveau s'est déroulée correctement.");
                }
                ActionHelper.setAttributeNiveaux(request);
                setView("/admin/gererNiveaux.jsp");
            }
        } else {
            request.setAttribute("message", "Niveau inexistant");
        }
    }

}
