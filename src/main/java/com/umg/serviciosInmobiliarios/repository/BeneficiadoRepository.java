package com.umg.serviciosInmobiliarios.repository;

import com.umg.serviciosInmobiliarios.entity.Beneficiado;
import com.umg.serviciosInmobiliarios.entity.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BeneficiadoRepository extends JpaRepository<Beneficiado, Integer> {

    List<Beneficiado> findByProyecto(Proyecto proyecto);

    Integer countByProyecto(Proyecto proyecto);

}
