package org.Yaed.windows;

import org.Yaed.controller.CarrerasController;
import org.Yaed.controller.EstudiantesController;
import org.Yaed.entity.Carrera;
import org.Yaed.entity.Estudiante;
import org.Yaed.entity.Etnia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class ConfiguracionVentana extends JFrame {

    public ConfiguracionVentana() {
        setTitle("SCEBE - Configuración");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Poner icono
        ImageIcon icono = new ImageIcon(Objects.requireNonNull(getClass().getResource("/logo.png")));
        setIconImage(icono.getImage());

        // Panel principal con fondo degradado
        JPanel panelPrincipal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color c1 = new Color(18, 28, 64);
                Color c2 = new Color(26, 42, 91);
                GradientPaint gp = new GradientPaint(0, 0, c1, 0, getHeight(), c2);
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        add(panelPrincipal);

        // Título
        JLabel titulo = new JLabel("Configuración");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
        panelPrincipal.add(titulo);

        // Botones de configuración
        JButton btnAdmin = crearBoton("Iniciar como admin");
        btnAdmin.addActionListener(e -> {
            // Cerrar TODAS las ventanas abiertas
            for (Window window : Window.getWindows()) {
                window.dispose();
            }

            // Abrir la nueva ventana
            new Login().setVisible(true);
        });


        JButton btnCarrera = crearBoton("Agregar carrera");
        btnCarrera.addActionListener(e -> {
            // Crear panel con campos
            JTextField tfNombre = new JTextField(15);
            JTextField tfModalidad = new JTextField(15);
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(new JLabel("Nombre de la carrera:"));
            panel.add(tfNombre);
            panel.add(Box.createVerticalStrut(10)); // espacio
            panel.add(new JLabel("Modalidad:"));
            panel.add(tfModalidad);

            int result = JOptionPane.showConfirmDialog(
                    null, panel, "Agregar nueva carrera", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String nombre = tfNombre.getText().trim();
                String modalidad = tfModalidad.getText().trim();

                Carrera car = new Carrera();
                car.setNombre(nombre);

                if (!nombre.isEmpty() && !modalidad.isEmpty()) {
                    CarrerasController.saveCarreras(car);
                    // Aquí puedes usar los datos ingresados
                    System.out.println("Nombre: " + nombre + ", Modalidad: " + modalidad);
                    JOptionPane.showMessageDialog(null, "Carrera agregada:\n" +
                            "Nombre: " + nombre + "\nModalidad: " + modalidad);
                } else {
                    JOptionPane.showMessageDialog(null, "Debe completar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        JButton btnEtnia = crearBoton("Agregar etnia");
        btnEtnia.addActionListener(e -> {
            // Crear panel con un campo
            JTextField tfNombre = new JTextField(15);
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(new JLabel("Nombre de la etnia:"));
            panel.add(tfNombre);

            int result = JOptionPane.showConfirmDialog(
                    null, panel, "Agregar nueva etnia", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String nombre = tfNombre.getText().trim();
                Etnia et = new Etnia();
                et.setNombre(nombre);

                if (!nombre.isEmpty()) {
                    EstudiantesController.saveEtnia(et);
                    // Aquí puedes usar el dato ingresado
                    System.out.println("Etnia: " + nombre);
                    JOptionPane.showMessageDialog(null, "Etnia agregada: " + nombre);
                } else {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un nombre", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        panelPrincipal.add(btnAdmin);
        panelPrincipal.add(Box.createVerticalStrut(15));
        panelPrincipal.add(btnCarrera);
        panelPrincipal.add(Box.createVerticalStrut(15));
        panelPrincipal.add(btnEtnia);

        panelPrincipal.add(Box.createVerticalGlue());
    }

    // Método para crear botones con la misma estética que tus primarios
    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(80, 120, 200));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(250, 40));

        // Hover effect
        btn.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { btn.setBackground(new Color(95,135,230)); }
            @Override public void mouseExited(MouseEvent e) { btn.setBackground(new Color(80,120,200)); }
            @Override public void mousePressed(MouseEvent e) { btn.setBackground(new Color(60,100,180)); }
            @Override public void mouseReleased(MouseEvent e) { btn.setBackground(new Color(95,135,230)); }
        });

        return btn;
    }

}
