package ar.unlu.edu.mvc.vista.vistagrafica.paneles;

import ar.unlu.edu.mvc.controlador.ControladorGrafico;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VistaGrafica extends  JFrame  {

    ControladorGrafico controladorGrafico;

    private  JPanel panelMensaje;
    private JPanel panelMenuInicial;
    private JPanel panelPrincipalJuego;
    private JPanel panelIngresarJugador;
    private String jugador;
    private String ultimoPanel;
    private panelJuego panelJuego;

    public VistaGrafica(){

        setTitle("PARADE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setExtendedState(Frame.MAXIMIZED_BOTH);
        JPanel panel= new JPanel();
        panel.setLayout(new BorderLayout());
        TextArea area = new TextArea("ANDA LA PUTA QUE TE PARIO");
        panel.add(area,BorderLayout.CENTER);
        setVisible(true);


    }


    public void iniciarVentana(){
        this.panelMenuInicial = (new panelMenuInicial(this.controladorGrafico,this)).getPanel();
        this.panelIngresarJugador = (new panelIngresarJugador(this.controladorGrafico,this)).getPanel();
        this.panelJuego =new panelJuego(this.controladorGrafico,this);
        this.panelPrincipalJuego=panelJuego.getPanel();
        this.panelMensaje= new JPanel();
        this.panelMensaje.setLayout(new GridBagLayout());
        this.panelMensaje.setVisible(true);


     }

    public void mostrarMenuInicial(){
        setContentPane(this.panelMenuInicial);
        panelMenuInicial.updateUI();
        this.ultimoPanel="Menu Inicial";
    }



    public void mostrarIngresarJugador(){
        setContentPane(this.panelIngresarJugador);
        panelIngresarJugador.updateUI();
        this.ultimoPanel="Ingresar Jugador";
    }

    public void mostrarVentanaJuego(){
       setContentPane(this.panelIngresarJugador);
       panelIngresarJugador.updateUI();
        this.ultimoPanel="Panel Juego";
    }

    public void mostrarPanelMensaje(){
        setContentPane(this.panelMensaje);

        panelMensaje.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mostrarUltimoPanel();
            }
        });
        panelMensaje.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    mostrarUltimoPanel();
                }
            }
        });

        panelMensaje.updateUI();
    }

    public void mostrarUltimoPanel(){
        switch (ultimoPanel){
            case "Menu Inicial"-> { setContentPane(this.panelMenuInicial); panelMenuInicial.updateUI();}
            case "Ingresar Jugador"-> { setContentPane(this.panelIngresarJugador);panelIngresarJugador.updateUI();}
            case "Panel Juego"-> { setContentPane(this.panelPrincipalJuego); panelPrincipalJuego.updateUI();}
        }
    }

    public void setNombreJugador(String nombre){
        this.jugador=nombre;
    }

    public String getNombreJugador(){
        return this.jugador;
    }


    public void setControlador(ControladorGrafico controladorGrafico){
        this.controladorGrafico = controladorGrafico;
    }



    public void iniciarVentanaJuego()  {
        panelJuego.iniciarVentanaJuego();
        mostrarVentanaJuego();

    }


    public void mostrarMensaje(String mensaje) {
        setMensaje(mensaje);
        mostrarPanelMensaje();
    }

    public void setMensaje(String mensaje){
        panelMensaje.removeAll();
        JLabel label= new JLabel(mensaje);
        label.setFont(new Font("Ravie",Font.ITALIC,20));
        label.setForeground(new Color(201,217,5));
        panelMensaje.setBackground(new Color(199,86,195));
        panelMensaje.add(label);
    }




    public void activarCartas(){
        this.panelJuego.activarBotones();
    }


    public void activarCartasMano() {
        this.panelJuego.activarCartasMano();
    }


    public void activarCartasCarnaval() {
        this.panelJuego.activarCartasCarnaval();
    }


    public void desactivarCartasMano() {
        this.panelJuego.desactivarCartasEnMano();
    }


    public void actualizarCarnaval() {

        this.panelJuego.actualizarCartasCarnaval();

    }


    public void desactivarCartasCarnaval() {
        this.panelJuego.desactivarCartasCarnaval();
    }


    public void actualizarCartasEnMano() {
        this.panelJuego.actualizarCartasEnMano();
    }


    public void desactivarTodosLosBotones() {
        this.panelJuego.desactivarTodosLosBotones();
    }


    public void desactivarUltimaCartaCarnaval() {
        this.panelJuego.desactivarUltimaCartaCarnaval();
    }


    public void desactivarCartaManoOponente(String oponente) {
        this.panelJuego.desactivarCartaManoOponente(oponente);
    }


    public void activarCartaOponente(String oponente) {
        this.panelJuego.activarCartaOponente(oponente);
    }


    public void actualizarAreaDeJuego() {
        this.panelJuego.actualizarAreaDeJuego();
    }


    public void actualizarAreaDeJuegoOponente(String oponente) {
        this.panelJuego.actualizarAreaOponente(oponente);
    }


    public void desactivaBotonAnalizarCartas() {
        this.panelJuego.desactivaBotonAnalizarCartas();
    }
}
