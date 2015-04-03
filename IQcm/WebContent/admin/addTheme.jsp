

<%@page import="qcm.models.Statut"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h4 class="margleft brownBold margbott2">Ajouter un thème</h4>
<jsp:include page="/scripts/errorViewHelper.jsp" />

<%
            List<Statut> statuts = (List<Statut>) request.getAttribute("statuts");
            Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");
%>

<div  class="choice">
    <form action="<%= request.getContextPath()%>/admin/themes/ajouter.html" method="post">
        <table class="table table-hover">
            <tr>
                <td><label for="libelle"><strong>Libellé : </strong></label></td>
                <td><input type="text" name="libelle" id="libelle" size="50" class="form-control
                           <% if ((errors != null) && errors.get("login").isEmpty()) {
                                           out.print("error-input");
                                       }
                           %>
                           " value='<%= errors == null ? "" : errors.get("libelle")%>' /></td>
            </tr>
        </table>
        <input class=" btn btn-sm btn-success" type="submit" value="Enregistrer" />
        <a class="btn btn-xs btn-danger" href="<%= request.getContextPath()%>/admin/Themes/index.html" class="button">Annuler</a>
    </form>
</div>
