package ar.unlu.edu.mvc.vista.vistagrafica.paneles;

import ar.unlu.edu.mvc.controlador.Controlador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaCargarPartida {
    private JPanel panelPrincipal;
    private JButton aceptarButton;
    private JTextField ingresarNombrePartida;
    private JLabel labelNombrePartida;
    private JLabel labelNombreJugador;
    private JTextField ingresarNombreJugador;

    public VistaCargarPartida(Controlador controlador,VistaGrafica vistaGrafica) {
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vistaGrafica.setNombreJugador(ingresarNombreJugador.getText());
                controlador.cargarPartida(ingresarNombrePartida.getText(),ingresarNombreJugador.getText());
            }
        });
    }

    public JPanel getPanel() {
        return this.panelPrincipal;
    }


}
