package qcm.actions.admin;

import java.sql.SQLException;

import qcm.actions.AdminAction;
import qcm.models.Niveau;
import qcm.persistences.NiveauDAO;

public class AdminNiveauxControleAction extends AdminAction {

    public void execute() throws SQLException {
        Integer idNiveau = Integer.parseInt(request.getParameter("id"));
        if (idNiveau != null && idNiveau >= 0) {
            Boolean estActif = Boolean.parseBoolean(request.getParameter("est_actif"));
            Niveau niveau = NiveauDAO.getById(idNiveau);
            niveau.setEstActif(estActif);
            NiveauDAO.update(niveau);
            String message = "Le niveau <strong>" + niveau.getLibelle() + "</strong> a été ";
            if (estActif) {
                message += "activé";
            } else {
                message += "désactivé";
            }
            request.setAttribute("message", message);
        } else {
            request.setAttribute("message", "Niveau inexistant");
        }
        request.setAttribute("niveaux", NiveauDAO.getAll());
        setView("/admin/gererNiveaux.jsp");
    }
}
