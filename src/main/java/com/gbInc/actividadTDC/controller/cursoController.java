package com.gbInc.actividadTDC.controller;

import com.gbInc.actividadTDC.service.IcursoService;
import com.gbInc.actividadTDC.model.Curso;
import com.gbInc.actividadTDC.model.Tema;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/curso")
public class cursoController {

	@Autowired
	private IcursoService cursoSV;
	
	@PostMapping("/crear")
	public ResponseEntity<Boolean> crearCurso(@RequestBody Curso curso){
	
		this.cursoSV.crearCurso(curso);
		return new ResponseEntity<>(true, HttpStatus.CREATED);
	
	}
	
	@PostMapping("/crearVarios")
	public ResponseEntity<Boolean> crearVariosCursos(
			@RequestBody List<Curso> cursos
	){
		
		this.cursoSV.crearVariosCursos(cursos);
		return new ResponseEntity<>(true,HttpStatus.CREATED);
	
	}
	
	@GetMapping("/obtenerTodosLosCursos")
	public ResponseEntity<List<Curso>> leerCursos(){
		
		List<Curso> cursos = this.cursoSV.obtenerTodosLosCursos();
		return new ResponseEntity<>(cursos,HttpStatus.ACCEPTED);
	};
	
	@GetMapping("/obtenerTemasCurso")
	public ResponseEntity<List<Tema>> obtenerTemasDeCurso
		(@RequestParam Long idCurso){
			
		List<Tema> temas = this.cursoSV.obtenerTemasDeCurso(idCurso);
		
		if(temas == null){
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<>(temas,HttpStatus.OK);
		}
		
	}
}
