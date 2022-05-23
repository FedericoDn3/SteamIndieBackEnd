package model;

import java.util.ArrayList;
import java.util.List;

public class DtoJuego {
	
	private String id;
	private String Nombre;
	private String Precio;
	private String Descripcion;
	private byte[] Imagen;
	private String Video;
	private List<String> Categorias = new ArrayList<String>();
	private List<String> Tags = new ArrayList<String>();
	private String Creador;
	private String FechaL;
	private String Url;
	private String Requisitos;
	private String Ban;
	private String Promedio;
	private List<String> Calificaciones = new ArrayList<String>();
	private List<String> Comentarios = new ArrayList<String>();
	
	public DtoJuego() {
	}

	public DtoJuego(String id, String nombre, String precio, String descripcion, byte[] imagen, String video,
			List<String> categorias, List<String> tags, String creador, String fechaL, String url, String requisitos,
			String ban, String promedio, List<String> calificaciones, List<String> comentarios) {
		this.id = id;
		Nombre = nombre;
		Precio = precio;
		Descripcion = descripcion;
		Imagen = imagen;
		Video = video;
		Categorias = categorias;
		Tags = tags;
		Creador = creador;
		FechaL = fechaL;
		Url = url;
		Requisitos = requisitos;
		Ban = ban;
		Promedio = promedio;
		Calificaciones = calificaciones;
		Comentarios = comentarios;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getPrecio() {
		return Precio;
	}
	public void setPrecio(String precio) {
		Precio = precio;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public byte[] getImagen() {
		return Imagen;
	}
	public void setImagen(byte[] imagen) {
		Imagen = imagen;
	}
	public String getVideo() {
		return Video;
	}
	public void setVideo(String video) {
		Video = video;
	}
	public List<String> getCategorias() {
		return Categorias;
	}
	public void setCategorias(List<String> categorias) {
		Categorias = categorias;
	}
	public List<String> getTags() {
		return Tags;
	}
	public void setTags(List<String> tags) {
		Tags = tags;
	}
	public String getCreador() {
		return Creador;
	}
	public void setCreador(String creador) {
		Creador = creador;
	}
	public String getFechaL() {
		return FechaL;
	}
	public void setFechaL(String fechaL) {
		FechaL = fechaL;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public String getRequisitos() {
		return Requisitos;
	}
	public void setRequisitos(String requisitos) {
		Requisitos = requisitos;
	}
	public String getBan() {
		return Ban;
	}
	public void setBan(String ban) {
		Ban = ban;
	}
	public String getPromedio() {
		return Promedio;
	}
	public void setPromedio(String promedio) {
		Promedio = promedio;
	}
	public List<String> getCalificaciones() {
		return Calificaciones;
	}
	public void setCalificaciones(List<String> calificaciones) {
		Calificaciones = calificaciones;
	}
	public List<String> getComentarios() {
		return Comentarios;
	}
	public void setComentarios(List<String> comentarios) {
		Comentarios = comentarios;
	}

}
