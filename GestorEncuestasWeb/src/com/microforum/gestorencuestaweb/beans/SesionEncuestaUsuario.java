package com.microforum.gestorencuestaweb.beans;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.microforum.gestorencuestaweb.entities.Encuesta;
import com.microforum.gestorencuestaweb.entities.Pregunta;

@ManagedBean
@SessionScoped
@RequestScoped
public class SesionEncuestaUsuario {
	private List<SelectItem> coleccionEncuesta;
	private List<SelectItem> coleccionPreguntasPorEncuesta;
	private String encuesta;
	private String textoPrueba;
	private String textoEnriquecidoPrueba;
	private String textoEnriquecido2Prueba;
	
	
	public SesionEncuestaUsuario(){
		coleccionEncuesta = new ArrayList<SelectItem>();
		coleccionEncuesta.add(new SelectItem("-----Encuestas----"));
		coleccionPreguntasPorEncuesta = new ArrayList<SelectItem>();
		coleccionPreguntasPorEncuesta.add(new SelectItem("-----Preguntas----"));
		//abrimos sesion en hibernate
		Configuration conf= new Configuration();
		SessionFactory sf=conf.configure().buildSessionFactory();
		Session session=sf.openSession();
		
		Query query=session.createQuery("from Encuesta");
		List<Encuesta> encuestas=query.list();
		for(Encuesta e:encuestas){
			String ref=e.getRef();
			String proposito=e.getProposito();
			SelectItem item= new SelectItem(ref, ref+":"+proposito);
			coleccionEncuesta.add(item);
		}
	}
	
	public void setPreguntas(ValueChangeEvent e){
		String ref=(String)e.getNewValue();
		Configuration conf= new Configuration();
		SessionFactory sf=conf.configure().buildSessionFactory();
		Session session=sf.openSession();
		Encuesta encuesta=(Encuesta)session.get(Encuesta.class, ref);
		Collection<Pregunta> preguntas=encuesta.getPreguntas();
		for(Pregunta p:preguntas){
			SelectItem item=new SelectItem(p.getRef(),p.getTexto());
			coleccionPreguntasPorEncuesta.add(item);
		}
	}
	
	public void selecionarEncuesta(ActionEvent e){
		System.out.println("seleccionando encuesta...");
	}
	
	public List<SelectItem> getColeccionEncuesta() {
		return coleccionEncuesta;
	}
	public void setColeccionEncuesta(List<SelectItem> coleccionEncuesta) {
		System.out.println("ColeccionEncuesta....");
		this.coleccionEncuesta = coleccionEncuesta;
	}
	public List<SelectItem> getColeccionPreguntasPorEncuesta() {
		return coleccionPreguntasPorEncuesta;
	}
	public void setColeccionPreguntasPorEncuesta(
			List<SelectItem> coleccionPreguntasPorEncuesta) {
		System.out.println("ColeccionPreguntasPorEncuesta....");
		this.coleccionPreguntasPorEncuesta = coleccionPreguntasPorEncuesta;
	}

	public String getTextoPrueba() {
		return textoPrueba;
		
	}

	public void setTextoPrueba(String textoPrueba) {
		System.out.println("Modificando tento prueba...");
		this.textoPrueba = textoPrueba;
		textoEnriquecidoPrueba="Enriqueciendo "+textoPrueba;
	}

	public String getTextoEnriquecidoPrueba() {
		return textoEnriquecidoPrueba;
	}

	public void setTextoEnriquecidoPrueba(String textoEnriquecidoPrueba) {
		System.out.println("TextoEnriquecidoPrueba...");
		this.textoEnriquecidoPrueba = textoEnriquecidoPrueba;
	}

	public String getEncuesta() {
		
		return encuesta;
	}

	public void setEncuesta(String encuesta) {
		System.out.println("selecionando encuesta");
		this.encuesta = encuesta;
	}

	public String getTextoEnriquecido2Prueba() {
		return textoEnriquecido2Prueba;
	}

	public void setTextoEnriquecido2Prueba(String textoEnriquecido2Prueba) {
		System.out.println("TextoEnriquecido2Prueba...");
		this.textoEnriquecido2Prueba = textoEnriquecido2Prueba;
	}
	
	

}
