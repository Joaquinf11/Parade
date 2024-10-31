package ar.unlu.edu.mvc.vista;

import ar.unlu.edu.mvc.controlador.Controlador;
import ar.unlu.edu.mvc.modelo.Carta;
import ar.unlu.edu.mvc.modelo.IJugador;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class VistaConsola {
    private Controlador controlador;
    private Scanner scanner;

    public VistaConsola() {
        this.scanner = new Scanner(System.in);
    }

    public void registrarControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public int mostrarMenuInicial(){
        System.out.println("|   PARADE |" );
        System.out.println("----------------");
        System.out.println("0 - Salir");
        System.out.println("1 - Agregar jugadores");
        System.out.println("2 - Comenzar partida");
        System.out.println("----------------");
        System.out.println("Ingrese una opcion: ");
        return scanner.nextInt();
    }

    public void ingresarJugadores(){
        System.out.println("Ingrese la cantidad de jugadores ");
        int cantidadJugadores= this.scanner.nextInt();
        this.scanner.nextLine();
        for (int i = 1 ; i <= cantidadJugadores ; i++){
            System.out.println("Ingrese el nombre del jugador " + i);
            String nombre = this.scanner.nextLine();
            this.controlador.agregarJugador(nombre);
        }
    }

    public void mostrarJugadores(List<IJugador> jugadores){
        for (IJugador jugador : jugadores){
            System.out.print(jugador.getNombre() + ", ");
        }
        System.out.println();
    }

    public void mostrarCartasEnMano(List<Carta> cartas){
        System.out.println(" CARTAS EN MANO");
        for (Carta carta : cartas){
        System.out.print("[ " + carta.getColor() + ", " + carta.getValor() + " ] ;");
        }
        System.out.println();
    }

    public void mostrarCartasCarnaval(List<Carta> cartas){
        System.out.println(" CARNAVAL");
        for (Carta carta : cartas){
            System.out.print("[ " + carta.getColor() + ", " + carta.getValor() + " ] ;");
        }
        System.out.println();
    }

    public void mostrarAreadeJuego(List<Carta> cartas){
        System.out.println(" AREA DE JUEGO");
        for (Carta carta : cartas){
            System.out.print("[ " + carta.getColor() + ", " + carta.getValor() + " ] ;");
        }
        System.out.println();
    }

    public int pedirCarta(){
        return 1;
    }

}
