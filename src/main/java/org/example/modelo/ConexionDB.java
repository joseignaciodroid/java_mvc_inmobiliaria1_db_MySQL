package org.example.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    // --- Configuración de la base de datos ---
    private static final String DB_URL = "jdbc:mysql://localhost:3306/inmobiliaria1_db";
    private static final String USER = "inmobiliaria1_db_root";
    private static final String PASS = "Tutann12"; // ¡CAMBIA ESTO POR TU CONTRASEÑA REAL!

    /**
     * Establece una conexión con la base de datos.
     * @return Objeto Connection si la conexión es exitosa, null en caso contrario.
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Cargar el driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Conexión exitosa a la base de datos."); // Puedes descomentar para depuración
        } catch (SQLException e) {
            System.err.println("Error SQL al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace(); // Para depuración
        } catch (ClassNotFoundException e) {
            System.err.println("No se encontró el driver JDBC de MySQL. Asegúrate de que el JAR esté en el classpath.");
            e.printStackTrace(); // Para depuración
        }
        return conn;
    }

    /**
     * Cierra la conexión a la base de datos de forma segura.
     * @param conn La conexión a cerrar.
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}  // class
