package Excepciones;

/**
 * Esta clase denominada EquipoMaratonProgramacion modela un
 * equipo de programadores que participará en una maratón de
 * programación. Un equipo cuenta con un nombre, la universidad
 * a la que pertenece, el lenguaje de programación que utilizará en la
 * competición, el tamaño del equipo y un array de programadores.
 * @version 1.0/2026
 */
public class EquipoMaratonProgramacion {

    private String nombreEquipo;          // Nombre del equipo de la maratón
    private String universidad;           // Universidad a la que pertenece el equipo
    private String lenguajeProgramacion;  // Lenguaje de programación utilizado por el equipo
    private Programador[] programadores;  // Array de programadores del equipo
    private int tamañoEquipo;             // Cantidad actual de programadores en el equipo

    /**
     * Constructor de la clase EquipoMaratonProgramacion
     * @param nombreEquipo Parámetro que define el nombre del equipo
     * @param universidad Parámetro que define la universidad a la que pertenece el equipo
     * @param lenguajeProgramacion Parámetro que define el lenguaje de programación que utilizará el equipo
     */
    public EquipoMaratonProgramacion(String nombreEquipo, String universidad, String lenguajeProgramacion) {
        this.nombreEquipo = nombreEquipo;
        this.universidad = universidad;
        this.lenguajeProgramacion = lenguajeProgramacion;
        this.tamañoEquipo = 0; // El tamaño del equipo inicialmente es cero
        this.programadores = new Programador[3]; // Mínimo dos, máximo tres programadores
    }

    /**
     * Método que determina si el array de programadores del equipo está lleno o no
     * @return Valor boolean que determina si el array de programadores está lleno o no
     */
    public boolean estaLleno() {
        return tamañoEquipo == programadores.length;
    }

    /**
     * Método que permite añadir un programador al array de programadores
     * @param programador Parámetro que define el programador a agregar al array
     * @throws Exception Excepción que indica que el equipo de programación está completo
     */
    public void añadir(Programador programador) throws Exception {
        if (estaLleno()) { // Si el array está lleno, se genera la excepción correspondiente
            throw new Exception("El equipo está completo. No se pudo agregar el programador.");
        }
        programadores[tamañoEquipo] = programador; // Se asigna el programador al array
        tamañoEquipo++; // Se incrementa el tamaño del equipo
    }

    /**
     * Método que permite validar un campo evaluando si no tiene dígitos y su
     * longitud no es superior a 20 caracteres. Si no cumple estos criterios,
     * se generan las excepciones correspondientes.
     * @param campo Parámetro que define el campo a validar
     * @throws Exception Excepción que indica que el campo no puede tener dígitos
     * o que su longitud no debe ser superior a 20 caracteres
     */
    public static void validarCampo(String campo) throws Exception {
        if (campo == null || campo.trim().isEmpty()) {
            throw new Exception("El campo no puede estar vacío.");
        }
        for (int j = 0; j < campo.length(); j++) {
            char c = campo.charAt(j);
            if (Character.isDigit(c)) { // Si el carácter es un dígito se genera la excepción
                throw new Exception("El campo no puede tener dígitos.");
            }
        }
        if (campo.length() >= 20) { // Si la longitud es igual o superior a 20, se genera la excepción
            throw new Exception("La longitud no debe ser igual o superior a 20 caracteres.");
        }
    }

    public int getTamañoEquipo() {
        return tamañoEquipo;
    }

    public Programador[] getProgramadores() {
        return programadores;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public String getUniversidad() {
        return universidad;
    }

    public String getLenguajeProgramacion() {
        return lenguajeProgramacion;
    }
}