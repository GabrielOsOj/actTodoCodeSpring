package com.gbInc.actividadTDC.repository;

import com.gbInc.actividadTDC.model.Tema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemaRepository extends JpaRepository<Tema, Long> {

}
