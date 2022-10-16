package com.umg.serviciosInmobiliarios.security.jwt;

import com.umg.serviciosInmobiliarios.exceptions.ResourceUnauthorizedException;
import com.umg.serviciosInmobiliarios.security.entity.UserManager;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Esta clase genera el token, métodos de validación que esté bien contruido
 * */

@Component
public class JwtProvider {
    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${app.jwt-secret}")
    private String secret;
    @Value("${app.jwt-expiration}")
    private int expiration;

    //generando el token
    //singWith es para firmar el token
    public String generateToken(Authentication authentication){
        UserManager userManager = (UserManager) authentication.getPrincipal(); //obteniendo el usuario de la autenticación

        return Jwts.builder().setSubject(userManager.getUsername())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000L))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    //obtenenemos el nombre del usuario en el token
    //subjet es el nombre de usuario.
    public String getUsernameFromToken(String token){
        return Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) throws ResourceUnauthorizedException {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException e ){ // mal formado el token
            logger.error("Token mal formado");

        }catch (UnsupportedJwtException e){
            logger.error("Token no soportado");

        }catch (ExpiredJwtException e){
            logger.error("Token expirado");
            throw new ResourceUnauthorizedException("Acceso denegado");

        }catch (IllegalArgumentException e){ //algún argumento que esé vacío
            logger.error("Token vacío");
        }catch (SignatureException e){
            logger.error("Fallo en la firma");
        }
        return false;
    }

}
