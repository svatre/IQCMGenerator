<%@page import="qcm.models.Theme" %>
<%@page import="qcm.models.Niveau" %>
<%@page import="java.util.Map" %>
<%@page import="java.util.List" %>
<%
            Integer theme = (Integer) request.getAttribute("theme");
            Integer niveau = (Integer) request.getAttribute("niveau");
%>

<div class="choice">
    <form id="choix_questionnaire_form" action="<%= request.getContextPath() %>/passerQuestionnaire/listeQuestionnaires.html" method="post" accept-charset="utf-8">

        <table class="no-border">
            <tr>
                <td class="static"><label for="theme">Choisissez le th�me : </label></td>
                <td>
                    <select name="theme" id="theme" class="medium-input" onchange="document.getElementById('choix_questionnaire_form').submit();">
                        <option value="0"></option>
                        <%
                                    List<Theme> themes = (List<Theme>) request.getAttribute("themes");
                                    if (themes != null) {
                                        for (Theme leTheme : themes) {
                                            out.println("<option value='" + leTheme.getIdTheme() + "'");
                                            if (theme != null && leTheme.getIdTheme() == theme) {
                                                out.print(" selected='selected' ");
                                            }
                                            out.print(">" + leTheme.getLibelle() + "</option>");
                                        }
                                    }
                        %>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="static"><label for="niveau">Choisissez le niveau : </label></td>
                <td>
                    <select name="niveau" id="niveau" class="medium-input" onchange="document.getElementById('choix_questionnaire_form').submit();">
                        <option value="0"></option>
                        <%
                                    List<Niveau> niveaux = (List<Niveau>) request.getAttribute("niveaux");
                                    if (niveaux != null) {
                                        for (Niveau leNiveau : niveaux) {
                                            out.println("<option value='" + leNiveau.getIdNiveau() + "'");
                                            if (niveau!=null && leNiveau.getIdNiveau() == niveau) {
                                                out.print(" selected='selected' ");
                                            }
                                            out.print(">" + leNiveau.getLibelle() + "</option>");
                                        }
                                    }
                        %>
                    </select>
                </td>
            </tr>
        </table>

        <input type="hidden" name="action" value="choixQuestionnaire"/>
    </form>
</div>
<div class="line"></div>

<h4 id="liste_questionnaires">Liste des questionnaires</h4>
<%
            Map<Integer, String> questionnaires = (Map) request.getAttribute("questionnaires");
            if (questionnaires != null) {
                out.println("<p>Cliquez sur un questionnaire pour le commencer.</p>");
                out.println("<ol class='liste'>");
                for (Integer idQuestionnaire : questionnaires.keySet()) {
                    out.println("<a href='"+request.getContextPath()+"/passerQuestionnaire/choix.html?questionnaire=" + idQuestionnaire + "'><li>" + questionnaires.get(idQuestionnaire) + "</li></a>");
                }
                out.println("</ol>");
            } else {
                out.println("<p>Choisissez un th�me et/ou un niveau</p>");
            }
%>
