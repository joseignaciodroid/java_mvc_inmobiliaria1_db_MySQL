package org.example.vista;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UtilidadesVista {

    // Se recomienda cerrar el scanner al finalizar la aplicación
    // Por ahora, lo mantenemos como estático para simplificar, pero en una app más grande
    // se podría pasar como parámetro o gestionar de forma más sofisticada.
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Lee una cadena de texto del teclado.
     * @param prompt Mensaje a mostrar al usuario antes de la lectura.
     * @return La cadena leída.
     */
    public static String leerString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     * Lee un entero del teclado.
     * @param prompt Mensaje a mostrar al usuario antes de la lectura.
     * @return El entero leído.
     */
    public static int leerInt(String prompt) {
        int valor = -1;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.print(prompt);
                valor = scanner.nextInt();
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, introduce un número entero.");
                // Limpiar el buffer del scanner. Esto es crucial.
                scanner.next();
            } finally {
                // Consumir el resto de la línea, incluyendo el salto de línea.
                // Esto es vital para que nextLine() funcione correctamente después de nextInt().
                scanner.nextLine();
            }
        }
        return valor;
    }

    /**
     * Muestra un mensaje en la consola.
     * @param mensaje El mensaje a mostrar.
     */
    public static void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    /**
     * Muestra un mensaje de error en la consola.
     * @param mensaje El mensaje de error a mostrar.
     */
    public static void mostrarError(String mensaje) {
        System.err.println(mensaje);
    }
}