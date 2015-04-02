package qcm.persistences;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import qcm.models.Question;
import qcm.models.Questionnaire;
import qcm.models.Reponse;

public class QuestionnaireDAO extends ModeleDAO {

    /**
     * Récupère le questionnaire dont on connaît l'identifiant en base de données
     * @param idQuestionnaire L'identifiant du questionnaire
     * @return Le questionnaire identifié par idQuestionnaire
     * @throws SQLException
     */
    public static Questionnaire getById(int idQuestionnaire) throws SQLException {
        Questionnaire questionnaire = null;
        String sql = "SELECT questionnaire.libelle,questionnaire.date_creation,questionnaire.limite_temps,questionnaire.est_actif,"
                + " questionnaire.id_theme,questionnaire.id_user,questionnaire.id_niveau, COUNT(questionnaire_passe.id_questionnaire) AS nbPasseParUser"
                + " FROM questionnaire INNER JOIN questionnaire_passe ON questionnaire_passe.id_questionnaire=questionnaire.id_questionnaire WHERE questionnaire.id_questionnaire = ?";
        ResultSet rs = selectById(sql, idQuestionnaire);
        if (rs.next()) {
            questionnaire = new Questionnaire(
                    idQuestionnaire,
                    rs.getString("libelle"),
                    rs.getDate("date_creation"),
                    rs.getInt("limite_temps"),
                    rs.getBoolean("est_actif"),
                    rs.getInt("id_theme"),
                    rs.getInt("id_user"),
                    rs.getInt("id_niveau"),
                    QuestionnaireDAO.getQuestionsById(idQuestionnaire),
                    rs.getInt("nbPasseParUser"));
        }
        rs.close();
        return questionnaire;
    }

    /**
     * Récupérer la liste de toutes les questions relatives à un questionnaire
     * @param idQuestionnaire L'identifiant du questionnaire dont on veut les questions
     * @return La liste des questions incluses dans le questionnaire spécifié en paramètre
     * @throws SQLException
     */
    public static ArrayList<Question> getQuestionsById(int idQuestionnaire) throws SQLException {
        ArrayList<Question> questions = new ArrayList<Question>();
        String sql = "SELECT contenu.id_question, question.id_theme AS theme_question, questionnaire.id_theme AS theme_questionnaire FROM contenu";
        sql += " INNER JOIN question ON (question.id_question=contenu.id_question)";
        sql += " INNER JOIN questionnaire ON (questionnaire.id_questionnaire=contenu.id_questionnaire)";
        sql += " WHERE contenu.id_questionnaire = ? ORDER BY contenu.id_question ASC";
        ResultSet rs = selectById(sql, idQuestionnaire);

        while (rs.next()) {
            assert rs.getInt("theme_question") == rs.getInt("theme_questionnaire");
            questions.add(QuestionDAO.getById(rs.getInt("id_question")));
        }
        rs.close();
        return questions;
    }

    /**
     * Récupérer tous les questionnaires d'un thème donné
     * @param idTheme L'identifiant du thème concerné
     * @return La liste de tous les questionnaires dont le thème est passé en argument
     * @throws SQLException
     */
    public static HashMap<Integer, String> getQuestionnairesByTheme(int idTheme) throws SQLException {
        HashMap<Integer, String> maMap = new HashMap<Integer, String>();
        String sql = "SELECT id_questionnaire,libelle FROM questionnaire WHERE id_theme=? AND est_actif= 1";
        ResultSet rs = selectById(sql, idTheme);

        while (rs.next()) {
            maMap.put(rs.getInt("id_questionnaire"), rs.getString("libelle"));
        }
        rs.close();
        return maMap;
    }

    /**
     * Récupérer tous les questionnaires d'un niveau donné
     * @param idNiveau L'identifiant du niveau concerné
     * @return La liste de tous les questionnaires dont le niveau est passé en argument
     * @throws SQLException
     */
    public static HashMap<Integer, String> getQuestionnairesByNiveau(int idNiveau) throws SQLException {
        HashMap<Integer, String> maMap = new HashMap<Integer, String>();
        String sql = "SELECT id_questionnaire,libelle FROM questionnaire WHERE id_niveau=? AND est_actif=1";
        ResultSet rs = selectById(sql, idNiveau);
        while (rs.next()) {
            maMap.put(rs.getInt("id_questionnaire"), rs.getString("libelle"));
        }
        rs.close();
        return maMap;
    }

    /**
     * Récupérer tous les questionnaires pour un niveau et un thème spécifiés
     * @param idTheme L'identifiant du thème concerné
     * @param idNiveau L'identifiant du niveau concerné
     * @return La liste de tous les questionnaires dont le thème et le niveau sont passés en argument
     * @throws SQLException
     */
    public static HashMap<Integer, String> getQuestionnairesByThemeAndNiveau(int idTheme, int idNiveau) throws SQLException {
        HashMap<Integer, String> maMap = new HashMap<Integer, String>();
        Connection connexion = getConnection();
        String sql = "SELECT id_questionnaire,libelle FROM questionnaire WHERE id_theme=? AND id_niveau=? AND est_actif=?";
        PreparedStatement ordre = connexion.prepareStatement(sql);
        ordre.setInt(1, idTheme);
        ordre.setInt(2, idNiveau);
        ordre.setBoolean(3, true);
        ResultSet rs = ordre.executeQuery();

        while (rs.next()) {
            maMap.put(rs.getInt("id_questionnaire"), rs.getString("libelle"));
        }
        rs.close();
        ordre.close();
        return maMap;
    }

