/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package qcm.actions.creerQuestionnaire;

import qcm.actions.EnseignantAction;
import qcm.models.Questionnaire;
import qcm.services.Helper;

public class CreerQuestionnaireEnregistrerAction extends EnseignantAction{

        public void execute() throws Exception {
            Questionnaire newQuestionnaire = (Questionnaire) request.getSession().getAttribute("newQuestionnaire");
            Helper.save(newQuestionnaire);
            request.getSession().setAttribute("newQuestionnaire", null);
            setView("/creerQuestionnaire/enregistrement.jsp");
        }

}
