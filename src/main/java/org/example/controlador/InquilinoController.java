package org.example.controlador;


import org.example.modelo.Inquilino;
import org.example.modelo.InquilinoDAO;
import org.example.utilidades.DNIValidator;

import java.util.List;

public class InquilinoController {

    // Implementación del patrón Singleton
    private static InquilinoController instance;
    private InquilinoDAO inquilinoDAO;

    // Constructor privado
    private InquilinoController() {
        inquilinoDAO = new InquilinoDAO();
    }

    // Método estático para obtener la única instancia del controlador
    public static InquilinoController getInstance() {
        if (instance == null) {
            instance = new InquilinoController();
        }
        return instance;
    }

    /**
     * Añade un nuevo inquilino a la base de datos después de validar el DNI.
     * @param nroDocumento Número de documento (DNI) del inquilino.
     * @param nombre Nombre del inquilino.
     * @return true si el inquilino fue añadido exitosamente, false en caso contrario.
     */


    public boolean anadirInquilino(String nroDocumento, String nombre) {


    /* PENDIENTE:
        Esto de abajo es un error. El controlador no debe ejecutar un println().
        Lo correcto en este caso sería Lanzar una excepción
        (por ejemplo, IllegalArgumentException o una excepción personalizada como DNIInvalidoException).
     */
        if (!DNIValidator.validarDNI(nroDocumento)) {
            System.err.println("Error: El número de documento '" + nroDocumento + "' no es un DNI válido.");
            return false;
        }

        Inquilino nuevoInquilino = new Inquilino(nroDocumento, nombre);
        return inquilinoDAO.insertarInquilino(nuevoInquilino);
    }

    /**
     * Obtiene la lista de todos los inquilinos.
     * @return Una lista de objetos Inquilino.
     */
    public List<Inquilino> listarInquilinos() {
        return inquilinoDAO.obtenerTodosInquilinos();
    }
}
