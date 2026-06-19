package Excepciones;

/**
 * Esta clase denominada Programador modela un integrante de un
 * equipo de programadores que participará en una maratón de
 * programación. Un programador cuenta con un nombre y apellidos.
 * @version 1.0/2026
 */
public class Programador {

    private String nombre;     // Atributo que identifica el nombre de un programador
    private String apellidos;  // Atributo que identifica los apellidos de un programador

    /**
     * Constructor de la clase Programador
     * @param nombre Parámetro que define el nombre del programador
     * @param apellidos Parámetro que define los apellidos del programador
     */
    public Programador(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    @Override
    public String toString() {
        return nombre + " " + apellidos;
    }
}