package com.umg.vetcare.vetcareapi.repository;

import com.umg.vetcare.vetcareapi.entity.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    boolean existsByCodigo(String codigo);
    boolean existsByCodigoAndIdNot(String codigo, Long id);
}