package ar.unlu.edu.mvc.exceptions;

public class CartaException extends Exception {
    TipoException tipo;
    public CartaException(String message ) {
        super(message);
    }

    public TipoException getTipo(){
        return tipo;
    }
}
