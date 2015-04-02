/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package qcm.actions.passerQuestionnaire;

import java.sql.SQLException;
import java.util.HashMap;

import qcm.actions.AbstractAction;
import qcm.exceptions.ExpiredSessionException;
import qcm.persistences.QuestionnaireDAO;
import qcm.services.ActionHelper;

public class PasserQuestionnaireListeQuestionnairesAction extends PasserQuestionnaireAction{

    
    public void execute() throws SQLException , ExpiredSessionException {
        
        HashMap<Integer, String> questionnaires = null;
        Integer theme = Integer.parseInt(request.getParameter("theme").toString());
        Integer niveau = Integer.parseInt(request.getParameter("niveau").toString());

        if (theme == null || theme < 0) {
            throw new IllegalArgumentException("Veuillez selectionner un thème");
        } else if (niveau == null || niveau < 0) {
            throw new IllegalArgumentException("Veuillez selectionner un niveau");
        } else if (theme == 0) {
            request.setAttribute("niveau", niveau);
            questionnaires = QuestionnaireDAO.getQuestionnairesByNiveau(niveau);
        } else if (niveau == 0) {
            request.setAttribute("theme", theme);
            questionnaires = QuestionnaireDAO.getQuestionnairesByTheme(theme);
        } else {
            request.setAttribute("theme", theme);
            request.setAttribute("niveau", niveau);
            questionnaires = QuestionnaireDAO.getQuestionnairesByThemeAndNiveau(theme, niveau);
        }
        request.setAttribute("questionnaires", questionnaires);
        ActionHelper.setAttributeNiveaux(request);
        ActionHelper.setAttributeThemes(request);
        setView("/passerQuestionnaire/choixQuestionnaire.jsp");
    }
}
