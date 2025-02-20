package com.gbInc.actividadTDC.dto;


public class DTOtema {
	
	private Long idTema;
	private String nombre;
	private String descripcion;
	
	private Long idCursoPerteneciente;

	public DTOtema() {
	}

	public DTOtema(Long idTema, String nombre, String descripcion, Long idCursoPerteneciente) {
		this.idTema = idTema;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.idCursoPerteneciente = idCursoPerteneciente;
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

	public Long getIdCursoPerteneciente() {
		return idCursoPerteneciente;
	}

	public void setIdCursoPerteneciente(Long idCursoPerteneciente) {
		this.idCursoPerteneciente = idCursoPerteneciente;
	}
	
	
	
}
