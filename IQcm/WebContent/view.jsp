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
        <link rel="stylesheet" href="/testProject/css/screen.css" type="text/css" media="screen" title="css" charset="utf-8" />
        <title>Projet QCM</title>
    </head>
    <body>
        <div id="content">
            <jsp:include page="scripts/header.jsp" />
            <div id="body">
                <jsp:include page="scripts/menu_left.jsp" />
                <div id="contenu">
                    <jsp:include page="<%= layout%>" />
                </div>
            </div>

            <div id="footer">
            </div>
        </div>
    </body>
</html>