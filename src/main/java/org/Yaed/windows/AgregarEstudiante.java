package org.Yaed.windows;

import org.Yaed.controller.BecasController;
import org.Yaed.controller.CarrerasController;
import org.Yaed.controller.EstudiantesController;
import org.Yaed.entity.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class AgregarEstudiante extends JFrame {

    private JTextField txtCarnet, txtCedula, txtNombres, txtApellidos, txtCelular;
    private JComboBox<String> comboEtnia, comboCarrera, comboSede, comboGenero, comboEstado, comboBeca;

    // Labels
    private JLabel lblCarnet, lblCedula, lblNombres, lblApellidos, lblCelular, lblEtnia, lblCarrera, lblSede, lblGenero, lblEstado, lblBeca;

    // Botones que puedes modificar luego
    private JButton btnConfirmar, btnCancelar;

    private List<Etnia> etnias;
    private List<Carrera> carreras;
    private List<Sede> sedes;
    private List<EstadoEstudiante> estados;
    private List<Beca> becas;


    public AgregarEstudiante() {
        setTitle("Formulario de Estudiante");
        setSize(750, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());       // Poner icono
        ImageIcon icono = new ImageIcon(Objects.requireNonNull(getClass().getResource("/logo.png")));
        setIconImage(icono.getImage());

        getContentPane().setBackground(new Color(14, 34, 71));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font fuente = new Font("Arial", Font.BOLD, 13);
        Color colorTexto = Color.WHITE;

        // Campo: Carnet
        lblCarnet = crearLabel("Carnet:", 0, 0, fuente, colorTexto, gbc);
        txtCarnet = crearTextField(1, 0, gbc);
        // Solo números y guiones en carnet
        txtCarnet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c) && c != '-' && c != '\b') {
                    evt.consume();
                }
            }
        });

        // Campo: Cédula
        lblCedula = crearLabel("Cédula:", 0, 1, fuente, colorTexto, gbc);
        txtCedula = crearTextField(1, 1, gbc);
        // Solo números, pero permite una letra al final
        txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                String texto = txtCedula.getText();
                char c = evt.getKeyChar();
                // Permitir dígitos en cualquier posición
                if (Character.isDigit(c) || c == '\b') {
                    return;
                }
                // Permitir una sola letra al final
                if (Character.isLetter(c) && texto.chars().noneMatch(Character::isLetter) && txtCedula.getCaretPosition() == texto.length()) {
                    return;
                }
                evt.consume();
            }
        });

        lblNombres = crearLabel("Nombres:", 0, 2, fuente, colorTexto, gbc);
        txtNombres = crearTextField(1, 2, gbc);
        // Solo letras y espacios en nombres
        txtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isLetter(c) && c != ' ' && c != '\b') {
                    evt.consume();
                }
            }
        });

        lblApellidos = crearLabel("Apellidos:", 0, 3, fuente, colorTexto, gbc);
        txtApellidos = crearTextField(1, 3, gbc);
        // Solo letras y espacios en apellidos
        txtApellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isLetter(c) && c != ' ' && c != '\b') {
                    evt.consume();
                }
            }
        });

        lblCelular = crearLabel("Celular (xxxx-xxxx):", 0, 4, fuente, colorTexto, gbc);
        txtCelular = crearTextField(1, 4, gbc);
        // Solo números en celular
        txtCelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c) && c != '\b') {
                    evt.consume();
                }
            }
        });

        // Etnia
        lblEtnia = crearLabel("Etnia:", 0, 5, fuente, colorTexto, gbc);
        etnias = EstudiantesController.getEtnias();
        comboEtnia = crearComboBoxDesdeLista(etnias, gbc, 1, 5);

        // Carrera
        lblCarrera = crearLabel("Carrera:", 0, 6, fuente, colorTexto, gbc);
        carreras = CarrerasController.getCarreras();
        comboCarrera = crearComboBoxDesdeLista(carreras, gbc, 1, 6);

        // Sede
        lblSede = crearLabel("Sede:", 0, 7, fuente, colorTexto, gbc);
        sedes = CarrerasController.getSedes();
        comboSede = crearComboBoxDesdeLista(sedes, gbc, 1, 7);

        // Género
        lblGenero = crearLabel("Género:", 0, 8, fuente, colorTexto, gbc);
        comboGenero = new JComboBox<>(new String[]{"Masculino", "Femenino", "Otro"});
        gbc.gridx = 1;
        gbc.gridy = 8;
        add(comboGenero, gbc);

        // Estado
        lblEstado = crearLabel("Estado:", 0, 9, fuente, colorTexto, gbc);
        estados = EstudiantesController.getEstadoEstudiantes();
        comboEstado = crearComboBoxDesdeLista(estados, gbc, 1, 9);

        // Beca
        lblBeca = crearLabel("Tipo de beca:", 0, 10, fuente, colorTexto, gbc);
        becas = BecasController.getBecas();
        comboBeca = crearComboBoxDesdeLista(becas, gbc, 1, 10);

        // Botones Confirmar y Cancelar
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnConfirmar = new JButton("Confirmar");
        btnCancelar = new JButton("Cancelar");

        panelBotones.add(btnConfirmar);
        panelBotones.add(btnCancelar);
        gbc.gridx = 0;
        gbc.gridy = 11; // Cambia a 11 porque se agrega la fila de cédula
        gbc.gridwidth = 3;
        add(panelBotones, gbc);

        btnCancelar.addActionListener(e -> dispose());
        btnConfirmar.addActionListener(e -> guardarEstudiante());
    }

    private void guardarEstudiante() {
        String carnet = txtCarnet.getText().trim();
        String cedula = txtCedula.getText().trim();
        String nombres = txtNombres.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String celular = txtCelular.getText().trim();

        // Validaciones de campos vacíos
        if (carnet.isEmpty() ||
            cedula.isEmpty() ||
            nombres.isEmpty() ||
            apellidos.isEmpty() ||
            celular.isEmpty() ||
            comboEtnia.getSelectedItem() == null ||
            comboCarrera.getSelectedItem() == null ||
            comboSede.getSelectedItem() == null ||
            comboGenero.getSelectedItem() == null ||
            comboEstado.getSelectedItem() == null ||
            comboBeca.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (carnetExiste(carnet)) {
            JOptionPane.showMessageDialog(this, "Estudiante ya existe", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Estudiante estudianteAgregar = new Estudiante();
        estudianteAgregar.setCarnet(carnet);
        estudianteAgregar.setCedula(cedula);
        estudianteAgregar.setNombre(nombres);
        estudianteAgregar.setApellido(apellidos);
        estudianteAgregar.setTelefono(celular);

        for (Etnia etnia : etnias)
            if (etnia.getNombre().equals(comboEtnia.getSelectedItem()))
                estudianteAgregar.setEtnia(etnia);

        for (Carrera carrera : carreras)
            if (carrera.getNombre().equals(comboCarrera.getSelectedItem()))
                estudianteAgregar.setCarrera(carrera);

        for (Sede sede : sedes)
            if (sede.getNombre().equals(comboSede.getSelectedItem()))
                estudianteAgregar.setSede(sede);

        switch (comboGenero.getSelectedItem().toString()) {
            case "Masculino" -> estudianteAgregar.setSexo('M');
            case "Femenino" -> estudianteAgregar.setSexo('F');
            default -> estudianteAgregar.setSexo('O');
        }

        for (EstadoEstudiante estado : estados)
            if (estado.getNombre().equals(comboEstado.getSelectedItem()))
                estudianteAgregar.setEstado(estado);

        for (Beca beca : becas)
            if (beca.getNombre().equals(comboBeca.getSelectedItem()))
                estudianteAgregar.setBecaid(beca);

        EstudiantesController.saveEstudiante(estudianteAgregar);
        JOptionPane.showMessageDialog(this, "Estudiante guardado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    private boolean carnetExiste(String carnet) {
        List<Estudiante> estudiantes = EstudiantesController.getEstudiantes();
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getCarnet().equalsIgnoreCase(carnet)) {
                return true;
            }
        }
        return false;
    }

    // Métodos auxiliares que ahora retornan los componentes
    private JLabel crearLabel(String texto, int x, int y, Font fuente, Color color, GridBagConstraints gbc) {
        JLabel label = new JLabel(texto);
        label.setFont(fuente);
        label.setForeground(color);
        gbc.gridx = x;
        gbc.gridy = y;
        add(label, gbc);
        return label;
    }

    private JTextField crearTextField(int x, int y, GridBagConstraints gbc) {
        JTextField txt = new JTextField();
        gbc.gridx = x;
        gbc.gridy = y;
        add(txt, gbc);
        return txt;
    }

    private JComboBox<String> crearComboBoxDesdeLista(List<?> lista, GridBagConstraints gbc, int x, int y) {
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        for (Object obj : lista) {
            if (obj instanceof Etnia etnia) modelo.addElement(etnia.getNombre());
            else if (obj instanceof Carrera carrera) modelo.addElement(carrera.getNombre());
            else if (obj instanceof Sede sede) modelo.addElement(sede.getNombre());
            else if (obj instanceof EstadoEstudiante estado) modelo.addElement(estado.getNombre());
            else if (obj instanceof Beca beca) modelo.addElement(beca.getNombre());
        }
        JComboBox<String> combo = new JComboBox<>(modelo);
        gbc.gridx = x;
        gbc.gridy = y;
        add(combo, gbc);
        return combo;
    }

    private void actualizarComboBecas() {
        comboBeca.removeAllItems();
        List<Beca> becas = BecasController.getBecas();
        for (Beca beca : becas) {
            comboBeca.addItem(beca.getNombre());
        }
    }

    private void actualizarComboEtnias() {
        comboEtnia.removeAllItems();
        List<Etnia> etnias = EstudiantesController.getEtnias();
        for (Etnia etnia : etnias) {
            comboEtnia.addItem(etnia.getNombre());
        }
    }

    private void actualizarComboCarreras() {
        comboCarrera.removeAllItems();
        List<Carrera> carreras = CarrerasController.getCarreras();
        for (Carrera carrera : carreras) {
            comboCarrera.addItem(carrera.getNombre());
        }
    }

    private void actualizarComboEstados() {
        comboEstado.removeAllItems();
        List<EstadoEstudiante> estados = EstudiantesController.getEstadoEstudiantes();
        for (EstadoEstudiante estadoEstudiante : estados) {
            comboEstado.addItem(estadoEstudiante.getNombre());
        }
    }

    private void actualizarComboSedes() {
        comboSede.removeAllItems();
        List<Sede> sedes = CarrerasController.getSedes();
        for (Sede sede : sedes) {
            comboSede.addItem(sede.getNombre());
        }
    }



}