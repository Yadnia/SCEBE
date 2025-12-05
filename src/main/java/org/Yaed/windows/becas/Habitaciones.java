package org.Yaed.windows.becas;

import org.Yaed.controller.ActController;
import org.Yaed.controller.EstudiantesController;
import org.Yaed.controller.HabController;
import org.Yaed.controller.PastController;
import org.Yaed.entity.Estudiante;
import org.Yaed.entity.HabitacionesEstudiantes;
import org.Yaed.windows.becas.cuartosF.*;
import org.Yaed.windows.becas.cuartosM.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Habitaciones extends JFrame {
    public Habitaciones() {
        setTitle("SCeBE - Habitaciones");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        // Poner icono
        ImageIcon icono = new ImageIcon(Objects.requireNonNull(getClass().getResource("/logo.png")));
        setIconImage(icono.getImage());
        // Colores y fuentes
        Color leftColor = new Color(10, 20, 60);
        Color rightColor = new Color(40, 51, 106);
        Color textColor = Color.WHITE;
        Font fuente = new Font("Outfit", Font.BOLD, 15);

        // Panel izquierdo
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(leftColor);
        leftPanel.setPreferredSize(new Dimension(180, getHeight()));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        // Botones del panel izquierdo
        JButton leftButton = createButton("UBC", leftColor, textColor, fuente);
        JButton listadoBttn = createButton("Listado Estudiantes", leftColor, textColor, new Font("Outfit", Font.BOLD, 14));
        listadoBttn.addActionListener(e -> {
            new ListadoEstudiantes();
            dispose();
        });
        JButton botonHabitaciones = createButton("Organización de Habitaciones", leftColor, textColor, new Font("Outfit", Font.BOLD, 14));
        botonHabitaciones.addActionListener(e -> {
            new Habitaciones();
            dispose();
        });
        JButton botonActividades = createButton("Actividades", leftColor, textColor, new Font("Outfit", Font.BOLD, 14));
        JButton botonGenerar = createButton("Generar", leftColor, textColor, new Font("Outfit", Font.BOLD, 14));
        JButton botonOpciones = createButton("Opciones", leftColor, textColor, new Font("Outfit", Font.BOLD, 14));
        JButton botonAyuda = createButton("Ayuda", leftColor, textColor, new Font("Outfit", Font.BOLD, 14));

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
       botonActividades.addActionListener(e ->{
           new BecasInicio();
           dispose();
       });

        // Agregar botones al panel izquierdo
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(leftButton);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(listadoBttn);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(botonHabitaciones);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(botonActividades);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(botonGenerar);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(botonOpciones);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(botonAyuda);

        // Panel derecho
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(rightColor);
        rightPanel.setLayout(new BorderLayout());

        // Panel superior
        JPanel panelUp = new JPanel();
        panelUp.setBackground(rightColor);
        panelUp.setPreferredSize(new Dimension(0, 70));
        panelUp.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        rightPanel.add(panelUp, BorderLayout.NORTH);

        JTextField searchField = new JTextField(25);
        searchField.setPreferredSize(new Dimension(250, 35));
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 15));
        searchField.setForeground(Color.WHITE);
        Color bgColor = new Color(14, 34, 71);
        searchField.setBackground(bgColor);
        searchField.setCaretColor(Color.WHITE);
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(bgColor, 1, true),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));

        JButton searchButton = new JButton("🔍");
        searchButton.setPreferredSize(new Dimension(50, 35));
        searchButton.setBackground(new Color(60, 80, 140));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        searchButton.setFocusPainted(false);
        searchButton.setBorderPainted(false);

        panelUp.add(searchField);
        panelUp.add(searchButton);

        // Panel inferior (contenido)
        JPanel panelDown = new JPanel();
        panelDown.setBackground(rightColor);
        panelDown.setLayout(null);
        rightPanel.add(panelDown, BorderLayout.CENTER);

        botonOpciones.addActionListener(e -> {
            new HistorialVentana();
            dispose();
        });

        // Crear botones para habitaciones grandes
        JButton panelGrande1 = createRoomButton("Habitación Grande 1", new Color(30, 60, 120), Color.WHITE);
        panelGrande1.setBounds(50, 30, 420, 150);
        panelDown.add(panelGrande1);

        panelGrande1.addActionListener( e ->{
            new HabitacionG1();
        });

        JButton panelGrande2 = createRoomButton("Habitación Grande 2", new Color(30, 60, 120), Color.WHITE);
        panelGrande2.setBounds(500, 30, 420, 150);
        panelDown.add(panelGrande2);
        panelGrande2.addActionListener( e ->{
            new HabitacionG2();
        });

   // Crear botones para habitaciones medianas
  JButton panelMediano1 = createRoomButton("Habitación Mediana 1", new Color(50, 90, 160), Color.WHITE);
   panelMediano1.setBounds(40, 200, 150, 90);
   panelDown.add(panelMediano1);
    panelMediano1.addActionListener( e ->{
         new HabitacionM1();
    });

   JButton panelMediano2 = createRoomButton("Habitación Mediana 2", new Color(50, 90, 160), Color.WHITE);
   panelMediano2.setBounds(210, 200, 150, 90);
   panelDown.add(panelMediano2);
    panelMediano2.addActionListener( e ->{
         new HabitacionM2();
    });

   JButton panelMediano3 = createRoomButton("Habitación Mediana 3", new Color(50, 90, 160), Color.WHITE);
   panelMediano3.setBounds(380, 200, 150, 90);
   panelDown.add(panelMediano3);
    panelMediano3.addActionListener( e ->{
         new HabitacionM3();
    });

   JButton panelMediano4 = createRoomButton("Habitación Mediana 4", new Color(50, 90, 160), Color.WHITE);
   panelMediano4.setBounds(550, 200, 150, 90);
   panelDown.add(panelMediano4);
    panelMediano4.addActionListener( e ->{
         new HabitacionM4();
    });

   JButton panelMediano5 = createRoomButton("Habitación Mediana 5", new Color(50, 90, 160), Color.WHITE);
   panelMediano5.setBounds(720, 200, 150, 90);
   panelDown.add(panelMediano5);
    panelMediano5.addActionListener( e ->{
         new HabitacionM5();
    });

   JButton panelMediano6 = createRoomButton("Habitación Mediana 6", new Color(50, 90, 160), Color.WHITE);
   panelMediano6.setBounds(40, 320, 150, 90);
   panelDown.add(panelMediano6);
    panelMediano6.addActionListener( e ->{
         new HabitacionM6();
    });

   JButton panelMediano7 = createRoomButton("Habitación Mediana 7", new Color(50, 90, 160), Color.WHITE);
   panelMediano7.setBounds(210, 320, 150, 90);
   panelDown.add(panelMediano7);
    panelMediano7.addActionListener( e ->{
         new HabitacionM7();
    });

   JButton panelMediano8 = createRoomButton("Habitación Mediana 8", new Color(50, 90, 160), Color.WHITE);
   panelMediano8.setBounds(380, 320, 150, 90);
   panelDown.add(panelMediano8);
    panelMediano8.addActionListener( e ->{
         new HabitacionM8();
    });

   JButton panelMediano9 = createRoomButton("Habitación Mediana 9", new Color(50, 90, 160), Color.WHITE);
   panelMediano9.setBounds(550, 320, 150, 90);
   panelDown.add(panelMediano9);
    panelMediano9.addActionListener( e ->{
         new HabitacionM9();
    });

   JButton panelMediano10 = createRoomButton("Habitación Mediana 10", new Color(50, 90, 160), Color.WHITE);
   panelMediano10.setBounds(720, 320, 150, 90);
   panelDown.add(panelMediano10);
    panelMediano10.addActionListener( e ->{
         new HabitacionM10();});

        // Crear botones para habitaciones pequeñas
      JButton panelPequeno1 = createRoomButton("Habitación Pequeña 1", new Color(80, 120, 180), Color.WHITE);
        panelPequeno1.setBounds(30, 460, 100, 100);
        panelDown.add(panelPequeno1);
        panelPequeno1.addActionListener( e ->{
            new HabitacionF1();
        });

        JButton panelPequeno2 = createRoomButton("Habitación Pequeña 2", new Color(80, 120, 180), Color.WHITE);
        panelPequeno2.setBounds(150, 460, 100, 100);
        panelDown.add(panelPequeno2);
        panelPequeno2.addActionListener( e ->{
            new HabitacionF2();
        });

        JButton panelPequeno3 = createRoomButton("Habitación Pequeña 3", new Color(80, 120, 180), Color.WHITE);
        panelPequeno3.setBounds(270, 460, 100, 100);
        panelDown.add(panelPequeno3);
        panelPequeno3.addActionListener( e ->{
            new HabitacionF3();
        });

        JButton panelPequeno4 = createRoomButton("Habitación Pequeña 4", new Color(80, 120, 180), Color.WHITE);
        panelPequeno4.setBounds(390, 460, 100, 100);
        panelDown.add(panelPequeno4);
        panelPequeno4.addActionListener( e ->{
            new HabitacionF4();
        });

        JButton panelPequeno5 = createRoomButton("Habitación Pequeña 5", new Color(80, 120, 180), Color.WHITE);
        panelPequeno5.setBounds(510, 460, 100, 100);
        panelDown.add(panelPequeno5);
        panelPequeno5.addActionListener( e ->{
            new HabitacionF5();
        });

        JButton panelPequeno6 = createRoomButton("Habitación Pequeña 6", new Color(80, 120, 180), Color.WHITE);
        panelPequeno6.setBounds(630, 460, 100, 100);
        panelDown.add(panelPequeno6);
        panelPequeno6.addActionListener( e ->{
            new HabitacionF6();
        });

        JButton panelPequeno7 = createRoomButton("Habitación Pequeña 7", new Color(80, 120, 180), Color.WHITE);
        panelPequeno7.setBounds(750, 460, 100, 100);
        panelDown.add(panelPequeno7);
        panelPequeno7.addActionListener( e ->{
            new HabitacionF7();
        });

        JButton panelPequeno8 = createRoomButton("Habitación Pequeña 8", new Color(80, 120, 180), Color.WHITE);
        panelPequeno8.setBounds(870, 460, 100, 100);
        panelDown.add(panelPequeno8);
        panelPequeno8.addActionListener( e ->{
            new HabitacionF8();
        });

        // Agregamos paneles al frame
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    // Método para crear botones
    private JButton createButton(String text, Color bgColor, Color fgColor, Font font) {
        JButton button = new JButton(text);
        button.setForeground(fgColor);
        button.setBackground(bgColor);
        button.setFont(font);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(true);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    // Método para crear botones de habitaciones
    private JButton createRoomButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        button.setForeground(fgColor);
        button.setBackground(bgColor);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        button.setLayout(new GridLayout(6, 1, 0, 2));
        return button;
    }


    private static void llenarPaneles(JPanel panelPequeno1, JPanel panelPequeno2, JPanel panelPequeno3, JPanel panelPequeno4, JPanel panelPequeno5, JPanel panelPequeno6, JPanel panelPequeno7, JPanel panelPequeno8, JPanel panelGrande1, JPanel panelGrande2, JPanel panelMediano1, JPanel panelMediano2, JPanel panelMediano3, JPanel panelMediano4, JPanel panelMediano5, JPanel panelMediano6, JPanel panelMediano7) {
        llenarPanel(panelPequeno1, 1);
        llenarPanel(panelPequeno2, 2);
        llenarPanel(panelPequeno3, 3);
        llenarPanel(panelPequeno4, 4);
        llenarPanel(panelPequeno5, 5);
        llenarPanel(panelPequeno6, 6);
        llenarPanel(panelPequeno7, 7);
        llenarPanel(panelPequeno8, 8);
//        llenarPanel(panelMediano1, 9);
//        llenarPanel(panelMediano2, 10);
//        llenarPanel(panelMediano3, 11);
//        llenarPanel(panelMediano4, 12);
//        llenarPanel(panelMediano5, 13);
//        llenarPanel(panelMediano6, 14);
//        llenarPanel(panelMediano7, 15);
//        llenarPanel(panelGrande1, 16);
//        llenarPanel(panelGrande2, 17);

    }
    private static void llenarPaneles2(JPanel panelMediano1, JPanel panelMediano2, JPanel panelMediano3, JPanel panelMediano4, JPanel panelMediano5, JPanel panelMediano6, JPanel panelMediano7, JPanel panelGrande1, JPanel panelGrande2) {
        llenarPanel2(panelMediano1, 1);
        llenarPanel2(panelMediano2, 10);
        llenarPanel2(panelMediano3, 11);
        llenarPanel2(panelMediano4, 12);
        llenarPanel2(panelMediano5, 13);
        llenarPanel2(panelMediano6, 14);
        llenarPanel2(panelMediano7, 15);
        llenarPanel2(panelGrande1, 16);
        llenarPanel2(panelGrande2, 17);
    }
    private void asignarAlPanel(JPanel panel, List<Estudiante> estudiantes) {
        // Elimina todas las etiquetas de estudiantes excepto el título (que está en índice 0)
        while (panel.getComponentCount() > 1) {
            panel.remove(1);
        }
        panel.setLayout(new GridLayout(estudiantes.size() + 1, 1)); // +1 para título
        for (Estudiante e : estudiantes) {
            JLabel label = new JLabel(e.getNombre() + " " + e.getApellido());
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setForeground(Color.WHITE);
            panel.add(label);
        }
        panel.revalidate();
        panel.repaint();
    }

    public List<Estudiante> getEstudiantesPorSexo(char sexo) {
        return EstudiantesController.getEstudiantes().stream()
                .filter(e -> e.getSexo() == sexo)
                .toList();
    }
    public List<Estudiante> mujeres = getEstudiantesPorSexo('F');
    public List<Estudiante> hombres = getEstudiantesPorSexo('M');


    public static void llenarPanel(JPanel panel, int idCuarto) {
        panel.setLayout(new GridLayout(12, 1, 0, 2)); // para 12 filas
        List<HabitacionesEstudiantes> habs = HabController.getHabitacionesEstudiantes();
        LocalDate hoy = LocalDate.now(); // fecha actual
        LocalDate manana = LocalDate.now().plusDays(1);
        for (HabitacionesEstudiantes habEstudiante : habs) {
            if (habEstudiante.getHabitacion().getId() == idCuarto &&
                    habEstudiante.getFecha().equals("1")) {

                JLabel label = new JLabel(
                        habEstudiante.getEstudiante().getNombre() + " " + habEstudiante.getEstudiante().getApellido()
                );
                label.setForeground(Color.WHITE);
                panel.add(label);
            }
        }

        panel.revalidate();
        panel.repaint();
    }
    public static void llenarPanel2(JPanel panel, int idCuarto) {
        panel.setLayout(new GridLayout(12, 1, 0, 2)); // para 12 filas
        List<HabitacionesEstudiantes> habs = HabController.getHabitacionesEstudiantes();
        LocalDate hoy = LocalDate.now(); // fecha actual
        LocalDate manana = LocalDate.now().plusDays(1);
        for (HabitacionesEstudiantes habEstudiante : habs) {
            if (habEstudiante.getHabitacion().getId() == idCuarto &&
                    habEstudiante.getFecha().equals("1")) {

                JLabel label = new JLabel(
                        habEstudiante.getEstudiante().getNombre() + " " + habEstudiante.getEstudiante().getApellido()
                );
                label.setForeground(Color.WHITE);
                label.setFont(new Font("Outfit", Font.BOLD, 9));
                panel.add(label);
            }
        }

        panel.revalidate();
        panel.repaint();
    }

    public static void dividirEstudiantesFBeca2(List<Estudiante> todos,
                                                List<Estudiante> grupo1,
                                                List<Estudiante> grupo2,
                                                List<Estudiante> grupo3,
                                                List<Estudiante> grupo4,
                                                List<Estudiante> grupo5,
                                                List<Estudiante> grupo6,
                                                List<Estudiante> grupo7,
                                                List<Estudiante> grupo8) {
        // Limpiar listas
        grupo1.clear(); grupo2.clear(); grupo3.clear(); grupo4.clear();
        grupo5.clear(); grupo6.clear(); grupo7.clear(); grupo8.clear();

        // Filtrar estudiantes mujeres con beca ID 2
        List<Estudiante> filtradas = new ArrayList<>();
        for (Estudiante e : todos) {
            if (Character.toUpperCase(e.getSexo()) == 'F' && e.getBecaid() != null && e.getBecaid().getId() == 2) {
                filtradas.add(e);
            }
        }

        // Mezclar aleatoriamente
        Collections.shuffle(filtradas);

        // Dividir en grupos de 12 estudiantes máximo
        for (int i = 0; i < filtradas.size(); i++) {
            Estudiante estudiante = filtradas.get(i);
            int grupo = i / 12;
            switch (grupo) {
                case 0 -> grupo1.add(estudiante);
                case 1 -> grupo2.add(estudiante);
                case 2 -> grupo3.add(estudiante);
                case 3 -> grupo4.add(estudiante);
                case 4 -> grupo5.add(estudiante);
                case 5 -> grupo6.add(estudiante);
                case 6 -> grupo7.add(estudiante);
                case 7 -> grupo8.add(estudiante);
                default -> {}
            }
        }
    }



}