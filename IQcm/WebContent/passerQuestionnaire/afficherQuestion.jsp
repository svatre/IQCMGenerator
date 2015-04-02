<%@page import="java.util.Map" %>
<%@page import="java.util.List" %>
<%@page import="qcm.models.*" %>
<%
    Question questionCourante = (Question) request.getAttribute("questionCourante");
    final Qcm qcm = (Qcm) request.getSession().getAttribute("qcm");
    if(!qcm.estFini()){
%>
<fieldset id="modifier_reponses" class="">
    <legend>Liste des questions</legend>
    <ul class="questions">
        <%
                    List<Question> questions = qcm.getQuestionnaire().getQuestions();
                    if (questions != null) {
                        for (Question question : questions) {
                            if (questionCourante != null && questionCourante.getIdQuestion() == question.getIdQuestion()) {
                                out.println("<li><strong>" + question.getLibelle() + "</strong></li>");
                            } else {
                                out.println("<li>" + question.getLibelle() + "</li>");
                            }
                        }
                    }
        %>
    </ul>
</fieldset>
<%
    String titre_questionnaire = qcm.getQuestionnaire().getLibelle();
    if (questionCourante != null) {
%>


<form class="question" action="<%= request.getContextPath() %>/passerQuestionnaire/questionSuivante.html" method="post" accept-charset="utf-8">
    <fieldset id="titre_questionnaire">
        <legend><strong>Questionnaire : <%= titre_questionnaire%></strong></legend>
        <p>
            <%= questionCourante.getLibelle()%>
        </p>
    </fieldset>
    <div id="reponses">
<%
        List<Reponse> reponses = questionCourante.getReponses();
        if (reponses != null) {
            for (Reponse reponse : reponses) {
                out.println("<input type='checkbox' name='reponses' value='" + reponse.getIdReponse() + "' ");
                if (qcm.getUserReponses().get(reponse.getIdQuestion()).contains(reponse.getIdReponse())) {
                    out.print("checked='checked' ");
                }
                out.print("id='" + reponse.getIdReponse() + "' /><label for='" + reponse.getIdReponse() + "'>" + reponse.getLibelle() + "</label><br />");
            }
        }
%>
    </div>
    <p><input type="submit" value="Valider la question" /></p>
    <input type="hidden" name="idQuestion" value="<%= questionCourante.getIdQuestion()%>"/>
</form>
<div id="temps_restant">
    <form action="<%= request.getContextPath() %>/passerQuestionnaire/terminer.html" method="post" accept-charset="utf-8">
        <input class="button" type="submit" value="Terminer maintenant &rarr;" />
    </form>
</div>
<%
       } else if (request.getAttribute("estFini") != null) {
%>
<div class="question">
    <fieldset id="titre_questionnaire">
        <legend><strong>Questionnaire fini : <%= titre_questionnaire%></strong></legend>
        <p>

        </p>
        <ul id="questionnaire_fini" class="liste">
            <%
                                                if (questions != null) {
                                                    for (Question question : questions) {
            %>
            <li onclick="document.getElementById('modifyQuestion').value='<%= question.getIdQuestion()%>'; document.getElementById('applyModif').submit();"><%= question.getLibelle()%></li>
            <%
                                                    }
                                                }
            %>
        </ul>
    </fieldset>
    <div>
            <form action="<%= request.getContextPath() %>/passerQuestionnaire/terminer.html" method="post" accept-charset="utf-8">
                <input class="button" type="submit" value="Terminer maintenant &rarr;" />
            </form>
            <form action="<%= request.getContextPath() %>/passerQuestionnaire/modifierReponses.html" method="post" id="applyModif">
                <input type="hidden" name="modifyQuestion" id="modifyQuestion" value="" />
            </form>
    </div>
</div>
<%
        } else {
            out.println("Erreur");
        }
    }
%>
               