package org.Yaed.windows.becas;

import org.Yaed.controller.ActController;
import org.Yaed.controller.EstudiantesController;
import org.Yaed.controller.HabController;
import org.Yaed.controller.PastController;
import org.Yaed.entity.Estudiante;
import org.Yaed.windows.AgregarEstudiante;
import org.Yaed.windows.EditarEstudiante;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Objects;

public class ListadoEstudiantes extends JFrame {

    public ListadoEstudiantes() {
        TableRowSorter<DefaultTableModel> sorter;
        setTitle("SCeBE - Listado Estudiantes");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        leftButton.setBorderPainted(false);
        leftButton.setFocusPainted(false);
        leftButton.setContentAreaFilled(true);

        Color normalBg = leftButton.getBackground();
        Color hoverBg = leftColor.darker();

        leftButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                leftButton.setBackground(hoverBg);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                leftButton.setBackground(normalBg);
            }
        });

//        leftButton.addActionListener(e -> {
//            new BecasAdmin().setVisible(true);
//            dispose();
//        });
        leftButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(leftButton);

        JButton listadoBttn = new JButton("Listado Estudiantes");
        listadoBttn.setForeground(textColor);
        listadoBttn.setBackground(leftColor);
        listadoBttn.setFont(fuente1);
        listadoBttn.setBorderPainted(false);
        listadoBttn.setFocusPainted(false);
        listadoBttn.setContentAreaFilled(true);

        Color normalBg1 = listadoBttn.getBackground();
        Color hoverBg1 = leftColor.darker();

        listadoBttn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                listadoBttn.setBackground(hoverBg1);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                listadoBttn.setBackground(normalBg1);
            }
        });

        listadoBttn.addActionListener(e -> {
            new ListadoEstudiantes();
            dispose();
        });

        listadoBttn.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(listadoBttn);

        JButton botonHabitaciones = new JButton("Organización de Habitaciones");
        botonHabitaciones.setForeground(textColor);
        botonHabitaciones.setBackground(leftColor);
        botonHabitaciones.setFont(fuente1);
        botonHabitaciones.setBorderPainted(false);
        botonHabitaciones.setFocusPainted(false);
        botonHabitaciones.setContentAreaFilled(true);
        botonHabitaciones.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonHabitaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonHabitaciones.setBackground(hoverBg1);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonHabitaciones.setBackground(normalBg1);
            }
        });
        botonHabitaciones.addActionListener(e -> {
            new Habitaciones();
            dispose();
        });
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(botonHabitaciones);

        JButton botonActividades = new JButton("Actividades");
        botonActividades.setForeground(textColor);
        botonActividades.setBackground(leftColor);
        botonActividades.setFont(fuente1);
        botonActividades.setBorderPainted(false);
        botonActividades.setFocusPainted(false);
        botonActividades.setContentAreaFilled(true);
        botonActividades.setAlignmentX(Component.CENTER_ALIGNMENT);
        Color normalBg2 = botonActividades.getBackground();
        Color hoverBg2 = leftColor.darker();
        botonActividades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonActividades.setBackground(hoverBg2);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonActividades.setBackground(normalBg2);
            }
        });
        botonActividades.addActionListener(e -> {
            new BecasInicio().setVisible(true);
            dispose();
        });

        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(botonActividades);

        JButton botonGenerar = new JButton("Generar");
        botonGenerar.setForeground(textColor);
        botonGenerar.setBackground(leftColor);
        botonGenerar.setFont(fuente1);
        botonGenerar.setBorderPainted(false);
        botonGenerar.setFocusPainted(false);
        botonGenerar.setContentAreaFilled(true);
        botonGenerar.setAlignmentX(Component.CENTER_ALIGNMENT);
        Color normalBg3 = botonGenerar.getBackground();
        Color hoverBg3 = leftColor.darker();
        botonGenerar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonGenerar.setBackground(hoverBg3);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonGenerar.setBackground(normalBg3);
            }
        });
        botonGenerar.addActionListener(e -> {
            int opcion = JOptionPane.showOptionDialog(
                    this,
                    "¿Qué desea generar?",
                    "Generar",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new Object[]{"Actividades", "Habitaciones"},
                    "Actividades"
            );
            if (opcion == JOptionPane.YES_OPTION) { // Actividades
                if (ActController.getActividades().isEmpty()) {
                    ActController.AsignarActividades();
                    JOptionPane.showMessageDialog(this, "Generación de Actividades completada.", "Información", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    int confirm = JOptionPane.showConfirmDialog(
                            this,
                            "Ya existen actividades asignadas. ¿Desea reestablecer las actividades y generar nuevas?",
                            "Reestablecer actividades",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    );
                    if (confirm == JOptionPane.YES_OPTION) {
                        PastController.respaldarActividades();
                        ActController.eliminarActividades();
                        ActController.AsignarActividades();
                        JOptionPane.showMessageDialog(this, "Generación de Actividades completada.", "Información", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } else if (opcion == JOptionPane.NO_OPTION) { // Habitaciones
                if (HabController.getHabitaciones().isEmpty()) {
                    HabController.AsignarMujeres();
                    HabController.AsignarHombres1();
                    HabController.AsignarHombres2();
                    JOptionPane.showMessageDialog(this, "Generación de Habitaciones completada.", "Información", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    int confirm = JOptionPane.showConfirmDialog(
                            this,
                            "Ya existen habitaciones asignadas. ¿Desea reestablecer las habitaciones y generar nuevas?",
                            "Reestablecer habitaciones",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    );
                    if (confirm == JOptionPane.YES_OPTION) {
                        PastController.respaldarHabitaciones();
                        HabController.eliminarHabitaciones();
                        HabController.AsignarMujeres();
                        HabController.AsignarHombres1();
                        HabController.AsignarHombres2();
                    }
                }
            }
        });
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(botonGenerar);

        JButton botonOpciones = new JButton("Opciones");
        botonOpciones.setForeground(textColor);
        botonOpciones.setBackground(leftColor);
        botonOpciones.setFont(fuente1);
        botonOpciones.setBorderPainted(false);
        botonOpciones.setFocusPainted(false);
        botonOpciones.setContentAreaFilled(true);
        botonOpciones.setAlignmentX(Component.CENTER_ALIGNMENT);
        Color normalBg4 = botonOpciones.getBackground();
        Color hoverBg4 = leftColor.darker();
        botonOpciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonOpciones.setBackground(hoverBg4);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonOpciones.setBackground(normalBg4);
            }
        });
        botonOpciones.addActionListener(e -> {
            new HistorialVentana().setVisible(true);
            dispose();
        });
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(botonOpciones);

        JButton botonAyuda = new JButton("Ayuda");
        botonAyuda.setForeground(textColor);
        botonAyuda.setBackground(leftColor);
        botonAyuda.setFont(fuente1);
        botonAyuda.setBorderPainted(false);
        botonAyuda.setFocusPainted(false);
        botonAyuda.setContentAreaFilled(true);
        botonAyuda.setAlignmentX(Component.CENTER_ALIGNMENT);
        Color normalBg5 = botonAyuda.getBackground();
        Color hoverBg5 = leftColor.darker();
        botonAyuda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonAyuda.setBackground(hoverBg5);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonAyuda.setBackground(normalBg5);
            }
        });
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(botonAyuda);


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
// Agregar al panel
        panelUp.add(searchField);
        panelUp.add(searchButton);
        panelUp.add(menuButton);
        panelUp.add(addButton);
        panelUp.add(editButton);

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

