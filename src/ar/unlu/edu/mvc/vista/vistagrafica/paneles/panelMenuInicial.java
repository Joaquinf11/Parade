package ar.unlu.edu.mvc.vista.vistagrafica.paneles;

import ar.unlu.edu.mvc.controlador.ControladorGrafico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class panelMenuInicial extends JPanel {
    protected JPanel panelPrincipal;
    private JLabel lTitulo;
    private JPanel panelCentro;
    private JButton salirButton;
    private JButton empezarButton;
    private JButton agregarJugButton;

    private VistaGrafica vistaGrafica;
    private ControladorGrafico controladorGrafico;

    public panelMenuInicial(ControladorGrafico controladorGrafico, VistaGrafica vistaGrafica){
        setLayout(new BorderLayout());
        add(panelPrincipal,BorderLayout.CENTER);
        this.controladorGrafico = controladorGrafico;
        this.vistaGrafica=vistaGrafica;
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        agregarJugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vistaGrafica.mostrarIngresarJugador();
                agregarJugButton.setEnabled(false);
            }
        });
        empezarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controladorGrafico.sePuedeComenzar()) {
                    controladorGrafico.empezarPartida();
                }
                else {
                    vistaGrafica.mostrarMensaje("Faltan jugadores"); // CONTROLADOR?
                }
                }
        });
    }

    public JPanel getPanel(){
        return this.panelPrincipal;
    }





}
