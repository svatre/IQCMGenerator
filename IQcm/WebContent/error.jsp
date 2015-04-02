<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" href="<%= request.getContextPath() %>/css/screen.css" type="text/css" media="screen" title="css" charset="utf-8" />
        <title>Projet QCM</title>
    </head>
    <body>
        <div id="content">
            <p id="top">Une erreur s'est produite</p>
            <div id="logo">
                <h1><a href="index.jsp">Qcm Generator</a></h1>
            </div>
            <div class="line"></div>

            <div id="body">
                <h1>Vous avez rencontré une erreur</h1><br/><br/>
                <%
                        String error = (String)  request.getAttribute("errorMessage");

                            if ( error != null) {
                                out.println((String) request.getAttribute("errorMessage"));
                            }
              
                            if(exception != null){
                                out.println(exception.toString());

                            }


               %>
            </div>

            <div id="footer">
                
            </div>
        </div>
    </body>
</html>
