/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package qcm.actions.creerQuestionnaire;

import java.util.ArrayList;

import qcm.actions.EnseignantAction;
import qcm.exceptions.UnauthorizedActionException;
import qcm.models.Question;
import qcm.models.Questionnaire;
import qcm.models.Reponse;
import qcm.persistences.QuestionDAO;
import qcm.services.ActionHelper;

public class CreerQuestionnaireAjouterReponsesAction extends EnseignantAction{

    public void execute() throws Exception {
        String libelleQuestion = request.getParameter("libelleQuestion").toString();
        
        if (libelleQuestion == null || libelleQuestion.trim().isEmpty()) {
            throw new UnauthorizedActionException("Merci d'entrer le libellé de votre question");
        } else if (request.getParameter("nbReponses") == null) {
            throw new UnauthorizedActionException("Merci d'entrer le nombre de réponses");
        }
        int nbReponses = Integer.parseInt(request.getParameter("nbReponses"));
        if (nbReponses <= 0) {
            throw new UnauthorizedActionException("Merci d'entrer un nombre correct de réponses");
        }
        Questionnaire newQuestionnaire = (Questionnaire) request.getSession().getAttribute("newQuestionnaire");
        Question nouvelleQuestion = new Question(null, libelleQuestion, newQuestionnaire.getIdTheme(), ActionHelper.getIdUser(request), 0, new ArrayList<Reponse>());
        if(QuestionDAO.search(nouvelleQuestion) != null){
            throw new UnauthorizedActionException("Une question du même libelle et du même th√®me existe déj√†");
        }
        request.setAttribute("nbReponses", nbReponses);
        request.setAttribute("libelleQuestion",libelleQuestion);
        setView("/creerQuestionnaire/ajouterReponses.jsp");
    }

}
