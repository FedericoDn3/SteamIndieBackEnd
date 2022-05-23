package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the publicacion database table.
 * 
 */
@Entity
@NamedQuery(name="Publicacion.findAll", query="SELECT p FROM Publicacion p")
public class Publicacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPub;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fpub;

	private byte isbanpub;

	//uni-directional one-to-one association to Juego
	@OneToOne
	@JoinColumn(name="idPub")
	private Juego juego;

	//uni-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idUsu")
	private Usuario usuario;

	public Publicacion() {
	}
	
	public Publicacion(int idPub) {
		this.idPub = idPub;
	}

	public Publicacion(byte isbanpub, Juego juego, Usuario usuario) {
		this.isbanpub = isbanpub;
		this.juego = juego;
		this.usuario = usuario;
	}

	public int getIdPub() {
		return this.idPub;
	}

	public void setIdPub(int idPub) {
		this.idPub = idPub;
	}

	public Date getFpub() {
		return this.fpub;
	}

	public void setFpub(Date fpub) {
		this.fpub = fpub;
	}

	public byte getIsbanpub() {
		return this.isbanpub;
	}

	public void setIsbanpub(byte isbanpub) {
		this.isbanpub = isbanpub;
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