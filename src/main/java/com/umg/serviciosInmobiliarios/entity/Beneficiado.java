package com.umg.serviciosInmobiliarios.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.umg.serviciosInmobiliarios.security.entity.Usuario;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "beneficiados")
public class Beneficiado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String direccion;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private Date inicio;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fin;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ultimoPago;

    @ManyToOne(cascade = CascadeType.ALL)
    private Proyecto proyecto;

    @ManyToOne(cascade = CascadeType.ALL)
    private Usuario usuario;

    public Beneficiado() {
    }

    public Beneficiado(String direccion, Date inicio, Date fin, Date ultimoPago, Proyecto proyecto, Usuario usuario) {
        this.direccion = direccion;
        this.inicio = inicio;
        this.fin = fin;
        this.ultimoPago = ultimoPago;
        this.proyecto = proyecto;
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
