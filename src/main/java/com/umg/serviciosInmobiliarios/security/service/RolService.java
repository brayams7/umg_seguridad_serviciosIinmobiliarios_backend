package com.umg.serviciosInmobiliarios.security.service;

import com.umg.serviciosInmobiliarios.security.entity.Rol;
import com.umg.serviciosInmobiliarios.security.enums.RolName;
import com.umg.serviciosInmobiliarios.security.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByRolName(RolName rolName){
        return this.rolRepository.findByName(rolName);
    }

    public void createRol(Rol rol){
        this.rolRepository.save(rol);
    }
}
