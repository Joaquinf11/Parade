package ar.unlu.edu.mvc.vista.vistagrafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal {
    private JPanel panelPrincipal;
    private JLabel lTitulo;
    private JPanel panelCentro;
    private JButton salirButton;
    private JButton empezarButton;
    private JButton agregarJugButton;

    private JFrame frame;

    public VentanaPrincipal(){
        frame= new JFrame("PARADE");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        agregarJugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarAgregarJugador();
            }
        });

        frame.setContentPane(panelPrincipal);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    public void mostrarAgregarJugador(){
        JPanel panelAgregar= new JPanel();
        panelAgregar.setLayout(new BorderLayout());
        panelAgregar.add(lTitulo,BorderLayout.NORTH);

        JPanel panelIngresar = new JPanel();
        panelIngresar.setLayout(new BoxLayout(panelIngresar,BoxLayout.Y_AXIS));
        JTextField nombre = new JTextField("Ingrese el nombre");
        JButton aceptar= new JButton("aceptar");

        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelAgregar.setVisible(false);
                //habria que guardar el nombre en algun lao
                mostrarMenuPrincipal();
            }
        });

        panelIngresar.add(nombre);
        panelIngresar.add(aceptar);
        panelAgregar.add(panelIngresar,BorderLayout.CENTER);

        panelPrincipal.setVisible(false);
        panelAgregar.setVisible(true);
        this.frame.setContentPane(panelAgregar);
    }

    public void mostrarMenuPrincipal(){
        frame.setContentPane(panelPrincipal);
        panelPrincipal.setVisible(true);
    }
}
