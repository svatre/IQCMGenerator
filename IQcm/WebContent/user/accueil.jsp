<%
/**
 * Contenu d'accueil
 */
%>

<%@page import="qcm.models.User" %>
<%
    User user = (User) request.getSession().getAttribute("user");
%>
<h4>Accueil de Qcm Generator</h4>

<div id="statistiques">
    <p>Bienvenue sur l'application QCM Generator<br/>

</div>
