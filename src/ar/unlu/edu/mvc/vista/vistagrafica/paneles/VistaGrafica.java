package ar.unlu.edu.mvc.vista.vistagrafica.paneles;

import ar.unlu.edu.mvc.controlador.Controlador;
import ar.unlu.edu.mvc.interfaces.IVista;
import ar.unlu.edu.mvc.interfaces.IJugador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class VistaGrafica extends  JFrame implements IVista {

    Controlador controlador;

    private  JPanel panelMensaje;
    private VistaMenuInicial vistaMenuInicial;
    private VistaIngresarJugador vistaIngresarJugador;
    private VistaJuego vistaJuego;
    private JPanel panelPrincipalMenuInicial;
    private JPanel panelPrincipalIngresarJugador;
    private JPanel panelPrincipalJuego;

    private String jugador;
    private String ultimoPanel;
    private final JMenuBar menuBar;
    private final JPanel panelReglas;
    private final JTextArea reglasText;
    private final JPanel panelPuntuacion;
    private JTextArea tablaJugadores;

    private final JMenu tabla;

    public VistaGrafica(){
        panelPuntuacion= new JPanel();
        panelPuntuacion.setBackground(new Color(199,86,195));
        panelPuntuacion.setLayout(new GridBagLayout());

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



        menuBar= new JMenuBar();
        menuBar.setBackground(new Color(201,217,5));
        menuBar.setSize(100,30);

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
                controlador.removeJugador(jugador);
                dispose();
            }
        });
        salir.add(salirItem);
        JMenuItem guardarItem= new JMenuItem("Guardar partida");
        guardarItem.setBackground(new Color(201,217,5));
        guardarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombrePartida= (String) JOptionPane.showInputDialog(getContentPane(),"Ingrese nombre de la partida",
                                                                            "Guardar Partida",JOptionPane.INFORMATION_MESSAGE);
                controlador.guardarPartida(nombrePartida);
            }
        });
        salir.add(guardarItem);

        JMenuItem nuevaPartidaItem= new JMenuItem("Nueva Partida");
        nuevaPartidaItem.setBackground(new Color(201,217,5));
        nuevaPartidaItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.nuevaPartida();
            }
        });
        salir.add(guardarItem);

        tabla= new JMenu("Tabla");
        tabla.setEnabled(false);
        JMenuItem tablaItem= new JMenuItem("Puntos");
        tablaItem.setBackground(new Color(201,217,5));
        tablaItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTabla();
            }
        });
        tabla.add(tablaItem);




        menuBar.add(ayuda);
        menuBar.add(tabla);
        menuBar.add(salir);


        setJMenuBar(menuBar);
        setTitle("PARADE");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setVisible(true);

    }

    private void mostrarTabla() {
        setContentPane(panelPuntuacion);
        panelPuntuacion.updateUI();
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

    @Override
    public void setControlador(Controlador controlador){
        this.controlador = controlador;
    }

    public void iniciarJuego()  {
        vistaJuego.iniciarVentanaJuego();
        mostrarVentanaJuego();
    }

    @Override
    public void cambioDeTurno() {
        this.vistaJuego.activarCartasMano();
    }

    @Override
    public void cartaTirada() {
            this.vistaJuego.actualizarCartasEnMano();
            this.vistaJuego.desactivarCartasEnMano();
            this.vistaJuego.activarCartasCarnaval();
            this.vistaJuego.setFinalizarButton(true);
    }

    @Override
    public void mostrarCarnaval() {
        this.vistaJuego.actualizarCartasCarnaval();
    }

    @Override
    public void cartaAgregadaCarnaval() {
        this.vistaJuego.actualizarCartasCarnaval();
    }

    @Override
    public void mostrarAreaDeJuego() {
        this.vistaJuego.desactivarCartasCarnaval();
        this.vistaJuego.desactivaBotonAnalizarCartas();
        this.vistaJuego.actualizarAreaDeJuego();
    }

    @Override
    public void mostrarAreaDeJuegoOponente(String nombreJugadorTurno) {
        this.vistaJuego.actualizarAreaOponente(nombreJugadorTurno);
    }

    @Override
    public void actualizarCantidadCartasMazo() {
        this.vistaJuego.setCantCartasMazo(this.controlador.getCantidadCartasMazo());
    }

    @Override
    public void finDeTurno() {
        this.vistaJuego.actualizarCartasEnMano();
        this.vistaJuego.clearCartasElegidas();
        this.vistaJuego.desactivarTodosLosBotones();
    }


    @Override
    public void iniciar() {
        this.vistaMenuInicial = new VistaMenuInicial(this.controlador,this);
        this.vistaIngresarJugador = new VistaIngresarJugador(this.controlador,this);
        this.vistaJuego =new VistaJuego(this.controlador,this);

        this.panelPrincipalMenuInicial= vistaMenuInicial.getPanel();
        this.panelPrincipalJuego= vistaJuego.getPanel();
        this.panelPrincipalIngresarJugador= vistaIngresarJugador.getPanel();

        this.panelMensaje= new JPanel();
        this.panelMensaje.setLayout(new GridBagLayout());
        this.panelMensaje.setVisible(true);
        mostrarMenuInicial();
    }

    @Override
    public void jugadorAgregado(String nombre) {
        if (nombre.equals(this.jugador)){
            mostrarMensaje("Jugador agregado con exito");
            this.ultimoPanel= "Menu Inicial";
            this.vistaMenuInicial.setAgregarJugador(false);
        }

    }

    @SuppressWarnings("StringConcatenationInLoop")
    @Override
    public void mostrarPuntos(String nombreGanadaor) {
        tabla.setEnabled(true);
        tablaJugadores= new JTextArea();
        tablaJugadores.setFont(new Font("Ravie",Font.PLAIN,20));
        tablaJugadores.setForeground(new Color(201,217,5));
        tablaJugadores.setBackground(new Color(199,86,195));

        List<IJugador> jugadores= this.controlador.listarJugadores();
        String resultado="";
        for (IJugador jugador : jugadores){
            resultado += jugador.getNombre() + " tiene " + jugador.getPuntos() + "\n";
        }
        resultado+= "\n\n EL GANADOR ES " + nombreGanadaor;
        tablaJugadores.setText(resultado);
        tablaJugadores.setEditable(false);

        JButton volverButton= new JButton();
        volverButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        volverButton.setBackground(new Color(201,217,5));
        volverButton.setForeground(new Color(199,86,195));
        volverButton.setFont(new Font("Ravie",Font.PLAIN,16));
        volverButton.setSize(200,100);
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarUltimoPanel();
            }
        });


        panelPuntuacion.add(tablaJugadores);
        setContentPane(panelPuntuacion);
        panelPuntuacion.updateUI();
    }

    @Override
    public void actualizarCartasEnMano() {
        this.vistaJuego.actualizarCartasEnMano();
    }

    @Override
    public void comienzoRondaDescarte() {
        mostrarMensaje("Comienza la RONDA DESCARTE");
        this.vistaJuego.removePanelCarnaval();
        this.vistaJuego.setEstado("RONDA DESCARTE");
    }

    @Override
    public void comienzoUltimaRonda() {
        mostrarMensaje("Comienza la ULTIMA RONDA");
        this.vistaJuego.setEstado(" ULTIMA RONDA");
    }

    @Override
    public void finDelJuego(String nombreGanadaor) {
        mostrarPuntos(nombreGanadaor);
    }

    @Override
    public void partidaCargada() {
        iniciarJuego();
    }

    @Override
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


    public void removeJugador() {
        this.controlador.removeJugador(this.jugador);
    }


    private void mostrarReglas() {
        reglasText.setText("""
                OBJETIVO DEL JUEGO
                Los jugadores deberán jugar sus cartas de la mano mientras intentan no quedarse cartas del carnaval, pues estas representan puntos negativos.
                Se reparten 5 a cada jugador. Luego se ponen 6 cartas más en el centro de la mesa que representan el carnaval.\
                El principio del carnaval comienza a la izquierda mientras que el final es a la derecha.
                
                SECUENCIA DEL JUEGO
                Durante su turno cada jugador llevará a cabo las siguientes acciones en el orden descrito:
                1. Escoger una carta de su mano y colocarla al final del carnaval;
                2. Si se da el caso, recoger cartas del carnaval y colocarlas en su area de juego;
                3. Robar una carta del mazo.
                Después, el turno pasa al siguiente jugador que realizará las mismas acciones.
                
                ACCIONES DEL JUEGO
                1.  La carta tirada no se cuenta a la hora de contar el número de cartas en la siguiente acción.
                2. Dependiendo de la carta jugada, algunas cartas serán retiradas del carnaval.
                \t -Si el número de cartas del carnaval es menor o igual al valor numérico de la carta jugada, no se retirará ninguna carta del carnaval.
                \t -Si el número de cartas del carnaval es mayor que el valor de la carta jugada, entonces algunas cartas pueden abandonarlo.
                Para determinar que cartas tienen que ser retiradas, deberán contarse las cartas desde el final del carnaval y hacia el principio, sin tener en cuenta la carta recién jugada.\s
                Toda carta a partir de esa posición (hasta donde hemos contado) será susceptible de ser retirada.
                Para saber que cartas de las susceptibles de ser retiradas, lo serán efectivamente, se siguen las siguientes reglas:
                \t • Todas las cartas con el mismo color que la carta jugada;
                \t • Todas las cartas de valor igual o inferior al de la carta jugada (véase ejemplo más adelante).
                Las cartas retiradas se disponen en el area de juego del jugador.
                3. El jugador roba una carta del mazo, volviendo a tener 5 cartas en mano.
                
                ULTIMA RONDA
                La última ronda empieza cuando un jugador tiene en su area de juego cartas de los 6 colores presentes en el juego o bien cuando se roba la última carta del mazo.
                Sin embargo, en esta última ronda, los jugadores no robarán carta del mazo (quedándose así con 4 cartas en la mano).
                Tras esta última ronda, comienza la ronda de descarte.
                
                RONDA DESCARTE
                Cada jugador escoge 2 cartas de su mano y las descarta. Las 2 cartas restantes, se colocan en su area de juego\s
                
                PUNTUACION
                Las cartas que aún formen parte del carnaval serán descartadas.
                1. Determinar quien tiene la mayoría de cada color. El jugador o jugadores con la mayoría de cartas de un color, girarán esas cartas y cada una las contará solo como 1 punto.
                2. Después, cada jugador sumará los valores del resto de cartas que tengan boca arriba. Sumar este total con el de las cartas sumadas en el punto anterior.
                3. El ganador sera quien tenga menos puntos negativos acumulados.
                4. En caso de empate ganara quien tenga menos cartas en su area de juego (las que estan dadas vuelta tambien se cuentan).""");
        setContentPane(panelReglas);
        panelReglas.updateUI();
    }
}
