<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="qcm.models.QuestionnairePasse" %>

<h4>Mes questionnaires passés</h4>
<%
    List<QuestionnairePasse> questionnairesPasses = (List<QuestionnairePasse>) request.getAttribute("questionnairesPasses");
    if (questionnairesPasses != null && !questionnairesPasses.isEmpty()) {

        out.println("<table class='format'>");
%>
<tr>
    <th>Date</th>
    <th>Intitulé</th>
    <th>Note</th>
</tr>

<%
     for (QuestionnairePasse qP : questionnairesPasses) {
%>
<tr>
    <td class="centered"><%= qP.getDate()%></td>
    <td class="centered"><%= qP.getLibelleQuestionnaire()%></td>
    <td class="centered"><%= qP.getNote()%></td>
</tr>
<%
    }
    out.println("</table>");
} else {
    out.println("Vous n'avez encore passé aucun questionnaire.");
}
%>
                