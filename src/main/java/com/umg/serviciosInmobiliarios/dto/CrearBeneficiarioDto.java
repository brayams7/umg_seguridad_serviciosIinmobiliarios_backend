package com.umg.serviciosInmobiliarios.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

public class CrearBeneficiarioDto {

    /*@NotBlank
    private String name;

    @NotBlank
    private String username;

    @Email(message = "El correo es inválido")
    @NotBlank(message = "Inngresa un correo electrónico")
    private String email;

    @NotBlank
    private String password;*/

    @NotNull
    @NotBlank
    private String direccion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inicio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Null
    private Date fin;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Null
    private Date ultimoPago;

    @NotNull
    private Integer idProyecto;

    @NotNull
    private Integer idUsuario;

    public CrearBeneficiarioDto(String direccion, Date inicio, Date fin, Date ultimoPago, Integer idProyecto, Integer idUsuario) {
        this.direccion = direccion;
        this.inicio = inicio;
        this.fin = fin;
        this.ultimoPago = ultimoPago;
        this.idProyecto = idProyecto;
        this.idUsuario = idUsuario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public Date getUltimoPago() {
        return ultimoPago;
    }

    public void setUltimoPago(Date ultimoPago) {
        this.ultimoPago = ultimoPago;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
