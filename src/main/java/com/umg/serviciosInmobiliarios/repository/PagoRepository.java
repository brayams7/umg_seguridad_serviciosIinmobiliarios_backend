package com.umg.serviciosInmobiliarios.repository;

import com.umg.serviciosInmobiliarios.entity.Anio;
import com.umg.serviciosInmobiliarios.entity.Beneficiado;
import com.umg.serviciosInmobiliarios.entity.Pago;
import com.umg.serviciosInmobiliarios.enums.MesName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {

    boolean existsByBeneficiadoAndMesAndAnio(Beneficiado beneficiado, MesName mes, Anio anio);

    List<Pago> findByBeneficiado(Beneficiado beneficiado);
}
