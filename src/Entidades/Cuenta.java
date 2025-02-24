package Entidades;

import java.sql.Date;

import Excepciones.CbuInvalidoException;

public class Cuenta {
	private int numeroCuenta;
    private String cbuCuenta;
    private int dniCuenta;
    private int tipoCuenta;
    private Date fechaCreacionCuenta;
    private float saldoCuenta;
    private Boolean estadoCuenta;
    
	public Cuenta(int numeroCuenta, String cbuCuenta, int dniCuenta, int tipoCuenta, Date fechaCreacionCuenta,
			float saldoCuenta, Boolean estadoCuenta) {

		this.numeroCuenta = numeroCuenta;
		this.cbuCuenta = cbuCuenta;
		this.dniCuenta = dniCuenta;
		this.tipoCuenta = tipoCuenta;
		this.fechaCreacionCuenta = fechaCreacionCuenta;
		this.saldoCuenta = saldoCuenta;
		this.estadoCuenta = estadoCuenta;
	}
	public Cuenta() {
		
	}
    
    
	public int getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public String getCbuCuenta() {
		return cbuCuenta;
	}
	public void setCbuCuenta(String cbuCuenta) {
		this.cbuCuenta = cbuCuenta;
	}
	public int getDniCuenta() {
		return dniCuenta;
	}
	public void setDniCuenta(int dniCuenta) {
		this.dniCuenta = dniCuenta;
	}
	public int getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(int tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	public Date getFechaCreacionCuenta() {
		return fechaCreacionCuenta;
	}
	public void setFechaCreacionCuenta(Date fechaCreacionCuenta) {
		this.fechaCreacionCuenta = fechaCreacionCuenta;
	}
	public float getSaldoCuenta() {
		return saldoCuenta;
	}
	public void setSaldoCuenta(float saldoCuenta) {
		this.saldoCuenta = saldoCuenta;
	}
	public Boolean getEstadoCuenta() {
		return estadoCuenta;
	}
	public void setEstadoCuenta(Boolean estadoCuenta) {
		this.estadoCuenta = estadoCuenta;
	}
	@Override
	public String toString() {
		return "Cuenta [numeroCuenta=" + numeroCuenta + ", cbuCuenta=" + cbuCuenta + ", dniCuenta=" + dniCuenta
				+ ", tipoCuenta=" + tipoCuenta + ", fechaCreacionCuenta=" + fechaCreacionCuenta + ", saldoCuenta="
				+ saldoCuenta + ", estadoCuenta=" + estadoCuenta + "]";
	}
	
	//Validaciones
	
	public boolean verificarCbuInvalido () throws CbuInvalidoException {
		
		boolean contieneLetra = false;
		for( int i = 0; i < cbuCuenta.length(); i++) {
			if(!Character.isDigit(cbuCuenta.charAt(i))){
			contieneLetra = true;
			}
		}
		if(contieneLetra) {
			throw new CbuInvalidoException();
		}
		return true;
	}
	
}
