package org.Yaed.windows.becas;

import org.Yaed.controller.PastController;
import org.Yaed.entity.PastHabs;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class OrganizacionesAnterioresVentana extends JFrame {
    public OrganizacionesAnterioresVentana() {
        setTitle("Organizaciones anteriores");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        // Colores y fuentes
        Color bgColor = new Color(40, 51, 106);
        Color headerColor = new Color(14, 34, 71);
        Color textColor = Color.WHITE;
        Font titleFont = new Font("Outfit", Font.BOLD, 28);
        Font tableFont = new Font("Outfit", Font.PLAIN, 16);
        // Poner icono
        ImageIcon icono = new ImageIcon(Objects.requireNonNull(getClass().getResource("/logo.png")));
        setIconImage(icono.getImage());
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(bgColor);

        JLabel titleLabel = new JLabel("Organizaciones anteriores", SwingConstants.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(textColor);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Modelo de tabla
        String[] columnas = {"cuarto", "nombres", "edad", "etnia"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        addRows(model);

        JTable table = new JTable(model);
        table.setFont(tableFont);
        table.setRowHeight(28);
        table.setForeground(textColor);
        table.setBackground(bgColor);
        table.setGridColor(headerColor);
        table.setSelectionBackground(headerColor);
        table.setSelectionForeground(textColor);

        // Encabezado de tabla personalizado
        JTableHeader header = table.getTableHeader();
        header.setBackground(headerColor);
        header.setForeground(textColor);
        header.setFont(tableFont.deriveFont(Font.BOLD));
        ((DefaultTableCellRenderer)header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        // Centrar celdas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 40));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        setContentPane(mainPanel);
        setVisible(true);
    }
    public static void addRows(DefaultTableModel model) {
        model.setRowCount(0);
        List<PastHabs> habsEst = PastController.getPastHabs();
        for (PastHabs habEst : habsEst) {
            model.addRow(new Object[]{
                    habEst.getHabitacion().getNombre(),
                    habEst.getEstudiante().getNombre(),
                    habEst.getEstudiante().getApellido(),
                    habEst.getEstudiante().getEdad(),
                    habEst.getEstudiante().getEtnia().getNombre(),

            });
        }
    }
}
