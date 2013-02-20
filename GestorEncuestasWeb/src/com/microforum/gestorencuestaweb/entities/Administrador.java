package com.microforum.gestorencuestaweb.entities;



import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
@DiscriminatorValue(value="1")
public class Administrador extends Usuario{
	@Column(name="NSS")
	private String nSS;
	@ManyToMany

	
	public String getnSS() {
		return nSS;
	}

	public void setnSS(String nSS) {
		this.nSS = nSS;
	}


}
