package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the estado database table.
 * 
 */
@Entity
@NamedQuery(name="Estado.findAll", query="SELECT e FROM Estado e")
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(insertable=false, updatable=false)
	private int idestado;

	private String comestado;

	@Temporal(TemporalType.TIMESTAMP)
	private Date festado;

	@Lob
	private byte[] imgestado;

	//uni-directional many-to-one association to Perfil
	@ManyToOne
	@JoinColumn(name="idperf")
	private Perfil perfil;

	public Estado() {
	}

	public Estado(String comestado, Date festado, byte[] imgestado, Perfil perfil) {
		this.comestado = comestado;
		this.festado = festado;
		this.imgestado = imgestado;
		this.perfil = perfil;
	}

	public Estado(int idestado) {
		this.idestado = idestado;
	}

	public int getIdestado() {
		return this.idestado;
	}

	public void setIdestado(int idestado) {
		this.idestado = idestado;
	}

	public String getComestado() {
		return this.comestado;
	}

	public void setComestado(String comestado) {
		this.comestado = comestado;
	}

	public Date getFestado() {
		return this.festado;
	}

	public void setFestado(Date festado) {
		this.festado = festado;
	}

	public byte[] getImgestado() {
		return this.imgestado;
	}

	public void setImgestado(byte[] imgestado) {
		this.imgestado = imgestado;
	}

	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

}