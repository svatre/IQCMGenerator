/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package qcm.actions.passerQuestionnaire;

import javax.servlet.http.HttpServletRequest;

import qcm.actions.AbstractAction;
import qcm.exceptions.ExpiredSessionException;

public abstract class PasserQuestionnaireAction  extends AbstractAction{
	
    @Override
    public void setRequestAndCheckAuthorization(HttpServletRequest request) throws Exception{
        super.setRequestAndCheckAuthorization(request);
        if(!isUserAuthenticated()){
            throw new ExpiredSessionException("Merci de vous authentifier");
        }
        
    }

    public abstract void execute() throws Exception;
}
