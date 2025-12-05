package org.Yaed.util;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class WindowManager {
    private static final Map<String, JFrame> ventanasAbiertas = new HashMap<>();

    public static void abrir(String nombre, JFrame ventana) {
        if (!ventanasAbiertas.containsKey(nombre) || !ventanasAbiertas.get(nombre).isDisplayable()) {
            // Ventana no existe o fue cerrada → la abrimos
            ventanasAbiertas.put(nombre, ventana);
            ventana.setVisible(true);
            ventana.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    // Eliminamos referencia cuando se cierra
                    ventanasAbiertas.remove(nombre);
                }
            });
        } else {
            // Ventana ya está abierta → traemos al frente
            JFrame v = ventanasAbiertas.get(nombre);
            v.toFront();
            v.requestFocus();
        }
    }
}
