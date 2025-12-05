package org.Yaed.windows.deportes;

import org.Yaed.util.WindowManager;
import org.Yaed.windows.AyudaVentana;
import org.Yaed.windows.ConfiguracionVentana;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsFilled;
import org.kordamp.ikonli.swing.FontIcon;
import org.Yaed.controller.DeportesController;
import org.Yaed.controller.EstudiantesController;
import org.Yaed.entity.CategoriaDeportes;
import org.Yaed.entity.GrupoDeporte;
import org.Yaed.entity.Estudiante;
import org.Yaed.entity.GruposDeportesEstudiantes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

public class GruposVentana extends JFrame {

    private DefaultTableModel model;
    public JTable table;
    private TableRowSorter<DefaultTableModel> sorter;
    private static JTable tablaEstudiantes;
    private JTextField tfSearch;

    public GruposVentana() {
        setTitle("SCEBE - Gestión de grupos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);
        // Poner icono
        ImageIcon icono = new ImageIcon(Objects.requireNonNull(getClass().getResource("/logo.png")));
        setIconImage(icono.getImage());
        setLayout(new BorderLayout());

        // ======== PANEL LATERAL ========
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

        // ======== PANEL PRINCIPAL ========
        JPanel panelPrincipal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                Color c1 = new Color(18, 28, 64);
                Color c2 = new Color(26, 42, 91);
                GradientPaint gp = new GradientPaint(0, 0, c1, 0, getHeight(), c2);
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        panelPrincipal.setOpaque(false);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPrincipal.setLayout(new BorderLayout(12, 12));

        // ======== HEADER ========
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JLabel lblTitulo = new JLabel("Gestión de grupos deportivos");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.add(lblTitulo, BorderLayout.WEST);

        JPanel rightHeader = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        rightHeader.setOpaque(false);

        tfSearch = new JTextField(18);
        tfSearch.setToolTipText("Buscar por nombre, deporte o sexo...");
        tfSearch.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
        tfSearch.setMaximumSize(new Dimension(220, 34));
        tfSearch.setPreferredSize(new Dimension(180, 32));
        rightHeader.add(tfSearch);

        JButton btnAgregar = new JButton("Agregar grupo");
        stylePrimaryButton(btnAgregar);

        // 👉 CONSTRUCTOR CORRECTO
        btnAgregar.addActionListener(e -> {
            GrupoForm form = new GrupoForm(this);
            form.setVisible(true);

            if (form.isSaved()) {
                addRows(model);
            }
        });

        rightHeader.add(btnAgregar);
        header.add(rightHeader, BorderLayout.EAST);
        panelPrincipal.add(header, BorderLayout.NORTH);

        // ======== TABLA ========
        String[] cols = {"Nombre", "Deporte", "Integrantes"};
        model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };

        addRows(model);

        table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setRowHeight(26);
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        panelPrincipal.add(sp, BorderLayout.CENTER);

        // ======== BOTONERA ========
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 8));
        bottom.setOpaque(false);

        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");
        stylePrimaryButton(btnEditar);

        btnEliminar.setBackground(new Color(220, 80, 80));
        btnEliminar.setForeground(Color.WHITE);

        // 👉 EDITAR CONSTRUCTOR CORRECTO
        btnEditar.addActionListener(e -> editSelectedRow());

        btnEliminar.addActionListener(e -> onDeleteSelected());

        bottom.add(btnEditar);
        bottom.add(btnEliminar);
        panelPrincipal.add(bottom, BorderLayout.SOUTH);

        add(panelPrincipal, BorderLayout.CENTER);

        // ===== FILTRO DE BÚSQUEDA =====
        tfSearch.getDocument().addDocumentListener(new DocumentListener() {
            private void update() {
                String text = tfSearch.getText();
                if (text.trim().isEmpty()) sorter.setRowFilter(null);
                else sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(text)));
            }
            @Override public void insertUpdate(DocumentEvent e) { update(); }
            @Override public void removeUpdate(DocumentEvent e) { update(); }
            @Override public void changedUpdate(DocumentEvent e) { update(); }
        });
    }

    private void editSelectedRow() {
        int sel = table.getSelectedRow();
        if (sel == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un grupo para editar.");
            return;
        }

        int modelIndex = table.convertRowIndexToModel(sel);
        String nombreGrupo = model.getValueAt(modelIndex, 0).toString();

        GrupoDeporte grupo = DeportesController.getAllGruposDeporte().stream()
                .filter(g -> g.getNombre().equals(nombreGrupo))
                .findFirst()
                .orElse(null);

        if (grupo == null) {
            JOptionPane.showMessageDialog(this, "No se encontró el grupo.");
            return;
        }

        // ✅ Abrir el formulario de edición
        EditarGrupoForm form = new EditarGrupoForm(this, grupo);
        form.setVisible(true);

        // ✅ Si se guardaron cambios, refrescar la tabla
        if (form.isSaved()) {
            addRows(model);
        }
    }


    private void onDeleteSelected() {
        int sel = table.getSelectedRow();
        if (sel == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un grupo para eliminar.");
            return;
        }

        int conf = JOptionPane.showConfirmDialog(this,
                "¿Eliminar el grupo seleccionado?", "Confirmar", JOptionPane.YES_NO_OPTION);

        if (conf == JOptionPane.YES_OPTION) {
            int modelIndex = table.convertRowIndexToModel(sel);
            // Obtener el nombre del grupo desde la tabla (asumimos columna 0 es nombre)
            String nombreGrupo = model.getValueAt(modelIndex, 0).toString();

            // Buscar el grupo en la lista de grupos
            GrupoDeporte grupoABorrar = null;
            for (GrupoDeporte g : DeportesController.getAllGruposDeporte()) {
                if (g.getNombre().equalsIgnoreCase(nombreGrupo)) {
                    grupoABorrar = g;
                    break;
                }
            }

            if (grupoABorrar != null) {
                // Buscar registros en la tabla intermedia
                List<GruposDeportesEstudiantes> relaciones = DeportesController.getAllGruposDeportesEstudiantes();
                for (GruposDeportesEstudiantes ge : relaciones) {
                    if (ge.getGrupoDeporte().getNombre().equalsIgnoreCase(nombreGrupo)) {
                        DeportesController.deleteGruposDeporteaEstudiante(ge);
                    }
                }

                // Borrar el grupo de la base de datos
                DeportesController.deleteGrupoDeporte(grupoABorrar);

                // Borrar de la tabla
                model.removeRow(modelIndex);
                JOptionPane.showMessageDialog(this, "Grupo y sus relaciones eliminados correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el grupo en la base de datos.");
            }
        }
    }



    public static void addRows(DefaultTableModel model) {
        model.setRowCount(0);
        List<GrupoDeporte> grupos = DeportesController.getAllGruposDeporte();

        for (GrupoDeporte grupo : grupos) {
            model.addRow(new Object[]{
                    grupo.getNombre(),
                    grupo.getCategoria().getNombre(),
                    grupo.getGruposDeportes().size()
            });
        }
    }

    private void abrirListadoEstudiantes() {
        WindowManager.abrir("ListadoEstudiantes", new EstudiantesDeportes());
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

    private void stylePrimaryButton(JButton btn) {
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(80, 120, 200));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
    }
}
