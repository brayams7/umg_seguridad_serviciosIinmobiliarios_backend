package com.umg.serviciosInmobiliarios.controller;

import com.umg.serviciosInmobiliarios.dto.PaginationDto;
import com.umg.serviciosInmobiliarios.dto.ProyectoDto;
import com.umg.serviciosInmobiliarios.entity.Proyecto;
import com.umg.serviciosInmobiliarios.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("proyecto")
@CrossOrigin(origins = "https://umg-seguridad-servicios-fe.herokuapp.com/")
@RestController
public class ProyectoController {

    @Autowired
    ProyectoService proyectoService;

    @PostMapping(path = "/crear/{idUsuario}")
    public ResponseEntity<String> crearProyectoRest(@Valid @RequestBody ProyectoDto proyectoDto,
                                                    @PathVariable Integer idUsuario,
                                                    BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>("Ingresa bien los campos", HttpStatus.BAD_REQUEST);

        boolean isCreate =  this.proyectoService.crearProyecto(proyectoDto,idUsuario);

        return isCreate ? new ResponseEntity<>("Se ha creado correctamente", HttpStatus.OK)
                : new ResponseEntity<>("Error al crear el usuario, verifica bien los campos", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(path = "/editar/{idProyecto}")
    public ResponseEntity<String> editarProyectoRest(@Valid @RequestBody ProyectoDto proyectoDto,
                                                     @PathVariable Integer idProyecto,
                                                     BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>("Ingresa bien los campos", HttpStatus.BAD_REQUEST);

        this.proyectoService.editarProyecto(proyectoDto,idProyecto);
        return new ResponseEntity<>("Editado correctamente", HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Proyecto> getProyecto(@PathVariable Integer id){
        return new ResponseEntity<>(this.proyectoService.getProyectoById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/list/{idUsuario}/pagination")
    public PaginationDto listarProyectosPorUsuario(@PathVariable Integer idUsuario,
                                                   @RequestParam(name = "pageCurrent", required = false, defaultValue = "0") int pageCurrent,
                                                   @RequestParam(name = "size", required = false, defaultValue = "10") int size){
        return this.proyectoService.listarProyectoUsuario(pageCurrent, size, idUsuario);
    }

    @GetMapping(path = "/list")
    public List<ProyectoDto> listarProyectosPorUsuarioTest(){
        return this.proyectoService.listarProyectos();
    }

}
