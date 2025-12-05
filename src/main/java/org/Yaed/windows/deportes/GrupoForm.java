package org.Yaed.windows.deportes;

import org.Yaed.controller.DeportesController;
import org.Yaed.controller.EstudiantesController;
import org.Yaed.entity.CategoriaDeportes;
import org.Yaed.entity.GrupoDeporte;
import org.Yaed.entity.Estudiante;
import org.Yaed.entity.GruposDeportesEstudiantes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GrupoForm extends JDialog {

    private JTextField tfNombre;
    private JSpinner spIntegrantes;
    private JComboBox<String> cbCategoria;
    private JComboBox<String> cbSexo;
    private JTable tablaEstudiantes;
    private JLabel lblCount;

    private boolean saved = false;

    public GrupoForm(Window owner) {
        super(owner, "Crear grupo deportivo", ModalityType.APPLICATION_MODAL);
        init();
        pack();
        setResizable(false);
        setLocationRelativeTo(owner);
        setVisible(true);
    }

    private void init() {

        JPanel content = new JPanel(new BorderLayout(12, 12));
        content.setBorder(new EmptyBorder(12, 12, 12, 12));
        content.setBackground(new Color(250, 250, 255));

        JLabel title = new JLabel("Crear grupo deportivo");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(new Color(25, 35, 60));
        content.add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nombre
        gbc.gridx = 0; gbc.gridy = 0;
        form.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        tfNombre = new JTextField(22);
        form.add(tfNombre, gbc);

        // Integrantes
        gbc.gridx = 0; gbc.gridy++;
        form.add(new JLabel("Integrantes (estimado):"), gbc);
        gbc.gridx = 1;
        spIntegrantes = new JSpinner(new SpinnerNumberModel(1, 1, 200, 1));
        form.add(spIntegrantes, gbc);

        // Categoría
        gbc.gridx = 0; gbc.gridy++;
        form.add(new JLabel("Categoría:"), gbc);
        gbc.gridx = 1;
        List<String> categorias = new ArrayList<>();
        for (CategoriaDeportes c : DeportesController.getAllCategoriasDeporte())
            categorias.add(c.getNombre());

        JPanel categoriaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        categoriaPanel.setOpaque(false);
        cbCategoria = new JComboBox<>(categorias.toArray(new String[0]));
        cbCategoria.setEditable(true);
        cbCategoria.setPreferredSize(new Dimension(200, 26));
        categoriaPanel.add(cbCategoria);

        JButton btnAddCat = new JButton("+");
        btnAddCat.setPreferredSize(new Dimension(30, 26));
        btnAddCat.setMargin(new Insets(0, 0, 0, 0));
        categoriaPanel.add(btnAddCat);

        form.add(categoriaPanel, gbc);

        btnAddCat.addActionListener(ev -> {
            String nuevaCat = JOptionPane.showInputDialog(
                    this,
                    "Nombre de la nueva categoría:",
                    "Agregar categoría",
                    JOptionPane.PLAIN_MESSAGE
            );

            if (nuevaCat != null) {
                nuevaCat = nuevaCat.trim();
                if (!nuevaCat.isEmpty()) {

                    boolean existe = false;
                    for (CategoriaDeportes c : DeportesController.getAllCategoriasDeporte()) {
                        if (c.getNombre().equalsIgnoreCase(nuevaCat)) {
                            existe = true;
                            break;
                        }
                    }

                    if (existe) {
                        JOptionPane.showMessageDialog(this,
                                "Esa categoría ya existe.",
                                "Aviso",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    CategoriaDeportes nueva = new CategoriaDeportes();
                    nueva.setNombre(nuevaCat);
                    DeportesController.saveCategoriaDeporte(nueva);

                    cbCategoria.addItem(nuevaCat);
                    cbCategoria.setSelectedItem(nuevaCat);
                }
            }
        });

        // Sexo
        gbc.gridx = 0; gbc.gridy++;
        form.add(new JLabel("Sexo:"), gbc);
        gbc.gridx = 1;
        cbSexo = new JComboBox<>(new String[]{"Masculino", "Femenino", "Mixto", "Otro"});
        form.add(cbSexo, gbc);

        // Tabla estudiantes
        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        form.add(new JLabel("Seleccionar estudiantes:"), gbc);

        gbc.gridy++;
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Nombre", "Apellido", "Carnet"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tablaEstudiantes = new JTable(model);
        tablaEstudiantes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        List<Estudiante> ests = EstudiantesController.getDeportesEstudiantes();
        for (Estudiante e : ests) {
            model.addRow(new Object[]{
                    e.getNombre(),
                    e.getApellido(),
                    e.getCarnet()
            });
        }

        JScrollPane spTable = new JScrollPane(tablaEstudiantes);
        spTable.setPreferredSize(new Dimension(400, 150));
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        form.add(spTable, gbc);

        // ❗ Limpia selección inicial
        SwingUtilities.invokeLater(() -> tablaEstudiantes.clearSelection());

        gbc.gridy++;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        lblCount = new JLabel("Seleccionados: 0");
        form.add(lblCount, gbc);

        content.add(form, BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCancel = new JButton("Cancelar");
        JButton btnOk = new JButton("Guardar");

        btnCancel.addActionListener(e -> dispose());

        btnOk.addActionListener(e -> guardarGrupo());

        actions.add(btnCancel);
        actions.add(btnOk);
        content.add(actions, BorderLayout.SOUTH);

        tablaEstudiantes.getSelectionModel().addListSelectionListener(
                e -> lblCount.setText("Seleccionados: " + tablaEstudiantes.getSelectedRowCount())
        );

        setContentPane(content);
    }

    private void guardarGrupo() {
        if (tfNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Ingrese un nombre para el grupo.");
            return;
        }

        int[] sel = tablaEstudiantes.getSelectedRows();
        if (sel.length == 0) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione al menos un estudiante.");
            return;
        }

        List<Estudiante> estudiantesSeleccionados = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) tablaEstudiantes.getModel();

        for (int r : sel) {
            String carnet = (String) model.getValueAt(r, 2);
            Estudiante es = EstudiantesController.getEstudianteDeporteByCarnet(carnet);

            GruposDeportesEstudiantes asignacion =
                    DeportesController.getGrupoDeporteEstudiantePorCarnet(carnet);

            if (asignacion != null) {
                JOptionPane.showMessageDialog(this,
                        "El estudiante ya está en otro grupo:\n" +
                                es.getNombre() + " " + es.getApellido(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            estudiantesSeleccionados.add(es);
        }

        String catNom = cbCategoria.getEditor().getItem().toString().trim();
        CategoriaDeportes categoriaSel = null;
        for (CategoriaDeportes c : DeportesController.getAllCategoriasDeporte()) {
            if (c.getNombre().equalsIgnoreCase(catNom)) {
                categoriaSel = c;
                break;
            }
        }

        if (categoriaSel == null) {
            JOptionPane.showMessageDialog(this,
                    "La categoría no es válida o no existe.");
            return;
        }

        GrupoDeporte grupoNuevo = new GrupoDeporte();
        grupoNuevo.setNombre(tfNombre.getText().trim());
        grupoNuevo.setCategoria(categoriaSel);
        DeportesController.saveGrupoDeporte(grupoNuevo);

        for (Estudiante estSel : estudiantesSeleccionados) {
            GruposDeportesEstudiantes gde = new GruposDeportesEstudiantes();
            gde.setEstudiante(estSel);
            gde.setGrupoDeporte(grupoNuevo);
            DeportesController.saveGruposDeporteaEstudiante(gde);
        }

        saved = true;
        JOptionPane.showMessageDialog(this,
                "Grupo creado y estudiantes asignados correctamente.",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    public boolean isSaved() { return saved; }
    public String getNombre() { return tfNombre.getText().trim(); }
    public String getCategoria() { return cbCategoria.getEditor().getItem().toString().trim(); }
    public String getSexo() { return (String) cbSexo.getSelectedItem(); }
}
