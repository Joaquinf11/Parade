package ar.unlu.edu.mvc.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TablaTopTest {
    TablaTop tablaTop = new TablaTop();Jugador elias= new Jugador("elias");
    Jugador joaquin= new Jugador("joaquin");
    Jugador carlos= new Jugador("carlos");
    Jugador nicolas= new Jugador("nicolas");
    Jugador rafa= new Jugador("rafa");

    @BeforeEach
    public void setUp(){
        nicolas.sumarVictoria();
        elias.sumarVictoria();elias.sumarVictoria();
        joaquin.sumarVictoria();joaquin.sumarVictoria();joaquin.sumarVictoria();
        carlos.sumarVictoria();carlos.sumarVictoria();carlos.sumarVictoria();carlos.sumarVictoria();
        rafa.sumarVictoria();rafa.sumarVictoria();rafa.sumarVictoria();rafa.sumarVictoria();rafa.sumarVictoria();
        tablaTop.agregarJugador(nicolas);
        tablaTop.agregarJugador(elias);
        tablaTop.agregarJugador(joaquin);
        tablaTop.agregarJugador(carlos);
        tablaTop.agregarJugador(rafa);
        Serializador serializador = new Serializador("TablaTest");
        try {
            serializador.persistir(tablaTop);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void probandoRecuperarYOrden(){
        Serializador serializador= new Serializador("TablaTest");
        try {
            TablaTop tabla= (TablaTop) serializador.recuperar();
            assertEquals(5,tabla.getJugadores().size());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        List<Jugador> jugadores= tablaTop.getJugadores();

        assertEquals("rafa",jugadores.get(0).getNombre());
        assertEquals("carlos",jugadores.get(1).getNombre());
        assertEquals("joaquin",jugadores.get(2).getNombre());
        assertEquals("elias",jugadores.get(3).getNombre());
        assertEquals("nicolas",jugadores.get(4).getNombre());

    }

    @Test
    public void probandoCambioDeJugador(){
        Jugador rodolfo= new Jugador("rodolfo");
        rodolfo.sumarVictoria();rodolfo.sumarVictoria();rodolfo.sumarVictoria();rodolfo.sumarVictoria();rodolfo.sumarVictoria();rodolfo.sumarVictoria();
        tablaTop.agregarJugador(rodolfo);

        assertEquals(5,tablaTop.getJugadores().size());

        List<Jugador> jugadores= tablaTop.getJugadores();

        assertEquals("rodolfo",jugadores.get(0).getNombre());
        assertEquals("rafa",jugadores.get(1).getNombre());
        assertEquals("carlos",jugadores.get(2).getNombre());
        assertEquals("joaquin",jugadores.get(3).getNombre());
        assertEquals("elias",jugadores.get(4).getNombre());

        nicolas.sumarVictoria();
        tablaTop.agregarJugador(nicolas);
        assertEquals(5,tablaTop.getJugadores().size());

        List<Jugador> jugadores2= tablaTop.getJugadores();

        assertEquals("rodolfo",jugadores2.get(0).getNombre());
        assertEquals("rafa",jugadores2.get(1).getNombre());
        assertEquals("carlos",jugadores2.get(2).getNombre());
        assertEquals("joaquin",jugadores2.get(3).getNombre());
        assertEquals("elias",jugadores2.get(4).getNombre());

        nicolas.sumarVictoria();nicolas.sumarVictoria();
        tablaTop.agregarJugador(nicolas);
        assertEquals(5,tablaTop.getJugadores().size());

        List<Jugador> jugadores3= tablaTop.getJugadores();

        assertEquals("rodolfo",jugadores3.get(0).getNombre());
        assertEquals("rafa",jugadores3.get(1).getNombre());
        assertEquals("carlos",jugadores3.get(2).getNombre());
        assertEquals("nicolas",jugadores3.get(3).getNombre());
        assertEquals("joaquin",jugadores3.get(4).getNombre());
    }


}