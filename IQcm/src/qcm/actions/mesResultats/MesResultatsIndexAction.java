/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package qcm.actions.mesResultats;

import java.sql.SQLException;

import qcm.actions.AbstractAction;
import qcm.exceptions.ExpiredSessionException;
import qcm.services.ActionHelper;

public class MesResultatsIndexAction extends AbstractAction{

        public void execute() throws SQLException , ExpiredSessionException{
            if(!isUserAuthenticated()){
                throw new ExpiredSessionException("Merci de vous authentifier");
            }
            request.setAttribute("questionnairesPasses", ActionHelper.getQuestionnairesPasseByUser(ActionHelper.getIdUser(request)));
            setView("/mesResultats/mesResultats.jsp");
        }
}
