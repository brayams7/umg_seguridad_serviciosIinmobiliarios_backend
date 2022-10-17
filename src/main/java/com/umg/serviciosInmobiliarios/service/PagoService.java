package com.umg.serviciosInmobiliarios.service;

import com.umg.serviciosInmobiliarios.dto.MultaDto;
import com.umg.serviciosInmobiliarios.dto.PagoDto;
import com.umg.serviciosInmobiliarios.dto.VerificarMultaDto;
import com.umg.serviciosInmobiliarios.entity.Anio;
import com.umg.serviciosInmobiliarios.entity.Beneficiado;
import com.umg.serviciosInmobiliarios.entity.Pago;
import com.umg.serviciosInmobiliarios.entity.Proyecto;
import com.umg.serviciosInmobiliarios.enums.MesName;
import com.umg.serviciosInmobiliarios.exceptions.ResourceBadRequest;
import com.umg.serviciosInmobiliarios.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class PagoService {

    @Autowired
    PagoRepository pagoRepository;

    @Autowired
    AnioService anioService;

    private MesName verficarMes(String mes){
        try {
            Integer.parseInt(mes);

            switch (mes){
                case "1":
                    return MesName.enero;

                case "2":
                    return MesName.febrero;

                case "3":
                    return MesName.marzo;

                case "4":
                    return MesName.abril;

                case "5":
                    return MesName.mayo;

                case "6":
                    return MesName.junio;

                case "7":
                    return MesName.julio;

                case "8":
                    return MesName.agosto;

                case "9":
                    return MesName.septiembre;

                case "10":
                    return MesName.octubre;

                case "11":
                    return MesName.noviembre;

                case "12":
                    return MesName.diciembre;


                default:
                    throw new ResourceBadRequest("Ingresa un mes correcto");
            }
        }catch (Exception e){
            throw new ResourceBadRequest("Ingresa un mes correcto");
        }
    }

    public int verificarMesNumber(String mes){
        try {
            switch (mes.toLowerCase()){
                case "enero":
                    return 1;

                case "febrero":
                    return 2;

                case "marzo":
                    return 3;

                case "abril":
                    return 4;

                case "mayo":
                    return 5;

                case "junio":
                    return 6;

                case "julio":
                    return 7;

                case "agosto":
                    return 8;

                case "septiembre":
                    return 9;

                case "octubre":
                    return 10;

                case "noviembre":
                    return 11;

                case "diciembre":
                    return 12;


                default:
                    throw new ResourceBadRequest("Ingresa un mes correcto");
            }
        }catch (Exception e){
            throw new ResourceBadRequest("Ingresa un mes correcto");
        }
    }


    public MesName verficarMesPorNombre(String mes){
        try {
            switch (mes.toLowerCase()){
                case "enero":
                    return MesName.enero;

                case "febrero":
                    return MesName.febrero;

                case "marzo":
                    return MesName.marzo;

                case "abril":
                    return MesName.abril;

                case "mayo":
                    return MesName.mayo;

                case "junio":
                    return MesName.junio;

                case "julio":
                    return MesName.julio;

                case "agosto":
                    return MesName.agosto;

                case "septiembre":
                    return MesName.septiembre;

                case "octubre":
                    return MesName.octubre;

                case "noviembre":
                    return MesName.noviembre;

                case "diciembre":
                    return MesName.diciembre;


                default:
                    throw new ResourceBadRequest("Ingresa un mes correcto");
            }
        }catch (Exception e){
            throw new ResourceBadRequest("Ingresa un mes correcto");
        }
    }

    public List<String> listarMesesyAniosInicioBeneficiado(Beneficiado beneficiado){

        List<String> listaMesesBeneficiado = new ArrayList<>();

        String[] meses = new String[12];
        meses[0] = "enero";
        meses[1] = "febrero";
        meses[2] = "marzo";
        meses[3] = "abril";
        meses[4] = "mayo";
        meses[5] = "junio";
        meses[6] = "julio";
        meses[7] = "agosto";
        meses[8] = "septiembre";
        meses[9] = "octubre";
        meses[10] = "noviembre";
        meses[11] = "diciembre";

        try {
            Calendar calendar= Calendar.getInstance();

            Date fechaInicioBeneficiado = beneficiado.getInicio();

            Integer mesBeneficiado = fechaInicioBeneficiado.getMonth() + 1;
            System.out.println(mesBeneficiado +"mes beneficiado");
            MesName mesInicioMapedo = this.verficarMes(String.valueOf(mesBeneficiado));
            System.out.println("mes inicio mapeado"+mesInicioMapedo);
            Integer mesActual = calendar.get(Calendar.MONTH) + 1;
            MesName mesActualMapeado = this.verficarMes(String.valueOf(mesActual));



            for (int i = 0; i < meses.length; i++) {

                if(mesInicioMapedo.name().equals(meses[i])){
                    for (int j = i; j < meses.length; j++) {
                        listaMesesBeneficiado.add(meses[i]);

                        if(meses[j].equals(mesActualMapeado.name()))
                            break;
                    }
                }

            }


            return listaMesesBeneficiado;
        }catch (Exception e){
            throw new ResourceBadRequest("Ocurrió un error en el servidor");
        }
    }

    @Transactional
    public boolean AsignarPago(List<Pago> pagoDtoList, Beneficiado beneficiado){
        try {

            this.pagoRepository.saveAll(pagoDtoList);
            return true;
        }catch (Exception e){
            throw new ResourceBadRequest("Ocurrió un error al realizar el pago");
        }

    }

    public List<Pago> listarPagos(){
        return this.pagoRepository.findAll();
    }

    public List<Pago> listarPagosPorBeneficiado(Beneficiado beneficiado){
        return this.pagoRepository.findByBeneficiado(beneficiado);
    }

    public boolean verificarPagoPorAnioMesDeBeneficiado(Beneficiado beneficiado, MesName mesName, Anio anio){
        return this.pagoRepository.existsByBeneficiadoAndMesAndAnio(beneficiado,mesName,anio);
    }

    public MultaDto tieneMultaBeneficiado(Beneficiado beneficiado, VerificarMultaDto verificarMultaDto){
        final float mora = 25;
        boolean tieneMulta = false;

        Proyecto proyecto = beneficiado.getProyecto();
        Date fechaMora = proyecto.getFechaMora();
        Date fechaPago = verificarMultaDto.getFecha_pago();
        Anio anio = this.anioService.getAnioByName(verificarMultaDto.getAnio());
        MesName mesName = null;

        for (MesName m:MesName.values()) {
            if(m.name().equals(verificarMultaDto.getMes())){
                mesName = m;
            }
        }
        if(mesName == null){
            throw new ResourceBadRequest("Ingresa un mes valor correcto");
        }

        boolean verificarSiExistePago = this.verificarPagoPorAnioMesDeBeneficiado(beneficiado,
                mesName, anio);

        if(verificarSiExistePago){
            throw new ResourceBadRequest("El pago del mes y año ya está registrado");
        }

        if(fechaPago.compareTo(fechaMora) > 0)
            tieneMulta = true;


        int mesAPagar = this.verificarMesNumber(mesName.name());
        int mesFechaMora = fechaMora.getMonth() + 1;
        int anioMora = fechaMora.getYear() + 1900;



        if((mesAPagar < mesFechaMora) || (anio.getAnioName() < anioMora)){

            tieneMulta = true;
        }


        MultaDto multaDto = new MultaDto();

        multaDto.setMulta(tieneMulta);

        if(tieneMulta){
            multaDto.setCantidadMulta(mora);
        }else{

            multaDto.setCantidadMulta(0);
        }

        return multaDto;
    }
}
