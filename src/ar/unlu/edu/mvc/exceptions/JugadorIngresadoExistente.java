package ar.unlu.edu.mvc.exceptions;

public class JugadorIngresadoExistente extends RuntimeException {
    public JugadorIngresadoExistente(String message) {
        super(message);
    }
}
