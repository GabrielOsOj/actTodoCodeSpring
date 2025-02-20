package com.gbInc.actividadTDC.service;

import com.gbInc.actividadTDC.model.Curso;
import com.gbInc.actividadTDC.repository.IcursoRepository;
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

}
