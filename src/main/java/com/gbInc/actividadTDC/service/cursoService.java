package com.gbInc.actividadTDC.service;

import com.gbInc.actividadTDC.model.Curso;
import com.gbInc.actividadTDC.model.Tema;
import com.gbInc.actividadTDC.repository.IcursoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class cursoService implements IcursoService {

	@Autowired
	IcursoRepository cursoRepo;

	@Override
	public void crearCurso(Curso curso) {

		this.cursoRepo.save(curso);

	}

	@Override
	public void crearVariosCursos(List<Curso> cursos) {
		
		for(Curso c : cursos){
			this.crearCurso(c);
		}
		
	}

	@Override
	public List<Curso> obtenerTodosLosCursos(){
		return this.cursoRepo.findAll();
	}

	@Override
	public List<Tema> obtenerTemasDeCurso(Long idCurso) {
		
		Curso c = this.cursoRepo.findById(idCurso).orElse(null);
		if(c==null){
			return null;
		}
		
		return c.getListaDeTemas();
	
	}
}
