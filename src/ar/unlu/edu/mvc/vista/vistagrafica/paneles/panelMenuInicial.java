package ar.unlu.edu.mvc.vista.vistagrafica.paneles;

import ar.unlu.edu.mvc.controlador.ControladorGrafico;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class panelMenuInicial  {
    protected JPanel panelPrincipal;
    private JLabel lTitulo;
    private JPanel panelCentro;
    private JButton salirButton;
    private JButton empezarButton;
    private JButton agregarJugButton;

    private VistaGrafica vistaGrafica;
    private ControladorGrafico controladorGrafico;

    public panelMenuInicial(ControladorGrafico controladorGrafico, VistaGrafica vistaGrafica){
        this.controladorGrafico = controladorGrafico;
        this.vistaGrafica=vistaGrafica;
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 vistaGrafica.removeJugador();
                 vistaGrafica.dispose();
            }
        });
        agregarJugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vistaGrafica.mostrarIngresarJugador();
            }
        });
        empezarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    controladorGrafico.empezarPartida();
                }
        });
    }

    public JPanel getPanel(){
        return this.panelPrincipal;
    }

    public void desactivarAgregarJugador() {
        this.agregarJugButton.setEnabled(false);
    }
}
