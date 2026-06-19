package Excepciones;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Esta clase denominada InterfazEquipoMaraton crea la interfaz gráfica
 * de usuario para registrar un equipo de maratón de programación y
 * validar los datos de sus integrantes, generando excepciones cuando
 * los datos ingresados no cumplen los requisitos del programa.
 * @version 1.0/2026
 */
public class InterfazEquipoMaraton extends JFrame {

    // Datos del equipo
    private JTextField txtNombreEquipo;
    private JTextField txtUniversidad;
    private JTextField txtLenguaje;
    private JButton btnCrearEquipo;

    // Datos del programador a agregar
    private JTextField txtNombreProgramador;
    private JTextField txtApellidosProgramador;
    private JButton btnAgregar;
    private JButton btnLimpiar;

    // Lista de integrantes ya agregados
    private JTextArea areaIntegrantes;

    private EquipoMaratonProgramacion equipo;

    public InterfazEquipoMaraton() {
        super("Equipo Maratón de Programación - Ejercicio 6.7");
        construirInterfaz();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(480, 480);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void construirInterfaz() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // ---- Panel datos del equipo ----
        JPanel panelEquipo = new JPanel(new GridLayout(3, 2, 8, 8));
        panelEquipo.setBorder(BorderFactory.createTitledBorder("Datos del equipo"));

        panelEquipo.add(new JLabel("Nombre del equipo:"));
        txtNombreEquipo = new JTextField();
        panelEquipo.add(txtNombreEquipo);

        panelEquipo.add(new JLabel("Universidad:"));
        txtUniversidad = new JTextField();
        panelEquipo.add(txtUniversidad);

        panelEquipo.add(new JLabel("Lenguaje de programación:"));
        txtLenguaje = new JTextField();
        panelEquipo.add(txtLenguaje);

        btnCrearEquipo = new JButton("Crear equipo");
        btnCrearEquipo.addActionListener(this::crearEquipo);

        // ---- Panel datos del programador ----
        JPanel panelProgramador = new JPanel(new GridLayout(2, 2, 8, 8));
        panelProgramador.setBorder(BorderFactory.createTitledBorder("Datos del integrante"));

        panelProgramador.add(new JLabel("Nombre:"));
        txtNombreProgramador = new JTextField();
        panelProgramador.add(txtNombreProgramador);

        panelProgramador.add(new JLabel("Apellidos:"));
        txtApellidosProgramador = new JTextField();
        panelProgramador.add(txtApellidosProgramador);

        // ---- Panel botones ----
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnAgregar = new JButton("Agregar integrante");
        btnLimpiar = new JButton("Limpiar");
        btnAgregar.addActionListener(this::agregarProgramador);
        btnLimpiar.addActionListener(this::limpiarCampos);
        panelBotones.add(btnAgregar);
        panelBotones.add(btnLimpiar);

        // ---- Panel lista de integrantes ----
        areaIntegrantes = new JTextArea(8, 30);
        areaIntegrantes.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaIntegrantes);
        JPanel panelLista = new JPanel(new BorderLayout());
        panelLista.setBorder(BorderFactory.createTitledBorder("Integrantes del equipo"));
        panelLista.add(scroll, BorderLayout.CENTER);

        // Botón crear equipo en su propia fila
        JPanel panelCrear = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelCrear.add(btnCrearEquipo);

        panelPrincipal.add(panelEquipo);
        panelPrincipal.add(panelCrear);
        panelPrincipal.add(Box.createVerticalStrut(10));
        panelPrincipal.add(panelProgramador);
        panelPrincipal.add(panelBotones);
        panelPrincipal.add(Box.createVerticalStrut(10));
        panelPrincipal.add(panelLista);

        setContentPane(panelPrincipal);

        // Al iniciar, los campos de integrantes están deshabilitados hasta crear el equipo
        habilitarPanelIntegrante(false);
    }

    private void habilitarPanelIntegrante(boolean habilitar) {
        txtNombreProgramador.setEnabled(habilitar);
        txtApellidosProgramador.setEnabled(habilitar);
        btnAgregar.setEnabled(habilitar);
    }

    /**
     * Crea el objeto EquipoMaratonProgramacion con los datos ingresados
     */
    private void crearEquipo(ActionEvent e) {
        try {
            String nombreEquipo = txtNombreEquipo.getText();
            String universidad = txtUniversidad.getText();
            String lenguaje = txtLenguaje.getText();

            if (nombreEquipo.trim().isEmpty() || universidad.trim().isEmpty() || lenguaje.trim().isEmpty()) {
                throw new Exception("Todos los datos del equipo son obligatorios.");
            }

            equipo = new EquipoMaratonProgramacion(nombreEquipo, universidad, lenguaje);
            areaIntegrantes.setText("");
            JOptionPane.showMessageDialog(this,
                    "Equipo \"" + nombreEquipo + "\" creado correctamente.\nAhora ingrese los integrantes.",
                    "Atención", JOptionPane.INFORMATION_MESSAGE);

            txtNombreEquipo.setEnabled(false);
            txtUniversidad.setEnabled(false);
            txtLenguaje.setEnabled(false);
            btnCrearEquipo.setEnabled(false);
            habilitarPanelIntegrante(true);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Atención", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Valida y agrega un programador al equipo. Si el equipo está lleno o los
     * datos no son válidos, se muestra la excepción correspondiente.
     */
    private void agregarProgramador(ActionEvent e) {
        try {
            if (equipo == null) {
                throw new Exception("Primero debe crear el equipo.");
            }

            String nombre = txtNombreProgramador.getText();
            String apellidos = txtApellidosProgramador.getText();

            // Validación de los campos: solo texto y longitud menor a 20 caracteres
            EquipoMaratonProgramacion.validarCampo(nombre);
            EquipoMaratonProgramacion.validarCampo(apellidos);

            Programador programador = new Programador(nombre, apellidos);
            equipo.añadir(programador); // Puede lanzar excepción si el equipo está lleno

            areaIntegrantes.append((equipo.getTamañoEquipo()) + ". " + programador + "\n");

            txtNombreProgramador.setText("");
            txtApellidosProgramador.setText("");

            if (equipo.estaLleno()) {
                JOptionPane.showMessageDialog(this,
                        "El equipo ya tiene el máximo de integrantes (3).",
                        "Atención", JOptionPane.INFORMATION_MESSAGE);
                habilitarPanelIntegrante(false);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Atención", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Limpia los campos de texto del integrante actual
     */
    private void limpiarCampos(ActionEvent e) {
        txtNombreProgramador.setText("");
        txtApellidosProgramador.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfazEquipoMaraton ventana = new InterfazEquipoMaraton();
            ventana.setVisible(true);
        });
    }
}