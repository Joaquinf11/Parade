package ar.unlu.edu.mvc.vista.vistagrafica.paneles;

import ar.unlu.edu.mvc.controlador.Controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class panelMenuInicial  {
    protected JPanel panelPrincipal;
    private JLabel lTitulo;
    private JPanel panelCentro;
    private JButton salirButton;
    private JButton empezarButton;
    private JButton agregarJugButton;
    private JButton cargarPartidaButton;

    private VistaGrafica vistaGrafica;
    private Controlador controlador;

    public panelMenuInicial(Controlador controlador, VistaGrafica vistaGrafica){
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
                panelCargarPartida();
            }
        });
    }

    public JPanel getPanel(){
        return this.panelPrincipal;
    }

    public void panelCargarPartida(){
        JPanel panelCentro= new JPanel();
        panelCentro.setLayout(new BorderLayout());

        JPanel panelCargarPartida= new JPanel();
        panelCargarPartida.setBackground(new Color(199,86,195));
        panelCargarPartida.setLayout(new GridLayout(3,1));

        JLabel label= new JLabel("Ingrese el nombre de la partida ");
        label.setOpaque(false);
        label.setForeground(new Color(201,217,5));
        label.setFont(new Font("Ravie",Font.PLAIN,18));

        JTextField ingresarNombrePartida= new JTextField();
        ingresarNombrePartida.setBackground(new Color(201,217,5));
        ingresarNombrePartida.setForeground(new Color(199,86,195));
        ingresarNombrePartida.setFont(new Font("Ravie",Font.PLAIN,18));
        ingresarNombrePartida.setSize(50,100);

        JButton aceptar= new JButton("Aceptar");
        aceptar.setBackground(new Color(201,217,5));
        aceptar.setForeground(new Color(199,86,195));
        aceptar.setFont(new Font("Ravie",Font.PLAIN,18));
        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.cargarPartida(ingresarNombrePartida.getText());
            }
        });

        panelCargarPartida.add(label,2,0);
        panelCargarPartida.add(ingresarNombrePartida,1,0);
        panelCargarPartida.add(aceptar,0,0);
        panelCentro.add(panelCargarPartida);
        vistaGrafica.setContentPane(panelCentro);
        panelCentro.updateUI();

    }

    public void setAgregarJugador(boolean b) {
        agregarJugButton.setEnabled(b);
    }
}
