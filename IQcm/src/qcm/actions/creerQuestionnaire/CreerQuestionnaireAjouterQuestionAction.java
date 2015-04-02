/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package qcm.actions.creerQuestionnaire;

import java.util.List;

import qcm.actions.EnseignantAction;
import qcm.models.Question;
import qcm.models.Questionnaire;
import qcm.persistences.QuestionDAO;

public class CreerQuestionnaireAjouterQuestionAction extends EnseignantAction{

    public void execute() throws Exception {
        Questionnaire newQuestionnaire = (Questionnaire) request.getSession().getAttribute("newQuestionnaire");
        List<Question> questionsByThemeNewQuestionnaire = (List<Question>) request.getSession().getAttribute("questionsByThemeNewQuestionnaire");
        int idQuestion = Integer.parseInt(request.getParameter("idQuestionToAdd").toString());
        Question questionToAdd = QuestionDAO.getById(idQuestion);
        if (questionsByThemeNewQuestionnaire.contains(questionToAdd) && (!newQuestionnaire.getQuestions().contains(questionToAdd)) ){
            newQuestionnaire.addQuestion(questionToAdd);
            questionsByThemeNewQuestionnaire.remove(questionToAdd);
        }
        request.getSession().setAttribute("newQuestionnaire", newQuestionnaire);
        request.getSession().setAttribute("questionsByThemeNewQuestionnaire", questionsByThemeNewQuestionnaire);
        setView("/creerQuestionnaire/nouveau.jsp");
    }

}
