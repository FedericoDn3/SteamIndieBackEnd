package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the amistad database table.
 * 
 */
@Entity
@NamedQuery(name="Amistad.findAll", query="SELECT a FROM Amistad a")
public class Amistad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(insertable=false, updatable=false)
	private int idamist;

	private int ami1;

	private int ami2;

	public Amistad() {
	}
	
	public int getIdamist() {
		return this.idamist;
	}

	public void setIdamist(int idamist) {
		this.idamist = idamist;
	}

	public int getAmi1() {
		return this.ami1;
	}

	public void setAmi1(int ami1) {
		this.ami1 = ami1;
	}

	public int getAmi2() {
		return this.ami2;
	}

	public void setAmi2(int ami2) {
		this.ami2 = ami2;
	}

}