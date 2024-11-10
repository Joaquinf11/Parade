package ar.unlu.edu.mvc.modelo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class JuegoTest {
    Juego juego;

    @BeforeEach
    void setUp(){
        juego= new Juego();
        juego.agregarJugador("Joaquin");
        juego.agregarJugador("Elias");
        juego.agregarJugador("Nicolas");

        for (Jugador jugador : juego.getJugadores()){
            if (Objects.equals(jugador.getNombre(),"Joaquin")){
                jugador.agarrarCarta(new Carta(6,Color.VERDE));
                jugador.agarrarCarta(new Carta(5,Color.VERDE));
                jugador.agarrarCarta(new Carta(10,Color.ROJO));
                jugador.agarrarCarta(new Carta(10,Color.VIOLETA));
                jugador.agarrarCarta(new Carta(1,Color.ROJO));
            }
            if (Objects.equals(jugador.getNombre(),"Elias")){
                jugador.agarrarCarta(new Carta(1,Color.VERDE));
                jugador.agarrarCarta(new Carta(2,Color.VERDE));
                jugador.agarrarCarta(new Carta(2,Color.ROJO));
                jugador.agarrarCarta(new Carta(2,Color.VIOLETA));
                jugador.agarrarCarta(new Carta(3,Color.ROJO));
            }
            if (Objects.equals(jugador.getNombre(),"Nicolas")){
                jugador.agarrarCarta(new Carta(4,Color.AZUL));
                jugador.agarrarCarta(new Carta(2,Color.NEGRO));
                jugador.agarrarCarta(new Carta(1,Color.AZUL));
                jugador.agarrarCarta(new Carta(3,Color.VIOLETA));
                jugador.agarrarCarta(new Carta(1,Color.AMARILLO));
            }

        }

        juego.getCarnaval().agregarCarta(new Carta(0,Color.VERDE));
        juego.getCarnaval().agregarCarta(new Carta(0,Color.AMARILLO));
        juego.getCarnaval().agregarCarta(new Carta (5,Color.AZUL));
        juego.getCarnaval().agregarCarta(new Carta(8,Color.NEGRO));
        juego.getCarnaval().agregarCarta(new Carta(0,Color.ROJO));
        juego.getCarnaval().agregarCarta(new Carta(6,Color.VIOLETA));


//        juego.getCarnaval().mostrarCarnaval();
//        juego.mostrarCartasEnManoPorJugador();
//        for (Jugador jugador : juego.getJugadores()){
//            if (Objects.equals(jugador.getNombre(),"Joaquin")){
//                juego.jugarTurno(jugador,3);
//                juego.getCarnaval().mostrarCarnaval();
//                System.out.println("Joaquin");
//                jugador.mostrarAreaDeJuego();
//                jugador.mostrarCartasEnMano();
//            }
//            if (Objects.equals(jugador.getNombre(),"Elias")){
//                juego.jugarTurno(jugador,0);
//                juego.getCarnaval().mostrarCarnaval();
//                System.out.println("Elias");
//                jugador.mostrarAreaDeJuego();
//                jugador.mostrarCartasEnMano();
//            }
//            if (Objects.equals(jugador.getNombre(),"Nicolas")){
//                juego.jugarTurno(jugador,0);
//                juego.getCarnaval().mostrarCarnaval();
//                System.out.println("Nicolas");
//                jugador.mostrarAreaDeJuego();
//                jugador.mostrarCartasEnMano();
//            }


    }

//    @Test
//    public void probandoRepartirCartas(){
//        juego.agregarJugador("Joaquin");
//        juego.agregarJugador("Elias");
//        juego.agregarJugador("Nicolas");
//        juego.repartirCartas();
//        Carnaval carnaval= juego.getCarnaval();
//        assertEquals(6,carnaval.getCartas().size());
//        for (Jugador jugador : juego.getJugadores()){
//            assertEquals(jugador.getCartas().size(),5);
//        }
//        assertEquals(juego.getMazo().getCartas().size(),60 - (5 * juego.getJugadores().size()));
////        carnaval.mostrarCarnaval();
////        juego.mostrarCartasEnManoPorJugador();
//    }
//    @Test
//    public void probandoJugarTurno(){
//        for (Jugador jugador : juego.getJugadores()){
//            if (Objects.equals(jugador.getNombre(),"Joaquin")){
//                juego.jugarTurno(jugador,3);
//                juego.getCarnaval().mostrarCarnaval();
//                System.out.println("Joaquin");
//                jugador.mostrarAreaDeJuego();
//                jugador.mostrarCartasEnMano();
//            }
//            if (Objects.equals(jugador.getNombre(),"Elias")){
//                juego.jugarTurno(jugador,0);
//                juego.getCarnaval().mostrarCarnaval();
//                System.out.println("Elias");
//                jugador.mostrarAreaDeJuego();
//                jugador.mostrarCartasEnMano();
//            }
//            if (Objects.equals(jugador.getNombre(),"Nicolas")){
//                juego.jugarTurno(jugador,0);
//                juego.getCarnaval().mostrarCarnaval();
//                System.out.println("Nicolas");
//                jugador.mostrarAreaDeJuego();
//                jugador.mostrarCartasEnMano();
//            }
//
//        }
//        juego.calcularPuntos();
//        for (Jugador jugador : juego.getJugadores()){
//            System.out.print("PUNTOS DE " + jugador.getNombre() + " ");
//            jugador.mostrarPuntos();
//        }
//        Jugador ganador = juego.definirGanador();
//        System.out.println("GANADOR: " + ganador.getNombre());
//    }
//        @Test
//        public void probandoDefinirGanador(){
//        probandoJugarTurno();
//        juego.calcularPuntos();
//        for (Jugador jugador : juego.getJugadores()){
//            System.out.print("PUNTOS DE " + jugador.getNombre() + " ");
//            jugador.mostrarPuntos();
//        }
//        Jugador ganador = juego.definirGanador();
//        System.out.println("GANADOR: " + ganador.getNombre());
//          }




}

