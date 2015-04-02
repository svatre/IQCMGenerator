<%
            /**
             * Fragment de page qui affiche les erreurs ou les messages destinés à l'utilisateur
             */
            String message = (String) request.getAttribute("message");
            if (message != null) {
                out.println("<div id='flash-message'>");
                out.println(message);
                out.println("</div>");
            }
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
                out.println("<div id='error-message' class='error'>");
                out.println(errorMessage);
                out.println("</div>");
            }
%>