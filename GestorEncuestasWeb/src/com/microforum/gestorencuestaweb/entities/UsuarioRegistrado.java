package com.microforum.gestorencuestaweb.entities;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@DiscriminatorValue(value="2")
public class UsuarioRegistrado extends Usuario {

	@Column(name= "FECHA_REGISTRO")
	@Temporal(TemporalType.DATE)
	private Date fechaRegistro;
	
	@OneToMany(mappedBy="encuestado")
	private Collection<EventoEncuesta> encuestas= new ArrayList<EventoEncuesta>();

	
	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Collection<EventoEncuesta> getEncuestas() {
		return encuestas;
	}

	public void setEncuestas(Collection<EventoEncuesta> encuestas) {
		this.encuestas = encuestas;
	}

	
	
}