    /**
     * Recherche dans la base données un questionnaire qui a comme thème idTheme, comme niveau idNiveau,
     * et comme libelle libelle
     * @param idTheme
     * @param idNiveau
     * @param libelle
     * @return un questionnaire si la recherche a réussi, null sinon
     * @throws java.sql.SQLException
     */
    public static Questionnaire search(final int idTheme, final int idNiveau, final String libelle) throws SQLException {
        Questionnaire questionnaire = null;
        String sql = "SELECT id_user FROM questionnaire WHERE id_theme = ? AND id_niveau = ? AND libelle = ? LIMIT 0, 1";
        PreparedStatement ordre = getConnection().prepareStatement(sql);
        ordre.setInt(1, idTheme);
        ordre.setInt(2, idNiveau);
        ordre.setString(3, libelle);
        ResultSet rs = ordre.executeQuery();

        if (rs.next()) {
            questionnaire = new Questionnaire(libelle, idTheme, rs.getInt("id_user"), idNiveau);
        }
        rs.close();
        ordre.close();
        return questionnaire;
    }

    public static void update(Questionnaire q) throws SQLException {
        String sql = "UPDATE questionnaire SET libelle = ? , id_niveau = ?, est_actif = ? WHERE id_questionnaire = ?";
        PreparedStatement ps = getConnection().prepareStatement(sql);
        ps.setString(1, q.getLibelle());
        ps.setInt(2, q.getIdNiveau());
        ps.setBoolean(3, q.estActif());
        ps.setInt(4, q.getIdQuestionnaire());
        ps.executeUpdate();
        ps.close();
    }

    public static void insert(Questionnaire questionnaire) throws SQLException {
        ResultSet rs = null;
        Connection connexion = null;
        ResultSet rsContenu = null, rsQuestion = null;
        Integer idQuestionnaire = null, idQuestion = null;
        Statement statement = null;
        try {
            connexion = getConnection();
            statement = connexion.createStatement();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO questionnaire(libelle, limite_temps, id_niveau, id_theme, id_user, date_creation) "
                    + " VALUES(?, ?, ?, ?, ?, NOW())";
            PreparedStatement psQ = connexion.prepareStatement(sql);
            psQ.setString(1, questionnaire.getLibelle());
            psQ.setInt(2, questionnaire.getLimiteTemps());
            psQ.setInt(3, questionnaire.getIdNiveau());
            psQ.setInt(4, questionnaire.getIdTheme());
            psQ.setInt(5, questionnaire.getIdUser());
            psQ.executeUpdate();
            rs = statement.executeQuery("SELECT LAST_INSERT_ID() FROM questionnaire");
            rs.next();
            idQuestionnaire = rs.getInt(1);
            if (idQuestionnaire <= 0) {
                throw new SQLException("impossible d'enregistrer les informations concernant le questionnaire.");
            }
            /**
             * Insertion des questions
             */
            String sqlQuestion = "INSERT INTO question(libelle,id_theme,id_user) VALUES (?,?,?)";
            PreparedStatement ps = getConnection().prepareStatement(sqlQuestion);
            ps.setInt(2, questionnaire.getIdTheme());


            for (Question q : questionnaire.getQuestions()) {
                idQuestion = q.getIdQuestion();
                if (idQuestion == null) {

                    ps.setString(1, q.getLibelle());
                    ps.setInt(3, q.getIdUser());
                    ps.executeUpdate();
                    rsQuestion = statement.executeQuery("SELECT LAST_INSERT_ID() FROM question");
                    rsQuestion.next();
                    idQuestion = rsQuestion.getInt(1);
                    if (idQuestion <= 0) {
                        throw new SQLException("impossible d'enregistrer les informations concernant la question " + q.getLibelle());
                    }


                    String sqlRep = "INSERT INTO reponse(libelle, descriptif, est_correcte, note, id_question) "
                            + " VALUES(?, ?, ?, ?, ?)";

                    PreparedStatement psRep = getConnection().prepareStatement(sqlRep);
                    psRep.setInt(5, idQuestion);
                    for (Reponse r : q.getReponses()) {
                        psRep.setString(1, r.getLibelle());
                        psRep.setString(2, r.getDescriptif());
                        psRep.setBoolean(3, r.estCorrecte());
                        psRep.setInt(4, r.getNote());
                        psRep.executeUpdate();
                    }
                    psRep.close();

                }
                /**
                 * Insertion dans la table contenu
                 */
                String sqlContenu = "INSERT INTO contenu(id_questionnaire, id_question) VALUES(?, ?)";
                PreparedStatement psContenu = connexion.prepareStatement(sqlContenu);
                psContenu.setInt(1, idQuestionnaire);
                psContenu.setInt(2, idQuestion);
                psContenu.executeUpdate();

                rsContenu = statement.executeQuery("SELECT LAST_INSERT_ID() FROM contenu");
                rsContenu.next();
                if (rsContenu.getInt(1) <= 0) {
                    throw new SQLException("Impossible d'enregistrer les questions");
                }
            }
            connexion.commit();

        } catch (SQLException e) {
            if (connexion != null) {
                connexion.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }

        }
    }

