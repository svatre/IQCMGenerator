
<%@page import="qcm.models.User" %>
<%
            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
                request.setAttribute("errorMessage", "Vous n'êtes pas connecté");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
                <div id="navigation">
                    <ul class="menu_gauche">
                    <% if (!user.isAdmin()) {              
                        if (user.isCreator()) {%>
                        	<h5>Teacher</h5>
                        	<a href="<%= request.getContextPath()%>/creerQuestionnaire/index.html"><li>Créer un questionnaire</li></a>
                        	<a href="<%= request.getContextPath()%>/mesQuestionnaires/index.html"><li>Mes questionnaires</li></a>
                       <% }
                        else
                        {%>
                    		<h5>Student</h5>
                    		<a href="<%= request.getContextPath()%>/passerQuestionnaire/index.html"><li>Passer un questionnaire</li></a>
                        	<a href="<%= request.getContextPath()%>/mesResultats/index.html"><li>Mes résultats</li></a>
                        
                    	<%}
                       }%>
                    </ul>
                    <%if (user.isAdmin()) {%>
                    <ul class="menu_gauche">
                    	<h5>Admin</h5>
                        <a href="<%= request.getContextPath()%>/admin/themes/index.html"><li>Thèmes</li></a>
                        <a href="<%= request.getContextPath()%>/admin/niveaux/index.html"><li>Niveau</li></a>
                        <a href="<%= request.getContextPath()%>/admin/questionnaires/index.html"><li>Questionnaires</li></a>
                        <a href="<%= request.getContextPath()%>/admin/users/index.html"><li>Utilisateurs</li></a>
                    </ul>
                    <% }%>
                </div>
