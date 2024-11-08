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

    public List<Carta> getCartas(int[] indices){
        List<Carta> cartas = new ArrayList<>();
        for (int index : indices) {
            cartas.add(this.cartas.remove(index));
        }
        return cartas;
    }

    public void agregarCarta(Carta carta){
        this.cartas.add(carta);
    }

    public void sacarCarta(Carta carta){
        this.cartas.remove(carta);
    }

    // volver a chequear si funciona
    public List<Carta> salvarCartas(int valor) {
        List<Carta> temporal= new ArrayList<>();
        int cantidad=this.cartas.size();
        for (int i = cantidad - 1; i >=0 && valor != 0 ; i--){
            Carta cartaTemporal= this.cartas.get(i);
            temporal.add(new Carta(cartaTemporal.getValor(),cartaTemporal.getColor()));
            valor--;
        }
        return temporal;
    }

    public boolean analizarCarnaval(Carta carta){
        return carta.getValor() < this.cartas.size();
    }

    public boolean analizarCartasSalvadasCarnaval(int valorCarta,int[] cartasElegidas){
        List<Carta> salvadas = this.salvarCartas(valorCarta);
        for (int i = 0; i < cartasElegidas.length; i++) {
            if (salvadas.contains(this.cartas.get(cartasElegidas[i]))){
                return false;
            }
        }
        return true;
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
