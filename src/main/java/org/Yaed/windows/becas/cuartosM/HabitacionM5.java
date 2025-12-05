package org.Yaed.windows.becas.cuartosM;

import org.Yaed.controller.HabController;
import org.Yaed.entity.Habitacion;
import org.Yaed.entity.HabitacionesEstudiantes;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class HabitacionM5 extends JFrame {
    public HabitacionM5() {
        setTitle("Casa Mediana 5");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        // Poner icono
        ImageIcon icono = new ImageIcon(Objects.requireNonNull(getClass().getResource("/logo.png")));
        setIconImage(icono.getImage());
        // Colores similares a otras ventanas
        Color bgColor = new Color(245, 245, 245);
        Color accentColor = new Color(0, 102, 204);
        Color tableHeaderColor = new Color(220, 220, 220);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(bgColor);
        mainPanel.setLayout(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel titleLabel = new JLabel("Casa Mediana 5 (Masculino)");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setForeground(accentColor);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel subtitleLabel = new JLabel("Cuarto 5");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitleLabel.setForeground(Color.DARK_GRAY);
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel selectorPanel = new JPanel();
        selectorPanel.setBackground(bgColor);
        selectorPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JLabel mesLabel = new JLabel("Seleccionar periodo:");
        mesLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JLabel anioLabel = new JLabel("Año:");
        anioLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JComboBox<String> anioCombo = new JComboBox<>(new String[]{
                "2023", "2024", "2025"
        });
        selectorPanel.add(mesLabel);
        selectorPanel.add(anioLabel);
        selectorPanel.add(anioCombo);

        // Tabla bonita con 5 columnas
        String[] columnas = {"Nombre", "Apellido", "Edad", "Etnia", "Semestre"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);
        JTable tabla = new JTable(model);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabla.setRowHeight(28);
        tabla.setSelectionBackground(new Color(204, 229, 255));
        tabla.setSelectionForeground(Color.BLACK);

        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        tabla.getTableHeader().setBackground(tableHeaderColor);
        tabla.getTableHeader().setForeground(accentColor);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBorder(BorderFactory.createLineBorder(accentColor, 1));

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.setBackground(bgColor);
        topPanel.add(titleLabel);
        topPanel.add(subtitleLabel);

        anioCombo.addActionListener(e -> {
            String anioSeleccionado = (String) anioCombo.getSelectedItem();
            if (anioSeleccionado != null) {
                switch (anioSeleccionado) {
                    case "2023":
                        addRows23(model);
                        break;
                    case "2024":
                        addRows24(model);
                        break;
                    case "2025":
                        addRows25(model);
                        break;
                }
            }
        });

        JPanel centerPanel = new JPanel(new BorderLayout(0, 15));
        centerPanel.setBackground(bgColor);
        centerPanel.add(selectorPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout(0, 15));
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
    }
    public static void addRows25(DefaultTableModel model) {
        model.setRowCount(0);
        java.util.List<HabitacionesEstudiantes> habsEst = HabController.getHabitacionesEstudiantes();
        for (HabitacionesEstudiantes habEst : habsEst) {
            // Verificamos año y semestre
            if ("2025".equals(habEst.getAnio()) && "I Semestre".equals(habEst.getFecha())) {
                Habitacion hab1 = habEst.getHabitacion();
                int idHab = hab1.getId(); // Obtenemos el ID de la habitación

                // Verificamos si el ID de la habitación es 1, 2 o 3
                if (idHab == 31) {
                    model.addRow(new Object[]{
                            habEst.getEstudiante().getNombre(),
                            habEst.getEstudiante().getApellido(),
                            habEst.getEstudiante().getEdad(),
                            habEst.getEstudiante().getEtnia().getNombre(),
                            habEst.getFecha(),
                    });
                }
            }
        }
    }
    public static void addRows24(DefaultTableModel model) {
        model.setRowCount(0);
        java.util.List<HabitacionesEstudiantes> habsEst = HabController.getHabitacionesEstudiantes();
        for (HabitacionesEstudiantes habEst : habsEst) {
            // Verificamos año y semestre
            if ("2024".equals(habEst.getAnio()) && "I Semestre".equals(habEst.getFecha())) {
                Habitacion hab1 = habEst.getHabitacion();
                int idHab = hab1.getId(); // Obtenemos el ID de la habitación
                // Verificamos si el ID de la habitación es 1, 2 o 3
                if (idHab == 31) {
                    model.addRow(new Object[]{
                            habEst.getEstudiante().getNombre(),
                            habEst.getEstudiante().getApellido(),
                            habEst.getEstudiante().getEdad(),
                            habEst.getEstudiante().getEtnia().getNombre(),
                            habEst.getFecha(),
                    });
                }
            }
        }
    }
    public static void addRows23(DefaultTableModel model) {
        model.setRowCount(0);
        List<HabitacionesEstudiantes> habsEst = HabController.getHabitacionesEstudiantes();
        for (HabitacionesEstudiantes habEst : habsEst) {
            // Verificamos año y semestre
            if ("2024".equals(habEst.getAnio()) && "I Semestre".equals(habEst.getFecha())) {
                Habitacion hab1 = habEst.getHabitacion();
                int idHab = hab1.getId(); // Obtenemos el ID de la habitación
                // Verificamos si el ID de la habitación es 1, 2 o 3
                if (idHab == 31) {
                    model.addRow(new Object[]{
                            habEst.getEstudiante().getNombre(),
                            habEst.getEstudiante().getApellido(),
                            habEst.getEstudiante().getEdad(),
                            habEst.getEstudiante().getEtnia().getNombre(),
                            habEst.getFecha(),
                    });
                }
            }
        }
    }

}