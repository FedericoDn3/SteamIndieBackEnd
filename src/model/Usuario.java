package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.encontrarTodosUsuarios", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(insertable=false, updatable=false)
	private int idUsu;

	private String apell;

	private String email;

	@Temporal(TemporalType.DATE)
	private Date fnac;

	@Lob
	private byte[] img;

	private String nick;

	private String nom;

	private String pass;

	private String rol;

	private String sexo;

	//uni-directional one-to-one association to Billetera
	@OneToOne
	@JoinColumn(name="idUsu")
	private Billetera billetera;

	//uni-directional one-to-one association to Perfil
	@OneToOne
	@JoinColumn(name="idUsu")
	private Perfil perfil;

	public Usuario() {
	}
	
	public Usuario(int idUsu) {
		this.idUsu = idUsu;
	}
	
	
// DESPUES LO RAJO
	public Usuario(String apell, String email, Date fnac, String nick, String nom, String pass, String rol,
			String sexo) {
		this.apell = apell;
		this.email = email;
		this.fnac = fnac;
		this.nick = nick;
		this.nom = nom;
		this.pass = pass;
		this.rol = rol;
		this.sexo = sexo;
	}

	public Usuario(int idUsu, String apell, String email, Date fnac, byte[] img, String nick, String nom, String pass,
			String rol, String sexo) {
		this.idUsu = idUsu;
		this.apell = apell;
		this.email = email;
		this.fnac = fnac;
		this.img = img;
		this.nick = nick;
		this.nom = nom;
		this.pass = pass;
		this.rol = rol;
		this.sexo = sexo;
	}

	public Usuario(String apell, String email, Date fnac, byte[] img, String nick, String nom, String pass, String rol,
			String sexo) {
		this.apell = apell;
		this.email = email;
		this.fnac = fnac;
		this.img = img;
		this.nick = nick;
		this.nom = nom;
		this.pass = pass;
		this.rol = rol;
		this.sexo = sexo;
	}

	public int getIdUsu() {
		return this.idUsu;
	}

	public void setIdUsu(int idUsu) {
		this.idUsu = idUsu;
	}

	public String getApell() {
		return this.apell;
	}

	public void setApell(String apell) {
		this.apell = apell;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFnac() {
		return this.fnac;
	}

	public void setFnac(Date fnac) {
		this.fnac = fnac;
	}

	public byte[] getImg() {
		return this.img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public String getNick() {
		return this.nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getRol() {
		return this.rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Billetera getBilletera() {
		return this.billetera;
	}

	public void setBilletera(Billetera billetera) {
		this.billetera = billetera;
	}

	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

}