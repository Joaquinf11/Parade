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

    //TODO chequear si sirve para algo TAREA: agregar EnumComando
    public static Comando fromString(String input) {
        try {
            String normalizado = input.trim().toUpperCase().replace(" ", "_");
            return Comando.valueOf(normalizado);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Comando no válido: " + input);
        }

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
