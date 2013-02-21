package com.microforum.gestorencuestaweb.beans;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import com.microforum.gestorencuestaweb.entities.Administrador;
import com.microforum.gestorencuestaweb.entities.Encuesta;
import com.microforum.gestorencuestaweb.entities.EventoEncuesta;
import com.microforum.gestorencuestaweb.entities.Pregunta;
import com.microforum.gestorencuestaweb.entities.RegistroRespuestaEncuesta;
import com.microforum.gestorencuestaweb.entities.Usuario;
import com.microforum.gestorencuestaweb.entities.UsuarioRegistrado;


@ManagedBean
@SessionScoped
public class EncuestaSesion {
	
	private List<String> preguntasEncuesta=new ArrayList<String>();
	private List<Pregunta> preguntasaContestar = new ArrayList<Pregunta>();
	private List<RegistroEncuesta> registrosEncuesta=
			new ArrayList<RegistroEncuesta>();
	private String nuevaPregunta="";
	private String visibilidadDetalle="display: none;";
	private String proposito;
	private String nombre;
	private int valoracion;
	private Encuesta encuesta;
	private EventoEncuesta evento= new EventoEncuesta();
	private List<RegistroRespuestaEncuesta> respuestas = new ArrayList<RegistroRespuestaEncuesta>();
	
	private List<String> getRefList(List<RegistroEncuesta> regList){
		List<String> refList=new ArrayList<String>();
		for(RegistroEncuesta re:regList){
			refList.add(re.getRef());
		}
		return refList;
	}
	public String registrarEncuesta(){
		FacesContext fContext=FacesContext.getCurrentInstance();
		ExternalContext ec=fContext.getExternalContext();
		HttpServletRequest request=(HttpServletRequest) ec.getRequest();
		HttpSession session=request.getSession();
		Object obj=session.getAttribute("userBean");
		if(obj==null){
			session.invalidate();
			return "/index.jsp";
		}else{
			UsuarioAutenticado authUser=(UsuarioAutenticado)obj;
			Administrador user=(Administrador) authUser.getUser();
			Configuration conf=new Configuration();
			SessionFactory sf=conf.configure().buildSessionFactory();
			Session hSession=sf.openSession();
			Transaction tr=hSession.beginTransaction();
			Query query=hSession.createQuery(
					"from Pregunta where ref in (:preguntasRef)");
			List<String> refList=getRefList(registrosEncuesta);
			query.setParameterList("preguntasRef", refList);
			List<Pregunta> preguntas=query.list();
			hSession.merge(user);
			Encuesta encuesta=new Encuesta();
			encuesta.setAutor(user);
			encuesta.setProposito(proposito);
			for(Pregunta p:preguntas){
				encuesta.getPreguntas().add(p);
			}
			hSession.save(encuesta);
			tr.commit();
			hSession.close();
			return "/encuestas/administracion/administracionIndex";
		}
		
	}
	
	public void validarNombre(FacesContext context,
			UIComponent componentToValidate,
			Object value)throws ValidatorException{
		String nombre=(String)value;
		if(!nombre.startsWith("Encuesta")){
			FacesMessage msg=
					new FacesMessage("El nombre debe empezar por Encuesta");
			throw new ValidatorException(msg);
		}
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		if(!nombre.startsWith("Encuesta")){
			FacesMessage fc=new FacesMessage("El nombre debe ser Encuesta<X>");
			FacesContext ctx=FacesContext.getCurrentInstance();
			ctx.addMessage("nombreE",fc);
		}else
			this.nombre = nombre;
	}
	public int getValoracion() {
		return valoracion;
	}
	public void setValoracion(int valoracion) {
		if(valoracion<0 || valoracion>10){
			FacesMessage fc=new FacesMessage("La valoracion debe estar entre 0 y 10");
			FacesContext ctx=FacesContext.getCurrentInstance();
			ctx.addMessage("importanciaE",fc);
		}
		this.valoracion = valoracion;
	}
	public String getProposito() {
		return proposito;
	}
	public void setProposito(String proposito) {
		this.proposito = proposito;
	}
	public String iniciarEncuesta(){
		/*FacesMessage fc=new FacesMessage("ERROR");
		FacesContext ctx=FacesContext.getCurrentInstance();
		ctx.addMessage(null,fc);
		return null;*/
		return "DetalleEncuesta";
	}
	public String terminarEncuesta(){
		//agregar control de navegacion
		return "resumenEncuesta";
	}
	public String realizarEncuesta(){
		return "inicioRealizacionEncuesta";
	}
	
