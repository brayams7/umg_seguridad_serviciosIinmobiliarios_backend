package com.umg.serviciosInmobiliarios.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

public class ProyectoDto {

    @Null
    private Integer id;

    @NotNull
    private String nombre;

    @NotNull
    private Integer cantidadBeneficiados;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date fechaMora;

    public ProyectoDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidadBeneficiados() {
        return cantidadBeneficiados;
    }

    public void setCantidadBeneficiados(Integer cantidadBeneficiados) {
        this.cantidadBeneficiados = cantidadBeneficiados;
    }

    public Date getFechaMora() {
        return fechaMora;
    }

    public void setFechaMora(Date fechaMora) {
        this.fechaMora = fechaMora;
    }
}
