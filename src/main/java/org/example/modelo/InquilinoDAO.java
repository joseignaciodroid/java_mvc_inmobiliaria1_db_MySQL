package org.example.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InquilinoDAO {

    /**
     * Inserta un nuevo inquilino en la tabla 'inquilino'.
     * @param inquilino El objeto Inquilino a insertar.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
    public boolean insertarInquilino(Inquilino inquilino)  {
        String sql = "INSERT INTO inquilino (nro_documento, nombre) VALUES (?, ?)";
        boolean insertado = false;


        /*
        // Lo siguiente es una alternativa sin try/catch dal código inferior comentado.
        Connection conn = ConexionDB.getConnection();
        if (conn == null) {
            System.err.println("No se pudo establecer conexión para insertar.");
            return false;
        }

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, inquilino.getNroDocumento());
        pstmt.setString(2, inquilino.getNombre());

        int filasAfectadas = pstmt.executeUpdate();
        if (filasAfectadas > 0) {
            insertado = true;
        }

        pstmt.close();
        conn.close();
        */



        try (
                Connection conn = ConexionDB.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
            ) {

                if (conn == null) {
                    System.err.println("No se pudo establecer conexión para insertar.");
                    return false;
                }

                pstmt.setString(1, inquilino.getNroDocumento());
                pstmt.setString(2, inquilino.getNombre());

                int filasAfectadas = pstmt.executeUpdate();
                if (filasAfectadas > 0) {
                    insertado = true;
                    // System.out.println("Inquilino '" + inquilino.getNombre() + "' insertado exitosamente."); // Puedes descomentar
                }

        } catch (SQLException e) {
            System.err.println("Error al insertar inquilino: " + e.getMessage());
            // e.printStackTrace(); // Para depuración
        }

        return insertado;
    }




    /**
     * Obtiene una lista de todos los inquilinos de la tabla 'inquilino'.
     * @return Una lista de objetos Inquilino.
     */
    public List<Inquilino> obtenerTodosInquilinos() {
        List<Inquilino> inquilinos = new ArrayList<>();
        String sql = "SELECT nro_documento, nombre FROM inquilino";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (conn == null) {
                System.err.println("No se pudo establecer conexión para listar.");
                return inquilinos;
            }

            while (rs.next()) {
                String nroDocumento = rs.getString("nro_documento");
                String nombre = rs.getString("nombre");
                inquilinos.add(new Inquilino(nroDocumento, nombre));
            }

        } catch (SQLException e) {
            System.err.println("Error al listar inquilinos: " + e.getMessage());
            // e.printStackTrace(); // Para depuración
        }
        return inquilinos;
    }
}  // class
