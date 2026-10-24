package com.umg.reservas.reservasalones.repository;

import com.umg.reservas.reservasalones.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    // comprueba si ya hay una reserva confirmada para un salón en la misma fecha
    boolean existsBySalonIdAndFechaReservaAndEstadoNot(Long salonId, LocalDate fechaReserva, String estado);

    // filtro1 Buscar reservas por fecha específica
    List<Reserva> findByFechaReserva(LocalDate fechaReserva);

    // filtro2: Buscar reservas por estado (ej. CONFIRMADA, CANCELADA)
    List<Reserva> findByEstado(String estado);

    // indicador/resumen: suma total de ingresos generados por reservas confirmadas
    @Query("SELECT COALESCE(SUM(r.costoTotal), 0) FROM Reserva r WHERE r.estado = 'CONFIRMADA'")
    BigDecimal sumarTotalIngresos();
}
