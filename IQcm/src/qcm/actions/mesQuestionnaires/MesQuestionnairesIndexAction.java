/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package qcm.actions.mesQuestionnaires;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import qcm.actions.EnseignantAction;
import qcm.models.Questionnaire;
import qcm.persistences.QuestionnaireDAO;
import qcm.services.ActionHelper;

public class MesQuestionnairesIndexAction extends EnseignantAction{

    public void execute() throws SQLException{
        int idUser = ActionHelper.getIdUser(request);

         List<Questionnaire> questionnaires = QuestionnaireDAO.getCreatedByUser(idUser);
         Map<Integer , String> mapQuestionnaires = new HashMap<Integer, String>();
         for(Questionnaire q: questionnaires){
            mapQuestionnaires.put(q.getIdQuestionnaire(), q.getLibelle());
         }
         request.setAttribute("mapQuestionnaires", mapQuestionnaires);
         setView("/mesQuestionnaires/mesQuestionnaires.jsp");
    }
}
