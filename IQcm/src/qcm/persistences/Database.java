package qcm.persistences;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe qui encapsule la connexion à la base de données et stocke les
 * informations de connexion (pilote, URL et identifiants)
 * @author Ingesup
 */
public class Database {

    /**
     * Nom du pilote (driver) de connexion à la base de données
     */
    public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    /**
     * URL de connexion a la base de données
     */
    public static final String URL = "jdbc:mysql://localhost:8889/QCM";
    /**
     * Identifiant de l'utilisateur à fournir lors de la connexion
     */
    public static final String USER = "root";
    /**
     * Mot de passe de l'utilisateur à fournir lors de la connexion
     */
    public static final String PASSWORD = "root";
    private static Connection connexion;

    public Database() {
    }

    /**
     * Fournit la connexion à la base de données
     * @return La connexion
     * @throws java.sql.SQLException
     */
    public Connection getConnection() throws SQLException {
        if (connexion == null) {
            try {
                Class.forName(DRIVER_NAME).newInstance();
                connexion = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("*** Driver loaded.");
            } catch (ClassNotFoundException e) {
                System.err.println("*** ERROR: Driver " + DRIVER_NAME + " not found");
            } catch (InstantiationException e) {
                System.err.println("*** ERROR: Impossible to create an instance of " + DRIVER_NAME);
                System.err.println(e.getMessage());
            } catch (IllegalAccessException e) {
                System.err.println("*** ERROR: Impossible to create an instance of " + DRIVER_NAME);
                System.err.println(e.getMessage());
            }
        }
        return connexion;
    }
}
