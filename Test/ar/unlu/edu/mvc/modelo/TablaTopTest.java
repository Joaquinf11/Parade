package ar.unlu.edu.mvc.modelo;

import ar.unlu.edu.mvc.persistencia.Serializador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Serializable;

import static org.junit.jupiter.api.Assertions.*;

class TablaTopTest {

    @BeforeEach
    public void setUp(){
        TablaTop tablaTop = new TablaTop();
        Jugador elias= new Jugador("elias");
        Jugador joaquin= new Jugador("joaquin");
        Jugador carlos= new Jugador("carlos");
        elias.sumarVictoria();
        joaquin.sumarVictoria();
        joaquin.sumarVictoria();
        tablaTop.agregarJugador(elias);
        tablaTop.agregarJugador(carlos);
        tablaTop.agregarJugador(joaquin);
        Serializador serializador = new Serializador("TablaTest");
        try {
            serializador.persistir(tablaTop);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void probandoRecuperar(){
        Serializador serializador= new Serializador("TablaTest");
        try {
            TablaTop tabla= (TablaTop) serializador.recuperar();
            assertEquals(3,tabla.getJugadores().size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}