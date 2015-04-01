<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="/error.jsp" %>
<%
            /**
             * Layout de l'application
             */
            String layout = (String) request.getAttribute("view");
%>
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
            <div class="row margtop">
            	<div class="col-lg-2">
                	<jsp:include page="scripts/menu_left.jsp" />
            	</div>
                <div class="col-lg-10">
                    <jsp:include page="<%= layout%>" />
                </div>
            </div>
        </div>
    </body>
</html>