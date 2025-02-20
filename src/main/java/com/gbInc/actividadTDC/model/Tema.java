package com.gbInc.actividadTDC.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tema {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idTema;
	private String nombre;
	private String descripcion;

	public Tema() {
	}

	public Tema(Long idTema, String nombre, String descripcion) {
		this.idTema = idTema;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Long getIdTema() {
		return idTema;
	}

	public void setIdTema(Long idTema) {
		this.idTema = idTema;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
