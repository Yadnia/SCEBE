package org.Yaed.windows.deportes;
import org.Yaed.controller.DeportesController;
import org.Yaed.entity.ActividadesDeportesEstudiantes;
import org.Yaed.entity.Estudiante;
import org.Yaed.entity.GruposDeportesEstudiantes;
import org.Yaed.util.WindowManager;
import org.Yaed.windows.AyudaVentana;
import org.Yaed.windows.ConfiguracionVentana;
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

public class ActividadesHistorialVentana extends JFrame {
    private DefaultTableModel model;
    private JTable table;
    private JTextField tfBuscar;

    public ActividadesHistorialVentana() {
        setTitle("SCEBE - Historial de actividades");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1100, 700);
        // Poner icono
        ImageIcon icono = new ImageIcon(Objects.requireNonNull(getClass().getResource("/logo.png")));
        setIconImage(icono.getImage());
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

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

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        JLabel lbl = new JLabel("Historial de actividades");
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
        header.add(lbl, BorderLayout.WEST);

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        right.setOpaque(false);
        JButton btnExport = new JButton("Exportar CSV");
        stylePrimaryButton(btnExport);
        btnExport.addActionListener(e -> exportTableToExcel(table));
        right.add(btnExport);
        header.add(right, BorderLayout.EAST);

        panelPrincipal.add(header, BorderLayout.NORTH);

