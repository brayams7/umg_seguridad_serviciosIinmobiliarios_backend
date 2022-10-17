package com.umg.serviciosInmobiliarios.controller;

import com.umg.serviciosInmobiliarios.dto.ListPagoDto;
import com.umg.serviciosInmobiliarios.dto.MultaDto;
import com.umg.serviciosInmobiliarios.dto.PagoDto;
import com.umg.serviciosInmobiliarios.dto.VerificarMultaDto;
import com.umg.serviciosInmobiliarios.entity.Beneficiado;
import com.umg.serviciosInmobiliarios.entity.Pago;
import com.umg.serviciosInmobiliarios.enums.MesName;
import com.umg.serviciosInmobiliarios.entity.Anio;
import com.umg.serviciosInmobiliarios.service.AnioService;
import com.umg.serviciosInmobiliarios.service.BeneficiadoService;
import com.umg.serviciosInmobiliarios.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("pago")
@CrossOrigin(origins = "https://umg-seguridad-servicios-fe.herokuapp.com/")
@RestController
public class PagoController {

    @Autowired
    PagoService pagoService;

    @Autowired
    BeneficiadoService beneficiadoService;

    @Autowired
    AnioService anioService;

    @PostMapping(path = "/realizarPagoServicio")
    public ResponseEntity<?> relizarPagoBeneficiado(@RequestBody ListPagoDto listPagoDto){


        Beneficiado beneficiado = null;
        MesName mesItem = null;
        Anio anioItem = null;
        List<Pago> pagos = new ArrayList<>();

        for (PagoDto pagoDto: listPagoDto.getPagoDtoList()) {
            Pago pagoItem = new Pago();

            if(pagoDto.getIdBeneficiario() != null){
                beneficiado =  beneficiadoService.getBeneficiado(pagoDto.getIdBeneficiario());
            }else
                return new ResponseEntity<>("Ingresa un valor correcto para el id del Beneficiado", HttpStatus.BAD_REQUEST);

            if(!pagoDto.getMes().isEmpty() && pagoDto.getMes() != null){
                mesItem = this.pagoService.verficarMesPorNombre(pagoDto.getMes());
            }else
                return new ResponseEntity<>("Ingresa el mes de pago", HttpStatus.BAD_REQUEST);

            if(pagoDto.getAnio() != null){
                anioItem = this.anioService.getAnioByName(pagoDto.getAnio());
            }else
                return new ResponseEntity<>("Ingresa un año correcto", HttpStatus.BAD_REQUEST);

            pagoItem.setCantidad(pagoDto.getCantidad());
            pagoItem.setFecha_pago(pagoDto.getFechaPago());
            pagoItem.setMes(mesItem);
            pagoItem.setAnio(anioItem);
            pagoItem.setCantidadMulta(pagoDto.getCantidadMulta());
            pagoItem.setMulta(pagoDto.isMulta());
            pagoItem.setBeneficiado(beneficiado);

            pagos.add(pagoItem);
        }

        boolean isCreate = this.pagoService.AsignarPago(pagos,beneficiado);
        if(isCreate)
            return new ResponseEntity<>("Se ha asignado el pago correctamente",HttpStatus.OK);
        else
            return new ResponseEntity<>("Ocurrió un error al realizar el pago",HttpStatus.BAD_REQUEST);

    }

    @GetMapping(path = "/mesesBeneficiado/{idBeneficiado}")
    public String listarMesesBeneficiado(@PathVariable Integer idBeneficiado){
        if(idBeneficiado == null){
            return "Ingresa un valor correcto";
        }
        Beneficiado beneficiado = beneficiadoService.getBeneficiado(idBeneficiado);
        this.pagoService.listarMesesyAniosInicioBeneficiado(beneficiado);

        return "hola mudno";
    }

    @GetMapping(path = "/list")
    public ResponseEntity<List<Pago>> listarPagos(){
        return new ResponseEntity<>(this.pagoService.listarPagos(),HttpStatus.OK);
    }

    @GetMapping(path = "/list/{idBeneficiado}")
    public ResponseEntity<?> listarPagosBeneficiado(@PathVariable Integer idBeneficiado){
        if(idBeneficiado == null){
            return new ResponseEntity<>("Ingresa un valor correcto", HttpStatus.BAD_REQUEST);
        }
        Beneficiado beneficiado = beneficiadoService.getBeneficiado(idBeneficiado);
        return new ResponseEntity<>(this.pagoService.listarPagosPorBeneficiado(beneficiado), HttpStatus.OK);
    }

    @PostMapping(path = "/verificarMulta/{idBeneficiado}")
    public ResponseEntity<?> verificarMultaBeneficiado(@PathVariable Integer idBeneficiado,
                                                       @Valid @RequestBody VerificarMultaDto verificarMultaDto,
                                                       BindingResult bindingResult){
        if(idBeneficiado == null){
            return new ResponseEntity<>("Ingresa un valor correcto", HttpStatus.BAD_REQUEST);
        }

        if(bindingResult.hasErrors())
            return new ResponseEntity<>("Ingresa bien los campos", HttpStatus.BAD_REQUEST);


        Beneficiado beneficiado = beneficiadoService.getBeneficiado(idBeneficiado);

        return new ResponseEntity<>(this.pagoService.tieneMultaBeneficiado(beneficiado,verificarMultaDto), HttpStatus.OK);
    }

}
