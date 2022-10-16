package com.umg.serviciosInmobiliarios.service;

import com.umg.serviciosInmobiliarios.dto.BeneficiadoDto;
import com.umg.serviciosInmobiliarios.entity.Beneficiado;
import com.umg.serviciosInmobiliarios.entity.Proyecto;
import com.umg.serviciosInmobiliarios.exceptions.ResourceBadRequest;
import com.umg.serviciosInmobiliarios.exceptions.ResourceNotFoundException;
import com.umg.serviciosInmobiliarios.repository.BeneficiadoRepository;
import com.umg.serviciosInmobiliarios.security.entity.Usuario;
import com.umg.serviciosInmobiliarios.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class BeneficiadoService {

    @Autowired
    BeneficiadoRepository beneficiadoRepository;

    @Autowired
    ProyectoService proyectoService;

    @Autowired
    UserService userService;

    @Transactional
    public String asignarBeneficiadoAProyecto(Usuario usuario, Proyecto proyecto, Beneficiado beneficiado){
        try {

            Proyecto newProyecto = this.proyectoService.proyectoRepository.save(proyecto);

            Usuario newUsuario = this.userService.createUser(usuario);


            Beneficiado newBeneficiado = new Beneficiado(beneficiado.getDireccion(),
                    beneficiado.getInicio(),
                    beneficiado.getFin(),
                    beneficiado.getUltimoPago(),
                    newProyecto, newUsuario);
            this.beneficiadoRepository.save(newBeneficiado);
            return "El beneficiado fué creado correctamente";
        }catch (NullPointerException e){
                return "Ingrese correctamente los datos";
        }catch (Exception e){
            return "Ocurrió un error al crear el usuario";
        }
    }

    @Transactional
    public boolean asignarBeneficiadoAProyecto1(Beneficiado beneficiado){
        try {

            this.beneficiadoRepository.save(beneficiado);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Integer cantidadBeneficiadosProyecto(Proyecto proyecto){
        return this.beneficiadoRepository.countByProyecto(proyecto);
    }

    public Beneficiado getBeneficiado(Integer idBeneficiado){
        return this.beneficiadoRepository.findById(idBeneficiado)
                .orElseThrow(()->new ResourceNotFoundException("No existe el beneficiado"));
    }

    public List<Beneficiado> listarBeneficiadosProyecto(Integer idProyecto){
        Proyecto proyecto = this.proyectoService.getProyectoById(idProyecto);
        return this.beneficiadoRepository.findByProyecto(proyecto);
    }

    public String editarBeneficiado(Beneficiado beneficiadoDto, Integer idBeneficiado){
        Beneficiado beneficiado = this.getBeneficiado(idBeneficiado);
        beneficiado.setDireccion(beneficiadoDto.getDireccion());
        beneficiado.setInicio(beneficiadoDto.getInicio());
        beneficiado.setFin(beneficiadoDto.getFin());
        beneficiado.setUltimoPago(beneficiadoDto.getUltimoPago());
        beneficiado.setProyecto(beneficiadoDto.getProyecto());
        beneficiado.setUsuario(beneficiadoDto.getUsuario());

        this.beneficiadoRepository.save(beneficiado);
        return "Se ha actualizado correctamente";
    }

}
