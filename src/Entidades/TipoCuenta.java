package Entidades;

public class TipoCuenta {
	
	private int codTipoCuenta;
    private String descripcionTipoCuenta;
    
	public TipoCuenta() {
		
	}

	public TipoCuenta(int codTipoCuenta, String descripcionTipoCuenta) {
	
		this.codTipoCuenta = codTipoCuenta;
		this.descripcionTipoCuenta = descripcionTipoCuenta;
	}

	public int getCodTipoCuenta() {
		return codTipoCuenta;
	}

	public void setCodTipoCuenta(int codTipoCuenta) {
		this.codTipoCuenta = codTipoCuenta;
	}

	public String getDescripcionTipoCuenta() {
		return descripcionTipoCuenta;
	}

	public void setDescripcionTipoCuenta(String descripcionTipoCuenta) {
		this.descripcionTipoCuenta = descripcionTipoCuenta;
	}
    
    
}
