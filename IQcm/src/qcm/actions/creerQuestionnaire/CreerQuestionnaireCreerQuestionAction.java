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

public class CreerQuestionnaireCreerQuestionAction extends EnseignantAction{

    public void execute() throws Exception {
        String libelleQuestion = (String) request.getParameter("libelleQuestion");
        if (libelleQuestion == null || libelleQuestion.trim().isEmpty()) {
            throw new UnauthorizedActionException("Merci d'entrer le libellé de votre question");
        } else if (request.getParameter("nbReponses") == null) {
            throw new UnauthorizedActionException("Le nombre de réponses ne correspond pas √† ce qui est attendu");
        }
        int nbReponses = Integer.parseInt(request.getParameter("nbReponses"));
        if (nbReponses <= 0) {
            throw new UnauthorizedActionException("Le nombre de réponses ne correspond pas √† ce qui est attendu");
        }
        Questionnaire newQuestionnaire = (Questionnaire) request.getSession().getAttribute("newQuestionnaire");
        Question nouvelleQuestion = new Question(null, libelleQuestion, newQuestionnaire.getIdTheme(), ActionHelper.getIdUser(request), 0, new ArrayList<Reponse>());
        if (newQuestionnaire.getQuestions().contains(nouvelleQuestion)) {
            throw new UnauthorizedActionException("Cette question existe déj√† pour ce questionnaire");
        }
        if(QuestionDAO.search(nouvelleQuestion) != null){
            throw new UnauthorizedActionException("Une question du même libelle et du même th√®me existe déj√†");
        }
        String libelleReponse = null;
        String descriptifReponse = null;
        Integer noteReponse = 0;
        boolean estCorrecte = false;
        boolean existeEstCorecte = false;
        for(int i = 1 ; i<=nbReponses ; i++){
            libelleReponse = (String) request.getParameter("libelleReponse_" + i);
            descriptifReponse = (String) request.getParameter("descriptifReponse_" + i);
            noteReponse = Integer.parseInt(request.getParameter("noteReponse_" + i ));
            if(noteReponse == null){
                throw new UnauthorizedActionException("Merci de spécifier chaque note pour chaque réponse");
            }
            estCorrecte = request.getParameterValues("estCorrecteReponse_" + i) != null ;
            nouvelleQuestion.getReponses().add(new Reponse(null, libelleReponse, descriptifReponse, estCorrecte, noteReponse, null));
            existeEstCorecte = existeEstCorecte || estCorrecte;
        }
        if(!existeEstCorecte){
            throw new UnauthorizedActionException("Merci de spécifier au moins une bonne réponse");
        }
        newQuestionnaire.addQuestion(nouvelleQuestion);
        request.getSession().setAttribute("newQuestionnaire" , newQuestionnaire);
        setView("/creerQuestionnaire/nouveau.jsp");

    }

}
