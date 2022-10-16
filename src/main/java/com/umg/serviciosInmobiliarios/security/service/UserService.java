package com.umg.serviciosInmobiliarios.security.service;

import com.umg.serviciosInmobiliarios.exceptions.ResourceNotFoundException;
import com.umg.serviciosInmobiliarios.security.entity.Usuario;
import com.umg.serviciosInmobiliarios.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional //mantener la coherencia en la base de datos.
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<Usuario> getByUsername(String username){
        return this.userRepository.findByUsername(username);
    }

    public boolean existsByUsername(String username){
        return this.userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email){
        return this.userRepository.existsByEmail(email);
    }

    public boolean existsById(Integer id){
        return this.userRepository.existsById(id);
    }

    public Usuario getById(Integer id){
        return this.userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No se encuentra el usuario"));
    }

    public Usuario createUser(Usuario user){
        return this.userRepository.save(user);
    }

}
