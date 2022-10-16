package com.umg.serviciosInmobiliarios.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.umg.serviciosInmobiliarios.security.entity.Usuario;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "proyectos")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false)
    private Integer cantidadBeneficiados;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date FechaMora;

    @ManyToOne(cascade = CascadeType.ALL)
    private Usuario usuario;

    public Proyecto() {
    }

    public Proyecto(String nombre, Integer cantidadBeneficiados, Date fechaMora, Usuario usuario) {
        this.nombre = nombre;
        this.cantidadBeneficiados = cantidadBeneficiados;
        FechaMora = fechaMora;
        this.usuario = usuario;
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
        return FechaMora;
    }

    public void setFechaMora(Date fechaMora) {
        FechaMora = fechaMora;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
