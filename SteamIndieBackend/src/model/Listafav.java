package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the listafav database table.
 * 
 */
@Entity
@NamedQuery(name="Listafav.findAll", query="SELECT l FROM Listafav l")
public class Listafav implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(insertable=false, updatable=false)
	private int idLF;

	private byte compradolf;

	//uni-directional many-to-one association to Juego
	@ManyToOne
	@JoinColumn(name="idJuego")
	private Juego juego;

	//uni-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idUsu")
	private Usuario usuario;

	public Listafav() {
	}

	public int getIdLF() {
		return this.idLF;
	}

	public void setIdLF(int idLF) {
		this.idLF = idLF;
	}

	public byte getCompradolf() {
		return this.compradolf;
	}

	public void setCompradolf(byte compradolf) {
		this.compradolf = compradolf;
	}

	public Juego getJuego() {
		return this.juego;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}