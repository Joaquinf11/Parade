package ar.unlu.edu.mvc.vista.vistagrafica.paneles;

import ar.unlu.edu.mvc.controlador.Controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaCargarPartida {
    private JPanel panelPrincipal;
    private JPanel panelBotones;
    private JButton aceptarButton;
    private JTextField ingresarNombre;
    private JLabel label;

    public VistaCargarPartida(Controlador controlador) {
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.cargarPartida(ingresarNombre.getText());
            }
        });
    }

    public JPanel getPanel() {
        return this.panelPrincipal;
    }
}
