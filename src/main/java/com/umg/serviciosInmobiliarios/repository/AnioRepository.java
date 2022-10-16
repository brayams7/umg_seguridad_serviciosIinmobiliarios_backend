package com.umg.serviciosInmobiliarios.repository;

import com.umg.serviciosInmobiliarios.entity.Anio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnioRepository extends JpaRepository<Anio,Integer> {

    List<Anio> findByAnioNameGreaterThanEqual(Integer anio);

    Optional<Anio> findByAnioName(Integer anio);
}
