<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
            int nbReponses = Integer.parseInt(request.getAttribute("nbReponses").toString());
            String libelleQuestion = (String) request.getAttribute("libelleQuestion");
%>
<h4 class="margleft brownBold margbott2">Ajouter les réponses</h4>
<form class="margleft" action="<%= request.getContextPath()%>/creerQuestionnaire/creerQuestion.html" method="post">
    <table>
        <tr>
            <td>
                <em>Rappel de la question : </em>
            </td>
            <td>
                <textarea class="form-control margleft" id="libelleQuestion" name="libelleQuestion" cols="69" rows="5"><%= libelleQuestion%></textarea>
            </td>
        </tr>
    </table>

    <table id="question_a_ajouter" class="table table-bordered table-hover margtop">
        <tr>
            <th>Numéro</th>
            <th>Réponse</th>
            <th>Note</th>
            <th>Réponse correcte</th>
        </tr>
        <%
                    for (int i = 1; i <= nbReponses; i++) {
                        out.println("<tr>");
                        out.println("<td class='centered'><strong>" + i + "</strong></td>");
                        out.println("<td><input class='form-control' type='text' value='' name='libelleReponse_" + i + "' class='medium-input' /></td>");
                        out.println("<td><input class='form-control' type='text' value='' name='noteReponse_" + i + "' class='medium-input' size='4' /></td>");
                        out.println("<td class='centered'><input type='checkbox' value='' name='estCorrecteReponse_" + i + "' class='medium-input' /></td>");
                        out.println("</tr>");
                    }
        %>
    </table>
    <input type="hidden" name="nbReponses" value="<%= nbReponses%>" />
    <input class='btn btn-sm btn-success' type="submit" name="action" value="Ajouter ces réponses" class="button" />
</form>
