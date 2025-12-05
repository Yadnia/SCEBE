package org.Yaed.windows;

import org.Yaed.entity.Usuario;
import org.Yaed.windows.becas.BecasInicio;
import org.Yaed.windows.cultura.CulturaInicio;
import org.Yaed.windows.deportes.DeportesInicio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

import static org.Yaed.controller.UserController.getUsers;

public class Login extends JFrame {
    public Login() {
        setTitle("Inicio de sesión");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        // Poner icono
        ImageIcon icono = new ImageIcon(Objects.requireNonNull(getClass().getResource("/logo.png")));
        setIconImage(icono.getImage());

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(10, 20, 60));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));

        // Título
        JLabel title = new JLabel("Identifíquese");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Serif", Font.BOLD, 28));
        title.setForeground(new Color(90, 150, 255));
        mainPanel.add(title);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Panel para usuario y contraseña
        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(10, 20, 60));
        formPanel.setLayout(new GridLayout(4, 1, 0, 15));

        // Campo Usuario
        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setForeground(Color.WHITE);
        JTextField userField = new JTextField();
        styleTextField(userField);

        // Campo Contraseña
        JLabel passLabel = new JLabel("Contraseña:");
        passLabel.setForeground(Color.WHITE);
        JPasswordField passField = new JPasswordField();
        styleTextField(passField);

        // Agregar listener para clic en contraseña
        passField.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Iniciar como admin");
            }
        });



        formPanel.add(userLabel);
        formPanel.add(userField);
        formPanel.add(passLabel);
        formPanel.add(passField);

        mainPanel.add(formPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // ¿Olvidaste tu contraseña?
        JLabel forgotLabel = new JLabel("¿Olvidaste tu contraseña?");
        forgotLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        forgotLabel.setForeground(new Color(170, 190, 255));
        mainPanel.add(forgotLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Botón de iniciar
        JButton loginButton = new JButton("Iniciar sesión");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setBackground(new Color(90, 150, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        userField.addActionListener(e -> passField.requestFocus());

        passField.addActionListener(e ->{
            loginButton.doClick();
        });
        mainPanel.add(loginButton);

        // Acción al presionar "Iniciar"
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = userField.getText();
                char[] pass = passField.getPassword();
                if (existentUser(user, new String(pass))) {
                    Usuario usuario = getUsuario(user);
                    tipoUsuario(usuario);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario no encontrado");
                }
            }
        });

        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    // Método para estilizar campos de texto
    private void styleTextField(JTextField field) {
        field.setBackground(new Color(20, 30, 70));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 130, 255), 1, true),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        field.setFont(new Font("SansSerif", Font.PLAIN, 14));
    }

    private static boolean existentUser(String usuario, String password){
        List<Usuario> usuarios = getUsers();
        for (Usuario user: usuarios){
            if (user.getUsuario().equalsIgnoreCase(usuario) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    private static void tipoUsuario(Usuario usuario){
        int iduser = usuario.getTipoUsuario().getId();
        if (iduser == 1){
            new AdminInicio().setVisible(true);
        } else if (iduser == 2){
            new BecasInicio().setVisible(true);
        } else if (iduser == 3){
            new CulturaInicio().setVisible(true);
        } else if (iduser == 4){
            new DeportesInicio().setVisible(true);
        }
    }

    private static Usuario getUsuario(String usuario) {
        List<Usuario> usuarios = getUsers();
        for (Usuario user : usuarios) {
            if (user.getUsuario().equalsIgnoreCase(usuario)) {
                return user;
            }
        }
        return null;
    }
}
