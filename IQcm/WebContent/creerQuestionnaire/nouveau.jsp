<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="qcm.models.*" %>
<%@page import="java.util.List" %>

<%
List<Question> questionsByThemeNewQuestionnaire = (List<Question>) request.getSession().getAttribute("questionsByThemeNewQuestionnaire");
Questionnaire newQuestionnaire = (Questionnaire) request.getSession().getAttribute("newQuestionnaire");
%>
<fieldset id="modifier_reponses" class="">
    <legend>Questions du questionnaire</legend>
    <ul class="questions">
        <%
	       for (Question question : newQuestionnaire.getQuestions()) {
	           out.println("<li>" + question.getLibelle() + "</li>");
	       }
        %>
    </ul>
</fieldset>

<div class="panel_left">
    <%
     if (newQuestionnaire != null) {
         out.print("<h1>Nouveau questionnaire : &laquo; " + newQuestionnaire.getLibelle() + " &raquo;</h1><br/><br/>");
	%>

    <label for="question">Ajouter des questions dans votre questionnaire :</label>
    <br/><br/>

    <div id="reponses">
        <form id="question_0" class="question_a_ajouter" action="<%= request.getContextPath()%>/creerQuestionnaire/ajouterReponses.html" method="post">
            <table>
                <tr>
                    <td colspan="2"><label for="libelleQuestion">Libellé de la question : </label></td>
                </tr>
                <tr>
                    <td colspan="2"><textarea id="libelleQuestion" name="libelleQuestion" cols="50" rows="5"></textarea></td>
                </tr>
                <tr>
                    <td><label for="nbReponses">Nombre de réponses à cette question</label></td>
                    <td><input type="text" id="nbReponses" name="nbReponses" size="3" class="medium-input" /></td>
                </tr>
            </table>
            <input type='hidden' name='action' value='applyToAddNewQuestion' />
            <input class='button' type='submit' value='Ajouter les réponses' />
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
	    <a class="button" href="testProject/creerQuestionnaire/enregistrement.html">Enregistrer tout</a>
	</div>
<%  }
%>
