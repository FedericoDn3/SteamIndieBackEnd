package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the billetera database table.
 * 
 */
@Entity
@NamedQuery(name="Billetera.findAll", query="SELECT b FROM Billetera b")
public class Billetera implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idBilletera;

	private String autentificador;

	private int numtarj;

	private String pin;

	private BigDecimal saldo;

	public Billetera() {
	}
	
	public int getIdBilletera() {
		return this.idBilletera;
	}

	public void setIdBilletera(int idBilletera) {
		this.idBilletera = idBilletera;
	}

	public String getAutentificador() {
		return this.autentificador;
	}

	public void setAutentificador(String autentificador) {
		this.autentificador = autentificador;
	}

	public int getNumtarj() {
		return this.numtarj;
	}

	public void setNumtarj(int numtarj) {
		this.numtarj = numtarj;
	}

	public String getPin() {
		return this.pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public BigDecimal getSaldo() {
		return this.saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

}