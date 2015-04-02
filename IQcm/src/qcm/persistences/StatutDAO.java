package qcm.persistences;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import qcm.models.Statut;

public class StatutDAO extends ModeleDAO {

    public static List<Statut> getAll() throws SQLException {
        List<Statut> statuts = new ArrayList<Statut>();
        String sql = "SELECT id_statut, libelle FROM statut ORDER BY id_statut ASC";
        ResultSet rs = execute(sql);
        while (rs.next()) {
            Statut statut = new Statut(
                    rs.getInt("id_statut"),
                    rs.getString("libelle"));
            statuts.add(statut);
        }
        rs.close();
        return statuts;
    }

    public static Statut getById(int idStatut) throws SQLException {
        Statut statut = null;
        String sql = "SELECT id_statut, libelle FROM statut WHERE id_statut = ?";
        ResultSet rs = selectById(sql, idStatut);
        if (rs.next()) {
            statut = new Statut(rs.getInt("id_statut"), rs.getString("libelle"));
        }
        rs.close();
        return statut;
    }
}
