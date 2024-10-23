package ar.unlu.edu.mvc.observer;

import ar.unlu.edu.mvc.modelo.Evento;

public interface Observado {
    public void agregarObservador(Observador observador);

    public void notificar(Evento evento);
}
