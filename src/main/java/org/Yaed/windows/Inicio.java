package org.Yaed.windows;

import org.Yaed.controller.EstudiantesController;
import org.Yaed.entity.Estudiante;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Scanner;

public class Inicio extends JFrame {

    public Inicio() {
        setVisible(true);
        setTitle("SCEBE - Sistema de Control de Estudiantes Becados");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        // Poner icono
        ImageIcon icono = new ImageIcon(Objects.requireNonNull(getClass().getResource("/logo.png")));
        setIconImage(icono.getImage());

        // Colores
        Color fondoPrincipal = new Color(15, 21, 59);
        Color fondoSecundario = new Color(20, 30, 80);
        Color textoColor = new Color(190, 205, 255);

        // Panel izquierdo - Bienvenida
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setBackground(fondoPrincipal);
        panelIzquierdo.setPreferredSize(new Dimension(400, getHeight()));
        panelIzquierdo.setLayout(new GridBagLayout());

        JLabel titulo = new JLabel("Bienvenido a SCEBE");
        titulo.setForeground(textoColor);
        titulo.setFont(new Font("Serif", Font.BOLD, 28));

        JLabel subtitulo = new JLabel("Sistema de Control de Estudiantes Becados");
        subtitulo.setForeground(textoColor);
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JPanel textoPanel = new JPanel();
        textoPanel.setBackground(fondoPrincipal);
        textoPanel.setLayout(new BoxLayout(textoPanel, BoxLayout.Y_AXIS));
        textoPanel.add(titulo);
        textoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        textoPanel.add(subtitulo);

        panelIzquierdo.add(textoPanel);

        // Panel derecho - Botones
        JPanel panelDerecho = new JPanel();
        panelDerecho.setBackground(fondoSecundario);
        panelDerecho.setLayout(new GridLayout(3, 2, 20, 20));
        panelDerecho.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        String[] botones = {
                "Área Becas",
                "Área Cultura",
                "Área Deportes",
                "Área Psicología",
                "Iniciar como admin"
        };

        for (String texto : botones) {
            JButton boton = new JButton("<html><center>" + texto + "</center></html>");
            boton.setFont(new Font("Serif", Font.BOLD, 18));
            boton.setBackground(fondoSecundario);
            boton.setForeground(textoColor);
            boton.setFocusPainted(false);
            boton.setBorder(BorderFactory.createLineBorder(new Color(60, 70, 130), 1, true));
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new Login().setVisible(true);
                    dispose();
                }
            });
            panelDerecho.add(boton);
        }


        panelDerecho.add(new JLabel(""));


        add(panelIzquierdo, BorderLayout.WEST);
        add(panelDerecho, BorderLayout.CENTER);
    }

}
