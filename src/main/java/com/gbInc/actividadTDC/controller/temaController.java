package com.gbInc.actividadTDC.controller;

import com.gbInc.actividadTDC.dto.DTOtema;
import com.gbInc.actividadTDC.model.Tema;
import com.gbInc.actividadTDC.service.ItemaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tema")
public class temaController {

	@Autowired
	ItemaService temaSV;

	@PostMapping("/crearTema")
	public ResponseEntity<Boolean> crearTema(@RequestBody DTOtema tema) {

		Boolean creado = this.temaSV.crearTema(tema);

		if (creado) {

			return new ResponseEntity<>(true, HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/crearVariosTemas")
	public ResponseEntity<Boolean> crearVariosTemas(@RequestBody List<DTOtema> temas) {

		Boolean b = this.temaSV.crearVariosTemas(temas);

		if (b) {
			return new ResponseEntity<>(true, HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/editar")
	public ResponseEntity<Boolean> editarTema(@RequestBody Tema t) {

		Boolean editado = this.temaSV.editarTema(t);
		if (editado) {
			return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}

	}

}
