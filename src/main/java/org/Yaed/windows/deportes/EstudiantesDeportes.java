package org.Yaed.windows.deportes;

import org.Yaed.controller.BecasController;
import org.Yaed.controller.CarrerasController;
import org.Yaed.controller.EstudiantesController;
import org.Yaed.entity.*;
import org.Yaed.util.WindowManager;
import org.Yaed.windows.AyudaVentana;
import org.Yaed.windows.ConfiguracionVentana;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsFilled;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

public class EstudiantesDeportes extends JFrame {

    private DefaultTableModel model;
    private JTable table;
    private TableRowSorter<DefaultTableModel> sorter;
    private JTextField tfSearch;

    public EstudiantesDeportes() {
        setTitle("SCEBE - Listado de estudiantes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);
        // Poner icono
        ImageIcon icono = new ImageIcon(Objects.requireNonNull(getClass().getResource("/logo.png")));
        setIconImage(icono.getImage());
        setLayout(new BorderLayout());

        // PANEL LATERAL
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

        // Panel principal con degradado
        JPanel panelPrincipal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color c1 = new Color(18, 28, 64);
                Color c2 = new Color(26, 42, 91);
                g2.setPaint(new GradientPaint(0,0,c1,0,getHeight(),c2));
                g2.fillRect(0,0,getWidth(),getHeight());
                g2.dispose();
            }
        };
        panelPrincipal.setOpaque(false);
        panelPrincipal.setBorder(new EmptyBorder(18,18,18,18));
        panelPrincipal.setLayout(new BorderLayout(12,12));

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        JLabel lbl = new JLabel("Listado de estudiantes");
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.add(lbl, BorderLayout.WEST);

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT,8,0));
        right.setOpaque(false);
        tfSearch = new JTextField(20);
        tfSearch.setToolTipText("Buscar por nombre, apellido, carnet o cédula...");
        tfSearch.setBorder(BorderFactory.createEmptyBorder(6,8,6,8));
        tfSearch.setMaximumSize(new Dimension(220, 34));
        tfSearch.setPreferredSize(new Dimension(180, 32));
        right.add(tfSearch);

        JButton btnAdd = new JButton("Agregar");
        stylePrimaryButton(btnAdd);
        btnAdd.addActionListener(e -> onAddStudent());
        right.add(btnAdd);

        JButton btnEdit = new JButton("Editar");
        stylePrimaryButton(btnEdit);
        btnEdit.setPreferredSize(new Dimension(100,36));
        btnEdit.addActionListener(e -> onEditSelected());
        right.add(btnEdit);

        JButton btnDel = new JButton("Eliminar");
        btnDel.setBackground(new Color(220,80,80));
        btnDel.setForeground(Color.WHITE);
        btnDel.setFocusPainted(false);
        btnDel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDel.addActionListener(e -> onDeleteSelected());
        right.add(btnDel);

        header.add(right, BorderLayout.EAST);
        panelPrincipal.add(header, BorderLayout.NORTH);

        // Tabla
        String[] cols = {"Nombre","Apellido","Edad","Nº carnet","Nº cédula","Sexo"};
        model = new DefaultTableModel(cols,0) { @Override public boolean isCellEditable(int r,int c){return false;} };
        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        table.setFillsViewportHeight(true);
        table.setRowHeight(26);
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0,0));
        table.setOpaque(true);
        table.setBackground(new Color(250,250,255));
        table.setForeground(new Color(30,30,30));
        table.setSelectionBackground(new Color(80,120,200));
        table.setSelectionForeground(Color.WHITE);

        JTableHeader th = table.getTableHeader();
        th.setBackground(new Color(235,235,240));
        th.setForeground(new Color(30,40,70));
        th.setFont(new Font("Segoe UI", Font.BOLD, 12));

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer(){
            @Override public Component getTableCellRendererComponent(JTable t,Object v,boolean sel,boolean f,int row,int col){
                Component c = super.getTableCellRendererComponent(t,v,sel,f,row,col);
                if (sel) { c.setBackground(t.getSelectionBackground()); c.setForeground(t.getSelectionForeground()); }
                else { c.setForeground(new Color(30,30,30)); c.setBackground(row%2==0 ? Color.WHITE : new Color(245,247,250)); }
                return c;
            }
        };
        table.setDefaultRenderer(Object.class, cellRenderer);

        table.addMouseListener(new MouseAdapter(){
            @Override public void mouseClicked(MouseEvent e){
                if (e.getClickCount()==2) onEditSelected();
            }
        });

        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        sp.getViewport().setBackground(new Color(250,250,255));
        sp.setOpaque(false);
        sp.setPreferredSize(new Dimension(820, 460));
        panelPrincipal.add(sp, BorderLayout.CENTER);

        tfSearch.getDocument().addDocumentListener(new DocumentListener(){
            private void update(){
                String text = tfSearch.getText();
                if (text.trim().isEmpty()) sorter.setRowFilter(null);
                else sorter.setRowFilter(RowFilter.regexFilter("(?i)"+ Pattern.quote(text)));
            }
            @Override public void insertUpdate(DocumentEvent e){ update(); }
            @Override public void removeUpdate(DocumentEvent e){ update(); }
            @Override public void changedUpdate(DocumentEvent e){ update(); }
        });

        addRows(model);
        add(panelPrincipal, BorderLayout.CENTER);
    }

    // === MÉTODOS DE BOTONES ===
    private void onAddStudent() {
        StudentFormDialog dlg = new StudentFormDialog(this, null);
        dlg.setVisible(true);
        if (dlg.isSaved()) {
            Object[] row = dlg.getRow();
            model.addRow(row);
            table.revalidate();
            table.repaint();
        }
    }

    private void onEditSelected() {
        int sel = table.getSelectedRow();
        if (sel == -1) { JOptionPane.showMessageDialog(this, "Seleccione un estudiante."); return; }
        int modelIndex = table.convertRowIndexToModel(sel);

        Object[] pre = new Object[6];
        for (int i=0;i<6;i++) pre[i] = model.getValueAt(modelIndex,i);

        StudentFormDialog dlg = new StudentFormDialog(this, pre);
        dlg.setVisible(true);

        if (dlg.isSaved()) {
            Object[] row = dlg.getRow();
            for (int i=0;i<6;i++) model.setValueAt(row[i], modelIndex, i);
        }
    }

    private void onDeleteSelected() {
        int sel = table.getSelectedRow();
        if (sel == -1) { JOptionPane.showMessageDialog(this, "Seleccione un estudiante."); return; }
        int conf = JOptionPane.showConfirmDialog(this, "¿Eliminar estudiante seleccionado?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (conf == JOptionPane.YES_OPTION){
            int modelIndex = table.convertRowIndexToModel(sel);
            model.removeRow(modelIndex);
        }
    }

    // === ESTILOS ===
    private void stylePrimaryButton(JButton btn){
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(80,120,200));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBorder(BorderFactory.createEmptyBorder(8,14,8,14));
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(true);
        btn.setOpaque(true);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    // === LLENAR TABLA ===
    public static void addRows(DefaultTableModel model){
        List<Estudiante> estudianteList = null;
        try { estudianteList = EstudiantesController.getDeportesEstudiantes(); } catch(Throwable t){ t.printStackTrace(); }
        if (estudianteList==null || estudianteList.isEmpty()) return;
        for(Estudiante e : estudianteList){
            model.addRow(new Object[]{
                    e.getNombre(),
                    e.getApellido(),
                    String.valueOf(e.getEdad()),
                    e.getCarnet(),
                    e.getCedula(),
                    e.getSexo()=='M'?"Masculino":e.getSexo()=='F'?"Femenino":"Otro"
            });
        }
    }

    // === ABRIR VENTANAS ===
    private void abrirListadoEstudiantes() {
        java.awt.EventQueue.invokeLater(() -> WindowManager.abrir("ListadoEstudiantes", new EstudiantesDeportes()));
    }
    private void abrirActividadGrupo() { WindowManager.abrir("ActividadGrupo", new ActividadesGrupoVentana()); }

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

    // === DIALOGO INTERNO ===
    private static class StudentFormDialog extends JDialog {
        private JTextField tfNombre, tfApellido, tfEdad, tfCarnet, tfCedula, tfCelular;
        private JComboBox<String> cbSexo, cbCarrera, cbEtnia;
        private boolean saved = false;

        public StudentFormDialog(Window owner, Object[] prefill){
            super(owner, prefill==null?"Agregar estudiante":"Editar estudiante", ModalityType.APPLICATION_MODAL);
            init(prefill);
            pack();
            setResizable(false);
            setLocationRelativeTo(owner);
        }

        private void init(Object[] prefill){
            JPanel content = new JPanel(new BorderLayout(8,8));
            content.setBorder(new EmptyBorder(12,12,12,12));
            content.setBackground(new Color(250,250,255));

            JLabel title = new JLabel(prefill==null?"Nuevo estudiante":"Editar estudiante");
            title.setFont(new Font("Segoe UI", Font.BOLD, 14));
            title.setForeground(new Color(30,40,70));
            content.add(title, BorderLayout.NORTH);

            JPanel form = new JPanel(new GridBagLayout());
            form.setOpaque(false);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(6,6,6,6);
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            gbc.gridx=0; gbc.gridy=0; form.add(new JLabel("Nombre:"), gbc);
            gbc.gridx=1; tfNombre=new JTextField(18); form.add(tfNombre, gbc);

            gbc.gridx=0; gbc.gridy++; form.add(new JLabel("Apellido:"), gbc);
            gbc.gridx=1; tfApellido=new JTextField(18); form.add(tfApellido, gbc);

            gbc.gridx=0; gbc.gridy++; form.add(new JLabel("Edad:"), gbc);
            gbc.gridx=1; tfEdad=new JTextField(6); form.add(tfEdad, gbc);

            gbc.gridx=0; gbc.gridy++; form.add(new JLabel("Nº carnet:"), gbc);
            gbc.gridx=1; tfCarnet=new JTextField(12); form.add(tfCarnet, gbc);

            gbc.gridx=0; gbc.gridy++; form.add(new JLabel("Nº cédula:"), gbc);
            gbc.gridx=1; tfCedula=new JTextField(12); form.add(tfCedula, gbc);

            gbc.gridx=0; gbc.gridy++; form.add(new JLabel("Sexo:"), gbc);
            gbc.gridx=1; cbSexo = new JComboBox<>(new String[]{"Masculino","Femenino","Otro"}); form.add(cbSexo, gbc);

            gbc.gridx=0; gbc.gridy++; form.add(new JLabel("Carrera:"), gbc);
            gbc.gridx=1;
            List<String> carreras = new ArrayList<>();
            for(Carrera c : CarrerasController.getCarreras()) carreras.add(c.getNombre());
            cbCarrera = new JComboBox<>(carreras.toArray(new String[0]));
            cbCarrera.setEditable(true); cbCarrera.setPreferredSize(new Dimension(200,26)); form.add(cbCarrera, gbc);

            gbc.gridx=0; gbc.gridy++; form.add(new JLabel("Etnia:"), gbc);
            gbc.gridx=1; cbEtnia = new JComboBox<>(new String[]{""});
            for(Etnia e : EstudiantesController.getEtnias()) cbEtnia.addItem(e.getNombre());
            cbEtnia.setEditable(true); cbEtnia.setSelectedIndex(0); form.add(cbEtnia, gbc);

            gbc.gridx=0; gbc.gridy++; form.add(new JLabel("Celular:"), gbc);
            gbc.gridx=1; tfCelular = new JTextField(12); form.add(tfCelular, gbc);

            content.add(form, BorderLayout.CENTER);

            JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            actions.setOpaque(false);
            JButton bCancel = new JButton("Cancelar");
            JButton bOk = new JButton("Guardar");
            bCancel.addActionListener(e -> dispose());
            bOk.addActionListener(e -> {
                if(tfNombre.getText().trim().isEmpty()){ JOptionPane.showMessageDialog(this,"Ingrese nombre."); return; }
                String edad = tfEdad.getText().trim();
                if(!edad.isEmpty()){ try{ Integer.parseInt(edad); } catch(NumberFormatException ex){ JOptionPane.showMessageDialog(this,"Edad inválida"); return; } }
                saveEstudianteDep(prefill);
                saved = true;
                dispose();
            });
            styleAction(bOk);
            actions.add(bCancel); actions.add(bOk);
            content.add(actions, BorderLayout.SOUTH);

            if(prefill!=null){
                tfNombre.setText(String.valueOf(prefill[0]));
                tfApellido.setText(String.valueOf(prefill[1]));
                tfEdad.setText(String.valueOf(prefill[2]));
                tfCarnet.setText(String.valueOf(prefill[3]));
                tfCedula.setText(String.valueOf(prefill[4]));
                cbSexo.setSelectedItem(String.valueOf(prefill[5]));
            }

            setContentPane(content);
        }

        private void styleAction(JButton b){
            b.setBackground(new Color(60,110,190));
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
            b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            b.setBorder(BorderFactory.createEmptyBorder(6,12,6,12));
        }

        public boolean isSaved(){ return saved; }

        public Object[] getRow(){
            return new Object[]{
                    tfNombre.getText().trim(),
                    tfApellido.getText().trim(),
                    tfEdad.getText().trim(),
                    tfCarnet.getText().trim(),
                    tfCedula.getText().trim(),
                    cbSexo.getSelectedItem()
            };
        }
        private void saveEstudianteDep(Object[] prefill) {
            Estudiante estudiante = prefill != null
                    ? EstudiantesController.getEstudianteDeporteByCarnet((String) prefill[3])
                    : new Estudiante();

            estudiante.setNombre(tfNombre.getText().trim());
            estudiante.setApellido(tfApellido.getText().trim());

            try {
                estudiante.setEdad(Integer.parseInt(tfEdad.getText().trim()));
            } catch (NumberFormatException ex) {
                estudiante.setEdad(0);
            }

            estudiante.setCarnet(tfCarnet.getText().trim());
            estudiante.setCedula(tfCedula.getText().trim());

            String sexo = (String) cbSexo.getSelectedItem();
            if ("Femenino".equals(sexo)) estudiante.setSexo('F');
            else if ("Masculino".equals(sexo)) estudiante.setSexo('M');
            else estudiante.setSexo('O');

            // Carrera
            String carreraSel = cbCarrera.getSelectedItem() == null ? "" : String.valueOf(cbCarrera.getSelectedItem()).trim();
            List<Carrera> carreras = CarrerasController.getCarreras();
            for (Carrera c : carreras) {
                if (c.getNombre().equalsIgnoreCase(carreraSel)) {
                    estudiante.setCarrera(c);
                    break;
                }
            }

            // Etnia
            String etniaSel = cbEtnia.getSelectedItem() == null ? "" : String.valueOf(cbEtnia.getSelectedItem()).trim();
            List<Etnia> etnias = EstudiantesController.getEtnias();
            for (Etnia e : etnias) {
                if (e.getNombre().equalsIgnoreCase(etniaSel)) {
                    estudiante.setEtnia(e);
                    break;
                }
            }

            estudiante.setTelefono(tfCelular.getText().trim());

            // Tipo de estudiante (siempre el 3er tipo como antes)
            estudiante.setTipoEstudiante(EstudiantesController.getTipoEstudiantes().get(2));

            // Sede y estado
            estudiante.setSede(CarrerasController.getSedes().getFirst());
            estudiante.setEstado(EstudiantesController.getEstadoEstudiantes().getFirst());

            // Guardar
            EstudiantesController.saveEstudiante(estudiante);
        }}}
