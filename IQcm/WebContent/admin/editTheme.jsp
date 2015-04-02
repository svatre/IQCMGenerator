

<%@page import="qcm.models.Theme"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h4>Modifier le niveau</h4>
<%--<jsp:include page="scripts/errorViewHelper.jsp" />--%>
<%
            Theme theme = (Theme) request.getAttribute("theme");
%>
<p>Appliquez les modifications nécessaires sur le libellé du thème :</p>
<div  class="choice">
    <form action="<%= request.getContextPath()%>/admin/themes/editer.html" method="post">
        <table>
            <tr>
                <td><label for="libelle"><strong>Libellé : </strong></label></td>
                <td><input type="text" name="libelle" id="libelle" value="<%= theme.getLibelle()%>" size="50" class="medium-input" /></td>
            </tr>
        </table>
        <input type="hidden" name="id" value="<%= theme.getIdTheme()%>" />
        <input type="submit" value="Enregistrer" class="button" />
        <a href="<%= request.getContextPath()%>/admin/themes/index.html" class="button">Annuler</a>
    </form>
</div>
