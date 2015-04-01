<%
/**
 * Contenu d'accueil
 */
%>

<%@page import="qcm.models.User" %>
<%
    User user = (User) request.getSession().getAttribute("user");
%>

<div id="statistiques" class="margtop">
    <p class="margleft">Bienvenue sur l'application QCM Generator<br/>

</div>
