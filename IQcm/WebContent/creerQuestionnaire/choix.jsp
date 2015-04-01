<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="qcm.models.Niveau" %>
<%@page import="qcm.models.Theme" %>

    <p class="margleft brownBold margbott2">Ajouter un questionnaire</p>
    <form id="choix_questionnaire_form" action="<%= request.getContextPath()%>/creerQuestionnaire/nouveau.html" method="post" accept-charset="utf-8">
        <table class="table table-hover margleft">
            <tr>
                <td class="static"><label for="theme">Choisissez le thème</label></td>
                <td>
                    <select name="theme" id="theme" class="form-control">
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
                <td class="static"><label for="niveau">Choisissez le niveau</label></td>
                <td>
                    <select name="niveau" id="niveau" class="form-control">
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
                    <input type="text" name="libelle" value="" id="nom_questionnaire" class="form-control" />
                </td>
            </tr>
        </table>
        <input type="hidden" name="action" value="createQuestionnaire" />
        <input class="btn btn-sm btn-primary margleft" type="submit" value="Commencer" class="button" />
    </form>
