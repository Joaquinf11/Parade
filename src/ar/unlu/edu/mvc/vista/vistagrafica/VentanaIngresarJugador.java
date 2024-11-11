package ar.unlu.edu.mvc.vista.vistagrafica;

import ar.unlu.edu.mvc.controlador.Controlador;

import javax.swing.*;
import java.awt.event.*;

public class VentanaIngresarJugador {
    protected JPanel panelIngresarJugador;
    private JLabel lTitulo;
    private JTextField nombre;
    private JPanel panelCentro;
    private JButton aceptarButton;

    private Controlador controlador;
    private VistaGrafica vistaGrafica;

    public VentanaIngresarJugador(Controlador controlador,VistaGrafica vistaGrafica){
        this.controlador=controlador;
        this.vistaGrafica=vistaGrafica;
        aceptarButton.addActionListener(new ActionListener() {



            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.agregarJugador(nombre.getText());
                vistaGrafica.mostrarMenuInicial();
            }
        });

        nombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    controlador.agregarJugador(nombre.getText());
                    vistaGrafica.mostrarMenuInicial();
                }
            }
        });
    }

}
