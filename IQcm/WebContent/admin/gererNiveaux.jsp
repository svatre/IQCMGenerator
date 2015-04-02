

<%@page import="qcm.models.Niveau"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h4>Gérer les niveaux des questionnaires</h4>
<jsp:include page="/scripts/errorViewHelper.jsp" />

<%
            List<Niveau> niveaux = (ArrayList) request.getAttribute("niveaux");
            if (niveaux != null) {
%>
<table class="format">
    <tr>
        <th>Libellé</th>
        <th>Modifier</th>
        <th>Activité</th>
        <th>Contrôle</th>
    </tr>
    <%
                    for (Niveau niveau : niveaux) {
    %>
    <tr>
        <td> <%= niveau.getLibelle()%></td>
        <%
        if (niveau.getUtilisations() == 0) {
        %>
        <td class="centered">
            <a href="<%= request.getContextPath()%>/admin/niveaux/editer.html?id=<%= niveau.getIdNiveau()%>">Modifier
                <img src="<%= request.getContextPath()%>/img/edit_16.png" alt="Modifier le libellé du niveau" />
            </a>
        </td>
        <td class="centered">
            <%
               if (niveau.estActif()) {
                   out.println("<span class='bon'>Actif</span>");
               } else {
                   out.println("<span class='mauvais'>Inactif</span>");
               }
            %>
        </td>
        <td class="centered">
            <form method="post" action="<%= request.getContextPath()%>/admin/niveaux/controle.html">
                <input type="hidden" name="id" value="<%= niveau.getIdNiveau()%>" />
                <% if (niveau.estActif()) {%>
                <input type="submit" value="Désactiver" /> <input type="hidden" name="est_actif" value="false" />
                <% } else {%>
                <input type="submit" value="Activer" /> <input type="hidden" name="est_actif" value="true" />
                <% }%>
            </form>
        </td>
        <% } else {%>
        <td class="centered" colspan="3"><small>Ce niveau ne peut pas être modifié ou supprimé.</small></td>
        <% }%>
    </tr>
    <%
                    }
    %>
</table>
<%
            }
%>
