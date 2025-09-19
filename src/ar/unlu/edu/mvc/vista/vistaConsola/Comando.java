package ar.unlu.edu.mvc.vista.vistaConsola;

public enum Comando {
    COMANDOS,
    MAZO,
    CARNAVAL,
    AREA,
    MANO,
    SALIR,
    CLEAR,
    AREA_OPONENTES,
    COMO_JUGAR,
    REGLAS, FINALIZAR_TURNO, TABLA;


    public static Comando fromString(String input) {
            String normalizado = input.trim().toUpperCase().replace(" ", "_");
            return Comando.valueOf(normalizado);

    }

    public static boolean esComandoValido(String input) {
        String normalizado = input.trim().toUpperCase().replace(" ", "_");
        for (Comando c : Comando.values()) {
            if (c.name().equalsIgnoreCase(normalizado)) {
                return true;
            }
        }
        return false;
    }

}
