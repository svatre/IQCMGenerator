<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="qcm.models.User" %>
<%
            /**
             * Fragment à inclure pour afficher le header de la page
             */
            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
                request.setAttribute("errorMessage", "Vous n'êtes pas connecté");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
%>
       
<div class="row">
	<div class="col-lg-12 head">
		<h1><a class="qcm" href="<%= request.getContextPath() %>">QCM Generator</a></h1>
			<p class="right">
				Bienvenue <%= user.getPrenom()%> <%= user.getNom()%>, <a class="white" href="<%= request.getContextPath()%>/logout.html">Déconnexion</a>
			</p>
	</div>
</div>