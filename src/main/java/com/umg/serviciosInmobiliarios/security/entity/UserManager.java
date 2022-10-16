package com.umg.serviciosInmobiliarios.security.entity;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

//Este se encarga de implementar los privilegios de cada usuario
public class UserManager implements UserDetails {

    private final Integer id;
    private final String name;
    private final String email;
    private final String username;
    private final String password;

    private final Collection<? extends GrantedAuthority> authorities;

    public UserManager(Integer id, String name, String email, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    /**
     * Build: méotod que se encargará de asignar los privilegios a cada usuario (su autorización -> ADMIN, USER).
     * */

    public static UserManager build(Usuario user){
        //obtenemos una lista de grantedAutority a partir de los roles, convirtiendo la clase rol a GrantedAutority
        List<GrantedAuthority> authorities= user.getRolesuser()
                .stream().map(rol -> new SimpleGrantedAuthority(rol.getName().name())).collect(Collectors.toList());

        return new UserManager(user.getId(), user.getName(), user.getEmail(), user.getUsername(),
                user.getPassword(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}