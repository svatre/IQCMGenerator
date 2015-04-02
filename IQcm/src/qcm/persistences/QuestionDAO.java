package qcm.persistences;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import qcm.models.Question;
import qcm.models.Reponse;

public class QuestionDAO extends ModeleDAO {

    public static Question getById(int idQuestion) throws SQLException {
        Question question = null;
        String sql = "SELECT question.libelle, question.id_theme, question.id_user, "
                + "COUNT( questionnaire_passe.id_questionnaire ) AS nbQuestionnairePasseAppelant "
                + "FROM question "
                + "INNER JOIN contenu ON contenu.id_question = question.id_question "
                + "INNER JOIN questionnaire_passe ON questionnaire_passe.id_questionnaire = contenu.id_questionnaire "
                + "WHERE question.id_question = ?";
        ResultSet rs = selectById(sql, idQuestion);
        if (rs.next()) {
            question = new Question(
                    idQuestion,
                    rs.getString("libelle"),
                    rs.getInt("id_theme"),
                    rs.getInt("id_user"),
                    rs.getInt("nbQuestionnairePasseAppelant"),
                    getReponsesById(idQuestion));
        }
        rs.close();
        return question;
    }

    public static List<Reponse> getReponsesById(int idQuestion) throws SQLException {
        List<Reponse> reponses = new ArrayList<Reponse>();
        String sql = "SELECT id_reponse FROM reponse WHERE id_question = ? ORDER BY id_reponse ASC";
        ResultSet rs = selectById(sql, idQuestion);
        while (rs.next()) {
            reponses.add(ReponseDAO.getById(rs.getInt("id_reponse")));
        }
        rs.close();
        return reponses;
    }

    public static List<Question> getByTheme(int idTheme) throws SQLException {
        List<Question> questions = new ArrayList<Question>();
        String sql = "SELECT id_question FROM question WHERE id_theme = ? ORDER BY id_question ASC";
        ResultSet rs = selectById(sql, idTheme);
        while (rs.next()) {
            questions.add(getById(rs.getInt(1)));
        }
        rs.close();
        System.out.println(questions);
        return questions;
    }

    public static Question search(Question toSearch) throws SQLException {
        Question question = null;
        String sql = "SELECT id_question, libelle , id_theme , id_user FROM question WHERE id_theme = ? AND libelle = ? LIMIT 0, 1";
        PreparedStatement ordre = getConnection().prepareStatement(sql);
        ordre.setInt(1, toSearch.getIdTheme());
        ordre.setString(2, toSearch.getLibelle());
        ResultSet rs = ordre.executeQuery();
        if (rs.next()) {
            question = new Question(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), 0, new ArrayList<Reponse>());
        }
        rs.close();
        ordre.close();
        return question;
    }

    public static void update(Question question) throws SQLException {
        String sql = "UPDATE question SET libelle = ? WHERE id_question = ?";
        PreparedStatement ordre = getConnection().prepareStatement(sql);
        ordre.setString(1, question.getLibelle());
        ordre.setInt(2, question.getIdQuestion());
        ordre.executeUpdate();
        for (Reponse reponse : question.getReponses()) {
            ReponseDAO.update(reponse);
        }
        ordre.close();
    }
}
