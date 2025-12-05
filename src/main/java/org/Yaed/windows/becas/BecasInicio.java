package org.Yaed.windows.becas;

import org.Yaed.controller.ActController;
import org.Yaed.controller.HabController;
import org.Yaed.controller.PastController;
import org.Yaed.entity.ActividadesEstudiantesInternado;
import org.Yaed.windows.PerfilVentana;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class BecasInicio extends JFrame {
    public BecasInicio() {
        setTitle("SCeBE - Panel principal");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
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

        // Panel izquierdo
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


        leftButton.addActionListener(e -> {});
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
// Colores y fuente base
//        Color textColor = Color.WHITE;
//        Color leftColor = new Color(30, 30, 60);
//        Font fuente1 = new Font("Segoe UI", Font.BOLD, 16);

// BOTÓN 1
        JButton botonHabitaciones = new JButton("Organización de Habitaciones");
        botonHabitaciones.setForeground(textColor);
        botonHabitaciones.setBackground(leftColor);
        botonHabitaciones.setFont(fuente1);
        botonHabitaciones.setBorderPainted(false);
        botonHabitaciones.setFocusPainted(false);
        botonHabitaciones.setContentAreaFilled(true);
        botonHabitaciones.setAlignmentX(Component.CENTER_ALIGNMENT);
//        Color normalBg1 = botonHabitaciones.getBackground();
//        Color hoverBg1 = leftColor.darker();
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
        }
        );
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(botonHabitaciones);

// BOTÓN 2
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
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(botonActividades);

// BOTÓN 3
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
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(botonGenerar);

// BOTÓN 4
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
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(botonOpciones);
        botonOpciones.addActionListener(e -> {
            new HistorialVentana();
            dispose();
        });

// BOTÓN 5
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
        botonAyuda.addActionListener(e -> {
           new PerfilVentana().setVisible(true);
        });

        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(botonAyuda);


        // Panel derecho
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(rightColor);
        rightPanel.setLayout(new BorderLayout());

        // Panel superior (búsqueda)
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

        JButton searchButton = new JButton("Reestablecer");
        searchButton.setPreferredSize(new Dimension(150, 35));
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
        panelDown.setLayout(new GridLayout(1, 3, 30, 0));
        panelDown.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        rightPanel.add(panelDown, BorderLayout.CENTER);


        // Panel 1
        JPanel panel1 = new JPanel();
        panel1.setBackground(leftColor);
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelDown.add(panel1);
        llenarPanel1(panel1, ActController.getActividades());

        // Panel 2
        JPanel panel2 = new JPanel();
        panel2.setBackground(leftColor);
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelDown.add(panel2);
        llenarPanel2(panel2, ActController.getActividades());

        // Panel 3
        JPanel panel3 = new JPanel();
        panel3.setBackground(leftColor);
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        panel3.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelDown.add(panel3);
        llenarPanel3(panel3, ActController.getActividades());

        searchButton.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Está seguro que desea reestablecer las actividades?",
                    "Reestablecer actividades",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );
            if (opcion == JOptionPane.YES_OPTION) {
                PastController.respaldarActividades();
                ActController.eliminarActividades();
                panel1.removeAll();
                panel1.revalidate();
                panel1.repaint();
                panel2.removeAll();
                panel2.revalidate();
                panel2.repaint();
                panel3.removeAll();
                panel3.revalidate();
                panel3.repaint();

            } else if (opcion == JOptionPane.NO_OPTION) {
                // Acción si el usuario elige "No"
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
                  llenarPanel1(panel1, ActController.getActividades());
                  llenarPanel2(panel2, ActController.getActividades());
                  llenarPanel3(panel3, ActController.getActividades());
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
                      llenarPanel1(panel1, ActController.getActividades());
                      llenarPanel2(panel2, ActController.getActividades());
                      llenarPanel3(panel3, ActController.getActividades());
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

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        setVisible(true);
    }
    public static void llenarPanel1 (JPanel panel, List<ActividadesEstudiantesInternado> actividades) {
        panel.removeAll();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel("Actividades de Limpieza");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Outfit", Font.BOLD, 18));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

        for (ActividadesEstudiantesInternado actividad : actividades) {
            if (actividad.getActividad().getId() ==1){
            JLabel actividadLabel = new JLabel(actividad.getEstudiante().getNombre() +" "+ actividad.getEstudiante().getApellido());
            actividadLabel.setFont(new Font("Outfit", Font.PLAIN, 13));
            actividadLabel.setForeground(Color.WHITE);
            actividadLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(Box.createVerticalStrut(10));
            panel.add(actividadLabel);
        }

        panel.revalidate();
        panel.repaint();
    }


    }
    public static void llenarPanel2 (JPanel panel, List<ActividadesEstudiantesInternado> actividades) {
        panel.removeAll();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel("Actividades colaborativas");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Outfit", Font.BOLD, 18));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

        for (ActividadesEstudiantesInternado actividad : actividades) {
            if (actividad.getActividad().getId() ==2){
                JLabel actividadLabel = new JLabel(actividad.getEstudiante().getNombre() +" "+ actividad.getEstudiante().getApellido());
                actividadLabel.setFont(new Font("Outfit", Font.PLAIN, 13));
                actividadLabel.setForeground(Color.WHITE);
                actividadLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                panel.add(Box.createVerticalStrut(10));
                panel.add(actividadLabel);
            }

            panel.revalidate();
            panel.repaint();
        }
}
    public static void llenarPanel3 (JPanel panel, List<ActividadesEstudiantesInternado> actividades) {
        panel.removeAll();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel("Apoyo en cocina");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Outfit", Font.BOLD, 18));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

        for (ActividadesEstudiantesInternado actividad : actividades) {
            if (actividad.getActividad().getId() ==3){
                JLabel actividadLabel = new JLabel(actividad.getEstudiante().getNombre() +" " + actividad.getEstudiante().getApellido());
                actividadLabel.setFont(new Font("Outfit", Font.PLAIN, 13));
                actividadLabel.setForeground(Color.WHITE);
                actividadLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                panel.add(Box.createVerticalStrut(10));
                panel.add(actividadLabel);
            }

            panel.revalidate();
            panel.repaint();
        }

}


}