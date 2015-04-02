package qcm.actions.admin;

import java.sql.SQLException;

import qcm.actions.AdminAction;
import qcm.models.Questionnaire;
import qcm.persistences.QuestionnaireDAO;

public class AdminQuestionnairesControleAction extends AdminAction {

    public void execute() throws SQLException {
        Integer idQuestionnaire = Integer.parseInt(request.getParameter("id"));
        if (idQuestionnaire != null && idQuestionnaire >= 0) {
            Boolean estActif = Boolean.parseBoolean(request.getParameter("est_actif"));
            Questionnaire q = QuestionnaireDAO.getById(idQuestionnaire);
            q.setEstActif(estActif);
            QuestionnaireDAO.update(q);
            String message = "Le questionnaire <strong>" + q.getLibelle() + "</strong> a été ";
            if (estActif) {
                message += "activé";
            } else {
                message += "désactivé";
            }
            request.setAttribute("message", message);
        } else {
            request.setAttribute("message", "Questionnaire inexistant");
        }
        request.setAttribute("questionnaires", QuestionnaireDAO.getAll());
        setView("/admin/gererQuestionnaires.jsp");
    }
}
