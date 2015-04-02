<!--
    Page d'index / Accueil pour les utilisateur non connectés.
-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" href="<%= request.getContextPath() %>/css/screen.css" type="text/css" media="screen" title="css" charset="utf-8" />
        <title>Projet QCM</title>
    </head>
    <body>
        <div id="content">
            <p id="top">Bienvenue, merci de vous authentifier.</p>
            <div id="logo">
                <h1><a href="<%= request.getContextPath() %>">QCM Generator</a></h1>
            </div>
            <div class="line"></div>

            <div id="body">
            <jsp:include page="scripts/login.jsp" />
            </div>
            <div id="footer">
            </div>
        </div>
    </body>
</html>
