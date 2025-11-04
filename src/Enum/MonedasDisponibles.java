package Enum;

public enum MonedasDisponibles {
    USD("Dolar"), ARS("Peso Argentino"),EUR("Euro"),
    BRL("Real Brasileño"),JPY("Yen Japonés"), RUB("Rublo");

    private final String descripcion;

    private MonedasDisponibles(String descripcion){
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return this.descripcion;
    }


}

