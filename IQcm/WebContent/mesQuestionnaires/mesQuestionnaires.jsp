<%@page import="java.util.Map" %>
<h4>Mes questionnaires créés</h4>
<%
        Map<Integer, String> questionnaires = (Map<Integer, String>) request.getAttribute("mapQuestionnaires");
        if (questionnaires != null && !questionnaires.isEmpty()) {
            out.println("<table class='format'>");
            out.println("<tr>");
            out.println("<th>Name</th>");
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