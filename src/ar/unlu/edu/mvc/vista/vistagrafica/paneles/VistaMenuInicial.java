package ar.unlu.edu.mvc.vista.vistagrafica.paneles;

import ar.unlu.edu.mvc.controlador.Controlador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaMenuInicial {
    protected JPanel panelPrincipal;
    private JLabel lTitulo;
    private JPanel panelCentro;
    private JButton salirButton;
    private JButton empezarButton;
    private JButton agregarJugButton;
    private JButton cargarPartidaButton;

    private VistaGrafica vistaGrafica;
    private Controlador controlador;

    public VistaMenuInicial(Controlador controlador, VistaGrafica vistaGrafica){
        this.controlador = controlador;
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
                    controlador.empezarPartida();
                }
        });

        cargarPartidaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVistaCargarPartida();
            }
        });
    }

    private void mostrarVistaCargarPartida() {
        VistaCargarPartida vistaCargarPartida= new VistaCargarPartida(controlador,this.vistaGrafica);
        vistaGrafica.setContentPane(vistaCargarPartida.getPanel());
        vistaCargarPartida.getPanel().updateUI();
    }

    public JPanel getPanel(){
        return this.panelPrincipal;
    }



    public void setAgregarJugador(boolean b) {
        agregarJugButton.setEnabled(b);
    }
}
