package com.umg.serviciosInmobiliarios.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Esta se ejecuta por cada petición, va comprobar que sea valido el token utilizando el provider
 * y si es valido permite el acceso al recurso,
 * sino lanza un exception
 *
 * @OncePerRequestFilter: se va ejecutar por cada petición
 * */


public class JwtTokenFilter extends OncePerRequestFilter {

    private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);
    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserDetailsService userDetailsService;

    //verificar quien está autenticado y valida si el tóken es valido o no a travéz jwtProvider
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getToken(request);
            if(token != null && jwtProvider.validateToken(token)){
                String username = jwtProvider.getUsernameFromToken(token);
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                //creamos una nueva autenticación, diciendole que usuario que cuales son sus permisos con autorities
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                //una vez obtenida la autenticación, se lo pasamos al contexto.
                SecurityContextHolder.getContext().setAuthentication(auth);
            }else{
                System.out.println("no encontró el token");
            }
        }catch (Exception e){
            logger.error("fail en el método doFilter");
        }
        filterChain.doFilter(request, response);
    }

    //obtenemos la parte del token de : "Bearer 234lklsfjlasjdf8483"
    //el request lleva la cebeceza authorization
    private String getToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if(!header.isEmpty() && header != null && header.startsWith("Bearer"))
            return header.replace("Bearer ", "");
        return null;
    }
}
