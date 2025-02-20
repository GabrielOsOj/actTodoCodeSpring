package com.gbInc.actividadTDC.controller;

import com.gbInc.actividadTDC.service.IcursoService;
import com.gbInc.actividadTDC.model.Curso;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


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
	
}
