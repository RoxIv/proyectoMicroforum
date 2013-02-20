package com.microforum.gestorencuestaweb.beans;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.microforum.gestorencuestaweb.entities.Pregunta;

@ManagedBean(eager=true)//modo eager: el bean se instancia al arrancar la aplicacion (no espera a que nadie le llame)
@ApplicationScoped
public class BancoPreguntas {
	private Map<String, String> mapaPreguntas;
	private List<String> arrayPreguntas=new ArrayList<>();
	private String nuevaPregunta;
	private int tipo;
	private List<SelectItem> preguntasItems;
	//private List<String> preguntaEncuesta;//este es un elemento de sesion, lo llevamos a otro bean
	
	public BancoPreguntas(){
		Configuration conf= new Configuration();
		SessionFactory sf=conf.configure().buildSessionFactory();
		Session session=sf.openSession();
		Query query=session.createQuery("from Pregunta");
		List<Pregunta> listadoPreguntas=query.list();
		preguntasItems= new ArrayList<SelectItem>();
		arrayPreguntas=new ArrayList();
		preguntasItems.add(new SelectItem("------PREGUNTAS-----"));
		for(int i=0; i<listadoPreguntas.size();i++){
			arrayPreguntas.add(listadoPreguntas.get(i).getTexto());
			String ref=listadoPreguntas.get(i).getRef();
			String texto=listadoPreguntas.get(i).getTexto();
			preguntasItems.add(new SelectItem(ref,texto));
		}
		session.close();
		
	}
	public void addPregunta(ActionEvent e){
		Configuration conf= new Configuration();
		SessionFactory sf=conf.configure().buildSessionFactory();
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		Pregunta p=new Pregunta();
		p.setTexto(nuevaPregunta);
		p.setTipo(tipo);
		//TODO hay que incluir las referencias al autor
		//p.setAutor(autor);
		session.save(p);
		tr.commit();
		session.close();
		SelectItem item=new SelectItem(p.getRef(),p.getTexto());
		preguntasItems.add(item);
		
		/*if(nuevaPregunta!=null)
			arrayPreguntas.add(nuevaPregunta);*/
	}
	public void deletePregunta(){
		//TODO
	}
	
	public List<SelectItem> getArrayItemPregunta(){
		return preguntasItems;
	}
	
	public Map<String, String> getMapaPreguntas() {
		return mapaPreguntas;
	}
	public void setMapaPreguntas(Map<String, String> mapaPreguntas) {
		this.mapaPreguntas = mapaPreguntas;
	}
	
	public List<String> getArrayPreguntas() {
		return arrayPreguntas;
	}
	public void setArrayPreguntas(List<String> arrayPreguntas) {
		this.arrayPreguntas = arrayPreguntas;
	}
	public String getNuevaPregunta() {
		return nuevaPregunta;
	}
	public void setNuevaPregunta(String nuevaPregunta) {
		this.nuevaPregunta = nuevaPregunta;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	
}
