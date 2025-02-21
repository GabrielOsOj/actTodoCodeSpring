package com.gbInc.actividadTDC.service;

import com.gbInc.actividadTDC.model.Curso;
import com.gbInc.actividadTDC.model.Tema;
import com.gbInc.actividadTDC.repository.IcursoRepository;
import com.gbInc.actividadTDC.repository.ItemaRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class cursoService implements IcursoService {

	@Autowired
	IcursoRepository cursoRepo;

	@Autowired
	ItemaRepository temaRepo;

	@Override
	public Boolean crearCurso(Curso curso) {

		if (this.controlDeTemas(curso.getListaDeTemas())) {
			this.cursoRepo.save(curso);
			return true;
		}

		return false;
	}

	@Override
	public Boolean crearVariosCursos(List<Curso> cursos) {

		for (Curso c : cursos) {
			if (!this.crearCurso(c)) {
				return false;
			}
			;
		}
		return true;
	}

	@Override
	public List<Curso> obtenerTodosLosCursos() {
		return this.cursoRepo.findAll();
	}

	@Override
	public List<Tema> obtenerTemasDeCurso(Long idCurso) {

		Curso c = this.cursoRepo.findById(idCurso).orElse(null);
		if (c == null) {
			return null;
		}

		return c.getListaDeTemas();

	}

	@Override
	public List<Curso> obtenerCursosPorBusqueda(String nombre) {

		return this.cursoRepo.findByNombreCurso(nombre);

	};

	@Override
	public Boolean editarCurso(Curso c) {

		if (this.cursoRepo.findById(c.getIdCurso()).isEmpty()) {
			return false;
		}

		this.cursoRepo.save(c);
		return true;
	}

	/***
	 * Verifica que los temas existan en la base de datos, sino los guarda
	 * @param temas
	 * @return true si todo se guardo correctamente, false si hubo algun error
	 */
	private Boolean controlDeTemas(List<Tema> temas) {

		/*
		 * si tiene id, pero el nombre y descripcion son null, es un tema que deberia
		 * existir en la base de datos, por lo tanto se verifica que exista
		 */

		/*
		 * Si hay algun tema que se necesite guarda, se almacena en este array, es para
		 * que se guarden solo si todos los temas de la lista son correctos. (sino se
		 * guardarian algunos si y otros no, si hay un error, se corrige y se reenvia la
		 * peticion de nuevo va a existir un error de tema repetido)
		 */
		List<Tema> temasAguardar = new ArrayList<>();

		for (Tema t : temas) {

			if (t.getIdTema() != null && t.getNombre() == null && t.getDescripcion() == null) {

				if (!this.temaRepo.existsById(t.getIdTema())) {
					return false;
				}
			}

			if (t.getIdTema() == null) {
				temasAguardar.add(t);
			}

		}

		/*si todos los temas de la lista son validos, se guardan los necesarios*/
		
		for(Tema t : temasAguardar){
			
			temaRepo.save(t);
			
		}
		
		return true;
	}

}
