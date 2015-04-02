package qcm.actions;

import javax.servlet.http.HttpServletRequest;

public interface Action {

    public void execute() throws Exception;

    public void setRequestAndCheckAuthorization(HttpServletRequest request) throws Exception;

    public String getView();

}
