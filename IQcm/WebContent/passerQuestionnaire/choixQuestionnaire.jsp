<%@page import="qcm.models.Theme" %>
<%@page import="qcm.models.Niveau" %>
<%@page import="java.util.Map" %>
<%@page import="java.util.List" %>
<%
            Integer theme = (Integer) request.getAttribute("theme");
            Integer niveau = (Integer) request.getAttribute("niveau");
%>


    <form id="choix_questionnaire_form" action="<%= request.getContextPath() %>/passerQuestionnaire/listeQuestionnaires.html" method="post" accept-charset="utf-8">

        <table class="table table-bordered table-hover margleft margtop2">
            <tr>
                <td class="static"><label for="theme">Choisissez le thème </label></td>
                <td>
                    <select name="theme" id="theme" class="form-control" onchange="document.getElementById('choix_questionnaire_form').submit();">
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
                    <select name="niveau" id="niveau" class="form-control" onchange="document.getElementById('choix_questionnaire_form').submit();">
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

<h4 id="liste_questionnaires" class="margleft brownBold margbott2">Liste des questionnaires</h4>
<%
            Map<Integer, String> questionnaires = (Map) request.getAttribute("questionnaires");
            if (questionnaires != null) {
                out.println("<p class='margleft'>Cliquez sur un questionnaire pour le commencer.</p>");
                out.println("<ol class='liste margleft'>");
                for (Integer idQuestionnaire : questionnaires.keySet()) {
                    out.println("<a href='"+request.getContextPath()+"/passerQuestionnaire/choix.html?questionnaire=" + idQuestionnaire + "'><li>" + questionnaires.get(idQuestionnaire) + "</li></a>");
                }
                out.println("</ol>");
            } else {
                out.println("<p class='margleft'>Choisissez un thème et/ou un niveau</p>");
            }
%>
