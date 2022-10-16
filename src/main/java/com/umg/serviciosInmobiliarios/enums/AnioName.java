package com.umg.serviciosInmobiliarios.enums;

public enum AnioName {
    anio_2021 (2021), anio_2022 (2022);

    private final int anio;

    AnioName(int anio){
        this.anio =anio;
    }

    public int getAnio() {
        return anio;
    }
}