        // Card central
        RoundedPanel card = new RoundedPanel(new Color(245,246,250), 14);
        card.setLayout(new BorderLayout(12,12));
        card.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));

        // Buscador
        JPanel top = new JPanel(new BorderLayout(8,0));
        top.setOpaque(false);
        tfBuscar = new JTextField();
        tfBuscar.setToolTipText("Filtrar historial...");
        tfBuscar.setPreferredSize(new Dimension(240, 32));
        tfBuscar.setMaximumSize(new Dimension(320, 34));
        tfBuscar.addKeyListener(new KeyAdapter() {
            @Override public void keyReleased(KeyEvent e) { aplicarFiltro(tfBuscar.getText()); }
        });
        top.add(tfBuscar, BorderLayout.CENTER);
        card.add(top, BorderLayout.NORTH);

        // Tabla historial
        String[] cols = {"Fecha","Actividad","Grupo","Estudiantes"};
        model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r,int c){return false;}
        };
        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        table.setRowHeight(26);
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        sp.getViewport().setBackground(new Color(250,250,255));
        sp.setPreferredSize(new Dimension(820, 420));
        card.add(sp, BorderLayout.CENTER);

        // Ejemplos
    //        addRow("2025-10-01","16:00","Entrenamiento técnico","Equipo A","María López","Realizada","Buena asistencia");
    //        addRow("2025-10-05","18:30","Partido liga","Equipo B","Carlos Pérez","Cancelada","Mal tiempo");
    //        addRow("2025-10-12","15:00","Evaluación física","Equipo A","Laura Gómez","Realizada","Resultados guardados");

        addRows(model);
        // Footer con ver detalle
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT,8,0));
        footer.setOpaque(false);
        JButton btnVer = new JButton("Ver detalle");
        stylePrimaryButton(btnVer);
        btnVer.setEnabled(false);
        btnVer.addActionListener(e -> verDetalle());
        footer.add(btnVer);
        card.add(footer, BorderLayout.SOUTH);

        table.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            btnVer.setEnabled(table.getSelectedRow() != -1);
        });

        table.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==2) verDetalle();
            }
        });

        panelPrincipal.add(card, BorderLayout.CENTER);
        add(panelPrincipal, BorderLayout.CENTER);
    }

    private void aplicarFiltro(String texto) {
        texto = texto == null ? "" : texto.trim();
        if (texto.isEmpty()) { table.setRowSorter(null); return; }
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        sorter.setRowFilter(RowFilter.regexFilter("(?i)"+Pattern.quote(texto)));
        table.setRowSorter(sorter);
    }

    private void addRow(String fecha,String hora,String actividad,String grupo,String resp,String estado,String notas){
        model.addRow(new Object[]{fecha,hora,actividad,grupo,resp,estado,notas});
    }

    private void verDetalle() {
        int row = table.getSelectedRow();
        if (row==-1) return;
        int m = table.convertRowIndexToModel(row);
        StringBuilder sb = new StringBuilder();
        sb.append("Fecha: ").append(model.getValueAt(m,0)).append("\n");
        sb.append("Hora: ").append(model.getValueAt(m,1)).append("\n");
        sb.append("Actividad: ").append(model.getValueAt(m,2)).append("\n");
        sb.append("Grupo: ").append(model.getValueAt(m,3)).append("\n");
        sb.append("Responsable: ").append(model.getValueAt(m,4)).append("\n");
        sb.append("Estado: ").append(model.getValueAt(m,5)).append("\n");
        sb.append("Notas: ").append(model.getValueAt(m,6)).append("\n");
        JTextArea ta = new JTextArea(sb.toString());
        ta.setEditable(false);
        ta.setBackground(new Color(250,250,255));
        ta.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        JOptionPane.showMessageDialog(this, new JScrollPane(ta), "Detalle de actividad", JOptionPane.INFORMATION_MESSAGE);
    }

    private void exportCSV() {
        if (model.getRowCount()==0) { JOptionPane.showMessageDialog(this,"No hay datos para exportar.","Exportar",JOptionPane.INFORMATION_MESSAGE); return; }
        StringBuilder sb = new StringBuilder();
        for (int c=0;c<model.getColumnCount();c++){
            if (c>0) sb.append(',');
            sb.append('"').append(model.getColumnName(c)).append('"');
        }
        sb.append('\n');
        for (int r=0;r<model.getRowCount();r++){
            for (int c=0;c<model.getColumnCount();c++){
                if (c>0) sb.append(',');
                Object v = model.getValueAt(r,c);
                sb.append('"').append(v==null?"":v.toString().replace("\"","\"\"")).append('"');
            }
            sb.append('\n');
        }
        JTextArea ta = new JTextArea(sb.toString());
        ta.setCaretPosition(0);
        ta.setEditable(false);
        JScrollPane sp = new JScrollPane(ta);
        sp.setPreferredSize(new Dimension(800,400));
        JOptionPane.showMessageDialog(this, sp, "CSV generado (copiar/guardar manualmente)", JOptionPane.INFORMATION_MESSAGE);
    }

    // Helpers visuales (coherentes con ActividadesGrupoVentana)
    private void styleSidebarButton(JButton btn) {
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(8, 17, 45));
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 14, 10, 14));
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { btn.setBackground(new Color(18,30,70)); }
            @Override public void mouseExited(MouseEvent e) { btn.setBackground(new Color(8,17,45)); }
        });
    }
    private void stylePrimaryButton(JButton btn) {
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(80, 120, 200));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    // Pequeño RoundedPanel reutilizable
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

    public static void addRows(DefaultTableModel model) {
        List<ActividadesDeportesEstudiantes> actividadesList = DeportesController.getAllActividadesGruposDeportes();
        List<GruposDeportesEstudiantes> gruposEstudiantesList = DeportesController.getAllGruposDeportesEstudiantes();

        for (ActividadesDeportesEstudiantes actividadEstudiante : actividadesList) {
            String fecha = actividadEstudiante.getFecha();
            String actividad = actividadEstudiante.getActividad().getNombre();
            Estudiante estudiante = actividadEstudiante.getEstudiante();

            // Buscar el grupo del estudiante en la lista usando un for
            String grupoNombre = "—";
            for (GruposDeportesEstudiantes gde : gruposEstudiantesList) {
                if (gde.getEstudiante().getCarnet().equals(estudiante.getCarnet())) {
                    grupoNombre = gde.getGrupoDeporte().getNombre();
                    break; // ya encontramos el grupo, salimos del for
                }
            }

            // Agregar la fila al modelo
            model.addRow(new Object[]{fecha, actividad, grupoNombre,estudiante.getNombre()});
        }
    }
    private void exportTableToExcel(JTable table) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar como");
        fileChooser.setSelectedFile(new java.io.File("actividades.xlsx"));

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            java.io.File fileToSave = fileChooser.getSelectedFile();

            try (org.apache.poi.xssf.usermodel.XSSFWorkbook workbook = new org.apache.poi.xssf.usermodel.XSSFWorkbook()) {
                org.apache.poi.xssf.usermodel.XSSFSheet sheet = workbook.createSheet("Actividades");

                // Escribir encabezados
                org.apache.poi.xssf.usermodel.XSSFRow headerRow = sheet.createRow(0);
                for (int col = 0; col < table.getColumnCount(); col++) {
                    headerRow.createCell(col).setCellValue(table.getColumnName(col));
                }

                // Escribir datos
                for (int row = 0; row < table.getRowCount(); row++) {
                    org.apache.poi.xssf.usermodel.XSSFRow excelRow = sheet.createRow(row + 1);
                    for (int col = 0; col < table.getColumnCount(); col++) {
                        Object value = table.getValueAt(row, col);
                        excelRow.createCell(col).setCellValue(value != null ? value.toString() : "");
                    }
                }

                // Ajustar ancho de columnas
                for (int col = 0; col < table.getColumnCount(); col++) {
                    sheet.autoSizeColumn(col);
                }

                // Guardar archivo
                try (java.io.FileOutputStream out = new java.io.FileOutputStream(fileToSave)) {
                    workbook.write(out);
                }

                JOptionPane.showMessageDialog(null, "Tabla exportada correctamente a Excel.");

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al exportar: " + e.getMessage());
            }
        }
    }



}