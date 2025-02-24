package Entidades;

public class TipoMovimiento {
	
	 private int codTipoMovimiento;
	 private String descripcionTipoMovimiento;
	 
	public TipoMovimiento() {
	
	}

	public TipoMovimiento(int codTipoMovimiento, String descripcionTipoMovimiento) {
	
		this.codTipoMovimiento = codTipoMovimiento;
		this.descripcionTipoMovimiento = descripcionTipoMovimiento;
	}

	public int getCodTipoMovimiento() {
		return codTipoMovimiento;
	}

	public void setCodTipoMovimiento(int codTipoMovimiento) {
		this.codTipoMovimiento = codTipoMovimiento;
	}

	public String getDescripcionTipoMovimiento() {
		return descripcionTipoMovimiento;
	}

	public void setDescripcionTipoMovimiento(String descripcionTipoMovimiento) {
		this.descripcionTipoMovimiento = descripcionTipoMovimiento;
	}
	 
	 
	 
}
