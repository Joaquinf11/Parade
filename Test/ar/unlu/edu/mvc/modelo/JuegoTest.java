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


//        juego.getCarnaval().mostrarCarnaval();
        juego.mostrarCartasEnManoPorJugador();

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
//
    @Test
    public void probandoJugarTurno(){



    }

}