	public void salvarEncuesta(){
		Configuration conf=new Configuration();
		SessionFactory sf=conf.configure().buildSessionFactory();
		Session session=sf.openSession();
		Query query=session.createQuery("from Pregunta where ref in (:preguntasRef)");
		
	}
	public void eliminarPregunta(ActionEvent e){
		UIComponent component=e.getComponent();
		component= component.findComponent("preguntaRef");
		if(component!=null){
			if(component instanceof UIParameter){
				UIParameter parameter=(UIParameter)component;
				RegistroEncuesta reg=(RegistroEncuesta) parameter.getValue();
				registrosEncuesta.remove(reg);
				if(registrosEncuesta.isEmpty())
					visibilidadDetalle="display: none;";
			}
		}
	}
	
	public String getVisibilidadDetalle() {
		return visibilidadDetalle;
	}

	public void setVisibilidadDetalle(String visibilidadDetalle) {
		this.visibilidadDetalle = visibilidadDetalle;
	}

	public List<RegistroEncuesta> getRegistrosEncuesta() {
		return registrosEncuesta;
	}

	public String getNuevaPregunta() {
		return nuevaPregunta;
	}

	public void setNuevaPregunta(String nuevaPregunta) {
		this.nuevaPregunta = nuevaPregunta;
	}

	public List<String> getPreguntasEncuesta() {
		return preguntasEncuesta;
	}

	public void setPreguntasEncuesta(List<String> preguntasEncuesta) {
		this.preguntasEncuesta = preguntasEncuesta;
	}
	
	public void addPregunta(ActionEvent e){
		System.out.println("ActionEvent");
	}
	
	
	public EventoEncuesta getEvento() {
		return evento;
	}
	public void setEvento(EventoEncuesta evento) {
		this.evento = evento;
	}
	public Encuesta getEncuesta() {
		return encuesta;
	}
	public void setEncuesta(Encuesta encuesta) {
		this.encuesta = encuesta;
	}
	
	public List<Pregunta> getPreguntasaContestar() {
		return preguntasaContestar;
	}
	public void setPreguntasaContestar(List<Pregunta> preguntasaContestar) {
		this.preguntasaContestar = preguntasaContestar;
	}
	
	public List<RegistroRespuestaEncuesta> getRespuestas() {
		return respuestas;
	}
	public void setRespuestas(List<RegistroRespuestaEncuesta> respuestas) {
		this.respuestas = respuestas;
	}
	public void selectPregunta(ValueChangeEvent e){
		System.out.println(e.getNewValue());
		System.out.println("ValueChangeEvent");
		String ref=(String) e.getNewValue();
		Configuration conf=new Configuration();
		SessionFactory sf=conf.configure().buildSessionFactory();
		Session session=sf.openSession();
		Pregunta p=(Pregunta) session.get(Pregunta.class,ref);
		if(p!=null){
			visibilidadDetalle="display:inline-block;";
			RegistroEncuesta re=new RegistroEncuesta();
			re.setRef(p.getRef());
			re.setTexto(p.getTexto());
			re.setTipo(re.getTipo());
			registrosEncuesta.add(re);
		}
		session.close();
		//preguntasEncuesta.add((String) e.getNewValue());
	}
	
	public void selectEncuesta(ValueChangeEvent e){
		//System.out.println(e.getNewValue());
		System.out.println("Selecionando encuesta");
		String ref=(String) e.getNewValue();
		Configuration conf=new Configuration();
		SessionFactory sf=conf.configure().buildSessionFactory();
		Session session=sf.openSession();
		encuesta=(Encuesta)session.get(Encuesta.class, ref);
		Collection<Pregunta>preguntasSel=encuesta.getPreguntas();
		for(Pregunta p:preguntasSel){
			preguntasaContestar.add(p);
		}
		
		evento.setEncuesta(encuesta);
		//obtener usuario encuestado
		UIComponent component=e.getComponent();
		component= component.findComponent("encuestado");
		if(component!=null){
			if(component instanceof UIParameter){
				UIParameter parameter=(UIParameter)component;
				UsuarioAutenticado usu=(UsuarioAutenticado) parameter.getValue();
				UsuarioRegistrado encuestado =(UsuarioRegistrado) usu.getUser();
				evento.setEncuestado(encuestado);
			}
		}
		session.close();
	}
	public String contestarEncuesta(){
		return "encuestaCompletada";
		
	}

}
