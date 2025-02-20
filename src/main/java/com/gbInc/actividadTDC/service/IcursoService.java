package com.gbInc.actividadTDC.service;

import com.gbInc.actividadTDC.model.Curso;
import java.util.List;


public interface IcursoService {

	public void crearCurso(Curso curso);
	public void crearVariosCursos(List<Curso> cursos);
	
}
