package com.gbInc.actividadTDC.controller;

import com.gbInc.actividadTDC.service.IcursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/curso")
public class cursoController {

	@Autowired
	private IcursoService cursoSV;
	
	@PostMapping("/crear")
	public void crearCurso(@RequestBody Curso curso){
		this.cursoSV.crearCurso(curso);
	}
	
}
