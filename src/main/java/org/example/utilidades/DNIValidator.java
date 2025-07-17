package org.example.utilidades;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DNIValidator {

    // Patrón para DNI: 8 dígitos seguidos de una letra (mayúscula o minúscula)
    private static final String DNI_REGEX = "^(\\d{8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])$";
    private static final String DNI_LETTERS = "TRWAGMYFPDXBNJZSQVHLCKE";

    /**
     * Valida si una cadena es un DNI español válido.
     * Ignora NIE (X, Y, Z al principio).
     * @param dni La cadena a validar.
     * @return true si es un DNI válido, false en caso contrario.
     */
    public static boolean validarDNI(String dni) {
        if (dni == null || dni.length() != 9) {
            return false;
        }

        Pattern pattern = Pattern.compile(DNI_REGEX, Pattern.CASE_INSENSITIVE); // CASE_INSENSITIVE para ignorar mayúsculas/minúsculas de la letra
        Matcher matcher = pattern.matcher(dni);

        if (!matcher.matches()) {
            return false; // No cumple el formato básico (8 dígitos + 1 letra)
        }

        String numeroStr = matcher.group(1); // Grupo 1: los 8 dígitos
        String letraIntroducidaStr = matcher.group(2).toUpperCase(); // Grupo 2: la letra, convertida a mayúscula

        try {
            int numeroDNI = Integer.parseInt(numeroStr);
            char letraCalculada = DNI_LETTERS.charAt(numeroDNI % 23);

            return letraIntroducidaStr.charAt(0) == letraCalculada;

        } catch (NumberFormatException e) {
            // Esto no debería ocurrir si el regex ya validó 8 dígitos
            return false;
        } catch (StringIndexOutOfBoundsException e) {
            // Esto no debería ocurrir si el regex ya validó la letra
            return false;
        }
    }
}