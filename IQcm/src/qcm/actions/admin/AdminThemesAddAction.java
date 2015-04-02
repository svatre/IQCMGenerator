package qcm.actions.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import qcm.actions.AdminAction;
import qcm.models.Statut;
import qcm.models.Theme;
import qcm.models.User;
import qcm.persistences.StatutDAO;
import qcm.persistences.ThemeDAO;
import qcm.persistences.UserDAO;

public class AdminThemesAddAction extends AdminAction {

    @Override
    public void execute() throws Exception {
        List<Statut> statuts = StatutDAO.getAll();
        if (request.getMethod().equals("GET")) {
            setView("/admin/addTheme.jsp");
            
        } else if (request.getMethod().equals("POST")) {
            boolean ok = true;
            Map<String, String> errors = new HashMap();
            String errorMessage = "<ul class='clean'>";
            
            String libelle = request.getParameter("libelle").trim();

            errors.put("libelle", libelle);

            if (libelle.isEmpty()) {
                ok = false;
                errorMessage += "<li>Le Libelle ne doit pas être vide</li>";
                errors.put("login", "");
            }
          
            errorMessage += "</ul>";
            
            if (!ok) {
                request.setAttribute("errorMessage", errorMessage);
                request.setAttribute("errors", errors);
                request.setAttribute("statuts", statuts);
                setView("/admin/addUser.jsp");
            } else {
                Theme theme = new Theme(null, 1, libelle, 0, true);
                ThemeDAO.insert(theme);
                request.setAttribute("message", "Le thème a été ajouté");
                List<Theme> themes = ThemeDAO.getAll();
                request.setAttribute("themes", themes);
                setView("/admin/gererThemes.jsp");
            }
        }
    }
}

