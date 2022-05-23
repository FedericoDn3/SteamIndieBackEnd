package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cat database table.
 * 
 */
@Entity
@NamedQuery(name="Cat.findAll", query="SELECT c FROM Cat c")
public class Cat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idcat;

	private String nomCat;

	public Cat() {
	}

	public int getIdcat() {
		return this.idcat;
	}

	public void setIdcat(int idcat) {
		this.idcat = idcat;
	}

	public String getNomCat() {
		return this.nomCat;
	}

	public void setNomCat(String nomCat) {
		this.nomCat = nomCat;
	}

}