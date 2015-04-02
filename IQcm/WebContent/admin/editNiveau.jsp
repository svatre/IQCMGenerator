

<%@page import="qcm.models.Niveau"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h4>Modifier le niveau</h4>

<%
            Niveau niveau = (Niveau) request.getAttribute("niveau");
%>
<div class="choice">
    <p>Appliquez les modifications nécessaires sur le libellé du niveau :</p>
    <form action="<%= request.getContextPath()%>/admin/niveaux/editer.html" method="post">
        <table>
            <tr>
                <td class="static"><label for="libelle">Libellé : </label></td>
                <td><input type="text" name="libelle" id="libelle" value="<%= niveau.getLibelle()%>" size="50" class="medium-input" /></td>
            </tr>
        </table>
        <input type="hidden" name="id" value="<%= niveau.getIdNiveau()%>" />
        <input type="submit" value="Enregistrer" class="button" />
        <a href="<%= request.getContextPath()%>/admin/themes/index.html" class="button">Annuler</a>
    </form>
</div>
