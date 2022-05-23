package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the instanciaoferta database table.
 * 
 */
@Entity
@NamedQuery(name="Instanciaoferta.findAll", query="SELECT i FROM Instanciaoferta i")
public class Instanciaoferta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(insertable=false, updatable=false)
	private int idio;

	//uni-directional many-to-one association to Juego
	@ManyToOne
	@JoinColumn(name="idJuego")
	private Juego juego;

	//uni-directional many-to-one association to Oferta
	@ManyToOne
	@JoinColumn(name="idoferta")
	private Oferta oferta;

	public Instanciaoferta() {
	}
	
	public Instanciaoferta(int idio) {
		this.idio = idio;
	}

	public Instanciaoferta(Juego juego, Oferta oferta) {
		this.juego = juego;
		this.oferta = oferta;
	}

	public int getIdio() {
		return this.idio;
	}

	public void setIdio(int idio) {
		this.idio = idio;
	}

	public Juego getJuego() {
		return this.juego;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
	}

	public Oferta getOferta() {
		return this.oferta;
	}

	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}

}