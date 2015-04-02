package qcm.services;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import qcm.exceptions.UnauthorizedActionException;
import qcm.models.Question;
import qcm.models.Questionnaire;
import qcm.models.QuestionnairePasse;
import qcm.models.User;
import qcm.persistences.NiveauDAO;
import qcm.persistences.QuestionDAO;
import qcm.persistences.QuestionnairePasseDAO;
import qcm.persistences.ThemeDAO;
import qcm.persistences.UserDAO;

/**
 * Ensemble de méthodes qui sont réguli√®rement appelées dans l'application
 * @author Ingesup
 */
public class ActionHelper {

    public static void setAttributeThemes(HttpServletRequest request) throws SQLException {
        request.setAttribute("themes", ThemeDAO.getAllActif());
    }


    public static void setAttributeTheme(int theme , HttpServletRequest request) throws SQLException {
        request.setAttribute("theme", ThemeDAO.getById(theme));
    }

    public static void setAttributeNiveaux(HttpServletRequest request) throws SQLException {
        request.setAttribute("niveaux", NiveauDAO.getAllActif());
    }

    public static void setAttributeNiveau(int niveau , HttpServletRequest request) throws SQLException {
        request.setAttribute("niveau", NiveauDAO.getById(niveau));
    }

    public static boolean userHasAlreadyPassedQuestionnaire(int idUser, Questionnaire q) throws SQLException {
        return getQuestionnairesPasseByUser(idUser).contains(new QuestionnairePasse(q.getIdQuestionnaire(), idUser));
    }

    public static int getIdUser(HttpServletRequest request) {
        return ((User) request.getSession().getAttribute("user")).getIdUser();
    }

    public static List<QuestionnairePasse> getQuestionnairesPasseByUser(int idUser) throws SQLException {
        return QuestionnairePasseDAO.getByUser(idUser);
    }

    public static boolean userHasRoleToAccessRequest(final String role, final HttpServletRequest request) {
        return ((User) request.getSession().getAttribute("user")).getStatut().getLibelle().equals(role);
    }

    public static User checkUserByLoginAndPassWord(String login, String passw) throws SQLException {
        return UserDAO.getByLoginAndPassword(login, passw);
    }

    public static void setAttributeQuestionsByThemeNewQuestionnaire(int idThemeQuestionnaire , HttpServletRequest request) throws Exception{
        List<Question> questions = QuestionDAO.getByTheme(idThemeQuestionnaire);
        if (questions.isEmpty()) {
            throw new UnauthorizedActionException("Th√®me inconnu");
        }
        request.getSession().setAttribute("questionsByThemeNewQuestionnaire", QuestionDAO.getByTheme(idThemeQuestionnaire));
    }
}