    public static List<Questionnaire> getCreatedByUser(int idUser) throws SQLException {
        List<Questionnaire> questionnaires = new ArrayList<Questionnaire>();
        String sql = "SELECT id_questionnaire FROM questionnaire WHERE id_user = ? ORDER BY id_questionnaire";
        ResultSet rs = selectById(sql, idUser);
        while (rs.next()) {
            questionnaires.add(getById(rs.getInt(1)));
        }
        rs.close();
        return questionnaires;
    }

    public static HashMap<Integer, Questionnaire> getAll() throws SQLException {
        HashMap<Integer, Questionnaire> questionnaires = new HashMap<Integer, Questionnaire>();
        String sql = "SELECT id_questionnaire, libelle, date_creation, limite_temps, est_actif, id_theme, id_user, id_niveau FROM questionnaire";
        ResultSet rs = getConnection().createStatement().executeQuery(sql);
        while (rs.next()) {
            int idQuestionnaire = rs.getInt("id_questionnaire");
            questionnaires.put(idQuestionnaire, new Questionnaire(
                    idQuestionnaire,
                    rs.getString("libelle"),
                    rs.getDate("date_creation"),
                    rs.getInt("limite_temps"),
                    rs.getBoolean("est_actif"),
                    rs.getInt("id_Theme"),
                    rs.getInt("id_user"),
                    rs.getInt("id_niveau"),
                    QuestionnaireDAO.getQuestionsById(idQuestionnaire),
                    1));
        }
        return questionnaires;
    }

    public static HashMap<Integer, Questionnaire> getAllActives() throws SQLException {
        HashMap<Integer, Questionnaire> questionnaires = new HashMap<Integer, Questionnaire>();
        String sql = "SELECT id_questionnaire, libelle, date_creation, limite_temps, est_actif, id_theme, id_user, id_niveau FROM questionnaire WHERE est_actif = 1";
        ResultSet rs = getConnection().createStatement().executeQuery(sql);
        while (rs.next()) {
            int idQuestionnaire = rs.getInt("id_questionnaire");
            questionnaires.put(idQuestionnaire, new Questionnaire(
                    idQuestionnaire,
                    rs.getString("libelle"),
                    rs.getDate("date_creation"),
                    rs.getInt("limite_temps"),
                    rs.getBoolean("est_actif"),
                    rs.getInt("id_Theme"),
                    rs.getInt("id_user"),
                    rs.getInt("id_niveau"),
                    QuestionnaireDAO.getQuestionsById(idQuestionnaire),
                    1));
        }
        return questionnaires;
    }

    public static void addQuestion(int idQuestionnaire, Question question) throws SQLException {
        String sql = "";
        PreparedStatement ps = null;

        if (question.getIdQuestion() == null) {
            Connection connexion = getConnection();
            try {

                connexion.setAutoCommit(false);
                sql = "INSERT INTO question(libelle,id_theme,id_user) VALUES (?,?,?)";
                ps = getConnection().prepareStatement(sql);
                ps.setString(1, question.getLibelle());
                ps.setInt(2, question.getIdTheme());
                ps.setInt(3, question.getIdUser());
                ps.executeUpdate();
                ResultSet rsQuestion = connexion.createStatement().executeQuery("SELECT LAST_INSERT_ID() FROM question");
                rsQuestion.next();
                int idQuestion = rsQuestion.getInt(1);
                if (idQuestion <= 0) {
                    throw new SQLException("impossible d'enregistrer les informations concernant la question " + question.getLibelle());
                }
                String sqlRep = "INSERT INTO reponse(libelle, descriptif, est_correcte, note, id_question) "
                        + " VALUES(?, ?, ?, ?, ?)";
                ps = getConnection().prepareStatement(sqlRep);
                ps.setInt(5, idQuestion);
                for (Reponse r : question.getReponses()) {
                    ps.setString(1, r.getLibelle());
                    ps.setString(2, r.getDescriptif());
                    ps.setBoolean(3, r.estCorrecte());
                    ps.setInt(4, r.getNote());
                    ps.executeUpdate();
                }
                addQuestion(idQuestionnaire, QuestionDAO.getById(idQuestion));
                connexion.commit();
            } catch (SQLException e) {
                if (connexion != null) {
                    connexion.rollback();
                }
                throw e;
            }

        } else {
            assert QuestionDAO.getById(question.getIdQuestion()).equals(question);
            sql = "INSERT INTO contenu(id_questionnaire,id_question) VALUES (?,?)";
            ps = getConnection().prepareStatement(sql);
            ps.setInt(1, idQuestionnaire);
            ps.setInt(2, question.getIdQuestion());
            ps.executeUpdate();
        }

        ps.close();
    }
}
