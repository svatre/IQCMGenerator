/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package qcm.actions.passerQuestionnaire;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import qcm.actions.AbstractAction;
import qcm.exceptions.ExpiredSessionException;
import qcm.exceptions.UnauthorizedActionException;
import qcm.models.Qcm;
import qcm.models.Reponse;
import qcm.persistences.QuestionDAO;

public class PasserQuestionnaireQuestionSuivanteAction extends PasserQuestionnaireAction{

    public void execute() throws SQLException , ExpiredSessionException , UnauthorizedActionException{

        Qcm qcm = (Qcm) request.getSession().getAttribute("qcm");
        String[] reponses = request.getParameterValues("reponses");
        if (reponses != null && reponses.length != 0) {
            List<Integer> userReponses = new ArrayList<Integer>();
            for (String reponse : reponses) {
                userReponses.add(Integer.parseInt(reponse));
            }
            int idQuestion = Integer.parseInt(request.getParameter("idQuestion"));
            List<Reponse> reponsesDeQuestion = QuestionDAO.getById(idQuestion).getReponses();
            if (!qcm.estFini() && reponsesDeQuestion.size() > userReponses.size()) {
                qcm.setUserReponses(idQuestion, userReponses);
            }
        }
        Integer questionCourante = qcm.getQuestionSuivante();
        if (questionCourante == null) {
            request.setAttribute("questionCourante", null);
            request.setAttribute("estFini", true);
        } else {
            request.setAttribute("questionCourante", QuestionDAO.getById((int) questionCourante));
        }
        setView("/passerQuestionnaire/afficherQuestion.jsp");
    }

}
