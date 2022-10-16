package com.umg.serviciosInmobiliarios.service;

import com.umg.serviciosInmobiliarios.entity.Beneficiado;
import com.umg.serviciosInmobiliarios.enums.MesName;
import com.umg.serviciosInmobiliarios.exceptions.ResourceBadRequest;
import com.umg.serviciosInmobiliarios.repository.AnioRepository;
import com.umg.serviciosInmobiliarios.entity.Anio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnioService {
    @Autowired
    AnioRepository anioRepository;

    public void crearAnio(Anio anio){
        this.anioRepository.save(anio);
    }

    public List<Anio> listarAnios(){
        return this.anioRepository.findAll();
    }

    public List<Anio> listarAniosBeneficiado(Beneficiado beneficiado){
        Date fechaInicioBeneficiado = beneficiado.getInicio();

        Integer anioInicioBeneficiado = (fechaInicioBeneficiado.getYear() + 1900);

        return this.anioRepository.findByAnioNameGreaterThanEqual(anioInicioBeneficiado);

        /*try {


        }catch (Exception e){
            throw new ResourceBadRequest("Ocurrió un error en el servidor");
        }*/
    }

    public Anio getAnio(Integer anio){
        return this.anioRepository.findById(anio)
                .orElseThrow(()->new ResourceBadRequest("Ingresa un año correcto"));
    }

    public Anio getAnioByName(Integer anio){
        return this.anioRepository.findByAnioName(anio)
                .orElseThrow(()->new ResourceBadRequest("Ingresa un año correcto"));
    }

    public List<MesName> listarMeses(){
        List<MesName> listaMeses = new ArrayList<>();

        listaMeses.addAll(Arrays.asList(MesName.values()));

        return listaMeses;
    }
}
