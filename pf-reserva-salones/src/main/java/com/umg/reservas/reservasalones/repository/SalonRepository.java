package com.umg.reservas.reservasalones.repository;


import com.umg.reservas.reservasalones.entity.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalonRepository extends JpaRepository<Salon, Long> {

    // filtro3:buscar salones activos con capacidad mayor o igual a un minimo
    List<Salon> findByActivoTrueAndCapacidadGreaterThanEqual(Integer capacidadMinima);

}
