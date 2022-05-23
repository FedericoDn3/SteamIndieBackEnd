package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tag database table.
 * 
 */
@Entity
@NamedQuery(name="Tag.findAll", query="SELECT t FROM Tag t")
public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idtag;

	private String nomTag;

	public Tag() {
	}

	public int getIdtag() {
		return this.idtag;
	}

	public void setIdtag(int idtag) {
		this.idtag = idtag;
	}

	public String getNomTag() {
		return this.nomTag;
	}

	public void setNomTag(String nomTag) {
		this.nomTag = nomTag;
	}

}