<!--
    Page d'index / Accueil pour les utilisateur non connectés.
-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
			<div class="row">
				<div class="col-lg-12 head">
					<h1><a class="qcm" href="<%= request.getContextPath() %>">QCM Generator</a></h1>
					<p class="right">Bienvenue, merci de vous authentifier</p>
				</div>
			</div>
			<div class="row margtop">
				<div class="col-lg-offset-3 col-lg-4">
					<jsp:include page="scripts/login.jsp" />
				</div>
			</div>
		</div>
    </body>
</html>
