package com.umg.serviciosInmobiliarios.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.umg.serviciosInmobiliarios.enums.MesName;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class PagoDto {

    @NotNull
    private Double cantidad;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date fechaPago;

    private boolean multa=false;

    private float cantidadMulta = 0;

    @NotNull
    private String mes;

    @NotNull
    private Integer anio;

    @NotNull
    private Integer idBeneficiario;


    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

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

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getIdBeneficiario() {
        return idBeneficiario;
    }

    public void setIdBeneficiario(Integer idBeneficiario) {
        this.idBeneficiario = idBeneficiario;
    }
}
