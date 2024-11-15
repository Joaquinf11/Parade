package ar.unlu.edu.mvc.vista.paneles;

import ar.unlu.edu.mvc.controlador.Controlador;
import ar.unlu.edu.mvc.interfaces.IVista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaGrafica extends  JFrame implements IVista {

    Controlador controlador;
    private  CardLayout cardLayout;
    private  JPanel paneles;
    private PanelMensaje panelMensaje;
    private VentanaJuego ventanaJuego;
    private String jugador;

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
        VentanaJuego ventanaJuego= new VentanaJuego(this.controlador,this);
        this.ventanaJuego= ventanaJuego;

        paneles.add(ventanaMenuInicial.panelPrincipal,"Menu Inicial");
        paneles.add(ventanaIngresarJugador.panelIngresarJugador,"Ingresar Jugador");
        paneles.add(ventanaJuego.panelVentanaJuego,"Ventana Juego");

        panelMensaje= new PanelMensaje();
        paneles.add(panelMensaje,"Mensaje");
        add(paneles);
        setVisible(true);
    }

    public void mostrarMenuInicial(){
        cardLayout.show(paneles,"Menu Inicial");
    }

    public void mostrarIngresarJugador(){
        cardLayout.show(paneles,"Ingresar Jugador");
    }

    public void mostrarVentanaJuego(){
        cardLayout.show(paneles,"Ventana Juego");
    }


    public void setNombreJugador(String nombre){
        this.jugador=nombre;
    }

    public String getNombreJugador(){
        return this.jugador;
    }

    @Override
    public void setControlador(Controlador controlador){
        this.controlador=controlador;
    }


    @Override
    public void iniciarVentanaJuego()  {
        ventanaJuego.iniciarVentanaJuego();
        mostrarVentanaJuego();

    }

    @Override
    public void mostrarMensaje(String texto){
        JFrame frame= new JFrame();
        JPanel panel= new JPanel();
        panel.setLayout(new GridBagLayout());

        JLabel mensaje= new JLabel(texto);
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


        //CHEQUEAR DESPUES POR QUE NO FUNCIONA ESTO
//        panelMensaje.setMensaje(texto);
//        cardLayout.show(paneles,"Mensaje");
//
//        // Crear un Timer que cerrará la ventana después de segundos
//        Timer timer = new Timer(5000, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                cardLayout.last(paneles); // Cierra la ventana
//            }
//        });
//        timer.setRepeats(false); // Asegurarse de que solo se ejecute una vez
//        timer.start();
    }



    @Override
    public void activarCartas(){
        this.ventanaJuego.activarBotones();
    }

    @Override
    public void activarCartasMano() {
        this.ventanaJuego.activarCartasMano();
    }

    @Override
    public void activarCartasCarnaval() {
        this.ventanaJuego.activarCartasCarnaval();
    }

    @Override
    public void desactivarCartasMano() {
        this.ventanaJuego.desactivarCartasEnMano();
    }

    @Override
    public void actualizarCarnaval() {

        this.ventanaJuego.actualizarCartasCarnaval();

    }

    @Override
    public void desactivarCartasCarnaval() {
        this.ventanaJuego.desactivarCartasCarnaval();
    }

    @Override
    public void actualizarCartasEnMano() {
        this.ventanaJuego.actualizarCartasEnMano();
    }

    @Override
    public void desactivarTodosLosBotones() {
        this.ventanaJuego.desactivarTodosLosBotones();
    }

    @Override
    public void desactivarUltimaCartaCarnaval() {
        this.ventanaJuego.desactivarUltimaCartaCarnaval();
    }

    @Override
    public void desactivarCartaManoOponente(String oponente) {
        this.ventanaJuego.desactivarCartaManoOponente(oponente);
    }

    @Override
    public void activarCartaOponente(String oponente) {
        this.ventanaJuego.activarCartaOponente(oponente);
    }

    @Override
    public void actualizarAreaDeJuego() {
        this.ventanaJuego.actualizarAreaDeJuego();
    }


}
