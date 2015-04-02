/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package qcm.actions.passerQuestionnaire;

import java.sql.SQLException;

import qcm.exceptions.ExpiredSessionException;
import qcm.models.Qcm;
import qcm.persistences.QuestionDAO;

public class PasserQuestionnaireModifierReponsesAction  extends PasserQuestionnaireAction{

    public void execute() throws SQLException , ExpiredSessionException{
        
        if(!((Qcm) request.getSession().getAttribute("qcm")).estFini()){
            int modifyQuestion = Integer.parseInt(request.getParameter("modifyQuestion").toString());
            request.setAttribute("questionCourante", QuestionDAO.getById(modifyQuestion));
        }
        setView("/passerQuestionnaire/afficherQuestion.jsp");
    }
}
