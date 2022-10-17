package com.umg.serviciosInmobiliarios.controller;

import com.umg.serviciosInmobiliarios.dto.CrearBeneficiarioDto;
import com.umg.serviciosInmobiliarios.entity.Beneficiado;
import com.umg.serviciosInmobiliarios.entity.Proyecto;
import com.umg.serviciosInmobiliarios.exceptions.ResourceBadRequest;
import com.umg.serviciosInmobiliarios.security.entity.Usuario;
import com.umg.serviciosInmobiliarios.security.service.UserService;
import com.umg.serviciosInmobiliarios.service.BeneficiadoService;
import com.umg.serviciosInmobiliarios.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "beneficiado")
@CrossOrigin(origins = "https://umg-seguridad-servicios-fe.herokuapp.com/")
public class BeneficiadoController {

    @Autowired
    BeneficiadoService beneficiadoService;

    @Autowired
    UserService userService;

    @Autowired
    ProyectoService proyectoService;


    @PostMapping(path = "/asiganarBeneficiadoProyecto")
    public ResponseEntity<String> asiganarBeneficiadoProyecto(@Valid @RequestBody CrearBeneficiarioDto crearBeneficiarioDto,
                                                      BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>("Ingrese los datos correctamente", HttpStatus.BAD_REQUEST);

        Usuario usuario = this.userService.getById(crearBeneficiarioDto.getIdUsuario());
        Proyecto proyecto = this.proyectoService.getProyectoById(crearBeneficiarioDto.getIdProyecto());

        Beneficiado beneficiado = new Beneficiado(crearBeneficiarioDto.getDireccion(),
                crearBeneficiarioDto.getInicio(),
                crearBeneficiarioDto.getFin(),crearBeneficiarioDto.getUltimoPago(),proyecto,usuario);

        Integer cB = beneficiado.getProyecto().getCantidadBeneficiados();
        Integer cBAgregados = this.beneficiadoService.cantidadBeneficiadosProyecto(beneficiado.getProyecto());

        if(Objects.equals(cBAgregados, cB)){
            return new ResponseEntity<>("Ha alcanzado el número máximo de beneficiados", HttpStatus.BAD_REQUEST);
        }

        boolean isCreate = this.beneficiadoService.asignarBeneficiadoAProyecto1(beneficiado);

        return isCreate ? new ResponseEntity<>("Creado correctamente", HttpStatus.OK):
                new ResponseEntity<>("Ocurrió un error al crear el beneficiado", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/list/{idProyecto}")
    public ResponseEntity<List<Beneficiado>> listarBeneficiadosProyecto(@PathVariable Integer idProyecto){
        return new ResponseEntity<>(this.beneficiadoService.listarBeneficiadosProyecto(idProyecto), HttpStatus.OK);
    }

    @GetMapping("/{idBeneficiado}")
    public ResponseEntity<Beneficiado> getBeneficiado(@PathVariable Integer idBeneficiado){
        return new ResponseEntity<>(this.beneficiadoService.getBeneficiado(idBeneficiado), HttpStatus.OK);
    }
}