//        deleteButton.addActionListener(e -> {
//            int filaSeleccionada = tablaEstudiantes.getSelectedRow();
//
//            if (filaSeleccionada == -1) {
//                JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
//                return;
//            }
//
//            int totalColumnas = tablaEstudiantes.getColumnCount();
//            String carnet = tablaEstudiantes.getValueAt(filaSeleccionada, totalColumnas - 2).toString();
//
//            // Buscar al estudiante por carnet
//            List<Estudiante> estudiantes = EstudiantesController.getEstudiantes();
//            Estudiante estudianteAEliminar = null;
//
//            for (Estudiante estudiante : estudiantes) {
//                if (estudiante.getCarnet().equalsIgnoreCase(carnet)) {
//                    estudianteAEliminar = estudiante;
//                    break;
//                }
//            }
//
//            if (estudianteAEliminar != null) {
//                int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar al estudiante con carnet: " + carnet + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
//                if (confirmacion == JOptionPane.YES_OPTION) {
//                    EstudiantesController.deleteEstudiante(estudianteAEliminar);
//                    JOptionPane.showMessageDialog(null, "Estudiante eliminado exitosamente.");
//                    // Actualiza la tabla si es necesario
//                }
//            } else {
//                JOptionPane.showMessageDialog(null, "Estudiante no encontrado en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        });


// Agregar los paneles principales al frame
        editButton.addActionListener(e -> {
            int filaSeleccionada = tablaEstudiantes.getSelectedRow();
            if (filaSeleccionada != -1) {
                String carnet = tablaEstudiantes.getValueAt(filaSeleccionada, 3).toString();
                List<Estudiante> estudiantes = EstudiantesController.getEstudiantes();
                Estudiante estudianteSeleccionado = null;
                for (Estudiante estudiante : estudiantes) {
                    if (estudiante.getCarnet().equalsIgnoreCase(carnet)) {
                        estudianteSeleccionado = estudiante;
                        break;
                    }
                }
                if (estudianteSeleccionado != null) {
                    new EditarEstudiante(estudianteSeleccionado).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró el estudiante.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un estudiante para editar.");
            }
        });


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
