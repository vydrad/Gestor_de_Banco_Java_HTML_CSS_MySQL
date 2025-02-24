package Entidades;

import java.sql.Date;

public class Movimientos {
	
	 private int codMovimiento;
	 private int codTipoMovimiento;
	 private int numeroCuentaMovimiento;
	 private Date fechaMovimiento;
	 private Float importeMovimiento;
	 private String descripcionMovimiento;
	 
	public Movimientos() {
		
	}

	public Movimientos(int codMovimiento, int codTipoMovimiento, int numeroCuentaMovimiento, Date fechaMovimiento,
			Float importeMovimiento, String descripcionMovimiento) {
	
		this.codMovimiento = codMovimiento;
		this.codTipoMovimiento = codTipoMovimiento;
		this.numeroCuentaMovimiento = numeroCuentaMovimiento;
		this.fechaMovimiento = fechaMovimiento;
		this.importeMovimiento = importeMovimiento;
		this.descripcionMovimiento = descripcionMovimiento;
	}

	public int getCodMovimiento() {
		return codMovimiento;
	}

	public void setCodMovimiento(int codMovimiento) {
		this.codMovimiento = codMovimiento;
	}

	public int getCodTipoMovimiento() {
		return codTipoMovimiento;
	}

	public void setCodTipoMovimiento(int codTipoMovimiento) {
		this.codTipoMovimiento = codTipoMovimiento;
	}

	public int getNumeroCuentaMovimiento() {
		return numeroCuentaMovimiento;
	}

	public void setNumeroCuentaMovimiento(int numeroCuentaMovimiento) {
		this.numeroCuentaMovimiento = numeroCuentaMovimiento;
	}

	public Date getFechaMovimiento() {
		return fechaMovimiento;
	}

	public void setFechaMovimiento(Date fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}

	public Float getImporteMovimiento() {
		return importeMovimiento;
	}

	public void setImporteMovimiento(Float importeMovimiento) {
		this.importeMovimiento = importeMovimiento;
	}

	public String getDescripcionMovimiento() {
		return descripcionMovimiento;
	}

	public void setDescripcionMovimiento(String descripcionMovimiento) {
		this.descripcionMovimiento = descripcionMovimiento;
	}
	 
	 
	 
}
