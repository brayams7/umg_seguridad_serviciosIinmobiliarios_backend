package com.umg.serviciosInmobiliarios.dto;

import javax.validation.constraints.NotNull;

public class MultaDto {

    @NotNull
    private boolean multa;

    @NotNull
    private float cantidadMulta = 0;

    public boolean isMulta() {
        return multa;
    }

    public void setMulta(boolean multa) {
        this.multa = multa;
    }

    public float getCantidadMulta() {
        return cantidadMulta;
    }

    public void setCantidadMulta(float cantidadMulta) {
        this.cantidadMulta = cantidadMulta;
    }
}
