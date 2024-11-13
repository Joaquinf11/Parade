package ar.unlu.edu.mvc.vista.paneles;

import ar.unlu.edu.mvc.controlador.Controlador;

import javax.swing.*;
import java.awt.*;
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
                if(nombre.getText().isEmpty()){
                    mostrarMensajeNombreInvalido();
                }
                else{
                    controlador.agregarJugador(nombre.getText());
                }
                vistaGrafica.mostrarMenuInicial();
            }
        });

        nombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if(nombre.getText().isEmpty()){
                        mostrarMensajeNombreInvalido();
                    }
                    else{
                        controlador.agregarJugador(nombre.getText());
                    }
                    vistaGrafica.mostrarMenuInicial();
                }
            }
        });
    }

   private void mostrarMensajeNombreInvalido(){
        JFrame frame= new JFrame();

        JPanel panel= new JPanel();
        panel.setLayout(new GridBagLayout());

        JLabel mensaje= new JLabel("El nombre ingresado es invalido");
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
