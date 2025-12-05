package org.Yaed.windows;

import org.Yaed.controller.BecasController;
import org.Yaed.controller.CarrerasController;
import org.Yaed.controller.EstudiantesController;
import org.Yaed.entity.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class EditarEstudiante extends JFrame {

    private JTextField txtCarnet, txtNombres, txtApellidos, txtCelular;
    private JComboBox<String> comboEtnia, comboCarrera, comboSede, comboGenero, comboEstado, comboBeca;

    private JLabel lblCarnet, lblNombres, lblApellidos, lblCelular, lblEtnia, lblCarrera, lblSede, lblGenero, lblEstado, lblBeca;

    private JButton btnConfirmar, btnCancelar;

    private List<Etnia> etnias;
    private List<Carrera> carreras;
    private List<Sede> sedes;
    private List<EstadoEstudiante> estados;
    private List<Beca> becas;

    private Estudiante estudiante;

    public EditarEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
        setTitle("Editar Estudiante");
        setSize(750, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        // Poner icono
        ImageIcon icono = new ImageIcon(Objects.requireNonNull(getClass().getResource("/logo.png")));
        setIconImage(icono.getImage());

        getContentPane().setBackground(new Color(14, 34, 71));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font fuente = new Font("Arial", Font.BOLD, 13);
        Color colorTexto = Color.WHITE;

        // Carnet (no editable)
        lblCarnet = crearLabel("Carnet:", 0, 0, fuente, colorTexto, gbc);
        txtCarnet = crearTextField(1, 0, gbc);
        txtCarnet.setText(estudiante.getCarnet());
        txtCarnet.setEditable(false);

        lblNombres = crearLabel("Nombres:", 0, 1, fuente, colorTexto, gbc);
        txtNombres = crearTextField(1, 1, gbc);
        txtNombres.setText(estudiante.getNombre());

        lblApellidos = crearLabel("Apellidos:", 0, 2, fuente, colorTexto, gbc);
        txtApellidos = crearTextField(1, 2, gbc);
        txtApellidos.setText(estudiante.getApellido());

        lblCelular = crearLabel("Celular (xxxx-xxxx):", 0, 3, fuente, colorTexto, gbc);
        txtCelular = crearTextField(1, 3, gbc);
        txtCelular.setText(estudiante.getTelefono());

        // Etnia
        lblEtnia = crearLabel("Etnia:", 0, 4, fuente, colorTexto, gbc);
        etnias = EstudiantesController.getEtnias();
        comboEtnia = crearComboBoxDesdeLista(etnias, gbc, 1, 4);
        comboEtnia.setSelectedItem(estudiante.getEtnia() != null ? estudiante.getEtnia().getNombre() : null);

        // Carrera
        lblCarrera = crearLabel("Carrera:", 0, 5, fuente, colorTexto, gbc);
        carreras = CarrerasController.getCarreras();
        comboCarrera = crearComboBoxDesdeLista(carreras, gbc, 1, 5);
        comboCarrera.setSelectedItem(estudiante.getCarrera() != null ? estudiante.getCarrera().getNombre() : null);

        // Sede
        lblSede = crearLabel("Sede:", 0, 6, fuente, colorTexto, gbc);
        sedes = CarrerasController.getSedes();
        comboSede = crearComboBoxDesdeLista(sedes, gbc, 1, 6);
        comboSede.setSelectedItem(estudiante.getSede() != null ? estudiante.getSede().getNombre() : null);

        // Género
        lblGenero = crearLabel("Género:", 0, 7, fuente, colorTexto, gbc);
        comboGenero = new JComboBox<>(new String[]{"Masculino", "Femenino", "Otro"});
        gbc.gridx = 1;
        gbc.gridy = 7;
        add(comboGenero, gbc);
        if (estudiante.getSexo() == 'M') comboGenero.setSelectedItem("Masculino");
        else if (estudiante.getSexo() == 'F') comboGenero.setSelectedItem("Femenino");
        else comboGenero.setSelectedItem("Otro");

        // Estado
        lblEstado = crearLabel("Estado:", 0, 8, fuente, colorTexto, gbc);
        estados = EstudiantesController.getEstadoEstudiantes();
        comboEstado = crearComboBoxDesdeLista(estados, gbc, 1, 8);
        comboEstado.setSelectedItem(estudiante.getEstado() != null ? estudiante.getEstado().getNombre() : null);

        // Beca
        lblBeca = crearLabel("Tipo de beca:", 0, 9, fuente, colorTexto, gbc);
        becas = BecasController.getBecas();
        comboBeca = crearComboBoxDesdeLista(becas, gbc, 1, 9);
        comboBeca.setSelectedItem(estudiante.getBecaid() != null ? estudiante.getBecaid().getNombre() : null);

        // Botones Confirmar y Cancelar
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnConfirmar = new JButton("Guardar Cambios");
        btnCancelar = new JButton("Cancelar");

        panelBotones.add(btnConfirmar);
        panelBotones.add(btnCancelar);
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 3;
        add(panelBotones, gbc);

        btnCancelar.addActionListener(e -> dispose());
        btnConfirmar.addActionListener(e -> guardarCambios());
    }

    private void guardarCambios() {
        estudiante.setNombre(txtNombres.getText().trim());
        estudiante.setApellido(txtApellidos.getText().trim());
        estudiante.setTelefono(txtCelular.getText().trim());

        for (Etnia etnia : etnias)
            if (etnia.getNombre().equals(comboEtnia.getSelectedItem()))
                estudiante.setEtnia(etnia);

        for (Carrera carrera : carreras)
            if (carrera.getNombre().equals(comboCarrera.getSelectedItem()))
                estudiante.setCarrera(carrera);

        for (Sede sede : sedes)
            if (sede.getNombre().equals(comboSede.getSelectedItem()))
                estudiante.setSede(sede);

        switch (comboGenero.getSelectedItem().toString()) {
            case "Masculino" -> estudiante.setSexo('M');
            case "Femenino" -> estudiante.setSexo('F');
            default -> estudiante.setSexo('O');
        }

        for (EstadoEstudiante estado : estados)
            if (estado.getNombre().equals(comboEstado.getSelectedItem()))
                estudiante.setEstado(estado);

        for (Beca beca : becas)
            if (beca.getNombre().equals(comboBeca.getSelectedItem()))
                estudiante.setBecaid(beca);

        EstudiantesController.updateEstudiante(estudiante);
        JOptionPane.showMessageDialog(this, "Estudiante actualizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    // Métodos auxiliares
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
}
