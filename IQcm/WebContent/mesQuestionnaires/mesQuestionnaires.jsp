<%@page import="java.util.Map" %>
<h4 class="margleft brownBold margbott2">Mes questionnaires créés</h4>
<%
        Map<Integer, String> questionnaires = (Map<Integer, String>) request.getAttribute("mapQuestionnaires");
        if (questionnaires != null && !questionnaires.isEmpty()) {
            out.println("<table class='table table-bordered table-hover margleft'>");
            out.println("<tr>");
            out.println("<th>Nom</th>");
            out.println("</tr>");
            for (Integer i : questionnaires.keySet()) {
		%>
		<tr>
		    <td>
				<%= questionnaires.get(i)%>
		    </td>
		</tr>
			<%
            }
            out.println("</table>");
        }
		%>