package ar.unlu.edu.mvc.modelo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JuegoTest {
    Juego juego;

    @BeforeEach
    void setUp(){
        juego= new Juego();
        juego.agregarJugador("Joaquin");
        juego.agregarJugador("Elias");
        juego.agregarJugador("Nicolas");
    }

    @Test
    public void probandoRepartirCartas(){
        juego.repartirCartas();
        Carnaval carnaval= juego.getCarnaval();
        assertEquals(carnaval.getCartas().size(),6);
        for (Jugador jugador : juego.getJugadores()){
            assertEquals(jugador.getCartas().size(),5);
        }
        assertEquals(juego.getMazo().getCartas().size(),60 - (5 * juego.getJugadores().size()));
    }


}