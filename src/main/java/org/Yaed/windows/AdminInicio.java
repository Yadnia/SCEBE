package org.Yaed.windows;

import org.Yaed.controller.UserController;
import org.Yaed.entity.Usuario;
import org.Yaed.windows.becas.BecasAdmin;
import org.Yaed.windows.cultura.CulturaAdmin;
import org.Yaed.windows.deportes.DeportesAdmin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Objects;

import org.apache.poi.xssf.usermodel.*;

public class AdminInicio extends JFrame {

    private DefaultTableModel modelo;

    public AdminInicio() {
        setTitle("SCeBE - Panel Opciones");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        // Poner icono
        ImageIcon icono = new ImageIcon(Objects.requireNonNull(getClass().getResource("/logo.png")));
        setIconImage(icono.getImage());

        Color leftColor = new Color(10, 20, 60);
        Color rightColor = new Color(40, 51, 106);
        Color textColor = Color.WHITE;
        Font fuenteBoton = new Font("Outfit", Font.BOLD, 15);

        // Panel izquierdo con botones
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(leftColor);
        leftPanel.setPreferredSize(new Dimension(180, getHeight()));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JButton opcionesButton = crearBoton("Opciones", leftColor, fuenteBoton, e -> {
            System.out.println("Botón Opciones clickeado");
        });

        JButton becasButton = crearBoton("Becas", leftColor, fuenteBoton, e -> new BecasAdmin().setVisible(true));
        JButton deportesButton = crearBoton("Deportes", leftColor, fuenteBoton, e -> new DeportesAdmin().setVisible(true));
        JButton culturaButton = crearBoton("Cultura", leftColor, fuenteBoton, e -> new CulturaAdmin().setVisible(true));
        JButton exportButton = crearBoton("Exportar a Excel", leftColor, fuenteBoton, e -> exportarTablaExcel());

        // Añadir botones con espaciado
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(opcionesButton);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(becasButton);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(deportesButton);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(culturaButton);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(exportButton);

        // Panel derecho con tabla
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(rightColor);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Usuarios", SwingConstants.CENTER);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Outfit", Font.BOLD, 22));
        rightPanel.add(titulo, BorderLayout.NORTH);

        modelo = new DefaultTableModel(
                new Object[]{"Nombre", "Apellido", "Tipo", "Usuario"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        addRows();

        JTable tablaUsuarios = new JTable(modelo);
        tablaUsuarios.setFillsViewportHeight(true);
        tablaUsuarios.setSelectionBackground(new Color(90, 150, 255));
        tablaUsuarios.setSelectionForeground(Color.WHITE);
        tablaUsuarios.getTableHeader().setReorderingAllowed(false);
        tablaUsuarios.getTableHeader().setBackground(leftColor);
        tablaUsuarios.getTableHeader().setForeground(textColor);
        tablaUsuarios.getTableHeader().setFont(new Font("Outfit", Font.BOLD, 14));
        tablaUsuarios.setFont(new Font("Outfit", Font.PLAIN, 14));
        tablaUsuarios.setRowHeight(25);

        rightPanel.add(new JScrollPane(tablaUsuarios), BorderLayout.CENTER);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JButton crearBoton(String texto, Color bgColor, Font font, ActionListener action) {
        JButton boton = new JButton(texto);
        boton.setForeground(Color.WHITE);
        boton.setBackground(bgColor);
        boton.setFont(font);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setContentAreaFilled(true);
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.addActionListener(action);

        Color normalBg = boton.getBackground();
        Color hoverBg = bgColor.darker();

        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent evt) { boton.setBackground(hoverBg); }
            public void mouseExited(MouseEvent evt) { boton.setBackground(normalBg); }
        });
        return boton;
    }

    private void addRows() {
        modelo.setRowCount(0);
        List<Usuario> usuarios = UserController.getUsers();
        for (Usuario user : usuarios) {
            modelo.addRow(new Object[]{user.getNombres(), user.getApellidos(),
                    user.getTipoUsuario().getNombre(), user.getUsuario()});
        }
    }

    private void exportarTablaExcel() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar archivo Excel");
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try (XSSFWorkbook workbook = new XSSFWorkbook()) {
                XSSFSheet sheet = workbook.createSheet("Usuarios");

                // Encabezado
                XSSFRow headerRow = sheet.createRow(0);
                for (int col = 0; col < modelo.getColumnCount(); col++) {
                    headerRow.createCell(col).setCellValue(modelo.getColumnName(col));
                }

                // Filas
                for (int row = 0; row < modelo.getRowCount(); row++) {
                    XSSFRow excelRow = sheet.createRow(row + 1);
                    for (int col = 0; col < modelo.getColumnCount(); col++) {
                        Object value = modelo.getValueAt(row, col);
                        excelRow.createCell(col).setCellValue(value != null ? value.toString() : "");
                    }
                }

                // Guardar archivo
                FileOutputStream fileOut = new FileOutputStream(fileChooser.getSelectedFile() + ".xlsx");
                workbook.write(fileOut);
                fileOut.close();
                JOptionPane.showMessageDialog(this, "Usuarios exportados correctamente!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al exportar: " + ex.getMessage());
            }
        }
    }

}