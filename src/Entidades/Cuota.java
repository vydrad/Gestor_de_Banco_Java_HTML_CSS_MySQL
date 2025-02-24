package Entidades;

import java.sql.Date;

public class Cuota {
	private int codCuota;
    private int numeroCuotaPagar;
    private Float montoCuota;
    private Date fechaVencimientoCuota;
    private Date fechaPagoCuota;
    private String estadoCuota;
    
	public Cuota(int codCuota, int numeroCuotaPagar, Float montoCuota, Date fechaVencimientoCuota,
			Date fechaPagoCuota, String estadoCuota) {
		super();
		this.codCuota = codCuota;
		this.numeroCuotaPagar = numeroCuotaPagar;
		this.montoCuota = montoCuota;
		this.fechaVencimientoCuota = fechaVencimientoCuota;
		this.fechaPagoCuota = fechaPagoCuota;
		this.estadoCuota = estadoCuota;
	}

	public Cuota() {
		super();
	}

	public int getCodCuota() {
		return codCuota;
	}

	public void setCodCuota(int codCuota) {
		this.codCuota = codCuota;
	}

	public int getNumeroCuotaPagar() {
		return numeroCuotaPagar;
	}

	public void setNumeroCuotaPagar(int numeroCuotaPagar) {
		this.numeroCuotaPagar = numeroCuotaPagar;
	}

	public Float getMontoCuota() {
		return montoCuota;
	}

	public void setMontoCuota(Float montoCuota) {
		this.montoCuota = montoCuota;
	}

	public Date getFechaVencimientoCuota() {
		return fechaVencimientoCuota;
	}

	public void setFechaVencimientoCuota(Date fechaVencimientoCuota) {
		this.fechaVencimientoCuota = fechaVencimientoCuota;
	}

	public Date getFechaPagoCuota() {
		return fechaPagoCuota;
	}

	public void setFechaPagoCuota(Date fechaPagoCuota) {
		this.fechaPagoCuota = fechaPagoCuota;
	}

	public String getEstadoCuota() {
		return estadoCuota;
	}

	public void setEstadoCuota(String estadoCuota) {
		this.estadoCuota = estadoCuota;
	}
    
	
	
}
