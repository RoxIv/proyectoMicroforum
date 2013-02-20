package com.microforum.gestorencuestaweb.entities;



import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable //entidad embebida, no necesita tener identificador
public class DatosDomicilio {

	public static final int CALLE=1;
	public static final int AVENIDA=2;
	public static final int GLORIETA=3;
	public static final int PLAZA=4;
	public static final int BOULEVARD=5;
	
	@Column(name="NOMBRE_VIA")
	private String nombreVia;
	@Column(name="TIPO_VIA")
	private int tipoVia;
	@Column(name="NUMERO")
	private int numero;
	@Column(name="PISO")
	private int piso;
	@Column(name="COD_POSTAL")
	private int codPostal;
	
	public String getNombreVia() {
		return nombreVia;
	}
	public void setNombreVia(String nombreVia) {
		this.nombreVia = nombreVia;
	}
	public int getTipoVia() {
		return tipoVia;
	}
	public void setTipoVia(int tipoVia) {
		this.tipoVia = tipoVia;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getPiso() {
		return piso;
	}
	public void setPiso(int piso) {
		this.piso = piso;
	}
	public int getCodPostal() {
		return codPostal;
	}
	public void setCodPostal(int codPostal) {
		this.codPostal = codPostal;
	}
	
	public String toString(){
		StringBuilder builder=new StringBuilder();
		switch(tipoVia){
		case DatosDomicilio.CALLE:
			builder.append("C\\");
			break;
		case DatosDomicilio.AVENIDA:
			builder.append("Avd.");
			break;
		case DatosDomicilio.BOULEVARD:
			builder.append("Bl.");
			break;
		case DatosDomicilio.GLORIETA:
			builder.append("Glta.");
			break;
		case DatosDomicilio.PLAZA:
			builder.append("Pza.");
			break;
		}
		return builder.append(nombreVia).toString();
	}
	
	public String getDomicilioCompleto(){
		return toString();
	}
	
}