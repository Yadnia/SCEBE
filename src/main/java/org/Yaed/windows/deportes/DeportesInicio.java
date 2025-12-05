package org.Yaed.windows.deportes;
import org.Yaed.util.WindowManager;
import org.Yaed.windows.AyudaVentana;
import org.Yaed.windows.ConfiguracionVentana;
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
import java.util.*;
import java.util.List;

public class DeportesInicio extends JFrame {

    private Connection conexion;

    public DeportesInicio() {
        setTitle("SCEBE - Grupos Deportivos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        // Poner icono
        ImageIcon icono = new ImageIcon(Objects.requireNonNull(getClass().getResource("/logo.png")));
        setIconImage(icono.getImage());

        // ===== PANEL LATERAL =====
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
// Opciones
        String[] opciones = {
                "Listado de estudiantes",
                "Actividad de grupo",
                "Historial de actividad",
                "Grupos",
                "Configuración",
                "Ayuda"
        };
// Iconos (AntDesign Icons)
        Ikon[] iconos = {
                AntDesignIconsFilled.PROFILE,      // Listado de estudiantes
                AntDesignIconsFilled.CALENDAR,     // Actividad de grupo
                AntDesignIconsFilled.BOOK,       // Historial de actividad
                AntDesignIconsFilled.IDCARD, // Grupos
                AntDesignIconsFilled.SETTING,      // Configuración
                AntDesignIconsFilled.CUSTOMER_SERVICE// Ayuda
        };

        for (int i = 0; i < opciones.length; i++) {

            JButton btn = new JButton(opciones[i]);
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            btn.setForeground(Color.WHITE);
            btn.setBackground(new Color(8, 17, 45));
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            // Ícono
            btn.setIcon(FontIcon.of(iconos[i], 20, Color.WHITE));
            btn.setHorizontalAlignment(SwingConstants.LEFT);
            btn.setIconTextGap(10);

            // --- ARREGLO PARA ALINEAR ---
            btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

            // Listener
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


        // ===== PANEL PRINCIPAL con fondo degradado =====
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

        // Encabezado principal mejorado
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        JLabel lblTitulo = new JLabel("Grupos deportivos");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        header.add(lblTitulo, BorderLayout.NORTH);

        JLabel subt = new JLabel("Explora los grupos activos y la información relevante");
        subt.setForeground(new Color(200, 210, 230));
        subt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subt.setBorder(new EmptyBorder(6, 0, 12, 0));
        header.add(subt, BorderLayout.SOUTH);

        // --- NUEVO: botón "Agregar equipo" en el header (lado derecho)
        JPanel headerRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        headerRight.setOpaque(false);
        JButton btnAgregar = new JButton("Agregar equipo");
        stylePrimaryButton(btnAgregar);
        btnAgregar.addActionListener(e -> {
            // abrir formulario modal para crear equipo
            new GrupoForm(this);
        });
        JButton actualizarBtn = new JButton("Actualizar");
        stylePrimaryButton(actualizarBtn);
        actualizarBtn.addActionListener(e -> {
            // refrescar la ventana actual
            this.dispose();
            DeportesInicio nuevaVentana = new DeportesInicio();
            nuevaVentana.setVisible(true);
        });
        headerRight.add(btnAgregar);
        headerRight.add(actualizarBtn);
        header.add(headerRight, BorderLayout.EAST);

        panelPrincipal.add(header, BorderLayout.NORTH);

        // PANEL DE TARJETAS: ahora con 2 filas y scroll horizontal
        // parámetros de las tarjetas (coincidir con crearPanelGrupo)
        int cardWidth = 240;
        int cardHeight = 140;
        int hGap = 20;
        int vGap = 20;

        // número de tarjetas (mismo ejemplo que antes)
//        int nTarjetas = 8;
        int nTarjetas = DeportesController.getAllGruposDeporte().size();
        // si más adelante cargas desde BD, asigna aquí la cantidad real

        // columnas necesarias para 2 filas
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

        // Añadir tarjetas y mantener tamaño lógico
        for (int i = 1; i <= nTarjetas; i++) {
            List<GrupoDeporte> grupos = DeportesController.getAllGruposDeporte();
            String nombreGrupo = grupos.get(i - 1).getNombre();
            String categoria = grupos.get(i - 1).getCategoria().getNombre();
            String integrantes = String.valueOf(DeportesController.getAllGruposDeporte().get(i - 1).getGruposDeportes().size());
            JPanel card = crearPanelGrupo(i, nombreGrupo, categoria, Integer.parseInt(integrantes));
            card.setPreferredSize(new Dimension(cardWidth, cardHeight));
            panelGrupos.add(card);
        }

        // Forzamos tamaño preferido del panel para que el JScrollPane haga scroll horizontal
        int totalWidth = columnas * (cardWidth + hGap) + 40; // margen extra
        int totalHeight = 2 * (cardHeight + vGap) + 40;
        panelGrupos.setPreferredSize(new Dimension(totalWidth, totalHeight));

        // Scroll pane: horizontal as needed, sin scroll vertical
        // Nota: el constructor es (view, vsbPolicy, hsbPolicy)
        JScrollPane scroll = new JScrollPane(panelGrupos, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        // configurar barra horizontal
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

    // ===== Crear una ficha (card) visual with RoundedPanel =====
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
        // placeholders para equilibrio visual
        centro.add(new JLabel(""));
        centro.add(new JLabel(""));

        panel.add(centro, BorderLayout.CENTER);

        // Efecto hover sobre la tarjeta
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

    // ===== Mostrar estudiantes del grupo =====
    private void mostrarEstudiantesGrupo(int grupoId, String nombreGrupo) {
        JOptionPane.showMessageDialog(this,
                "Mostrando estudiantes de " + nombreGrupo,
                "Grupo " + grupoId,
                JOptionPane.INFORMATION_MESSAGE);
    }

    // ===== Métodos para abrir cada sección (placeholder) =====
    private void abrirListadoEstudiantes() {
        // Abrir la ventana de listado de estudiantes siguiendo la misma estética
        java.awt.EventQueue.invokeLater(() -> {
            WindowManager.abrir("ListadoEstudiantes", new EstudiantesDeportes());
        });
    }

    private void abrirActividadGrupo() {
        WindowManager.abrir("ActividadGrupo", new ActividadesGrupoVentana());
    }

    private void abrirHistorialActividad() {
        WindowManager.abrir("HistorialActividad", new ActividadesHistorialVentana());
    }

    private void abrirGrupos() {
        // Abrir la ventana de gestión de grupos (misma estética)
        java.awt.EventQueue.invokeLater(() -> {
           WindowManager.abrir("GestionGrupos", new GruposVentana());
        });
    }

    private void abrirConfiguracion() {
       WindowManager.abrir("Configuracion", new ConfiguracionVentana());

    }

    private void abrirAyuda() {
       WindowManager.abrir("Ayuda", new AyudaVentana());
    }

    // --- NUEVO: estilo para botón primario (Agregar equipo)
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

    // Clase interna para paneles con bordes redondeados
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

            // sombra sutil
            g2.setColor(new Color(0,0,0,40));
            g2.fill(new RoundRectangle2D.Double(shadowGap, shadowGap, width - shadowGap*2, height - shadowGap*2, cornerRadius, cornerRadius));

            // panel principal
            g2.setColor(backgroundColor);
            g2.fill(new RoundRectangle2D.Double(0, 0, width - shadowGap, height - shadowGap, cornerRadius, cornerRadius));

            g2.dispose();
            super.paintComponent(g);
        }
    }
}
