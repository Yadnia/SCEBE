package org.Yaed.windows.cultura;

import org.Yaed.controller.CulturaController;
import org.Yaed.entity.GrupoCultura;
import org.Yaed.util.WindowManager;
import org.Yaed.windows.AyudaVentana;
import org.Yaed.windows.ConfiguracionVentana;
import org.Yaed.windows.cultura.EstudiantesCultura;
import org.Yaed.windows.deportes.EstudiantesDeportes;
import org.Yaed.windows.cultura.GrupoFormC;
import org.kordamp.ikonli.*;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsFilled;
import org.kordamp.ikonli.swing.FontIcon;
import org.Yaed.controller.DeportesController;
import org.Yaed.controller.EstudiantesController;
import org.Yaed.entity.Estudiante;
import org.Yaed.entity.GrupoDeporte;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.sql.*;
import java.util.List;
import java.util.Objects;

public class CulturaInicio extends JFrame {

    private Connection conexion;

    public CulturaInicio() {
        setTitle("SCEBE - Grupos de Cultura");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        // Poner icono
        ImageIcon icono = new ImageIcon(Objects.requireNonNull(getClass().getResource("/logo.png")));
        setIconImage(icono.getImage());
        JPanel panelLateral = new JPanel();
        panelLateral.setBackground(new Color(8, 17, 45));
        panelLateral.setPreferredSize(new Dimension(220, 0));
        panelLateral.setLayout(new BoxLayout(panelLateral, BoxLayout.Y_AXIS));
        JLabel titulo = new JLabel("SCEBE");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        panelLateral.add(titulo);

        String[] opciones = {
                "Listado de estudiantes",
                "Actividad de grupo",
                "Historial de actividad",
                "Grupos",
                "Configuración",
                "Ayuda"
        };

        Ikon[] iconos = {
                AntDesignIconsFilled.PROFILE,
                AntDesignIconsFilled.CALENDAR,
                AntDesignIconsFilled.BOOK,
                AntDesignIconsFilled.IDCARD,
                AntDesignIconsFilled.SETTING,
                AntDesignIconsFilled.CUSTOMER_SERVICE
        };

        for (int i = 0; i < opciones.length; i++) {
            JButton btn = new JButton(opciones[i]);
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            btn.setForeground(Color.WHITE);
            btn.setBackground(new Color(8, 17, 45));
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setIcon(FontIcon.of(iconos[i], 20, Color.WHITE));
            btn.setHorizontalAlignment(SwingConstants.LEFT);
            btn.setIconTextGap(10);
            btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

            String texto = opciones[i];
            btn.addActionListener(e -> {
                switch (texto) {
                    case "Listado de estudiantes" -> abrirListadoEstudiantes();
                    case "Actividad de grupo" -> abrirActividadGrupo();
                    case "Historial de actividad" -> abrirHistorialActividad();
                    case "Grupos" -> abrirGrupos();
                    case "Configuración" -> abrirConfiguracion();
                    case "Ayuda" -> abrirAyuda();
                }
            });

            panelLateral.add(btn);
        }

        panelLateral.add(Box.createVerticalGlue());
        add(panelLateral, BorderLayout.WEST);

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
        panelPrincipal.setOpaque(false);
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        JLabel lblTitulo = new JLabel("Grupos de Cultura");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        header.add(lblTitulo, BorderLayout.NORTH);

        JLabel subt = new JLabel("Explora los grupos activos y la información relevante");
        subt.setForeground(new Color(200, 210, 230));
        subt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subt.setBorder(new EmptyBorder(6, 0, 12, 0));
        header.add(subt, BorderLayout.SOUTH);

        JPanel headerRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        headerRight.setOpaque(false);
        JButton btnAgregar = new JButton("Agregar equipo");
        stylePrimaryButton(btnAgregar);
        btnAgregar.addActionListener(e -> new GrupoFormC(this));
        JButton actualizarBtn = new JButton("Actualizar");
        stylePrimaryButton(actualizarBtn);
        actualizarBtn.addActionListener(e -> {
            this.dispose();
            CulturaInicio nuevaVentana = new CulturaInicio();
            nuevaVentana.setVisible(true);
        });
        headerRight.add(btnAgregar);
        headerRight.add(actualizarBtn);
        header.add(headerRight, BorderLayout.EAST);

        panelPrincipal.add(header, BorderLayout.NORTH);

        int cardWidth = 240;
        int cardHeight = 140;
        int hGap = 20;
        int vGap = 20;

        int nTarjetas = CulturaController.getAllGrupoCultura().size();
        int columnas = (nTarjetas + 1) / 2;

        JPanel panelGrupos = new JPanel(new GridLayout(2, columnas, hGap, vGap)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setOpaque(false);
            }
        };
        panelGrupos.setOpaque(false);
        panelGrupos.setBorder(new EmptyBorder(10,10,10,10));

