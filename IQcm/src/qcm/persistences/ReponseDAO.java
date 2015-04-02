package qcm.persistences;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import qcm.models.Reponse;

/**
 * G√®re les acc√®s √† la base de données pour les réponses
 * @author Ingesup
 */
public class ReponseDAO extends ModeleDAO {

    public static Reponse getById(int idReponse) throws SQLException {
        Reponse reponse = null;
        String sql = "SELECT id_reponse, libelle, descriptif, est_correcte, note, id_question FROM reponse WHERE id_reponse = ?";
        ResultSet rs = selectById(sql, idReponse);
        if (rs.next()) {
            reponse = new Reponse(
                    rs.getInt("id_reponse"),
                    rs.getString("libelle"),
                    rs.getString("descriptif"),
                    rs.getBoolean("est_correcte"),
                    rs.getInt("note"),
                    rs.getInt("id_question"));
        }
        rs.close();
        return reponse;
    }

    public static void update(Reponse reponse) throws SQLException {
        String sql = "UPDATE reponse SET libelle = ?, descriptif = ?, note = ?, est_correcte = ? WHERE id_reponse=?";
        PreparedStatement ordre = getConnection().prepareStatement(sql);
        ordre.setString(1, reponse.getLibelle());
        ordre.setString(2, reponse.getLibelle());
        ordre.setInt(3, reponse.getNote());
        ordre.setBoolean(4, reponse.estCorrecte());
        ordre.setInt(5, reponse.getIdReponse());
        ordre.executeUpdate();
        ordre.close();
    }
}
