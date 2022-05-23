package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the perfil database table.
 * 
 */
@Entity
@NamedQuery(name="Perfil.findAll", query="SELECT p FROM Perfil p")
public class Perfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idperf;

	public Perfil() {
	}
	
	public Perfil(int idperf) {
		this.idperf = idperf;
	}

	public int getIdperf() {
		return this.idperf;
	}

	public void setIdperf(int idperf) {
		this.idperf = idperf;
	}

}