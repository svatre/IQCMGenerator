/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package qcm.actions.passerQuestionnaire;

import java.sql.SQLException;

import qcm.exceptions.ExpiredSessionException;
import qcm.exceptions.UnauthorizedActionException;
import qcm.models.Questionnaire;
import qcm.persistences.QuestionnaireDAO;
import qcm.services.ActionHelper;

public class PasserQuestionnaireChoixAction extends PasserQuestionnaireAction{

    public void execute() throws SQLException , UnauthorizedActionException , ExpiredSessionException {
        Questionnaire questionnaire = QuestionnaireDAO.getById(Integer.parseInt(request.getParameter("questionnaire").toString()));
        request.setAttribute("questionnaire", questionnaire);
        ActionHelper.setAttributeTheme(questionnaire.getIdTheme(), request);
        ActionHelper.setAttributeNiveau(questionnaire.getIdNiveau(), request);
        if(ActionHelper.userHasAlreadyPassedQuestionnaire(ActionHelper.getIdUser(request), questionnaire)){
            request.setAttribute("userHasAlreadyPassedQuestionnaire", true);
        }
        setView("/passerQuestionnaire/warning.jsp");
    }
}
