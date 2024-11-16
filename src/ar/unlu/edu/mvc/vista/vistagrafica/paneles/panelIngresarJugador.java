package ar.unlu.edu.mvc.vista.vistagrafica.paneles;

import ar.unlu.edu.mvc.controlador.ControladorGrafico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class panelIngresarJugador {
    protected JPanel panelIngresarJugador;
    private JLabel lTitulo;
    private JTextField nombre;
    private JPanel panelCentro;
    private JButton aceptarButton;

    private ControladorGrafico controladorGrafico;
    private VistaGrafica vistaGrafica;

    public panelIngresarJugador(ControladorGrafico controladorGrafico, VistaGrafica vistaGrafica){
        this.controladorGrafico = controladorGrafico;
        this.vistaGrafica=vistaGrafica;
        aceptarButton.addActionListener(new ActionListener() {



            @Override
            public void actionPerformed(ActionEvent e) {
                if(nombre.getText().isEmpty()){
                    vistaGrafica.mostrarMensaje("El nombre ingresado es invalido");
                }
                else{
                    controladorGrafico.agregarJugador(nombre.getText());
                    vistaGrafica.setNombreJugador(nombre.getText());
                    vistaGrafica.mostrarMenuInicial();

                }
            }
        });

        nombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if(nombre.getText().isEmpty()){
                        vistaGrafica.mostrarMensaje("El nombre ingresado es invalido");
                    }
                    else{
                        controladorGrafico.agregarJugador(nombre.getText());
                        vistaGrafica.setNombreJugador(nombre.getText());
                        vistaGrafica.mostrarMenuInicial();
                    }
                }
            }
        });

        nombre.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                nombre.setText("");
            }
        });
    }

    public JPanel getPanel(){
        return this.panelIngresarJugador;
    }

}
