package com.gbInc.actividadTDC.controller;

import com.gbInc.actividadTDC.dto.DTOtema;
import com.gbInc.actividadTDC.service.ItemaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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

		this.temaSV.crearTema(tema);
		return new ResponseEntity<>(true, HttpStatus.CREATED);

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

}
