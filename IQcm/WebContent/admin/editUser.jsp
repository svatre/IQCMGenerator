

<%@page import="qcm.models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h4>Modifier le compte utilisateur</h4>
<jsp:include page="/scripts/errorViewHelper.jsp" />
<%
            User user = (User) request.getAttribute("user");

%>
<p>Appliquez les modifications nécessaires sur ce compte utilisateur :</p>
<div  class="choice">
    <form action="<%= request.getContextPath()%>/admin/users/editer.html" method="post">
        <table>
            <tr>
                <td><label for="libelle"><strong>Login : </strong></label></td>
                <td><input type="text" name="login" id="login" value="<%= user.getLogin()%>" size="50" class="medium-input" /></td>
            </tr>
            <tr>
                <td><label for="libelle"><strong>Nom : </strong></label></td>
                <td><input type="text" name="nom" id="nom" value="<%= user.getNom()%>" size="50" class="medium-input" /></td>
            </tr>
            <tr>
                <td><label for="libelle"><strong>Prénom : </strong></label></td>
                <td><input type="text" name="prenom" id="prenom" value="<%= user.getPrenom()%>" size="50" class="medium-input" /></td>
            </tr>
            <tr>
                <td><label for="libelle"><strong>Email : </strong></label></td>
                <td><input type="text" name="email" id="email" value="<%= user.getEmail()%>" size="50" class="medium-input
                           <%
                                       if (request.getAttribute("emailError") != null) {
                                           out.println(" mauvais");
                                       }
                           %>
                           " /></td>
                           <%
                                       if (request.getAttribute("emailError") != null) {
                                           out.println("<td class='mauvais'>");
                                           out.println(request.getAttribute("emailError"));
                                           out.println("</td>");
                                       }
                           %>
            </tr>
            <tr>
                <td><label for="libelle"><strong>Mot de passe : </strong></label></td>
                <td><input type="password" name="password" id="password" value="<%= user.getPassword()%>" size="50" class="medium-input" /></td>
            </tr>
        </table>
        <input type="hidden" name="id" value="<%= user.getIdUser()%>" />
        <input type="submit" value="Enregistrer" class="button" />
        <a href="<%= request.getContextPath()%>/admin/users/index.html" class="button">Annuler</a>
    </form>
</div>
