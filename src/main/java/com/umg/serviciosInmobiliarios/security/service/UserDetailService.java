package com.umg.serviciosInmobiliarios.security.service;

import com.umg.serviciosInmobiliarios.security.entity.UserManager;
import com.umg.serviciosInmobiliarios.security.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Esta clase convierte un usuario en un usuario principal (cargamos el usuario).
 * */

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    UserService userService;

    //cargar el usuario por nombre usuario
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = this.userService.getByUsername(username).get();
        return UserManager.build(user);
    }
}
