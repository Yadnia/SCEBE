package org.Yaed.windows.becas;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class HistorialVentana extends JFrame {
    public HistorialVentana() {
        setTitle("SCeBE - Historial");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        // Poner icono
        ImageIcon icono = new ImageIcon(Objects.requireNonNull(getClass().getResource("/logo.png")));
        setIconImage(icono.getImage());
        // Colores y fuentes

        Color leftColor = new Color(10, 20, 60);
        Color rightColor = new Color(40, 51, 106);
        Color textColor = Color.WHITE;
        Font fuente = new Font("Outfit", Font.BOLD, 15);
        Font fuente1 = new Font("Outfit", Font.BOLD, 14);
        Font fuenteBotonGrande = new Font("Outfit", Font.BOLD, 22);

        // Panel izquierdo (idéntico al de BecasInicio)
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(leftColor);
        leftPanel.setPreferredSize(new Dimension(180, getHeight()));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        // Botón UBC
        JButton leftButton = new JButton("UBC");
        leftButton.setForeground(textColor);
        leftButton.setBackground(leftColor);
        leftButton.setFont(fuente);
        leftButton.setBorderPainted(false);
        leftButton.setFocusPainted(false);
        leftButton.setContentAreaFilled(true);
        leftButton.setAlignmentX(Component.CENTER_ALIGNMENT);
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
        leftButton.addActionListener(e -> {
            new BecasInicio();
        });
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(leftButton);

        // Botón Listado Estudiantes
        JButton listadoBttn = new JButton("Listado Estudiantes");
        listadoBttn.setForeground(textColor);
        listadoBttn.setBackground(leftColor);
        listadoBttn.setFont(fuente1);
        listadoBttn.setBorderPainted(false);
        listadoBttn.setFocusPainted(false);
        listadoBttn.setContentAreaFilled(true);
        listadoBttn.setAlignmentX(Component.CENTER_ALIGNMENT);
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
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(listadoBttn);

        // Botón Organización de Habitaciones
        JButton botonHabitaciones = new JButton("Organización de Habitaciones");
        botonHabitaciones.setForeground(textColor);
        botonHabitaciones.setBackground(leftColor);
        botonHabitaciones.setFont(fuente1);
        botonHabitaciones.setBorderPainted(false);
        botonHabitaciones.setFocusPainted(false);
        botonHabitaciones.setContentAreaFilled(true);
        botonHabitaciones.setAlignmentX(Component.CENTER_ALIGNMENT);
        Color normalBg2 = botonHabitaciones.getBackground();
        Color hoverBg2 = leftColor.darker();
        botonHabitaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonHabitaciones.setBackground(hoverBg2);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonHabitaciones.setBackground(normalBg2);
            }
        });
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(botonHabitaciones);

        // Botón Actividades
        JButton botonActividades = new JButton("Actividades");
        botonActividades.setForeground(textColor);
        botonActividades.setBackground(leftColor);
        botonActividades.setFont(fuente1);
        botonActividades.setBorderPainted(false);
        botonActividades.setFocusPainted(false);
        botonActividades.setContentAreaFilled(true);
        botonActividades.setAlignmentX(Component.CENTER_ALIGNMENT);
        Color normalBg3 = botonActividades.getBackground();
        Color hoverBg3 = leftColor.darker();
        botonActividades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonActividades.setBackground(hoverBg3);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonActividades.setBackground(normalBg3);
            }
        });
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(botonActividades);

        // Botón Generar
        JButton botonGenerar = new JButton("Generar");
        botonGenerar.setForeground(textColor);
        botonGenerar.setBackground(leftColor);
        botonGenerar.setFont(fuente1);
        botonGenerar.setBorderPainted(false);
        botonGenerar.setFocusPainted(false);
        botonGenerar.setContentAreaFilled(true);
        botonGenerar.setAlignmentX(Component.CENTER_ALIGNMENT);
        Color normalBg4 = botonGenerar.getBackground();
        Color hoverBg4 = leftColor.darker();
        botonGenerar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonGenerar.setBackground(hoverBg4);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonGenerar.setBackground(normalBg4);
            }
        });
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(botonGenerar);

        // Botón Opciones
        JButton botonOpciones = new JButton("Opciones");
        botonOpciones.setForeground(textColor);
        botonOpciones.setBackground(leftColor);
        botonOpciones.setFont(fuente1);
        botonOpciones.setBorderPainted(false);
        botonOpciones.setFocusPainted(false);
        botonOpciones.setContentAreaFilled(true);
        botonOpciones.setAlignmentX(Component.CENTER_ALIGNMENT);
        Color normalBg5 = botonOpciones.getBackground();
        Color hoverBg5 = leftColor.darker();
        botonOpciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonOpciones.setBackground(hoverBg5);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonOpciones.setBackground(normalBg5);
            }
        });
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(botonOpciones);

        // Botón Ayuda
        JButton botonAyuda = new JButton("Ayuda");
        botonAyuda.setForeground(textColor);
        botonAyuda.setBackground(leftColor);
        botonAyuda.setFont(fuente1);
        botonAyuda.setBorderPainted(false);
        botonAyuda.setFocusPainted(false);
        botonAyuda.setContentAreaFilled(true);
        botonAyuda.setAlignmentX(Component.CENTER_ALIGNMENT);
        Color normalBg6 = botonAyuda.getBackground();
        Color hoverBg6 = leftColor.darker();
        botonAyuda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonAyuda.setBackground(hoverBg6);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonAyuda.setBackground(normalBg6);
            }
        });
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(botonAyuda);

        // Panel derecho con dos botones grandes
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(rightColor);
        rightPanel.setLayout(new GridBagLayout());

        JButton btnOrgAnterior = new JButton("Organización anterior");
        btnOrgAnterior.setFont(fuenteBotonGrande);
        btnOrgAnterior.setForeground(textColor);
        btnOrgAnterior.setBackground(new Color(14, 34, 71));
        btnOrgAnterior.setFocusPainted(false);
        btnOrgAnterior.setBorderPainted(false);
        btnOrgAnterior.setPreferredSize(new Dimension(320, 100));

        btnOrgAnterior.addActionListener(e -> {
            new OrganizacionesAnterioresVentana();
        });

        JButton btnActAnterior = new JButton("Actividades anteriores");
        btnActAnterior.setFont(fuenteBotonGrande);
        btnActAnterior.setForeground(textColor);
        btnActAnterior.setBackground(new Color(14, 34, 71));
        btnActAnterior.setFocusPainted(false);
        btnActAnterior.setBorderPainted(false);
        btnActAnterior.setPreferredSize(new Dimension(320, 100));
        btnActAnterior.addActionListener(e -> {
            new ActividadesAnterioresVentana();
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(30, 0, 30, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        rightPanel.add(btnOrgAnterior, gbc);
        gbc.gridy = 1;
        rightPanel.add(btnActAnterior, gbc);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}
