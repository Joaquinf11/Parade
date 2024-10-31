package ar.unlu.edu.mvc.modelo;


import java.util.ArrayList;
import java.util.List;


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
        int cantidad=this.cartas.size();
        for (int i = cantidad - 1; i >=0 && valor != 0 ; i--){
            temporal.add(this.cartas.removeLast());
            valor--;
        }
        return temporal;
    }

    public boolean analizarCarnaval(Carta carta){
        return carta.getValor() < this.cartas.size();
    }


    //////////////////////////////////
    //FUNCIONES PARA TEST
    //////////////////////////////////

    public void mostrarCarnaval(){
        System.out.println("----------");
        System.out.println("CARNAVAL");
        for (Carta carta : this.cartas){
            System.out.println("[ " + carta.getColor() + ", " + carta.getValor() + " ] ;");
        }
        System.out.println("----------");
    }
}
