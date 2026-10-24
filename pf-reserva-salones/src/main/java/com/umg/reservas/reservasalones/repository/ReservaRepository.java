package com.umg.reservas.reservasalones.repository;

import com.umg.reservas.reservasalones.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    boolean existsBySalonIdAndFechaReserva(Long salonId, LocalDate fechaReserva);
}
