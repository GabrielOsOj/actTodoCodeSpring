package com.gbInc.actividadTDC.repository;

import com.gbInc.actividadTDC.model.Curso;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

@Repository
public interface IcursoRepository extends JpaRepository<Curso, Long> {
	
	@Query("SELECT c FROM Curso c where c.nombre LIKE %:nombre%")
	public List<Curso> findByNombreCurso(String nombre);
	
}
