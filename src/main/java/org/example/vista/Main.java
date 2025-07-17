package org.example.vista;

import org.example.controlador.InquilinoController;
import org.example.modelo.Inquilino;

import java.util.List;

public class Main { // Esta clase actúa como la Vista principal y el punto de entrada

    private InquilinoController controlador;

    public Main() {
        this.controlador = InquilinoController.getInstance(); // Obtenemos la única instancia del controlador
    }

    public static void main(String[] args) {
        Main app = new Main(); // Creamos una instancia de la clase principal (vista)
        app.iniciarAplicacion();
    }

    public void iniciarAplicacion() {
        int opcion;
        do {
            mostrarMenu();
            opcion = UtilidadesVista.leerInt("Seleccione una opción: ");
            ejecutarOpcion(opcion);
        } while (opcion != 3);
    }

    private void mostrarMenu() {
        UtilidadesVista.mostrarMensaje("\n--- Menú Principal Inmobiliaria ---");
        UtilidadesVista.mostrarMensaje("1. Añadir Inquilino");
        UtilidadesVista.mostrarMensaje("2. Listar Inquilinos");
        UtilidadesVista.mostrarMensaje("3. Fin");
        UtilidadesVista.mostrarMensaje("----------------------------------");
    }

    private void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                anadirInquilino();
                break;
            case 2:
                listarInquilinos();
                break;
            case 3:
                UtilidadesVista.mostrarMensaje("Saliendo de la aplicación. ¡Hasta pronto!");
                break;
            default:
                UtilidadesVista.mostrarError("Opción no válida. Por favor, intente de nuevo.");
                break;
        }
    }

    private void anadirInquilino() {
        UtilidadesVista.mostrarMensaje("\n--- Añadir Nuevo Inquilino ---");
        String nroDocumento = UtilidadesVista.leerString("Introduce el DNI del inquilino (8 números y 1 letra): ");
        String nombre = UtilidadesVista.leerString("Introduce el nombre del inquilino: ");

        // La validación del DNI se realiza en el controlador
        boolean anadido = controlador.anadirInquilino(nroDocumento, nombre);

        if (anadido) {
            UtilidadesVista.mostrarMensaje("Inquilino '" + nombre + "' (DNI: " + nroDocumento + ") añadido con éxito.");
        } else {
            UtilidadesVista.mostrarError("No se pudo añadir el inquilino. Revisa los datos y la conexión a la base de datos.");
        }
    }

    private void listarInquilinos() {
        UtilidadesVista.mostrarMensaje("\n--- Listado de Inquilinos ---");
        List<Inquilino> inquilinos = controlador.listarInquilinos();

        if (inquilinos.isEmpty()) {
            UtilidadesVista.mostrarMensaje("No hay inquilinos registrados.");
        } else {
            for (Inquilino inquilino : inquilinos) {
                UtilidadesVista.mostrarMensaje(inquilino.toString());
            }
        }
    }
}