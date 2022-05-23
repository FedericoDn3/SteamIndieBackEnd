package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the juego database table.
 * 
 */
@Entity
@NamedQuery(name="Juego.findAll", query="SELECT j FROM Juego j")
public class Juego implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(insertable=false, updatable=false)
	private int idJuego;

	private String ban;

	private float calificacion;

	@Lob
	private String categoria;

	@Temporal(TemporalType.DATE)
	private Date fechaL;

	@Lob
	private byte[] imagen;

	private String nomjuego;

	private BigDecimal precio;
	
	private String descripcion;
	
	private String requisitos;

	@Lob
	private String tags;

	private String url;

	private String video;

	//uni-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idUsu")
	private Usuario usuario;

	public Juego() {
	}
	
	public Juego(int idJuego) {
		this.idJuego = idJuego;
	}
	
	// juego con todos los campos
	public Juego(int idJuego, String ban, float calificacion, String categoria, Date fechaL, byte[] imagen,
			String nomjuego, BigDecimal precio, String descripcion, String requisitos, String tags, String url,
			String video, Usuario usuario) {
		this.idJuego = idJuego;
		this.ban = ban;
		this.calificacion = calificacion;
		this.categoria = categoria;
		this.fechaL = fechaL;
		this.imagen = imagen;
		this.nomjuego = nomjuego;
		this.precio = precio;
		this.descripcion = descripcion;
		this.requisitos = requisitos;
		this.tags = tags;
		this.url = url;
		this.video = video;
		this.usuario = usuario;
	}

	// constructor con todo menos el id
 	public Juego(String ban, float calificacion, String categoria, Date fechaL, byte[] imagen, String nomjuego,
			BigDecimal precio, String descripcion, String requisitos, String tags, String url, String video,
			Usuario usuario) {
		this.ban = ban;
		this.calificacion = calificacion;
		this.categoria = categoria;
		this.fechaL = fechaL;
		this.imagen = imagen;
		this.nomjuego = nomjuego;
		this.precio = precio;
		this.descripcion = descripcion;
		this.requisitos = requisitos;
		this.tags = tags;
		this.url = url;
		this.video = video;
		this.usuario = usuario;
	}
 	
	public int getIdJuego() {
		return this.idJuego;
	}

	public void setIdJuego(int idJuego) {
		this.idJuego = idJuego;
	}

	public String getBan() {
		return this.ban;
	}

	public void setBan(String ban) {
		this.ban = ban;
	}

	public float getCalificacion() {
		return this.calificacion;
	}

	public void setCalificacion(float calificacion) {
		this.calificacion = calificacion;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Date getFechaL() {
		return this.fechaL;
	}

	public void setFechaL(Date fechaL) {
		this.fechaL = fechaL;
	}

	public byte[] getImagen() {
		return this.imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public String getNomjuego() {
		return this.nomjuego;
	}

	public void setNomjuego(String nomjuego) {
		this.nomjuego = nomjuego;
	}

	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(String requisitos) {
		this.requisitos = requisitos;
	}

	public String getTags() {
		return this.tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getVideo() {
		return this.video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}