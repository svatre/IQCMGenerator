<%@page import="qcm.models.Questionnaire" %>
<%@page import="qcm.models.Theme" %>
<%@page import="qcm.models.Niveau" %>

<div class="warning-qcm">
<%
    Questionnaire questionnaire = (Questionnaire) request.getAttribute("questionnaire");
    if (questionnaire != null) {
%>
    <div class="recapitule_questionnaire liste margleft">
        <h4 class="margleft brownBold margbott2">Récapitulatif du questionnaire</h4>
        <table class="table table-bordered table-hover">
            <tr>
                <td class="static">Titre</td>
                <td><%=questionnaire.getLibelle()%></td>
            </tr>
            <tr>
                <td class="static">Thème</td>
                <td><%= ((Theme) request.getAttribute("theme")).getLibelle() %></td>
            </tr>
            <tr>
                <td class="static">Niveau</td>
                <td><%= ( (Niveau) request.getAttribute("niveau")).getLibelle() %></td>
            </tr>
            <tr>
                <td class="static">Nombre de questions</td>
                <td><%=questionnaire.getQuestions().size()%></td>
            </tr>
            <%-- <tr>
                <td class="static">Temps accordé</td>
                <td><%=questionnaire.getLimiteTemps()%> minutes</td>
            </tr> --%>
        </table>
    </div>
    <%
                }
    %>


    <a class="margleft" href="<%= request.getContextPath() %>/passerQuestionnaire/index.html" class="button">Retour à la liste des questionnaires</a>

    <%
                if (request.getAttribute("userHasAlreadyPassedQuestionnaire") == null) {
    %>

    <form action="<%= request.getContextPath() %>/passerQuestionnaire/commencer.html" method="post" id="commencer_qcm_form">
        <input type="hidden" name="questionnaire" value="<%= questionnaire.getIdQuestionnaire()%>" />
        <input class="btn btn-sm btn-primary margleft margtop" type="submit" value="Commencer" class="button" />
    </form>
    <%
                } else {
                    out.println("<p class='error' style='display:inline'>Vous avez déjà passé ce questionnaire</p>");
                }
    %>

</div>
              