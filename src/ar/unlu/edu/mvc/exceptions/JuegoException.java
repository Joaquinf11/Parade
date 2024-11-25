package ar.unlu.edu.mvc.exceptions;

public class JuegoException extends Exception {
    TipoException tipo;
    public JuegoException(String message , TipoException tipo) {
        super(message);
        this.tipo=tipo;
    }

    public TipoException getTipo(){
        return tipo;
    }
}
