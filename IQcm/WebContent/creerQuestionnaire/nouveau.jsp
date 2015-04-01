<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="qcm.models.*" %>
<%@page import="java.util.List" %>

<%
List<Question> questionsByThemeNewQuestionnaire = (List<Question>) request.getSession().getAttribute("questionsByThemeNewQuestionnaire");
Questionnaire newQuestionnaire = (Questionnaire) request.getSession().getAttribute("newQuestionnaire");
%>
<fieldset id="modifier_reponses" class="margleft">
    <p class="margleft brownBold margbott2">Questions du questionnaire</p>
    <ul class="questions">
        <%
	       for (Question question : newQuestionnaire.getQuestions()) {
	           out.println("<li>" + question.getLibelle() + "</li>");
	       }
        %>
    </ul>
</fieldset>

<div class="panel_left margleft">
    <%
     if (newQuestionnaire != null) {
         out.print("<h4 class='margbott2'>Nouveau questionnaire &laquo; " + newQuestionnaire.getLibelle() + " &raquo; : ajout d'une question</h4>");
	%>

    <div id="reponses">
        <form id="question_0" class="question_a_ajouter" action="<%= request.getContextPath()%>/creerQuestionnaire/ajouterReponses.html" method="post">
            <table class="table table-hover">
                <tr>
                    <td><label for="libelleQuestion">Libellé de la question</label></td>
                    <td><textarea class="form-control" id="libelleQuestion" name="libelleQuestion" cols="50" rows="5"></textarea></td>
                </tr>
                <tr>
                    <td><label for="nbReponses">Nombre de réponses à cette question</label></td>
                    <td><input class="form-control" type="text" id="nbReponses" name="nbReponses" size="3" class="medium-input" /></td>
                </tr>
            </table>
            <input type='hidden' name='action' value='applyToAddNewQuestion' />
            <input class='btn btn-sm btn-success margbott' type='submit' value='Ajouter les réponses' />
        </form>
        <%                           
         }
        %>
    </div>
</div>
<%
if (newQuestionnaire.getQuestions().size() > 0) {
%>
	<div id="terminer" class="clear">
	    <a  class='btn btn-sm btn-warning margleft' href="enregistrement.html">Enregistrer tout</a>
	</div>
<%  }
%>
