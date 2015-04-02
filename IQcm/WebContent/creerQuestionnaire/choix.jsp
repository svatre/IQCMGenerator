<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="qcm.models.Niveau" %>
<%@page import="qcm.models.Theme" %>

<div class="choice">
    <p>Veuillez sélectionner le thème et le niveau du questionnaire que vous souhaitez ajouter dans la base de données. </p>
    <form id="choix_questionnaire_form" action="<%= request.getContextPath()%>/creerQuestionnaire/nouveau.html" method="post" accept-charset="utf-8">
        <table>
            <tr>
                <td class="static"><label for="theme">Choisissez le thème : </label></td>
                <td>
                    <select name="theme" id="theme" class="medium-input">
                        <option></option>
                        <%
                                    List<Theme> themes = (List<Theme>) request.getAttribute("themes");
                                    if (themes != null) {
                                        for (Theme leTheme : themes) {
                                            out.println("<option value='" + leTheme.getIdTheme() + "'");
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
                    <select name="niveau" id="niveau" class="medium-input">
                        <option></option>
                        <%
                                    List<Niveau> niveaux = (List<Niveau>) request.getAttribute("niveaux");
                                    if (niveaux != null) {
                                        for (Niveau leNiveau : niveaux) {
                                            out.println("<option value='" + leNiveau.getIdNiveau() + "'");
                                            out.print(">" + leNiveau.getLibelle() + "</option>");
                                        }
                                    }

                        %>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="static"><label for="nom_questionnaire">Entrez le nom du questionnaire : </label></td>
                <td>
                    <input type="text" name="libelle" value="" id="nom_questionnaire" class="medium-input" />
                </td>
            </tr>
        </table>
        <input type="hidden" name="action" value="createQuestionnaire" />
        <input type="submit" value="Commencer" class="button" />
    </form>
</div>
<div class="line"></div>
