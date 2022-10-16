package com.umg.serviciosInmobiliarios.entity;

import javax.persistence.*;

@Entity
@Table(name = "anios")
public class Anio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer anioName;

    public Anio() {
    }

    public Anio(Integer anioName) {
        this.anioName = anioName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnioName() {
        return anioName;
    }

    public void setAnioName(Integer anioName) {
        this.anioName = anioName;
    }
}
