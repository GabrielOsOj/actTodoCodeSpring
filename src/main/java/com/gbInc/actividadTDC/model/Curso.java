package com.gbInc.actividadTDC.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Curso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idCurso;
	private String nombre;
	private String modalidad;
	private LocalDate fechaFinalizacion;
	
	@OneToMany
	private List<Tema> listaDeTemas; 

	public Curso() {
	}

	public Curso(Long idCurso, String nombre, String modalidad, LocalDate fechaFinalizacion, List<Tema> listaDeTemas) {
		this.idCurso = idCurso;
		this.nombre = nombre;
		this.modalidad = modalidad;
		this.fechaFinalizacion = fechaFinalizacion;
		this.listaDeTemas = listaDeTemas;
	}

	public Long getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Long idCurso) {
		this.idCurso = idCurso;
	}
 
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public LocalDate getFechaFinalizacion() {
		return fechaFinalizacion;
	}

	public void setFechaFinalizacion(LocalDate fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}

	public List<Tema> getListaDeTemas() {
		return listaDeTemas;
	}

	public void setListaDeTemas(List<Tema> listaDeTemas) {
		this.listaDeTemas = listaDeTemas;
	}
	
	
}
