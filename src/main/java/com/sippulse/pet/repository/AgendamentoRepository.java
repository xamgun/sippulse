package com.sippulse.pet.repository;

import com.sippulse.pet.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.*;


/**
 * Spring Data  repository for the Agendamento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

}
