<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="qcm.models.*" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Map" %>

<%
        Qcm qcm = (Qcm) request.getSession().getAttribute("qcm");
        int questionRepondues = 0;
        int nbQuestionsTotal = qcm.getQuestionnaire().getQuestions().size();
        int noteMax = qcm.getQuestionnaire().getNoteMax();
        Integer note = (Integer) request.getAttribute("note");

        List<Question> questions = qcm.getQuestionnaire().getQuestions();
        for (Question question : questions) {
            List<Integer> userRep = qcm.getUserReponses().get(question.getIdQuestion());
            if (!userRep.isEmpty()) {
                questionRepondues++;
            }
        }

%>
<h4 id="liste">Résultats pour ce questionnaire</h4>
<div id="score">
    <p>Votre note : <br /><span class="score_value <%= (note < noteMax / 2) ? "mauvais" : "bon"%>"><%= note%> / <%= noteMax%></span></p>
</div>
<a class="button" href="<%= request.getContextPath() %>/index.html">Retour à l'accueil</a>
               