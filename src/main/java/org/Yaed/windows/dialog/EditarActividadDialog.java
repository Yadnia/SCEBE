package org.Yaed.windows.dialog;

import com.github.lgooddatepicker.components.DatePicker;
import org.Yaed.controller.DeportesController;
import org.Yaed.entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EditarActividadDialog extends JDialog {

    private JTextField tfNombre;
    private JComboBox<String> cbGrupo;
    private DatePicker datePicker;
    private int rowSeleccionada;

    public EditarActividadDialog(Frame owner, int row, DefaultTableModel modelo) {
        super(owner, "Editar Actividad", true);
        this.rowSeleccionada = row;
        initUI(modelo);
        pack();
        setResizable(false);
        setLocationRelativeTo(owner);
    }

    private void initUI(DefaultTableModel modelo) {
        JPanel content = new JPanel(new BorderLayout(10, 10));
        content.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        content.setBackground(Color.WHITE);

        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ===== Fecha con DatePicker =====
        gbc.gridx = 0;
        gbc.gridy = 0;
        form.add(new JLabel("Fecha:"), gbc);
        gbc.gridx = 1;
        datePicker = new DatePicker();
        // Establecer fecha actual de la tabla
        String fechaStr = modelo.getValueAt(rowSeleccionada, 0).toString();
        try {
            datePicker.setDate(java.time.LocalDate.parse(fechaStr));
        } catch (Exception e) {
            // si hay error de parseo, dejamos la fecha vacía
        }
        form.add(datePicker, gbc);

        // ===== Nombre Actividad =====
        gbc.gridx = 0;
        gbc.gridy++;
        form.add(new JLabel("Actividad:"), gbc);
        gbc.gridx = 1;
        tfNombre = new JTextField(20);
        tfNombre.setText(modelo.getValueAt(rowSeleccionada, 1).toString());
        form.add(tfNombre, gbc);

        // ===== Grupo =====
        gbc.gridx = 0;
        gbc.gridy++;
        form.add(new JLabel("Grupo:"), gbc);
        gbc.gridx = 1;
        cbGrupo = new JComboBox<>();
        List<GrupoDeporte> grupos = DeportesController.getAllGruposDeporte();
        for (GrupoDeporte g : grupos) cbGrupo.addItem(g.getNombre());
        cbGrupo.setSelectedItem(modelo.getValueAt(rowSeleccionada, 2).toString());
        form.add(cbGrupo, gbc);

        content.add(form, BorderLayout.CENTER);

        // ===== Botones =====
        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnGuardar = new JButton("Guardar");

        btnCancelar.addActionListener(e -> dispose());

        btnGuardar.addActionListener(e -> {
            String fecha = datePicker.getDate() != null ? datePicker.getDate().toString() : "";
            String actividadNombre = tfNombre.getText().trim();
            String grupo = cbGrupo != null ? String.valueOf(cbGrupo.getSelectedItem()) : "—";

            // =======================
            // 1. Actualizar actividad
            // =======================
            ActividadDeporte actividad = new ActividadDeporte();
            actividad.setNombre(actividadNombre);

            DeportesController.updateActividadDeporte(actividad);

            // =======================
            // 2. Obtener estudiantes del grupo
            // =======================
            List<GruposDeportesEstudiantes> gruposEst =
                    DeportesController.getAllGruposDeportesEstudiantes();

            for (GruposDeportesEstudiantes ge : gruposEst) {
                if (ge.getGrupoDeporte().getNombre().equalsIgnoreCase(grupo)) {
                    Estudiante est = ge.getEstudiante();

                    // =======================
                    // 3. Actualizar relación actividad-estudiante
                    // =======================
                    ActividadesDeportesEstudiantes rel = new ActividadesDeportesEstudiantes();
                    rel.setActividad(actividad);
                    rel.setEstudiante(est);
                    rel.setFecha(fecha);

                    DeportesController.updateActividadesGruposDeportes(rel);
                }
            }

            dispose();
        });

        acciones.add(btnCancelar);
        acciones.add(btnGuardar);
        content.add(acciones, BorderLayout.SOUTH);

        setContentPane(content);
    }
}
