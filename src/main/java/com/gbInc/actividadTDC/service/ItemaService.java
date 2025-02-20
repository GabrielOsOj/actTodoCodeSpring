package com.gbInc.actividadTDC.service;

import com.gbInc.actividadTDC.dto.DTOtema;
import com.gbInc.actividadTDC.model.Tema;
import java.util.List;

public interface ItemaService {

	public Boolean crearTema(DTOtema tema);
	
	public Boolean crearVariosTemas(List<DTOtema> temas);
	
	public Boolean editarTema(Tema tema);
	
}
