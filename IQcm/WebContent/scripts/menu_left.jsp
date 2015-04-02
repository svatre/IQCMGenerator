
<%@page import="qcm.models.User" %>
<%
            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
                request.setAttribute("errorMessage", "Vous n'êtes pas connecté");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
                    <% if (!user.isAdmin()) {              
                        if (user.isCreator()) {%>
                        	<h4 class="margbott2" >
							<%= user.getPrenom()%> <%= user.getNom()%> <p style="font-size: 16px; color:grey"> (Enseignant)</p>
							</h4>
	                        <button class="btn btn-sm btn-default btn_menuEnsei">
	                        	<a href="<%= request.getContextPath()%>/creerQuestionnaire/index.html">Créer un questionnaire</a>
	                        </button>
                        	<button class="btn btn-sm btn-default btn_menuEnsei">
	                        	<a href="<%= request.getContextPath()%>/mesQuestionnaires/index.html">Mes questionnaires</a>
                       		</button>
                       		<button class="btn btn-sm btn-default btn_menuEnsei">	
	                        	<a href="<%= request.getContextPath()%>/logout.html">Déconnexion</a>
	                        </button>
                       <% }
                        else
                        {%>
                    		<h4 class="margbott2">
							<%= user.getPrenom()%> <%= user.getNom()%> <p style="font-size: 16px; color:grey"> (Etudiant)</p>
							</h4>
	                        <button class="btn btn-sm btn-default btn_menuEtud">
	                    		<a href="<%= request.getContextPath()%>/passerQuestionnaire/index.html">Passer un questionnaire</a>
	                        </button>
	                        <button class="btn btn-sm btn-default btn_menuEtud">	
	                        	<a href="<%= request.getContextPath()%>/mesResultats/index.html">Mes résultats</a>
	                        </button>
	                        <button class="btn btn-sm btn-default btn_menuEtud">	
	                        	<a href="<%= request.getContextPath()%>/logout.html">Déconnexion</a>
	                        </button>
                    	<%}
                       }%>
                    <%if (user.isAdmin()) {%>
                    	<h4 class="margbott2">
							<%= user.getPrenom()%> <%= user.getNom()%> <p style="font-size: 16px; color:grey"> (Administrateur)</p>
							</h4>
                        <button class="btn btn-sm btn-default btn_menuAdmin">
                        	<a href="<%= request.getContextPath()%>/admin/themes/index.html">Thèmes</a>
                        </button>
                        <button class="btn btn-sm btn-default btn_menuAdmin">
	                        <a href="<%= request.getContextPath()%>/admin/niveaux/index.html">Niveaux</a>
	                     </button>
	                     <button class="btn btn-sm btn-default btn_menuAdmin">   
	                        <a href="<%= request.getContextPath()%>/admin/questionnaires/index.html">Questionnaires</a>
	                     </button>
	                     <button class="btn btn-sm btn-default btn_menuAdmin">  
	                        <a href="<%= request.getContextPath()%>/admin/users/index.html">Utilisateurs</a>
                    	</button>
                    	<button class="btn btn-sm btn-default btn_menuAdmin">  
	                        <a href="<%= request.getContextPath()%>/logout.html">Déconnexion</a>
                    	</button>
                    <% }%>
