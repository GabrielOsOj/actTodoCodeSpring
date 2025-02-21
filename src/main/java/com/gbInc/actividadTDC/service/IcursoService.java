package com.gbInc.actividadTDC.service;

import com.gbInc.actividadTDC.model.Curso;
import com.gbInc.actividadTDC.model.Tema;

import java.util.List;


public interface IcursoService {

	public Boolean crearCurso(Curso curso);
	public Boolean crearVariosCursos(List<Curso> cursos);
	
	public List<Curso> obtenerTodosLosCursos();
	public List<Curso> obtenerCursosPorBusqueda(String nombre);
	
	public List<Tema> obtenerTemasDeCurso(Long idCurso);
	
	public Boolean editarCurso(Curso c);
	
}
