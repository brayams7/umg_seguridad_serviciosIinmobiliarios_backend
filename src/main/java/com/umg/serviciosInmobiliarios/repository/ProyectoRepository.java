package com.umg.serviciosInmobiliarios.repository;

import com.umg.serviciosInmobiliarios.entity.Proyecto;
import com.umg.serviciosInmobiliarios.security.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {

    Page<Proyecto> findByUsuario(Usuario usuario, Pageable pageable);

}
