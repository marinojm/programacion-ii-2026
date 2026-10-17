package com.umg.reservas.reservasalones.repository;


import com.umg.reservas.reservasalones.entity.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalonRepository extends JpaRepository<Salon, Long> {
    //JpaRepository nos prove automaticamente métodos como save(), findAll(), findById(), deleteById()
}
