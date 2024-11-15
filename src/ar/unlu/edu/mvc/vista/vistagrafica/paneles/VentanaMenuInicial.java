package ar.unlu.edu.mvc.vista.vistagrafica.paneles;

import ar.unlu.edu.mvc.controlador.ControladorGrafico;

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
    private ControladorGrafico controladorGrafico;

    public VentanaMenuInicial(ControladorGrafico controladorGrafico, VistaGrafica vistaGrafica){
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

    private void mostrarMensajeCargarMasJugadores(){
        JFrame frame= new JFrame();

        JPanel panel= new JPanel();
        panel.setLayout(new GridBagLayout());

        JLabel mensaje= new JLabel("Se deben cargar mas Jugadores");
        mensaje.setFont(new Font("Ravie",Font.ITALIC,20));
        mensaje.setForeground(new Color(201,217,5));


        panel.add(mensaje);
        panel.setVisible(true);
        panel.setBackground(new Color(199,86,195));

        frame.setContentPane(panel);

        frame.setSize(600,200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Crear un Timer que cerrará la ventana después de 5 segundos (5000 ms)
        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Cierra la ventana
            }
        });
        timer.setRepeats(false); // Asegurarse de que solo se ejecute una vez
        timer.start();
    }




}
