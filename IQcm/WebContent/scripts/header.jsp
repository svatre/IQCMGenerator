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
<p id="top">Bienvenue <%= user.getPrenom()%> <%= user.getNom()%>, <a href="<%= request.getContextPath()%>/logout.html">Déconnexion</a></p>
<div id="logo">
    <h1><a href="<%= request.getContextPath()%>/index.html">Qcm Generator</a></h1>
</div>
<div class="line"></div>