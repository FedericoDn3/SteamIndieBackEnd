package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the oferta database table.
 * 
 */
@Entity
@NamedQuery(name="Oferta.findAll", query="SELECT o FROM Oferta o")
public class Oferta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(insertable=false, updatable=false)
	private int idoferta;

	private int activa;

	//@Temporal(TemporalType.TIMESTAMP)
	private String ffinof;

	//@Temporal(TemporalType.TIMESTAMP)
	private String finiof;

	private String nominstof;

	private int porcentof;

	public Oferta() {
	}
	
	public Oferta(int idoferta) {
		this.idoferta = idoferta;
	}
	
	public Oferta(int activa, String ffinof, String finiof, String nominstof, int porcentof) {
		this.activa = activa;
		this.ffinof = ffinof;
		this.finiof = finiof;
		this.nominstof = nominstof;
		this.porcentof = porcentof;
	}
	
	public int getIdoferta() {
		return this.idoferta;
	}

	public void setIdoferta(int idoferta) {
		this.idoferta = idoferta;
	}

	public int getActiva() {
		return this.activa;
	}

	public void setActiva(int activa) {
		this.activa = activa;
	}

	public String getFfinof() {
		return this.ffinof;
	}

	public void setFfinof(String ffinof) {
		this.ffinof = ffinof;
	}

	public String getFiniof() {
		return this.finiof;
	}

	public void setFiniof(String finiof) {
		this.finiof = finiof;
	}

	public String getNominstof() {
		return this.nominstof;
	}

	public void setNominstof(String nominstof) {
		this.nominstof = nominstof;
	}

	public int getPorcentof() {
		return this.porcentof;
	}

	public void setPorcentof(int porcentof) {
		this.porcentof = porcentof;
	}

}