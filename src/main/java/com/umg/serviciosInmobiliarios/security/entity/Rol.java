package com.umg.serviciosInmobiliarios.security.entity;

import com.umg.serviciosInmobiliarios.security.enums.RolName;

import javax.persistence.*;

@Entity
@Table(name = "rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //se genera un valor único
    private Integer id;

    @Enumerated(EnumType.STRING)
    private RolName name;

    public Rol(){

    }

    public Rol(RolName name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RolName getName() {
        return name;
    }

    public void setName(RolName name) {
        this.name = name;
    }
}
