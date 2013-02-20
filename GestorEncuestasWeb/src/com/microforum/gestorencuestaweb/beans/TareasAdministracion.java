package com.microforum.gestorencuestaweb.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="tipoTarea")
@SessionScoped//el bean se mantiene mientras dure la sesion

public class TareasAdministracion {
	private  String tarea;
	public String[] adminTask={
		"alta pregunta", "alta encuesta"
	};
	
	public String getTarea() {
		return tarea;
	}
	public void setTarea(String tarea) {
		this.tarea = tarea;
	}
	public String[] getAdminTask() {
		return adminTask;
	}
	public void setAdminTask(String[] adminTask) {
		this.adminTask = adminTask;
	}
	

}
