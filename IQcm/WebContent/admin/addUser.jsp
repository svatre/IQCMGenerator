

<%@page import="qcm.models.Statut"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h4>Ajouter un utilisateur</h4>
<jsp:include page="/scripts/errorViewHelper.jsp" />

<%
            List<Statut> statuts = (List<Statut>) request.getAttribute("statuts");
            Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");
%>

<div  class="choice">
    <form action="<%= request.getContextPath()%>/admin/users/ajouter.html" method="post">
        <table>
            <tr>
                <td><label for="login"><strong>Login : </strong></label></td>
                <td><input type="text" name="login" id="login" size="50" class="medium-input
                           <% if ((errors != null) && errors.get("login").isEmpty()) {
                                           out.print("error-input");
                                       }
                           %>
                           " value='<%= errors == null ? "" : errors.get("login")%>' /></td>
            </tr>
            <tr>
                <td><label for="nom"><strong>Nom : </strong></label></td>
                <td><input type="text" name="nom" id="nom" size="50" class="medium-input
                           <% if ((errors != null) && errors.get("nom").isEmpty()) {
                                           out.print("error-input");
                                       }
                           %>
                           " value="<%= errors == null ? "" : errors.get("nom")%>" /></td>
            </tr>
            <tr>
                <td><label for="prenom"><strong>Prénom : </strong></label></td>
                <td><input type="text" name="prenom" id="prenom" size="50" class="medium-input
                           <% if ((errors != null) && errors.get("prenom").isEmpty()) {
                                           out.print("error-input");
                                       }
                           %>
                           " value="<%= errors == null ? "" : errors.get("prenom")%>" /></td>
            </tr>
            <tr>
                <td><label for="email"><strong>Email : </strong></label></td>
                <td><input type="text" name="email" id="email" size="50" class="medium-input
                           <% if ((errors != null) && errors.get("email").isEmpty()) {
                                           out.print("error-input");
                                       }
                           %>
                           " value="<%= errors == null ? "" : errors.get("email")%>" /></td>
            </tr>
            <tr>
                <td><label for="password"><strong>Mot de passe : </strong></label></td>
                <td><input type="password" name="password" id="password" size="50" class="medium-input
                           <% if ((errors != null) && errors.get("password").isEmpty()) {
                                           out.print("error-input");
                                       }
                           %>
                           " value="<%= errors == null ? "" : errors.get("password")%>" /></td>
            </tr>
            <tr>
                <td><label for="password2"><strong>Vérification du mot de passe : </strong></label></td>
                <td><input type="password" name="password2" id="password2" size="50" class="medium-input
                           <% if ((errors != null) && errors.get("passwordVerification").isEmpty()) {
                                           out.print("error-input");
                                       }
                           %>
                           " value="<%= errors == null ? "" : errors.get("passwordVerification")%>" /></td>
            </tr>
            <tr>
                <td><label for="statut"><strong>Statut : </strong></label></td>
                <td>
                    <select id="statut" name="statut" class="medium-input">
                        <%
                                    for (Statut statut : statuts) {
                                        out.println("<option value='" + statut.getId_statut() + "'");
                                        if (errors != null && (Integer.parseInt(errors.get("statut")) == statut.getId_statut())) {
                                            out.print(" selected='selected'");
                                        }
                                        out.print(">");
                                        out.print(statut.getLibelle());
                                        out.print("</options>");
                                    }
                        %>
                    </select>
                </td>
            </tr>
        </table>
        <input type="submit" value="Enregistrer" class="button" />
        <a href="<%= request.getContextPath()%>/admin/users/index.html" class="button">Annuler</a>
    </form>
</div>
