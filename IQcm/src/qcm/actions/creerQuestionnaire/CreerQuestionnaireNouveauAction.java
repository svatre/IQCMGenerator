/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package qcm.actions.creerQuestionnaire;

import qcm.actions.EnseignantAction;
import qcm.exceptions.UnauthorizedActionException;
import qcm.models.Questionnaire;
import qcm.persistences.QuestionnaireDAO;
import qcm.services.ActionHelper;

public class CreerQuestionnaireNouveauAction extends EnseignantAction{
    
    public void execute() throws Exception {
       String libelle = request.getParameter("libelle").toString();

        if (request.getParameter("theme") == null || request.getParameter("theme").toString().trim().isEmpty()) {
            throw new UnauthorizedActionException("Merci de spécifier le th√®me de votre questionnaire");
        } else if (request.getParameter("niveau") == null || request.getParameter("niveau").toString().trim().isEmpty()) {
            throw new UnauthorizedActionException("Merci de spécifier le niveau");
        } else if (libelle.trim().isEmpty()) {
            new UnauthorizedActionException("Merci de spécifier le libelle de votre questionnaire");
        }
        int idTheme = Integer.parseInt(request.getParameter("theme"));
        int idNiveau = Integer.parseInt(request.getParameter("niveau"));
        Questionnaire existeQuestionnaire = QuestionnaireDAO.search(idTheme, idNiveau, libelle);
        if (existeQuestionnaire == null) {
            Questionnaire newQuestionnaire = new Questionnaire(libelle, idTheme, ActionHelper.getIdUser(request), idNiveau);
            ActionHelper.setAttributeQuestionsByThemeNewQuestionnaire(newQuestionnaire.getIdTheme() , request);
            request.getSession().setAttribute("newQuestionnaire", newQuestionnaire);
        }
        setView("/creerQuestionnaire/nouveau.jsp");
    }
}
