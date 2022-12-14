package com.umg.serviciosInmobiliarios.security.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * este DTO se va a utilizar para devolver el token
 * */
public class JwtDto {
    private Integer id;
    private String token;
    private String bearer = "Bearer";
    private String username;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtDto(Integer id,String token, String username, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.token = token;
        this.username = username;
        this.authorities = authorities;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getBearer() {
        return bearer;
    }
}
