package org.Yaed.windows.becas;

import org.Yaed.controller.EstudiantesController;
import org.Yaed.entity.Estudiante;
import org.Yaed.windows.AgregarEstudiante;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Objects;

public class BecasAdmin extends JFrame {

    public BecasAdmin() {
        TableRowSorter<DefaultTableModel> sorter;
        setTitle("SCeBE - Becas Administrador");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        // Poner icono
        ImageIcon icono = new ImageIcon(Objects.requireNonNull(getClass().getResource("/logo.png")));
        setIconImage(icono.getImage());
        // Colores
        Color leftColor = new Color(10, 20, 60);   // Azul oscuro
        Color rightColor = new Color(40, 51, 106);  // Rojo vino
        Color textColor = Color.WHITE;
        Font fuente = new Font("Outfit", Font.BOLD, 15);
        Font fuente1 = new Font("Outfit", Font.BOLD, 14);
        Font font = new Font("Outfit", Font.PLAIN, 10);

        // Panel izquierdo - tamaño fijo
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(leftColor);
        leftPanel.setPreferredSize(new Dimension(180, getHeight()));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JButton leftButton = new JButton("UBC");
        leftButton.setForeground(textColor);
        leftButton.setBackground(leftColor);
        leftButton.setFont(fuente);
        // Estilo sin bordes ni marco
        leftButton.setBorderPainted(false);
        leftButton.setFocusPainted(false);
        leftButton.setContentAreaFilled(true);
        // Guardar el color original
        Color normalBg = leftButton.getBackground();
        Color hoverBg = leftColor.darker(); // Fondo más oscuro
        // Efecto hover en fondo
        leftButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                leftButton.setBackground(hoverBg);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                leftButton.setBackground(normalBg);
            }
        });
        // Acción al hacer clic
        leftButton.addActionListener(e -> {
            new BecasAdmin().setVisible(true);
            dispose();
        });
        leftButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(leftButton);

        // Panel derecho - ocupa el resto del espacio
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(rightColor);
        rightPanel.setLayout(new BorderLayout());

        // Panel superior (header pequeño)
        JPanel panelUp = new JPanel();
        panelUp.setBackground(rightColor);
        panelUp.setPreferredSize(new Dimension(0, 60)); // Altura fija para header
        panelUp.setLayout(new FlowLayout(FlowLayout.LEFT));
        rightPanel.add(panelUp, BorderLayout.NORTH);
        JTextField searchField = new JTextField(20);
        searchField.setPreferredSize(new Dimension(100, 30));
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        searchField.setForeground(Color.WHITE);

        Color bgColor = new Color(14, 34, 71); // Color de fondo
        searchField.setBackground(bgColor);
        searchField.setCaretColor(Color.WHITE);

        // Borde del mismo color y redondeado
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(bgColor, 1, true), // mismo color que el fondo, redondeado
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));

        JButton searchButton = new JButton("Buscar"); // temporal
        searchButton.setPreferredSize(new Dimension(100, 30));
        searchButton.setBackground(new Color(60, 80, 140));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        searchButton.setBorderPainted(false);
        JButton menuButton = new JButton("Opciones");
        menuButton.setPreferredSize(new Dimension(100, 30));
        menuButton.setForeground(Color.WHITE);
        menuButton.setBackground(new Color(60, 80, 140));
        menuButton.setFocusPainted(false);
        menuButton.setBorderPainted(false);
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem item1 = new JMenuItem("Activos");
        item1.setForeground(Color.WHITE);
        item1.setBackground(new Color(60, 80, 140));
        JMenuItem item2 = new JMenuItem("Inactivos");
        item2.setForeground(Color.WHITE);
        item2.setBackground(new Color(60, 80, 140));
        JMenuItem item3 = new JMenuItem("Egresados");
        item3.setForeground(Color.WHITE);
        item3.setBackground(new Color(60, 80, 140));
        popupMenu.add(item1);
        popupMenu.add(item2);
        popupMenu.add(item3);
        // Acción al hacer clic en el botón
        menuButton.addActionListener(e -> {
            popupMenu.show(menuButton, 0, menuButton.getHeight());
        });


        JButton addButton = new JButton("Agregar");
        addButton.setPreferredSize(new Dimension(100, 30));
        addButton.setBackground(new Color(60, 80, 140));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setBorderPainted(false);

        addButton.addActionListener(e -> {
            new AgregarEstudiante().setVisible(true);
        });

        JButton editButton = new JButton("Editar");
        editButton.setPreferredSize(new Dimension(100, 30));
        editButton.setBackground(new Color(60, 80, 140));
        editButton.setForeground(Color.WHITE);
        editButton.setFocusPainted(false);
        editButton.setBorderPainted(false);

        JButton deleteButton = new JButton("Eliminar");
        deleteButton.setPreferredSize(new Dimension(100, 30));
        deleteButton.setBackground(new Color(60, 80, 140));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        deleteButton.setBorderPainted(false);

        // Agregar al panel
        panelUp.add(searchField);
        panelUp.add(searchButton);
        panelUp.add(menuButton);
        panelUp.add(addButton);
        panelUp.add(editButton);
        panelUp.add(deleteButton);

        // Panel inferior (ocupa todo el espacio restante)
        JPanel panelDown = new JPanel();
        panelDown.setBackground(rightColor);
        panelDown.setLayout(new BorderLayout()); // 20px horizontal, 10px vertical

        rightPanel.add(panelDown, BorderLayout.CENTER);

        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.setBackground(leftColor);

        //tabla
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Genero");
        model.addColumn("Apellidos");
        model.addColumn("Carnet");
        model.addColumn("Etnia");

        JTable tablaEstudiantes = new JTable(model);
        tablaEstudiantes.setBackground(leftColor);
        tablaEstudiantes.setForeground(Color.WHITE);
        tablaEstudiantes.setFont(font);
        tablaEstudiantes.getTableHeader().setBackground(rightColor);
        tablaEstudiantes.getTableHeader().setForeground(Color.WHITE);
        tablaEstudiantes.getTableHeader().setFont(new Font("Outfit", Font.BOLD, 13));

        sorter = new TableRowSorter<>(model);
        tablaEstudiantes.setRowSorter(sorter);
        searchField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String texto = searchField.getText();
                if (texto.trim().isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto, 0));
                }
            }
        });


        JScrollPane scrollPane = new JScrollPane(tablaEstudiantes);
        scrollPane.getViewport().setBackground(leftColor); // Color de fondo del área visible
        scrollPane.setBackground(leftColor);
        scrollPane.setPreferredSize(new Dimension(750, 600));
        panel1.setBackground(leftColor);
        panel1.add(scrollPane, BorderLayout.CENTER);
        addRows(model);
        panelDown.add(panel1, BorderLayout.CENTER);

        item1.addActionListener(e -> {
            addActiveRows(model);
        });
        item2.addActionListener(e -> {
            addRetiredRows(model);
        });
        item3.addActionListener(e -> {
            addEgressedRows(model);
        });
        searchButton.addActionListener(e ->{
            addRows(model);
        });

        deleteButton.addActionListener(e -> {
            int filaSeleccionada = tablaEstudiantes.getSelectedRow();

            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int totalColumnas = tablaEstudiantes.getColumnCount();
            String carnet = tablaEstudiantes.getValueAt(filaSeleccionada, totalColumnas - 2).toString();

            // Buscar al estudiante por carnet
            List<Estudiante> estudiantes = EstudiantesController.getEstudiantes();
            Estudiante estudianteAEliminar = null;

            for (Estudiante estudiante : estudiantes) {
                if (estudiante.getCarnet().equalsIgnoreCase(carnet)) {
                    estudianteAEliminar = estudiante;
                    break;
                }
            }

            if (estudianteAEliminar != null) {
                int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar al estudiante con carnet: " + carnet + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    EstudiantesController.deleteEstudiante(estudianteAEliminar);
                    JOptionPane.showMessageDialog(null, "Estudiante eliminado exitosamente.");
                    // Actualiza la tabla si es necesario
                }
            } else {
                JOptionPane.showMessageDialog(null, "Estudiante no encontrado en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        // Agregar los paneles principales al frame
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
        setVisible(true);
    }
    public static void addRows (DefaultTableModel model) {
        model.setRowCount(0);
        List<Estudiante> estudianteList = EstudiantesController.getEstudiantes();
        for (Estudiante estudiante : estudianteList) {
           String nombre = estudiante.getNombre();
           char genero   = estudiante.getSexo();
           String apellido = estudiante.getApellido();
           String carnet = estudiante.getCarnet();
           String nombreEtnia = estudiante.getEtnia().getNombre();
           model.addRow(new Object[]{nombre, genero, apellido, carnet, nombreEtnia});
        }
    }
    private static void addActiveRows(DefaultTableModel model) {
        model.setRowCount(0);
        List<Estudiante> estudianteList = EstudiantesController.getEstudiantes();
        for (Estudiante estudiante : estudianteList) {
            int id = estudiante.getEstado().getId();
            if (id == 1){
                String nombre = estudiante.getNombre();
                char genero   = estudiante.getSexo();
                String apellido = estudiante.getApellido();
                String carnet = estudiante.getCarnet();
                String nombreEtnia = estudiante.getEtnia().getNombre();
                model.addRow(new Object[]{nombre, genero, apellido, carnet, nombreEtnia});
            }
        }
    }
    private static void addEgressedRows(DefaultTableModel model) {
        model.setRowCount(0);
        List<Estudiante> estudianteList = EstudiantesController.getEstudiantes();
        for (Estudiante estudiante : estudianteList) {
            int id = estudiante.getEstado().getId();
            if (id == 2){
                String nombre = estudiante.getNombre();
                char genero   = estudiante.getSexo();
                String apellido = estudiante.getApellido();
                String carnet = estudiante.getCarnet();
                String nombreEtnia = estudiante.getEtnia().getNombre();
                model.addRow(new Object[]{nombre, genero, apellido, carnet, nombreEtnia});
            }
        }
    }
    private static void addRetiredRows(DefaultTableModel model) {
        model.setRowCount(0);
        List<Estudiante> estudianteList = EstudiantesController.getEstudiantes();
        for (Estudiante estudiante : estudianteList) {
            int id = estudiante.getEstado().getId();
            if (id == 3){
                String nombre = estudiante.getNombre();
                char genero   = estudiante.getSexo();
                String apellido = estudiante.getApellido();
                String carnet = estudiante.getCarnet();
                String nombreEtnia = estudiante.getEtnia().getNombre();
                model.addRow(new Object[]{nombre, genero, apellido, carnet, nombreEtnia});
            }
        }
    }
}