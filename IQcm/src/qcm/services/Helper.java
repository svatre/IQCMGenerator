package qcm.services;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import qcm.models.Qcm;
import qcm.models.Questionnaire;
import qcm.persistences.QcmDAO;
import qcm.persistences.QuestionnaireDAO;
import qcm.persistences.ReponseDAO;

/**
 * Ensemble de méthodes qui sont appelées souvent
 * @author Ingesup
 */
public class Helper {

    public static void save(Qcm qcm) throws SQLException {
        QcmDAO.insert(qcm);
    }

    public static void save(Questionnaire questionnaire) throws SQLException {
        assert QuestionnaireDAO.search(questionnaire.getIdTheme(), questionnaire.getIdNiveau(), questionnaire.getLibelle()) == null;
        if (questionnaire.getIdQuestionnaire() == null) {
            QuestionnaireDAO.insert(questionnaire);
        } else {
            QuestionnaireDAO.update(questionnaire);
        }
    }

    public static Questionnaire getQuestionnaireById(int id) {
        Questionnaire quest = null;
        try {
            quest = QuestionnaireDAO.getById(id);
        } catch (SQLException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return quest;
    }

    public static int getNoteByReponse(int idReponse) {
        int note = 0;
        try {
            note = ReponseDAO.getById(idReponse).getNote();
        } catch (SQLException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return note;
    }
}
