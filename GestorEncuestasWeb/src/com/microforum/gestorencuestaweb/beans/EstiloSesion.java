package com.microforum.gestorencuestaweb.beans;

import java.awt.event.ActionEvent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class EstiloSesion {
	private String style="normalSize";

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
	
	public String setLargeSize(ActionEvent e){
		System.out.println("Large Size");
		style="largeSize";
		return "largeSizeTest";
	}
	public String setNormalSize(){
		System.out.println("Normal Size");
		style="normalSize";
		return "normalSizeTest";
	}
}
