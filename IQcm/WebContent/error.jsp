<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap.css" type="text/css" media="screen" title="css" charset="utf-8" />
		<link rel="stylesheet" href="<%= request.getContextPath() %>/css/index.css" type="text/css" media="screen" title="css" charset="utf-8" />
 		<title>Projet QCM</title>
    </head>
    <body>
        <div class="container">
            <jsp:include page="scripts/header.jsp" />
            <div class="row">
            	<div class="col-lg-12">		
		                <h3 class="brownBold margbott2">Vous avez rencontré une erreur</h3><br/><br/>
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
		    	</div>
			</div>
    </body>
</html>
