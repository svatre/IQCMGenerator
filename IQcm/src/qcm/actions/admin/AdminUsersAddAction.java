package qcm.actions.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import qcm.actions.AdminAction;
import qcm.models.Statut;
import qcm.models.User;
import qcm.persistences.StatutDAO;
import qcm.persistences.UserDAO;

public class AdminUsersAddAction extends AdminAction {

    @Override
    public void execute() throws Exception {
        List<Statut> statuts = StatutDAO.getAll();
        if (request.getMethod().equals("GET")) {
            request.setAttribute("statuts", statuts);
            setView("/admin/addUser.jsp");
            
        } else if (request.getMethod().equals("POST")) {
            boolean ok = true;
            Map<String, String> errors = new HashMap();
            String errorMessage = "<ul class='clean'>";
            
            String login = request.getParameter("login").trim();
            String email = request.getParameter("email").trim();
            String password = request.getParameter("password").trim();
            String passwordVerification = request.getParameter("password2").trim();
            String nom = request.getParameter("nom").trim();
            String prenom = request.getParameter("prenom").trim();
            int idStatut = Integer.parseInt(request.getParameter("statut"));
            Statut statut = StatutDAO.getById(idStatut);

            errors.put("login", login);
            errors.put("email", email);
            errors.put("password", password);
            errors.put("passwordVerification", passwordVerification);
            errors.put("nom", nom);
            errors.put("prenom", prenom);
            errors.put("statut", String.valueOf(idStatut));

            if (statut == null) {
                ok = false;
                errorMessage += "<li>La valeur du statut est incorrecte</li>";
                errors.put("statut", "");
            }
            if (login.isEmpty()) {
                ok = false;
                errorMessage += "<li>Le login ne doit pas être vide</li>";
                errors.put("login", "");
            }
            if (email.isEmpty()) {
                ok = false;
                errorMessage += "<li>L'email ne doit pas être vide</li>";
                errors.put("email", "");
            }
            if (nom.isEmpty()) {
                ok = false;
                errorMessage += "<li>Le nom ne doit pas être vide</li>";
                errors.put("nom", "");
            }
            if (prenom.isEmpty()) {
                ok = false;
                errorMessage += "<li>Le prénom ne doit pas être vide</li>";
                errors.put("prenom", "");
            }
            if (password.isEmpty()) {
                ok = false;
                errorMessage += "<li>Le mot de passe ne doit pas être vide</li>";
                errors.put("password", "");
            }
            if (!password.equals(passwordVerification)) {
                ok = false;
                errorMessage += "<li>Les mots de passe doivent être identiques</li>";
                errors.put("password", "");
                errors.put("passwordVerification", "");
            }
            if (!email.matches("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$")) {
                ok = false;
                errorMessage += "<li>L'email n'est pas valide</li>";
                errors.put("email", "");
            }
            errorMessage += "</ul>";
            
            if (!ok) {
                request.setAttribute("errorMessage", errorMessage);
                request.setAttribute("errors", errors);
                request.setAttribute("statuts", statuts);
                setView("/admin/addUser.jsp");
            } else {
                User user = new User(null, login, password, email, nom, prenom, statut, true);

                UserDAO.insert(user);
                request.setAttribute("message", "L'utilisateur a été ajouté");
                HashMap<Integer, User> users = UserDAO.getAll();
                request.setAttribute("users", users);
                setView("/admin/gererUtilisateurs.jsp");
            }
        }
    }
}

