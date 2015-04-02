package qcm.services;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import qcm.persistences.UserDAO;

public class AdminActionHelper {

    public static void setAttributeUser(int idUser, HttpServletRequest request) throws SQLException {
        request.setAttribute("user", UserDAO.getById(idUser));
    }

    public static boolean isEmail(String email, HttpServletRequest request) {
        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
        Matcher m = p.matcher(email.toUpperCase());
        boolean ok = m.matches();
        if (!ok) {
            request.setAttribute("emailError", "L'email saisi n'était pas correct");
        }
        return ok;
    }
}
