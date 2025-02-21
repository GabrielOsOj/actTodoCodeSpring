package com.gbInc.actividadTDC.service;

import com.gbInc.actividadTDC.dto.DTOtema;
import com.gbInc.actividadTDC.model.Tema;
import com.gbInc.actividadTDC.model.Curso;
import com.gbInc.actividadTDC.repository.IcursoRepository;
import com.gbInc.actividadTDC.repository.ItemaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class temaService implements ItemaService {

	@Autowired
	ItemaRepository temaRepo;

	@Autowired
	IcursoRepository cursoRepo;

	@Override
	public Boolean crearTema(DTOtema tema) {

		/*
		 * si viene con un id de un curso, se busca y si existe, se guarda, si viene sin
		 * id, se guarda sin id, y si el id del curso no existe, retorna false
		 */

		Tema temaDao = this.dtoToTema(tema);

		if (tema.getIdCursoPerteneciente() != null) {

			Curso curso = this.cursoRepo.findById(tema.getIdCursoPerteneciente()).orElse(null);

			if (curso == null) {
				return false;
			}

			this.temaRepo.save(temaDao);
			curso.getListaDeTemas().add(temaDao);
			this.cursoRepo.save(curso);
			return true;
		}

		this.temaRepo.save(temaDao);
		return true;

	}

	@Override
	public Boolean crearVariosTemas(List<DTOtema> temas) {

		for (DTOtema t : temas) {
			this.crearTema(t);
		}

		return true;
	}

	private Tema dtoToTema(DTOtema temaDto) {

		Tema t = new Tema();
		t.setIdTema(temaDto.getIdTema());
		t.setDescripcion(temaDto.getDescripcion());
		t.setNombre(temaDto.getNombre());

		return t;
	}

	@Override
	public Boolean editarTema(Tema t){
		
		if(this.temaRepo.findById(t.getIdTema()).isEmpty()){
			return false;
		}
		
		this.temaRepo.save(t);
		return true;
		
	}
}
