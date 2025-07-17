package org.example.modelo;

public class Inquilino {
    private String nroDocumento;
    private String nombre;

    public Inquilino(String nroDocumento, String nombre) {
        this.nroDocumento = nroDocumento;
        this.nombre = nombre;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Documento: " + nroDocumento + ", Nombre: " + nombre;
    }
}
