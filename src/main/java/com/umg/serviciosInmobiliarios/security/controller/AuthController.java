package com.umg.serviciosInmobiliarios.security.controller;


import com.umg.serviciosInmobiliarios.security.dto.JwtDto;
import com.umg.serviciosInmobiliarios.security.dto.LoginDto;
import com.umg.serviciosInmobiliarios.security.dto.UserDto;
import com.umg.serviciosInmobiliarios.security.entity.Rol;
import com.umg.serviciosInmobiliarios.security.entity.UserManager;
import com.umg.serviciosInmobiliarios.security.entity.Usuario;
import com.umg.serviciosInmobiliarios.security.enums.RolName;
import com.umg.serviciosInmobiliarios.security.jwt.JwtProvider;
import com.umg.serviciosInmobiliarios.security.service.RolService;
import com.umg.serviciosInmobiliarios.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "http://localhost:3000/")
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>("Ingresa correctamente los campos", HttpStatus.BAD_REQUEST);
        }
        if(this.userService.existsByUsername(userDto.getUsername()))
            return new ResponseEntity<>("El nombre del usuario ya existe", HttpStatus.BAD_REQUEST);
        if(this.userService.existsByEmail(userDto.getEmail()))
            return new ResponseEntity<>("El email ya existe", HttpStatus.BAD_REQUEST);

        Usuario user = new Usuario(userDto.getName(),userDto.getEmail(),userDto.getUsername(),
                this.passwordEncoder.encode(userDto.getPassword()));

        //asignado roles al usuario

        Set<Rol> roles = new HashSet<>();
        roles.add(this.rolService.getByRolName(RolName.ROLE_USER).get());
        if(userDto.getRoles().contains("admin")){
            roles.add(this.rolService.getByRolName(RolName.ROLE_ADMIN).get());
        }
        roles.stream().forEach(item -> System.out.println(item.getName()));
        user.setRolesuser(roles);

        this.userService.createUser(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginDto loginDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return new ResponseEntity("Ingresa correctamente los campos", HttpStatus.BAD_REQUEST);
        }

        Authentication authentication =
                this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(),
                        loginDto.getPassword()));

        //autenticamos al usuario
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = this.jwtProvider.generateToken(authentication);

        //obtenemos el nombre del usuario
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserManager u = (UserManager) authentication.getPrincipal();
        //userDetails.getAuthorities().forEach(grantedAuthority -> System.out.println(grantedAuthority.getAuthority()));
        JwtDto jwtDto=new JwtDto(u.getId(),token,userDetails.getUsername(),userDetails.getAuthorities());
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }

}
