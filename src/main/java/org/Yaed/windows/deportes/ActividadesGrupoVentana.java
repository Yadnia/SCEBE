package org.Yaed.windows.deportes;

import org.Yaed.controller.DeportesController;
import org.Yaed.entity.*;
import org.Yaed.util.WindowManager;
import org.Yaed.windows.AyudaVentana;
import org.Yaed.windows.ConfiguracionVentana;
import org.Yaed.windows.dialog.EditarActividadDialog;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsFilled;
import org.kordamp.ikonli.swing.FontIcon;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class ActividadesGrupoVentana extends JFrame {

    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField tfBuscar;
    private JButton btnEditar, btnEliminar;

    public ActividadesGrupoVentana() {
        setTitle("SCEBE - Actividades del Grupo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        // Panel principal con degradado
        JPanel panelPrincipal = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
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
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));

        // Header con título y botón Agregar actividad
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        JLabel lblTitulo = new JLabel("Actividades del grupo");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        header.add(lblTitulo, BorderLayout.WEST);

        JButton btnAgregar = new JButton("Agregar actividad");
        stylePrimaryButton(btnAgregar);
        btnAgregar.addActionListener(e -> new ActivityDialog(this, null).setVisible(true));
        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        right.setOpaque(false);
        right.add(btnAgregar);
        header.add(right, BorderLayout.EAST);

        panelPrincipal.add(header, BorderLayout.NORTH);

        // Centro: buscador + tabla dentro de tarjeta redondeada
        RoundedPanel card = new RoundedPanel(new Color(245, 246, 250), 14);
        card.setLayout(new BorderLayout(12, 12));
        card.setBorder(new EmptyBorder(14,14,14,14));

        // Buscador
        JPanel topRow = new JPanel(new BorderLayout(8,0));
        topRow.setOpaque(false);
        tfBuscar = new JTextField();
        tfBuscar.setToolTipText("Filtrar por actividad o responsable...");
        tfBuscar.setPreferredSize(new Dimension(240, 32));
        tfBuscar.setMaximumSize(new Dimension(320, 34));
        tfBuscar.addKeyListener(new KeyAdapter() {
            @Override public void keyReleased(KeyEvent e) { aplicarFiltro(tfBuscar.getText()); }
        });
        topRow.add(tfBuscar, BorderLayout.CENTER);
        card.add(topRow, BorderLayout.NORTH);

        // Tabla
        // ahora incluimos columna "Grupo" (nombre del grupo) entre Actividad y Responsable
        String[] cols = {"Fecha", "Actividad", "Grupo"};
        tableModel = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(tableModel);
        table.setRowHeight(26);
        // permitir que las columnas se ajusten al ancho de la tarjeta en 1100x700
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        sp.getViewport().setBackground(new Color(250,250,255));
        sp.setPreferredSize(new Dimension(820, 420));
        card.add(sp, BorderLayout.CENTER);
        // establecer anchos por columna (aprox)
        if (table.getColumnModel().getColumnCount() >= 5) {
            table.getColumnModel().getColumn(0).setPreferredWidth(100); // Fecha
            table.getColumnModel().getColumn(1).setPreferredWidth(70);  // Hora
            table.getColumnModel().getColumn(2).setPreferredWidth(260); // Actividad

        }
//Datos de ejemplo
        List<ActividadesDeportesEstudiantes> actividades = DeportesController.getAllActividadesGruposDeportes();

        for (ActividadesDeportesEstudiantes act : actividades){
            String fecha = act.getFecha();
            String actividad = act.getActividad().getNombre();
            String estudiante = act.getEstudiante().getNombre();

            // Buscamos el grupo del estudiante
            GruposDeportesEstudiantes grupoEstudiante = DeportesController
                    .getAllGruposDeportesEstudiantes()
                    .stream()
                    .filter(ge -> ge.getEstudiante().getNombre().equalsIgnoreCase(estudiante))
                    .findFirst() // solo el primero
                    .orElse(null);

            if (grupoEstudiante != null) {
                String grupo = grupoEstudiante.getGrupoDeporte().getNombre();
                agregarActividadTabla(fecha, actividad, grupo);
            }
        }


        // Pie con botones Editar/Eliminar
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        footer.setOpaque(false);
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        stylePrimaryButton(btnEditar); btnEditar.setBackground(new Color(110,140,190));
        stylePrimaryButton(btnEliminar); btnEliminar.setBackground(new Color(200,80,80));
        btnEditar.setEnabled(false); btnEliminar.setEnabled(false);

        btnEditar.addActionListener(e -> editarSeleccion());
        btnEliminar.addActionListener(e -> eliminarSeleccion());

        footer.add(btnEditar);
        footer.add(btnEliminar);
        card.add(footer, BorderLayout.SOUTH);

        // activar/desactivar botones según selección
        table.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            boolean sel = table.getSelectedRow() != -1;
            btnEditar.setEnabled(sel);
            btnEliminar.setEnabled(sel);
        });

        // doble clic para editar
        table.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) editarSeleccion();
            }
        });

        panelPrincipal.add(card, BorderLayout.CENTER);
        add(panelPrincipal, BorderLayout.CENTER);
    }

    private void aplicarFiltro(String texto) {
        texto = texto == null ? "" : texto.trim().toLowerCase();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        if (texto.isEmpty()) {
            table.setRowSorter(null);
            return;
        }
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(texto)));
        table.setRowSorter(sorter);
    }

    // ahora recibe también el nombre del grupo que realiza la actividad
    private void agregarActividadTabla(String fecha, String actividad, String grupo) {
        tableModel.addRow(new Object[]{fecha, actividad, grupo});
    }

    private void editarSeleccion() {
        int row = table.getSelectedRow();
        if (row == -1) return;
        int modelRow = table.convertRowIndexToModel(row);

        // Solo leer las columnas existentes
        String fecha = (String) tableModel.getValueAt(modelRow, 0);
        String actividad = (String) tableModel.getValueAt(modelRow, 1);
        String grupo = (String) tableModel.getValueAt(modelRow, 2);

        // Abrir el diálogo con esos datos mínimos
        new EditarActividadDialog(this, modelRow, tableModel).setVisible(true);
    }


    private void eliminarSeleccion() {
        int row = table.getSelectedRow();
        if (row == -1) return; // No hay selección

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Eliminar la actividad seleccionada?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) return;

        int modelRow = table.convertRowIndexToModel(row);

        // Leer los datos de la fila (solo si los quieres usar, opcional)
        String fecha = (String) tableModel.getValueAt(modelRow, 0);
        String actividad = (String) tableModel.getValueAt(modelRow, 1);
        String grupo = (String) tableModel.getValueAt(modelRow, 2);

        // Aquí puedes llamar al controlador si quieres eliminar también de la base de datos
        List<ActividadesDeportesEstudiantes> acts = DeportesController.getAllActividadesGruposDeportes();
        for (ActividadesDeportesEstudiantes e : acts){
            if (e.getActividad().getNombre().equalsIgnoreCase(actividad)){
                DeportesController.deleteActividadesGruposDeportes(e);
            }
        }


        // Eliminar la fila de la tabla
        tableModel.removeRow(modelRow);
    }


    // Helper: botón primario
    private void stylePrimaryButton(JButton btn) {
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(80, 120, 200));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    // Clase interna: actividad simple
    // ahora incluye información del grupo (nombre) además del responsable
    private static class Activity {
        String fecha, hora, actividad, grupo, responsable;
        Activity(String f, String h, String a, String g, String r) { fecha = f; hora = h; actividad = a; grupo = g; responsable = r; }
    }

    // Callback para edición
    private interface ActivityEditCallback {
        void onSave(Activity updated);
    }

    // Dialog modal para agregar/editar actividad
    private class ActivityDialog extends JDialog {
        private JTextField tfFecha, tfHora, tfNombreActividad, tfDescripcion;
        private JComboBox<String> cbCategoria, cbGrupo;
        private ActivityEditCallback callback;
        private Activity initial;

        public ActivityDialog(Frame owner, ActivityEditCallback cb) {
            super(owner, "Actividad", true);
            this.callback = cb;
            init();
            pack();
            setResizable(false);
            setLocationRelativeTo(owner);
        }

        public ActivityDialog setInitial(Activity a) {
            this.initial = a;
            tfFecha.setText(a.fecha);
            tfHora.setText(a.hora);
            tfNombreActividad.setText(a.actividad);
            return this;
        }

        private void init() {
            JPanel content = new JPanel(new BorderLayout(10,10));
            content.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
            content.setBackground(new Color(250,250,255));

            JPanel form = new JPanel(new GridBagLayout());
            form.setOpaque(false);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(6,6,6,6);
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            // ======== FECHA CON DATEPICKER DIRECTO ========
            gbc.gridx = 0; gbc.gridy = 0;
            form.add(new JLabel("Fecha:"), gbc);

            gbc.gridx = 1;

            com.github.lgooddatepicker.components.DatePicker datePicker =
                    new com.github.lgooddatepicker.components.DatePicker();

            // Guardamos la fecha en un TextField oculto (si tu lógica lo requiere)
            tfFecha = new JTextField();
            tfFecha.setVisible(false);

            // sincronizar fecha al TextField si lo usas en onSave()
            datePicker.addDateChangeListener(e -> {
                if (e.getNewDate() != null) {
                    tfFecha.setText(e.getNewDate().toString());
                } else {
                    tfFecha.setText("");
                }
            });

            // panel contenedor (DatePicker visible + tf oculto)
            JPanel fechaPanel = new JPanel(new BorderLayout());
            fechaPanel.setOpaque(false);
            fechaPanel.add(datePicker, BorderLayout.CENTER);
            fechaPanel.add(tfFecha, BorderLayout.SOUTH);

            form.add(fechaPanel, gbc);

            // ======== HORA ========
            // ======== HORA CON TIMEPICKER ========
            gbc.gridx = 0; gbc.gridy++;
            form.add(new JLabel("Hora:"), gbc);

            gbc.gridx = 1;

// TimePicker de LGoodDatePicker
            com.github.lgooddatepicker.components.TimePicker timePicker =
                    new com.github.lgooddatepicker.components.TimePicker();

// TextField oculto para mantener compatibilidad con onSave()
            tfHora = new JTextField();
            tfHora.setVisible(false);

// Sincroniza automáticamente la hora seleccionada
            timePicker.addTimeChangeListener(e -> {
                if (e.getNewTime() != null) {
                    tfHora.setText(e.getNewTime().toString()); // formato HH:MM
                } else {
                    tfHora.setText("");
                }
            });

// Panel contenedor
            JPanel horaPanel = new JPanel(new BorderLayout());
            horaPanel.setOpaque(false);
            horaPanel.add(timePicker, BorderLayout.CENTER);
            horaPanel.add(tfHora, BorderLayout.SOUTH);

            form.add(horaPanel, gbc);


            // ======== Nombre ========
            gbc.gridx = 0; gbc.gridy++;
            form.add(new JLabel("Nombre:"), gbc);
            gbc.gridx = 1;
            tfNombreActividad = new JTextField(22);
            form.add(tfNombreActividad, gbc);

            // ======== Descripcion ========
            gbc.gridx = 0; gbc.gridy++;
            form.add(new JLabel("Descripcion"), gbc);
            gbc.gridx = 1;
            tfDescripcion = new JTextField(22);
            form.add(tfDescripcion, gbc);

            // ======== GRUPO (CARGADO DESDE BD) ========
            gbc.gridx = 0; gbc.gridy++;
            form.add(new JLabel("Grupo:"), gbc);

            gbc.gridx = 1;

// Combo para mostrar grupos
            cbGrupo = new JComboBox<>();

// Cargar grupos desde la base de datos
            try {
                List<GrupoDeporte> grupos = DeportesController.getAllGruposDeporte();

                for (GrupoDeporte g : grupos) {
                    cbGrupo.addItem(g.getNombre());
                    // Si quieres guardar directamente el objeto:
                    // cbGrupo.addItem(g);
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Error cargando grupos: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            form.add(cbGrupo, gbc);
            ;



            // ======== TIPO DE ACTIVIDAD (SELECT + BOTÓN AGREGAR) ========
            gbc.gridx = 0; gbc.gridy++;
            form.add(new JLabel("Tipo de actividad:"), gbc);

            gbc.gridx = 1;

// Panel para combo + botón
            JPanel tipoPanel = new JPanel(new BorderLayout(5, 0));
            tipoPanel.setOpaque(false);

// ComboBox inicial con algunos tipos
            JComboBox<String> cbTipoActividad = new JComboBox<>();
            List<TipoActividadDeporte> tiposActividad = DeportesController.getAllTipoActividadDeporte();
            for (TipoActividadDeporte t : tiposActividad){
                cbTipoActividad.addItem(t.getNombre());
            }
            tipoPanel.add(cbTipoActividad, BorderLayout.CENTER);

// Botón + para agregar un nuevo tipo
            JButton btnAddTipo = new JButton("+");
            btnAddTipo.setMargin(new Insets(2,6,2,6));
            btnAddTipo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            btnAddTipo.addActionListener(e -> {
                String nuevoTipo = JOptionPane.showInputDialog(
                        ActivityDialog.this,
                        "Ingrese el nuevo tipo de actividad:",
                        "Agregar tipo",
                        JOptionPane.PLAIN_MESSAGE
                );
                if (nuevoTipo != null && !nuevoTipo.trim().isEmpty()) {
                    // Agregar al ComboBox y seleccionar automáticamente
                    TipoActividadDeporte tipoActividad = new TipoActividadDeporte();
                    tipoActividad.setNombre(nuevoTipo.trim());
                    DeportesController.saveTipoActividadDeporte(tipoActividad);

                }
            });

            tipoPanel.add(btnAddTipo, BorderLayout.EAST);

            form.add(tipoPanel, gbc);


            content.add(form, BorderLayout.CENTER);

            // ======== BOTONES ========
            JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
            actions.setOpaque(false);

//            JButton actualizar = new JButton("Actualizar");
//            actualizar.addActionListener(
//                    e -> dispose(),
//                    new ActivityDialog()
//            );

            JButton btnCancel = new JButton("Cancelar");
            JButton btnSave = new JButton("Guardar");
            styleDialogPrimary(btnSave);

            btnCancel.addActionListener(e -> dispose());
            btnSave.addActionListener(e -> onSave());

            actions.add(btnCancel);
            actions.add(btnSave);

            content.add(actions, BorderLayout.SOUTH);

            setContentPane(content);
        }



        private void styleDialogPrimary(JButton btn) {
            btn.setBackground(new Color(60,110,190));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createEmptyBorder(6,12,6,12));
            btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        private void onSave() {

            String fecha = tfFecha.getText().trim();
            String hora = tfHora.getText().trim(); // si lo usarás después
            String actividad = tfNombreActividad.getText().trim();
            String grupo = cbGrupo != null ? String.valueOf(cbGrupo.getSelectedItem()) : "—";
            String categoria = cbCategoria != null ? String.valueOf(cbCategoria.getSelectedItem()) : "—";
            String descripcion = tfDescripcion.getText().trim();

            // =======================
            // 1. Crear la actividad
            // =======================
            ActividadDeporte nuevaActividad = new ActividadDeporte();
            nuevaActividad.setNombre(actividad);
            nuevaActividad.setDescripcion(descripcion);

            // =======================
            // 2. Buscar tipo de actividad
            // =======================
            TipoActividadDeporte tipoSeleccionado = null;
            for (TipoActividadDeporte t : DeportesController.getAllTipoActividadDeporte()) {
                if (t.getNombre().equalsIgnoreCase(categoria)) {
                    tipoSeleccionado = t;
                    break;
                }
            }
            nuevaActividad.setTipo(tipoSeleccionado);

            // =======================
            // 3. (Opcional) guardar la actividad primero
            //     si tu saveActividadesGruposDeportes NO guarda actividad automáticamente
            DeportesController.saveActividadDeporte(nuevaActividad);
            // =======================
            // DeportesController.saveActividad(nuevaActividad);

            // =======================
            // 4. Obtener los estudiantes del grupo
            // =======================
            List<GruposDeportesEstudiantes> gruposEst =
                    DeportesController.getAllGruposDeportesEstudiantes();

            for (GruposDeportesEstudiantes ge : gruposEst) {

                if (ge.getGrupoDeporte().getNombre().equalsIgnoreCase(grupo)) {

                    Estudiante est = ge.getEstudiante();

                    // =======================
                    // 5. Crear relación actividad-estudiante
                    // =======================
                    ActividadesDeportesEstudiantes rel = new ActividadesDeportesEstudiantes();
                    rel.setActividad(nuevaActividad);
                    rel.setEstudiante(est);
                    rel.setFecha(fecha);

                    // =======================
                    // 6. Guardar fila en tabla intermedia
                    // =======================
                    DeportesController.saveActividadesGruposDeportes(rel);
                }
            }

            dispose();
        }

    }

    // Panel redondeado simple
    private static class RoundedPanel extends JPanel {
        private final Color backgroundColor;
        private final int cornerRadius;
        public RoundedPanel(Color bg, int radius) { backgroundColor = bg; cornerRadius = radius; setOpaque(false); }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(backgroundColor);
            g2.fillRoundRect(0,0,getWidth(),getHeight(),cornerRadius,cornerRadius);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    // ===== Métodos que manejan acciones del sidebar =====
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

}