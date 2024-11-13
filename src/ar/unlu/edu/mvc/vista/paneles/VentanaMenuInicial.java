package ar.unlu.edu.mvc.vista.paneles;

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
                if (controlador.sePuedeComenzar()) {
                    controlador.empezarPartida();
                }
                else {
                    mostrarMensajeCargarMasJugadores();
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
