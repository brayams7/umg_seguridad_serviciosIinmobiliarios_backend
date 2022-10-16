package com.umg.serviciosInmobiliarios.entity;

import com.umg.serviciosInmobiliarios.enums.MesName;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Double cantidad;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private Date fecha_pago;

    @Column(nullable = false)
    private boolean multa = false;

    private float cantidadMulta = 0;

    @Enumerated(EnumType.STRING)
    private MesName mes;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Anio anio;

    @ManyToOne(cascade = CascadeType.ALL)
    private Beneficiado beneficiado;

    public Pago() {
    }

    public Pago(Double cantidad, Date fecha_pago, MesName mes, Anio anio, Beneficiado beneficiado) {
        this.cantidad = cantidad;
        this.fecha_pago = fecha_pago;
        this.mes = mes;
        this.anio = anio;
        this.beneficiado = beneficiado;
    }

    public Pago(Double cantidad, Date fecha_pago, boolean multa, float cantidadMulta, MesName mes, Anio anio, Beneficiado beneficiado) {
        this.cantidad = cantidad;
        this.fecha_pago = fecha_pago;
        this.multa = multa;
        this.cantidadMulta = cantidadMulta;
        this.mes = mes;
        this.anio = anio;
        this.beneficiado = beneficiado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(Date fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public Beneficiado getBeneficiado() {
        return beneficiado;
    }

    public void setBeneficiado(Beneficiado beneficiado) {
        this.beneficiado = beneficiado;
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

    public MesName getMes() {
        return mes;
    }

    public void setMes(MesName mes) {
        this.mes = mes;
    }

    public Anio getAnio() {
        return anio;
    }

    public void setAnio(Anio anio) {
        this.anio = anio;
    }
}
