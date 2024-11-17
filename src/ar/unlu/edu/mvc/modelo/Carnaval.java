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

    public Carta getUltimaCarta() {
        return this.cartas.getLast();
    }

    public List<Carta> getCartas(int[] indices){
        List<Carta> resultado = new ArrayList<>();
        for (int index : indices){
            resultado.add(this.cartas.get(index));
        }
        return resultado;
    }

    public void agregarCarta(Carta carta){
        this.cartas.add(carta);
    }

    public void agregarCarta(int indice,Carta carta){
        if (indice > this.cartas.size()){
            this.cartas.add(carta);
        }
        else {
            this.cartas.add(indice, carta);
        }
    }

    public void sacarCarta(Carta carta){
        this.cartas.remove(carta);
    }

    public void eliminarCartas( List<Carta>cartas){
        for (Carta carta : cartas){
            this.cartas.remove(carta);
        }
    }

    public List<Carta> salvarCartas(int valor) {
        List<Carta> temporal= new ArrayList<>();
        int cantidad=this.cartas.size();
        for (int i = cantidad - 2; i >=0 && valor != 0 ; i--){
            Carta cartaTemporal= this.cartas.get(i);
            temporal.add(new Carta(cartaTemporal.getValor(),cartaTemporal.getColor()));
            valor--;
        }
        return temporal;
    }

    public boolean puedeAgarrarCarnaval(Carta carta){
        return carta.getValor() < this.cartas.size();
    }

    public boolean agarroCartasSalvadasCarnaval(int valorCarta, int[] cartasElegidas){
        List<Carta> salvadas = this.salvarCartas(valorCarta);
        for (int i = 0; i < cartasElegidas.length; i++) {
            if (salvadas.contains(this.cartas.get(cartasElegidas[i]))){
                return true;
            }
        }
        return false;
    }
    public boolean faltaAgarrarCartas(Carta carta, int[] cartasElegidas) {
        List<Carta> salvadas=this.salvarCartas(carta.getValor());
        int cantidadCartasElegidas;
        if (cartasElegidas == null){
            cantidadCartasElegidas=0;
        }
        else {
            cantidadCartasElegidas= cartasElegidas.length;
        }
        int contador=0;

        for( Carta cartaCarnaval : this.cartas){
            if (!cartaCarnaval.equals(carta) && !salvadas.contains(cartaCarnaval) && (carta.equalsColor(cartaCarnaval) || cartaCarnaval.getValor() <= carta.getValor())){
                contador++;
            }
        }
        return contador > cantidadCartasElegidas;
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
