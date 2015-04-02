package qcm.persistences;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModeleDAO {

    protected static Database database = new Database();

    protected static Connection getConnection() throws SQLException {
        return database.getConnection();
    }

    protected static ResultSet execute(String sql) throws SQLException {
        return getConnection().createStatement().executeQuery(sql);
    }

    protected static ResultSet selectById(String sql, int id) throws SQLException {
        PreparedStatement ps = getConnection().prepareStatement(sql);
        ps.setInt(1, id);
        return ps.executeQuery();
    }
}
