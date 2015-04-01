

<%@page import="qcm.models.Questionnaire"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h4 class="margleft brownBold">Gérer les questionnaires</h4>
<jsp:include page="/scripts/errorViewHelper.jsp" />

<%
            HashMap<Integer, Questionnaire> questionnaires = (HashMap) request.getAttribute("questionnaires");
            if (questionnaires != null) {
%>
<table class="table table-bordered table-hover">
    <thead>
        <tr>
	        <th>Libellé</th>
	        <th>Modifier</th>
	        <th>Activité</th>
	        <th>Contrôle</th>
        </tr>
    </thead>
    <%
                    for (Integer idQuestionnaire : questionnaires.keySet()) {
                        Questionnaire questionnaire = questionnaires.get(idQuestionnaire);
    %>
    <tbody>
	    <tr>
	        <td><%= questionnaire.getLibelle()%></td>
	        <td class="centered">
	            <%
	                                                        if (questionnaire.estPasse()) {
	                                                            out.println("Oui");
	                                                        } else {
	                                                            out.println("Non");
	                                                        }
	            %>
	        </td>
	        <td class="centered">
	            <%
	                                                        if (questionnaire.estActif()) {
	                                                            out.println("<span class='bon'>Actif</span>");
	                                                        } else {
	                                                            out.println("<span class='mauvais'>Inactif</span>");
	                                                        }
	            %>
	        </td>
	        <td class="centered">
	            <form method="post" action="<%= request.getContextPath()%>/admin/questionnaires/controle.html">
	                <input type="hidden" name="id" value="<%= questionnaire.getIdQuestionnaire()%>" />
	                <% if (questionnaire.estActif()) {%>
	                <input class="btn btn-sm btn-warning" type="submit" value="Désactiver" /> <input type="hidden" name="est_actif" value="false" />
	                <% } else {%>
	                <input class="btn btn-sm btn-success" type="submit" value="Activer" /> <input type="hidden" name="est_actif" value="true" />
	                <% }%>
	            </form>
	        </td>
	    </tr>
	</tbody>
    <%
                    }
    %>
</table>
<%
            }
%>
