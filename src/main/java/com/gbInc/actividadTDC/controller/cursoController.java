package com.gbInc.actividadTDC.controller;

import com.gbInc.actividadTDC.service.IcursoService;
import com.gbInc.actividadTDC.model.Curso;
import com.gbInc.actividadTDC.model.Tema;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/curso")
public class cursoController {

	@Autowired
	private IcursoService cursoSV;

	@PostMapping("/crear")
	public ResponseEntity<Boolean> crearCurso(@RequestBody Curso curso) {

		Boolean creado = this.cursoSV.crearCurso(curso);

		if (creado) {

			return new ResponseEntity<>(true, HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/crearVarios")
	public ResponseEntity<Boolean> crearVariosCursos(@RequestBody List<Curso> cursos) {

		Boolean creado = this.cursoSV.crearVariosCursos(cursos);
		if (creado) {

			return new ResponseEntity<>(true, HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);

		}

	}

	@GetMapping("/obtenerTodosLosCursos")
	public ResponseEntity<List<Curso>> leerCursos() {

		List<Curso> cursos = this.cursoSV.obtenerTodosLosCursos();
		return new ResponseEntity<>(cursos, HttpStatus.ACCEPTED);
	};

	@GetMapping("/obtenerTemasCurso")
	public ResponseEntity<List<Tema>> obtenerTemasDeCurso(@RequestParam Long idCurso) {

		List<Tema> temas = this.cursoSV.obtenerTemasDeCurso(idCurso);

		if (temas == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<>(temas, HttpStatus.OK);
		}

	}

	@GetMapping("/buscarCursos")
	public ResponseEntity<List<Curso>> obtenerCursosPorBusqueda(@RequestParam String nombre) {

		List<Curso> cursos = this.cursoSV.obtenerCursosPorBusqueda(nombre);

		if (cursos.size() == 0) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<>(cursos, HttpStatus.OK);
		}

	}

	@PutMapping("/editar")
	public ResponseEntity<Boolean> editarCurso(@RequestBody Curso c) {

		Boolean editado = this.cursoSV.editarCurso(c);

		if (editado) {
			return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}

	}

}
