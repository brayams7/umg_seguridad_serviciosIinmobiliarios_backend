package com.umg.serviciosInmobiliarios.controller;

import com.umg.serviciosInmobiliarios.entity.Beneficiado;
import com.umg.serviciosInmobiliarios.enums.MesName;
import com.umg.serviciosInmobiliarios.entity.Anio;
import com.umg.serviciosInmobiliarios.service.AnioService;
import com.umg.serviciosInmobiliarios.service.BeneficiadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("anio")
@CrossOrigin(origins = "https://umg-seguridad-servicios-fe.herokuapp.com/")
@RestController
public class AnioController {

    @Autowired
    AnioService anioService;

    @Autowired
    BeneficiadoService beneficiadoService;

    @GetMapping(path = "/meses")
    public ResponseEntity<List<MesName>> listarMeses(){
        return new ResponseEntity<>(this.anioService.listarMeses(), HttpStatus.OK);
    }

    @GetMapping(path = "/list")
    public ResponseEntity<List<Anio>> listarAnios(){
        return new ResponseEntity<>(this.anioService.listarAnios(), HttpStatus.OK);
    }

    @GetMapping(path = "/aniosBeneficiado/{idBeneficiado}")
    public ResponseEntity<?> listarAniosBeneficiado(@PathVariable Integer idBeneficiado){
        if(idBeneficiado == null){
            return new ResponseEntity<>("Ingresa un valor correcto", HttpStatus.BAD_REQUEST);
        }
        Beneficiado beneficiado = beneficiadoService.getBeneficiado(idBeneficiado);
        return new ResponseEntity<>(this.anioService.listarAniosBeneficiado(beneficiado), HttpStatus.OK);
    }
}
