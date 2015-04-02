package qcm.persistences;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import qcm.models.Theme;

/**
 * Gère les accès à la base de données pour les themes
 * @author Ingesup
 */
public class ThemeDAO extends ModeleDAO {

    public static List<Theme> getAll() throws SQLException {
        List<Theme> themes = new ArrayList<Theme>();
        String sql = "SELECT theme.id_theme, theme.id_user, theme.libelle, theme.est_actif, COUNT(questionnaire.id_theme) AS compteur "
                + "FROM theme "
                + "LEFT OUTER JOIN questionnaire "
                + "ON questionnaire.id_theme = theme.id_theme "
                + "GROUP BY theme.id_theme "
                + "ORDER BY theme.id_theme ASC";
        ResultSet rs = execute(sql);
        while (rs.next()) {
            Theme theme = new Theme(
                    rs.getInt("id_theme"),
                    rs.getInt("id_user"),
                    rs.getString("libelle"),
                    rs.getInt("compteur"),
                    rs.getBoolean("est_actif"));
            themes.add(theme);
        }
        rs.close();
        return themes;
    }

    public static List<Theme> getAllActif() throws SQLException {
        List<Theme> themes = new ArrayList<Theme>();
        String sql = "SELECT theme.id_theme, theme.id_user, theme.libelle, theme.est_actif, COUNT(questionnaire.id_theme) AS compteur "
                + "FROM theme "
                + "LEFT OUTER JOIN questionnaire "
                + "ON questionnaire.id_theme = theme.id_theme "
                + "WHERE theme.est_actif=1 "
                + "GROUP BY theme.id_theme "
                + "ORDER BY theme.id_theme ASC";
        ResultSet rs = execute(sql);
        while (rs.next()) {
            Theme theme = new Theme(
                    rs.getInt("id_theme"),
                    rs.getInt("id_user"),
                    rs.getString("libelle"),
                    rs.getInt("compteur"),
                    rs.getBoolean("est_actif"));
            themes.add(theme);
        }
        rs.close();
        return themes;
    }

    public static Theme getById(int idTheme) throws SQLException {
        Theme theme = null;
        String sql = "SELECT theme.id_theme, theme.id_user, theme.libelle, theme.est_actif, COUNT(questionnaire.id_theme) AS compteur "
                + "FROM theme "
                + "LEFT OUTER JOIN questionnaire ON questionnaire.id_theme = theme.id_theme "
                + "WHERE theme.id_theme = ? "
                + "GROUP BY theme.id_theme "
                + "ORDER BY theme.id_theme ASC";
        ResultSet rs = selectById(sql, idTheme);
        if (rs.next()) {
            theme = new Theme(
                    rs.getInt("id_theme"),
                    rs.getInt("id_user"),
                    rs.getString("libelle"),
                    rs.getInt("compteur"),
                    rs.getBoolean("est_actif"));
        }
        rs.close();
        return theme;
    }

    public static void update(Theme theme) throws SQLException {
        String sql = "UPDATE theme SET libelle = ?, est_actif = ? WHERE id_theme = ?";
        PreparedStatement ps = getConnection().prepareStatement(sql);
        ps.setString(1, theme.getLibelle());
        ps.setBoolean(2, theme.estActif());
        ps.setInt(3, theme.getIdTheme());
        ps.executeUpdate();
        ps.close();
    }
}
