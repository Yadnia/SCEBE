package org.Yaed.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class AyudaVentana extends JFrame {

    public AyudaVentana() {
        setTitle("SCEBE - Ayuda");
        setSize(600, 500);
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
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(panelPrincipal);

        // Título
        JLabel titulo = new JLabel("Preguntas Frecuentes");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
        panelPrincipal.add(titulo);

        // Scroll para las preguntas frecuentes
        JPanel faqPanel = new JPanel();
        faqPanel.setLayout(new BoxLayout(faqPanel, BoxLayout.Y_AXIS));
        faqPanel.setOpaque(false);

        // Preguntas frecuentes
        faqPanel.add(crearPregunta("¿Cómo agrego un nuevo grupo deportivo?",
                "Haz clic en 'Agregar equipo' en la ventana principal de Grupos Deportivos, completa la información y guarda."));

        faqPanel.add(Box.createVerticalStrut(10));

        faqPanel.add(crearPregunta("¿Cómo registro actividades de un grupo?",
                "Ve a 'Actividad de grupo', selecciona el grupo y agrega la actividad con fecha, hora y descripción."));

        faqPanel.add(Box.createVerticalStrut(10));

        faqPanel.add(crearPregunta("¿Cómo ver el historial de actividades?",
                "Selecciona 'Historial de actividad' en el menú lateral para ver todas las actividades registradas por grupo."));

        faqPanel.add(Box.createVerticalStrut(10));

        faqPanel.add(crearPregunta("¿Cómo inicio sesión como administrador?",
                "Ve a 'Configuración' y haz clic en 'Iniciar como admin' para acceder a privilegios administrativos."));

        faqPanel.add(Box.createVerticalStrut(10));

        faqPanel.add(crearPregunta("¿Cómo agregar carreras o etnias?",
                "En 'Configuración', usa los botones 'Agregar carrera' o 'Agregar etnia' y completa la información solicitada."));

        faqPanel.add(Box.createVerticalStrut(10));

        faqPanel.add(crearPregunta("¿Puedo actualizar la lista de grupos sin reiniciar la app?",
                "Sí, haz clic en el botón 'Actualizar' en la parte superior de la ventana principal para refrescar los grupos."));

        // JScrollPane
        JScrollPane scroll = new JScrollPane(faqPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getViewport().setOpaque(false);
        scroll.setOpaque(false);
        scroll.getVerticalScrollBar().setUI(new CustomScrollBarUI());

        panelPrincipal.add(scroll);
    }

    // Método para crear preguntas estilo tarjeta
    private JPanel crearPregunta(String pregunta, String respuesta) {
        RoundedPanel panel = new RoundedPanel(new Color(30, 40, 90), 12);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblPregunta = new JLabel(pregunta);
        lblPregunta.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblPregunta.setForeground(new Color(220, 220, 220));
        lblPregunta.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblRespuesta = new JLabel("<html><p style='width:450px'>" + respuesta + "</p></html>");
        lblRespuesta.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblRespuesta.setForeground(new Color(200, 210, 230));
        lblRespuesta.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblRespuesta.setBorder(BorderFactory.createEmptyBorder(4,0,0,0));

        panel.add(lblPregunta);
        panel.add(lblRespuesta);

        // Hover effect
        panel.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { panel.setBackground(new Color(45, 55, 110)); panel.repaint();}
            @Override public void mouseExited(MouseEvent e) { panel.setBackground(new Color(30, 40, 90)); panel.repaint();}
        });

        return panel;
    }

    // ScrollBar personalizado
    private static class CustomScrollBarUI extends javax.swing.plaf.basic.BasicScrollBarUI {
        @Override protected void configureScrollBarColors() {
            this.thumbColor = new Color(80, 95, 150);
        }
    }

    // RoundedPanel
    private static class RoundedPanel extends JPanel {
        private Color backgroundColor;
        private int cornerRadius = 15;

        public RoundedPanel(Color bg, int radius) {
            super();
            backgroundColor = bg;
            cornerRadius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();

            // sombra sutil
            g2.setColor(new Color(0,0,0,40));
            g2.fillRoundRect(2,2,width-4,height-4,cornerRadius,cornerRadius);

            // panel principal
            g2.setColor(backgroundColor);
            g2.fillRoundRect(0,0,width-4,height-4,cornerRadius,cornerRadius);

            g2.dispose();
            super.paintComponent(g);
        }
    }
}
