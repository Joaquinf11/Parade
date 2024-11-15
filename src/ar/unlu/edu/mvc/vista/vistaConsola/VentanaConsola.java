package ar.unlu.edu.mvc.vista.vistaConsola;

import ar.unlu.edu.mvc.controlador.ControladorConsola;

import javax.swing.*;
import java.awt.*;

public class VentanaConsola {
    private ControladorConsola controlador;

    private JPanel panelPrincipal;
    private JPanel panelEntrada;
    private JPanel panelSalida; //TEDNRIA QUE SER UN BOX LAYOUR Y_AXIS
    private JTextField entradaField;
    private JTextArea salidaArea;
    private final JFrame frame;

    public VentanaConsola (){
        frame=new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("PARADE");
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);

        frame.setContentPane(panelPrincipal);
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal,BoxLayout.Y_AXIS));
        //panelSalida.setLayout(new BoxLayout(panelSalida,BoxLayout.Y_AXIS));
        
    }
}
