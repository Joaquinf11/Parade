package ar.unlu.edu.mvc.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Turno {
    private List<Jugador> jugadores;
    private Carnaval carnaval;
    private Mazo mazo;

    public Turno(ArrayList<Jugador> jugadores,Carnaval carnaval, Mazo mazo) {
        this.jugadores = jugadores;
        this.carnaval = carnaval;
        this.mazo = mazo;
    }



}
