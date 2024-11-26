package ar.unlu.edu.mvc.vista.vistagrafica.paneles;

import ar.unlu.edu.mvc.controlador.Controlador;

import javax.swing.*;
import java.awt.event.*;

public class VistaIngresarJugador {
    protected JPanel panelIngresarJugador;
    private JLabel lTitulo;
    private JTextField nombre;
    private JPanel panelCentro;
    private JButton aceptarButton;

    private Controlador controlador;
    private VistaGrafica vistaGrafica;

    public VistaIngresarJugador(Controlador controlador, VistaGrafica vistaGrafica){
        this.controlador = controlador;
        this.vistaGrafica=vistaGrafica;

        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nombre.getText().isEmpty()){
                    vistaGrafica.mostrarMensaje("El nombre ingresado es invalido"); //REFACTOR
                }
                else{
                    controlador.agregarJugador(nombre.getText());
                    vistaGrafica.setNombreJugador(nombre.getText());


                }
            }
        });

        nombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                        controlador.agregarJugador(nombre.getText());
                        vistaGrafica.setNombreJugador(nombre.getText());
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
