package ar.unlu.edu.mvc.exceptions;

public class CartaException extends Exception {
    TipoException tipo;
    public CartaException(String message ,TipoException tipo) {
        super(message);
        this.tipo=tipo;
    }

    public TipoException getTipo(){
        return tipo;
    }
}