        for (int i = 1; i <= nTarjetas; i++) {
            List<GrupoCultura> grupos = CulturaController.getAllGrupoCultura();
            String nombreGrupo = grupos.get(i - 1).getNombre();
            String categoria = grupos.get(i - 1).getCategoria().getNombre();
            String integrantes = String.valueOf(CulturaController.getAllGrupoCultura().get(i - 1).getGruposCulturaEstudiantes().size());
            JPanel card = crearPanelGrupo(i, nombreGrupo, categoria, Integer.parseInt(integrantes));
            card.setPreferredSize(new Dimension(cardWidth, cardHeight));
            panelGrupos.add(card);
        }

        int totalWidth = columnas * (cardWidth + hGap) + 40;
        int totalHeight = 2 * (cardHeight + vGap) + 40;
        panelGrupos.setPreferredSize(new Dimension(totalWidth, totalHeight));

        JScrollPane scroll = new JScrollPane(panelGrupos, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(BorderFactory.createEmptyBorder());

        JScrollBar hBar = scroll.getHorizontalScrollBar();
        hBar.setUnitIncrement(16);
        hBar.setUI(new BasicScrollBarUI() {
            @Override protected void configureScrollBarColors() {
                this.thumbColor = new Color(80, 95, 150);
            }
        });

        panelPrincipal.add(scroll, BorderLayout.CENTER);
        add(panelPrincipal, BorderLayout.CENTER);
    }

    private JPanel crearPanelGrupo(int id, String nombre, String categoria, int integrantes) {
        RoundedPanel panel = new RoundedPanel(new Color(30, 40, 90), 16);
        panel.setPreferredSize(new Dimension(240, 140));
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(12,12,12,12));

        JLabel lblNombre = new JLabel(nombre);
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panel.add(lblNombre, BorderLayout.NORTH);

        JPanel centro = new JPanel(new GridLayout(2,2));
        centro.setOpaque(false);

        JLabel lblCategoria = new JLabel("Categoría: " + categoria);
        lblCategoria.setForeground(new Color(200, 210, 230));
        lblCategoria.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JLabel lblIntegrantes = new JLabel("Integrantes: " + integrantes);
        lblIntegrantes.setForeground(new Color(200, 210, 230));
        lblIntegrantes.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        centro.add(lblCategoria);
        centro.add(lblIntegrantes);
        centro.add(new JLabel(""));
        centro.add(new JLabel(""));

        panel.add(centro, BorderLayout.CENTER);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(new Color(45,55,110));
                panel.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBackground(new Color(30,40,90));
                panel.repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                mostrarEstudiantesGrupo(id, nombre);
            }
        });

        return panel;
    }

    private void mostrarEstudiantesGrupo(int grupoId, String nombreGrupo) {
        JOptionPane.showMessageDialog(this,
                "Mostrando estudiantes de " + nombreGrupo,
                "Grupo " + grupoId,
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void abrirListadoEstudiantes() {
        java.awt.EventQueue.invokeLater(() -> {
            WindowManager.abrir("ListadoEstudiantes", new EstudiantesCultura());
        });
    }

    private void abrirActividadGrupo() {
        WindowManager.abrir("ActividadGrupo", new ActividadesCGrupoVentana());
    }

    private void abrirHistorialActividad() {
        WindowManager.abrir("HistorialActividad", new ActividadesCHistorialVentana());
    }

    private void abrirGrupos() {
        java.awt.EventQueue.invokeLater(() -> {
            WindowManager.abrir("GestionGrupos", new GruposCVentana());
        });
    }

    private void abrirConfiguracion() {
        WindowManager.abrir("Configuracion", new ConfiguracionVentana());
    }

    private void abrirAyuda() {
        WindowManager.abrir("Ayuda", new AyudaVentana());
    }

    private void stylePrimaryButton(JButton btn) {
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(80, 120, 200));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(true);
        btn.setOpaque(true);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(140, 36));
        btn.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { btn.setBackground(new Color(95,135,230)); }
            @Override public void mouseExited(MouseEvent e) { btn.setBackground(new Color(80,120,200)); }
            @Override public void mousePressed(MouseEvent e) { btn.setBackground(new Color(60,100,180)); }
            @Override public void mouseReleased(MouseEvent e) { btn.setBackground(new Color(95,135,230)); }
        });
    }

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
            int shadowGap = 4;
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();

            g2.setColor(new Color(0,0,0,40));
            g2.fill(new RoundRectangle2D.Double(shadowGap, shadowGap, width - shadowGap*2, height - shadowGap*2, cornerRadius, cornerRadius));

            g2.setColor(backgroundColor);
            g2.fill(new RoundRectangle2D.Double(0, 0, width - shadowGap, height - shadowGap, cornerRadius, cornerRadius));

            g2.dispose();
            super.paintComponent(g);
        }
    }
}
