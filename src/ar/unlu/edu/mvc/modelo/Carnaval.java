package ar.unlu.edu.mvc.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Carnaval {
    private List<Carta> cartas;

    public Carnaval (){
        this.cartas= new ArrayList<>();
    }

    public List<Carta> getCartas() {
        return this.cartas;
    }


    public void agregarCarta(Carta carta){
        this.cartas.add(carta);
    }

    public void sacarCarta(Carta carta){
        this.cartas.remove(carta);
    }


    public List<Carta> salvarCartas(int valor) {
        List<Carta> temporal= new ArrayList<>();
        for (int i = this.cartas.size() - 1; i >=0 && valor != 0 ; i--){
            temporal.add(this.cartas.removeLast());
            valor--;
        }
        return temporal;
    }

    public boolean analizarCarnaval(Carta carta){
        return carta.getValor() < this.cartas.size();
    }

}
