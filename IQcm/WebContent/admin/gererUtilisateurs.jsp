

<%@page import="qcm.models.User"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h4 class="margleft brownBold margbott2">Gérer les utilisateurs</h4>
<jsp:include page="/scripts/errorViewHelper.jsp" />

<%
            HashMap<Integer, User> users = (HashMap) request.getAttribute("users");
            if (users != null) {
%>

<button class="btn btn-sm btn-primary margbott add">
	<a href="<%= request.getContextPath()%>/admin/users/ajouter.html">Ajouter un utilisateur</a>
</button>

<table class="table table-bordered table-hover">
    <thead>
        <tr>
	        <th>Login</th>
	        <th>Nom</th>
	        <th>Prénom</th>
	        <th>Email</th>
	        <th>Mot de passe</th>
	        <th>Statut</th>
	        <th>Modifier</th>
	        <th>Contrôle</th>
        </tr>
    </thead>
    <%
                                        for (Integer idUser : users.keySet()) {
                                            User user = users.get(idUser);
    %>
    <tbody>
	    <tr>
	        <td><%= user.getLogin()%></td>
	        <td><%= user.getNom()%></td>
	        <td><%= user.getPrenom()%></td>
	        <td><%= user.getEmail()%></td>
	        <td><%= user.getPassword()%></td>
	        <td><%= user.getStatut().getLibelle()%></td>
	        <td class="centered">
	            <a href="<%= request.getContextPath()%>/admin/users/editer.html?id=<%= user.getIdUser()%>">Modifier
	                <img src="<%= request.getContextPath()%>/img/edit_16.png" alt="Modifier les informations concernant l'utilisateur" />
	            </a>
	        </td>
	        <td class="centered">
	            <%
	            
	            User userCourant = (User) request.getSession().getAttribute("user");
	            if (userCourant.getIdUser() != user.getIdUser()) {
	                                            if (user.estActif()) {
	                                                out.println("<span class='bon'>Actif</span>");
	                                            } else {
	                                                out.println("<span class='mauvais'>Inactif</span>");
	                                            }
	            %>
	            <form method="post" action="<%= request.getContextPath() %>/admin/users/controle.html">
	                <input type="hidden" name="id" value="<%= user.getIdUser()%>" />
	                <% if (user.estActif()) {%>
	                <input class="btn btn-sm btn-warning" type="submit" value="Désactiver" /> <input type="hidden" name="est_actif" value="false" />
	                <% } else {%>
	                <input class="btn btn-sm btn-success" type="submit" value="Activer" /> <input type="hidden" name="est_actif" value="true" />
	                <% }%>
	            </form>
	        </td>
	    </tr>
	</tbody>
    <%      }
        }
    %>
</table>
<%
            }
%>
