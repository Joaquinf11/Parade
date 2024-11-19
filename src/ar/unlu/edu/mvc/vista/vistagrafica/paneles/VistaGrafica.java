package ar.unlu.edu.mvc.vista.vistagrafica.paneles;

import ar.unlu.edu.mvc.controlador.ControladorGrafico;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VistaGrafica extends  JFrame  {

    ControladorGrafico controladorGrafico;

    private  JPanel panelMensaje;
    private panelMenuInicial panelMenuInicial;
    private panelIngresarJugador panelIngresarJugador;
    private panelJuego panelJuego;
    private JPanel panelPrincipalMenuInicial;
    private JPanel panelPrincipalIngresarJugador;
    private JPanel panelPrincipalJuego;

    private String jugador;
    private String ultimoPanel;
    private JMenuBar menuBar;
    private JPanel panelReglas;
    private JTextArea reglasText;

    public VistaGrafica(){

        menuBar= new JMenuBar();
        menuBar.setBackground(new Color(201,217,5));
        menuBar.setSize(100,30);

        panelReglas= new JPanel();
        panelReglas.setBackground(new Color(199,86,195));
        panelReglas.setLayout(new BorderLayout());

        reglasText= new JTextArea();
        reglasText.setEditable(false);

        reglasText.setFont(new Font("Arial",Font.PLAIN,16));
        reglasText.setForeground(new Color(201,217,5));
        reglasText.setBackground(new Color(199,86,195));

        JScrollPane scrollReglas= new JScrollPane(reglasText);
        scrollReglas.setBackground(new Color(199,86,195));
        scrollReglas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelReglas.add(scrollReglas,BorderLayout.CENTER);

        JButton volverButton=new JButton("Volver");
        volverButton.setBackground(new Color(201,217,5));
        volverButton.setForeground(new Color(199,86,195));
        volverButton.setFont(new Font("Ravie",Font.PLAIN,16));
        volverButton.setSize(200,100);
        volverButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mostrarUltimoPanel();
            }
        });
        JPanel panelVolver= new JPanel(new GridBagLayout());
        panelVolver.setOpaque(false);
        panelVolver.add(volverButton);
        panelReglas.add(panelVolver, BorderLayout.SOUTH);

        JMenu ayuda= new JMenu("Ayuda");
        JMenuItem reglasItem= new JMenuItem("Reglas");
        reglasItem.setBackground(new Color(201,217,5));
        reglasItem.addActionListener(e -> {mostrarReglas();});
        JMenuItem comoJugarItem= new JMenuItem("Como jugar");
        comoJugarItem.setBackground(new Color(201,217,5));
        ayuda.add(reglasItem);
        ayuda.add(comoJugarItem);

        JMenu salir= new JMenu("Salir");
        JMenuItem salirItem= new JMenuItem("Salir");
        salirItem.setBackground(new Color(201,217,5));
        salirItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorGrafico.removeJugador(jugador);
            }
        });
        salir.add(salirItem);

        menuBar.add(ayuda);
        menuBar.add(salir);

        setJMenuBar(menuBar);
        setTitle("PARADE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setVisible(true);

    }




    public void iniciarVentana(){
        this.panelMenuInicial = new panelMenuInicial(this.controladorGrafico,this);
        this.panelIngresarJugador = new panelIngresarJugador(this.controladorGrafico,this);
        this.panelJuego =new panelJuego(this.controladorGrafico,this);
        this.panelPrincipalMenuInicial= panelMenuInicial.getPanel();
        this.panelPrincipalJuego=panelJuego.getPanel();
        this.panelPrincipalIngresarJugador= panelIngresarJugador.getPanel();
        this.panelMensaje= new JPanel();
        this.panelMensaje.setLayout(new GridBagLayout());
        this.panelMensaje.setVisible(true);


     }

    public void mostrarMenuInicial(){
        setContentPane(this.panelPrincipalMenuInicial);
        panelPrincipalMenuInicial.updateUI();
        this.ultimoPanel="Menu Inicial";
    }



    public void mostrarIngresarJugador(){
        setContentPane(this.panelPrincipalIngresarJugador);
        panelPrincipalIngresarJugador.updateUI();
        this.ultimoPanel="Ingresar Jugador";
    }

    public void mostrarVentanaJuego(){
       setContentPane(this.panelPrincipalIngresarJugador);
       panelPrincipalIngresarJugador.updateUI();
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
            case "Menu Inicial"-> { setContentPane(this.panelPrincipalMenuInicial); panelPrincipalMenuInicial.updateUI();}
            case "Ingresar Jugador"-> { setContentPane(this.panelPrincipalIngresarJugador);panelPrincipalIngresarJugador.updateUI();}
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

    public void setCantidadCartasMazo(int cantidad) {
        this.panelJuego.setCantCartasMazo(cantidad);
    }

    public void removeJugador() {
        this.controladorGrafico.removeJugador(this.jugador);
    }

    public void setFinalizarTurno(boolean b) {
        this.panelJuego.setFinalizarButton(b);
    }

    public void desactivarAgregarJugador() {
        this.panelMenuInicial.desactivarAgregarJugador();
    }

    private void mostrarReglas() {
        reglasText.setText("OBJETIVO DEL JUEGO\n" +
                "Los jugadores deberán jugar sus cartas de la mano mientras intentan no quedarse cartas del carnaval, pues estas representan puntos negativos.\n" +
                "Se reparten 5 a cada jugador. Luego se ponen 6 cartas más en el centro de la mesa que representan el carnaval." +
                "El principio del carnaval comienza a la izquierda mientras que el final es a la derecha.\n" +
                "\n" +
                "SECUENCIA DEL JUEGO\n" +
                "Durante su turno cada jugador llevará a cabo las siguientes acciones en el orden descrito:\n" +
                "1. Escoger una carta de su mano y colocarla al final del carnaval;\n" +
                "2. Si se da el caso, recoger cartas del carnaval y colocarlas en su area de juego;\n" +
                "3. Robar una carta del mazo.\n" +
                "Después, el turno pasa al siguiente jugador que realizará las mismas acciones.\n" +
                "\n" +
                "ACCIONES DEL JUEGO\n" +
                "1.  La carta tirada no se cuenta a la hora de contar el número de cartas en la siguiente acción.\n" +
                "2. Dependiendo de la carta jugada, algunas cartas serán retiradas del carnaval.\n" +
                "\t -Si el número de cartas del carnaval es menor o igual al valor numérico de la carta jugada, no se retirará ninguna carta del carnaval.\n" +
                "\t -Si el número de cartas del carnaval es mayor que el valor de la carta jugada, entonces algunas cartas pueden abandonarlo.\n" +
                "Para determinar que cartas tienen que ser retiradas, deberán contarse las cartas desde el final del carnaval y hacia el principio, sin tener en cuenta la carta recién jugada. \n" +
                "Toda carta a partir de esa posición (hasta donde hemos contado) será susceptible de ser retirada.\n" +
                "Para saber que cartas de las susceptibles de ser retiradas, lo serán efectivamente, se siguen las siguientes reglas:\n" +
                "\t • Todas las cartas con el mismo color que la carta jugada;\n" +
                "\t • Todas las cartas de valor igual o inferior al de la carta jugada (véase ejemplo más adelante).\n" +
                "Las cartas retiradas se disponen en el area de juego del jugador.\n" +
                "3. El jugador roba una carta del mazo, volviendo a tener 5 cartas en mano.\n" +
                "\n" +
                "ULTIMA RONDA\n" +
                "La última ronda empieza cuando un jugador tiene en su area de juego cartas de los 6 colores presentes en el juego o bien cuando se roba la última carta del mazo.\n" +
                "Sin embargo, en esta última ronda, los jugadores no robarán carta del mazo (quedándose así con 4 cartas en la mano).\n" +
                "Tras esta última ronda, comienza la ronda de descarte.\n" +
                "\n" +
                "RONDA DESCARTE\n" +
                "Cada jugador escoge 2 cartas de su mano y las descarta. Las 2 cartas restantes, se colocan en su area de juego \n" +
                "\n" +
                "PUNTUACION\n" +
                "Las cartas que aún formen parte del carnaval serán descartadas.\n" +
                "1. Determinar quien tiene la mayoría de cada color. El jugador o jugadores con la mayoría de cartas de un color, girarán esas cartas y cada una las contará solo como 1 punto.\n" +
                "2. Después, cada jugador sumará los valores del resto de cartas que tengan boca arriba. Sumar este total con el de las cartas sumadas en el punto anterior.\n" +
                "3. El ganador sera quien tenga menos puntos negativos acumulados.\n" +
                "4. En caso de empate ganara quien tenga menos cartas en su area de juego (las que estan dadas vuelta tambien se cuentan).");
        setContentPane(panelReglas);
        panelReglas.updateUI();
    }
}
