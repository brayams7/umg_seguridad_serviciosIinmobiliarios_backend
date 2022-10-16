package com.umg.serviciosInmobiliarios.security.repository;


import com.umg.serviciosInmobiliarios.security.entity.Rol;
import com.umg.serviciosInmobiliarios.security.enums.RolName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

    Optional<Rol> findByName(RolName name);

}
