package com.umg.serviciosInmobiliarios.service;

import com.umg.serviciosInmobiliarios.dto.PaginationDto;
import com.umg.serviciosInmobiliarios.dto.ProyectoDto;
import com.umg.serviciosInmobiliarios.entity.Proyecto;
import com.umg.serviciosInmobiliarios.exceptions.ResourceBadRequest;
import com.umg.serviciosInmobiliarios.repository.ProyectoRepository;
import com.umg.serviciosInmobiliarios.security.entity.Usuario;
import com.umg.serviciosInmobiliarios.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProyectoService {

    @Autowired
    ProyectoRepository proyectoRepository;

    @Autowired
    UserService userService;

    public boolean crearProyecto(ProyectoDto proyectoDto, Integer idUsuario){
        Usuario user = this.userService.getById(idUsuario);

        Proyecto proyecto = new Proyecto(proyectoDto.getNombre(),
                proyectoDto.getCantidadBeneficiados(),
                proyectoDto.getFechaMora(),user);
        this.proyectoRepository.save(proyecto);
        return true;
    }

    public Proyecto editarProyecto(ProyectoDto proyectoDto, Integer idProyecto){
        Optional<Proyecto> proyecto = this.proyectoRepository.findById(idProyecto);

        return proyecto.map(p -> {
            p.setNombre(proyectoDto.getNombre());
            p.setCantidadBeneficiados(proyectoDto.getCantidadBeneficiados());
            p.setFechaMora(proyectoDto.getFechaMora());
            return this.proyectoRepository.save(p);
        })
                .orElseThrow(()-> new ResourceBadRequest("No existe el id del Proyecto"));
    }

    public Proyecto getProyectoById(Integer id){
        return this.proyectoRepository.findById(id)
                .orElseThrow(()->new ResourceBadRequest("No existe el id del proyecto"));
    }

    public PaginationDto listarProyectoUsuario(int pageCurrent, int size, Integer idUsuario){
        Pageable pageable = PageRequest.of(pageCurrent, size);

        Usuario usuario = this.userService.getById(idUsuario);

        Page<Proyecto> pagination = this.proyectoRepository.findByUsuario(usuario,pageable);
        List<Proyecto> listProjects = pagination.getContent();

        List<ProyectoDto> proyectoDtoList = listProjects.stream().map(
                proyecto -> {
                    ProyectoDto p = new ProyectoDto();
                    p.setId(proyecto.getId());
                    p.setNombre(proyecto.getNombre());
                    p.setCantidadBeneficiados(proyecto.getCantidadBeneficiados());
                    p.setFechaMora(proyecto.getFechaMora());
                    return p;
                }
        ).collect(Collectors.toList());

        PaginationDto paginationDto=new PaginationDto();
        paginationDto.setContent(proyectoDtoList);
        paginationDto.setPageCurrent(pagination.getNumber());
        paginationDto.setSize(pagination.getSize());
        paginationDto.setTotalPages(pagination.getTotalPages());
        paginationDto.setLast(pagination.isLast());
        paginationDto.setTotalElements(pagination.getTotalElements());

        return paginationDto;
    }

    public List<ProyectoDto> listarProyectos(){


        return this.proyectoRepository.findAll()
                .stream().map(i->{
                    ProyectoDto proyectoDto=new ProyectoDto();
                    proyectoDto.setNombre(i.getNombre());
                    proyectoDto.setFechaMora(i.getFechaMora());
                    proyectoDto.setId(i.getId());
                    proyectoDto.setCantidadBeneficiados(i.getCantidadBeneficiados());
                    return proyectoDto;
                }).collect(Collectors.toList());
    }
}
