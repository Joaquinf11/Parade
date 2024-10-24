package ar.unlu.edu.mvc.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.geom.Area;

import static org.junit.jupiter.api.Assertions.*;

class AreaDeJuegoTest {
    AreaDeJuego area;
    @BeforeEach
    void setUp(){
        area = new AreaDeJuego();
    }

    @Test
    public void probandoSumarValorDeCartasDeAreaDeJuegoSinBocaAbajo(){
        area.agregarCarta(new Carta(0,Color.VERDE));
        area.agregarCarta(new Carta(0,Color.AMARILLO));
        area.agregarCarta(new Carta (5,Color.AZUL));
        area.agregarCarta(new Carta(8,Color.NEGRO));
        area.agregarCarta(new Carta(0,Color.ROJO));
        area.agregarCarta(new Carta(6,Color.VIOLETA));
        int puntos=area.sumarValorDeCartas();
        assertEquals(-19,puntos);
    }

    @Test
    public void probandoSumarValorDeCartasDeAreaDeJuegoConBocaAbajo(){
        area.agregarCarta(new Carta(0,Color.VERDE));
        area.agregarCarta(new Carta(0,Color.AMARILLO));
        area.agregarCarta(new Carta (5,Color.AZUL));
        area.agregarCarta(new Carta(8,Color.NEGRO));
        area.agregarCarta(new Carta(0,Color.ROJO));
        area.agregarCarta(new Carta(6,Color.VIOLETA));
        area.ponerCartasBocaAbajo(Color.NEGRO);
        int puntos=area.sumarValorDeCartas();
        assertEquals(-11,puntos);
    }

}