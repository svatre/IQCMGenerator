<%@page contentType="text/html" pageEncoding="UTF-8"%>
                <form id="connexion_form" action="<%= request.getContextPath() %>/index.html" method="post" accept-charset="utf-8">
                    <fieldset id="connexion" class="">
                        <legend>Connexion</legend>
                        <%
                            if(request.getAttribute("errorMessage")!=null) {
                                out.println("<p class='error'>"+request.getAttribute("errorMessage").toString()+"</p>");
                            }
                        %>
                        <table class="borderless">
                            <tr><td><label for="identifiant">Identifiant</label></td><td><input class="form-control" type="text" name="login" value="<% if(request.getParameter("login")!=null) out.println(request.getParameter("login").toString());%>" id="login" size="27" /></td></tr>
                            <tr><td><label for="mot_de_passe">Mot de passe</label></td><td><input class="form-control" type="password" name="password" value="<% if(request.getParameter("password")!=null) out.println(request.getParameter("password").toString());%>" id="mot_de_passe" size="27" /></td></tr>

                            <tr><td></td><td><input class="btn btn-primary" type="submit" value="Connexion" /></td></tr>
                        </table>

                    </fieldset>
                    <input type="hidden" name="action" value="authentification" />
                </form>
