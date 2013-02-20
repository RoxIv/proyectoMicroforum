package com.microforum.gestorencuestaweb.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.microforum.gestorencuestaweb.entities.Encuesta;
import com.microforum.gestorencuestaweb.entities.Pregunta;

@ManagedBean(eager=true)
@ApplicationScoped
public class BancoEncuestas {
	private List<Encuesta> encuestas=new ArrayList<>();
	private String nuevaEncuesta;
	private List<SelectItem> encuestaItems;
	private List<String> arrayEncuestas=new ArrayList<>();
	
	public BancoEncuestas(){
		Configuration conf= new Configuration();
		SessionFactory sf=conf.configure().buildSessionFactory();
		Session session=sf.openSession();
		Query query=session.createQuery("from Encuesta");
		encuestas=query.list();
		encuestaItems= new ArrayList<SelectItem>();
		arrayEncuestas=new ArrayList();
		encuestaItems.add(new SelectItem("------Encuestas-----"));
		for(int i=0; i<encuestas.size();i++){
			arrayEncuestas.add(encuestas.get(i).getProposito());
			String ref=encuestas.get(i).getRef();
			String texto=encuestas.get(i).getProposito();
			encuestaItems.add(new SelectItem(ref,texto));
		}
		session.close();
	}

	public List<Encuesta> getEncuestas() {
		return encuestas;
	}

	public void setEncuestas(List<Encuesta> encuestas) {
		this.encuestas = encuestas;
	}

	public String getNuevaEncuesta() {
		return nuevaEncuesta;
	}

	public void setNuevaEncuesta(String nuevaEncuesta) {
		this.nuevaEncuesta = nuevaEncuesta;
	}

	public List<SelectItem> getEncuestaItems() {
		return encuestaItems;
	}

	public void setEncuestaItems(List<SelectItem> encuestaItems) {
		this.encuestaItems = encuestaItems;
	}

	public List<String> getArrayEncuestas() {
		return arrayEncuestas;
	}

	public void setArrayEncuestas(List<String> arrayEncuestas) {
		this.arrayEncuestas = arrayEncuestas;
	}

	

}
