package ar.unlu.edu.mvc.vista.vistagrafica;

import ar.unlu.edu.mvc.controlador.Controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaMenuInicial {
    protected JPanel panelPrincipal;
    private JLabel lTitulo;
    private JPanel panelCentro;
    private JButton salirButton;
    private JButton empezarButton;
    private JButton agregarJugButton;

    private VistaGrafica vistaGrafica;
    private Controlador controlador;

    public VentanaMenuInicial(Controlador controlador,VistaGrafica vistaGrafica){
        this.controlador=controlador;
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
            }
        });
        empezarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.empezarPartida();
            }
        });
    }





}
