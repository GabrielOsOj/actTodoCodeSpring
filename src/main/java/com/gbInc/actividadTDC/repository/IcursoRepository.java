package com.gbInc.actividadTDC.repository;

import com.gbInc.actividadTDC.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IcursoRepository extends JpaRepository<Curso, Long> {
	
	
	
}
