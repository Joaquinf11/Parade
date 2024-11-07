package ar.unlu.edu.mvc.interfaces;

import ar.unlu.edu.mvc.modelo.Evento;

public interface Observado {
    public void agregarObservador(Observador observador);

    public void notificar(Evento evento);
}
