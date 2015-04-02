/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package qcm.actions.passerQuestionnaire;
import java.sql.SQLException;

import qcm.actions.AbstractAction;
import qcm.exceptions.ExpiredSessionException;
import qcm.exceptions.UnauthorizedActionException;
import qcm.models.Qcm;
import qcm.persistences.QuestionDAO;
import qcm.services.ActionHelper;

public class PasserQuestionnaireCommencerAction extends PasserQuestionnaireAction{

   public void execute() throws SQLException , UnauthorizedActionException ,ExpiredSessionException {

        int idQuestionnaire = Integer.parseInt(request.getParameter("questionnaire").toString());
        int idUser = ActionHelper.getIdUser(request);
        Qcm qcm = new Qcm(idQuestionnaire, idUser);
        if (qcm == null) {
            throw new UnauthorizedActionException("Questionnaire introuvable");
        }else if(ActionHelper.userHasAlreadyPassedQuestionnaire(idUser, qcm.getQuestionnaire())){
            qcm.setEstFini(true);
        }else{
            request.setAttribute("questionCourante", QuestionDAO.getById(qcm.getQuestionSuivante()));
        }
        request.getSession().setAttribute("qcm", qcm);
        setView("/passerQuestionnaire/afficherQuestion.jsp");
    }
}
