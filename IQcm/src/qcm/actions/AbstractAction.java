package qcm.actions;
import javax.servlet.http.HttpServletRequest;

public abstract class AbstractAction implements Action{


    protected HttpServletRequest request;
    private String view;



    protected AbstractAction(){
    }

    public void setRequestAndCheckAuthorization(HttpServletRequest request) throws Exception{
        this.request = request;
    }



    public String getView(){
        return view;
    }

    protected void setView(String forward){
        this.view = forward;
    }

    public boolean isUserAuthenticated() {
        return request.getSession().getAttribute("user") != null;
    }

    public abstract void execute() throws Exception;

}
