package ar.unlu.edu.mvc.vista.vistagrafica;

import ar.unlu.edu.mvc.controlador.Controlador;
import ar.unlu.edu.mvc.vista.IVista;

import javax.smartcardio.Card;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaGrafica extends  JFrame implements IVista {

    Controlador controlador;
    JFrame frame;
    private static CardLayout cardLayout;
    private static JPanel paneles;

    public VistaGrafica(){


        setTitle("PARADE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(Frame.MAXIMIZED_BOTH);

        cardLayout= new CardLayout();
        paneles= new JPanel(cardLayout);


    }

    @Override
    public void iniciarVentana(){
        VentanaMenuInicial ventanaMenuInicial= new VentanaMenuInicial(this.controlador,this);
        VentanaIngresarJugador ventanaIngresarJugador= new VentanaIngresarJugador(this.controlador,this);
        paneles.add(ventanaMenuInicial.panelPrincipal,"Menu Inicial");
        paneles.add(ventanaIngresarJugador.panelIngresarJugador,"Ingresar Jugador");

        add(paneles);
        setVisible(true);
    }

    public  void mostrarMenuInicial(){
        cardLayout.show(paneles,"Menu Inicial");
    }

    protected void mostrarIngresarJugador(){
        cardLayout.show(paneles,"Ingresar Jugador");
    }



    @Override
    public void setControlador(Controlador controlador){
        this.controlador=controlador;
    }

    @Override
    public void mostrarMensajeJugadorAgregado(){
        JFrame frame= new JFrame();

        JPanel panel= new JPanel();
        panel.setLayout(new GridBagLayout());

        JLabel mensaje= new JLabel("Jugador agregado con exito");
        mensaje.setFont(new Font("Ravie",Font.ITALIC,20));
        mensaje.setForeground(new Color(201,217,5));


        panel.add(mensaje);
        panel.setVisible(true);
        panel.setBackground(new Color(199,86,195));

        frame.setContentPane(panel);
        frame.setSize(400,200);
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