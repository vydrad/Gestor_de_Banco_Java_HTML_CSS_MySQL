package Entidades;

import java.sql.Date;

public class Prestamo {
	
	 private int codPrestamo;
	    private int dniPrestamo;
	    private int numeroCuentaPrestamo;
	    private int codCuotaPrestamo;
	    private Date fechaPrestamo;
	    private Float importePedidoPrestamo;
	    private Float importePagarPrestamo;
	    private int plazoMesesPrestamo;
	    private Float montoMesPrestamo;
	    private String estadoPrestamo;
	    
		public Prestamo() {
		
		}

		public Prestamo(int codPrestamo, int dniPrestamo, int numeroCuentaPrestamo, int codCuotaPrestamo,
				Date fechaPrestamo, Float importePedidoPrestamo, Float importePagarPrestamo, int plazoMesesPrestamo,
				Float montoMesPrestamo, String estadoPrestamo) {
		
			this.codPrestamo = codPrestamo;
			this.dniPrestamo = dniPrestamo;
			this.numeroCuentaPrestamo = numeroCuentaPrestamo;
			this.codCuotaPrestamo = codCuotaPrestamo;
			this.fechaPrestamo = fechaPrestamo;
			this.importePedidoPrestamo = importePedidoPrestamo;
			this.importePagarPrestamo = importePagarPrestamo;
			this.plazoMesesPrestamo = plazoMesesPrestamo;
			this.montoMesPrestamo = montoMesPrestamo;
			this.estadoPrestamo = estadoPrestamo;
		}

		public int getCodPrestamo() {
			return codPrestamo;
		}

		public void setCodPrestamo(int codPrestamo) {
			this.codPrestamo = codPrestamo;
		}

		public int getDniPrestamo() {
			return dniPrestamo;
		}

		public void setDniPrestamo(int dniPrestamo) {
			this.dniPrestamo = dniPrestamo;
		}

		public int getNumeroCuentaPrestamo() {
			return numeroCuentaPrestamo;
		}

		public void setNumeroCuentaPrestamo(int numeroCuentaPrestamo) {
			this.numeroCuentaPrestamo = numeroCuentaPrestamo;
		}

		public int getCodCuotaPrestamo() {
			return codCuotaPrestamo;
		}

		public void setCodCuotaPrestamo(int codCuotaPrestamo) {
			this.codCuotaPrestamo = codCuotaPrestamo;
		}

		public Date getFechaPrestamo() {
			return fechaPrestamo;
		}

		public void setFechaPrestamo(Date fechaPrestamo) {
			this.fechaPrestamo = fechaPrestamo;
		}

		public Float getImportePedidoPrestamo() {
			return importePedidoPrestamo;
		}

		public void setImportePedidoPrestamo(Float importePedidoPrestamo) {
			this.importePedidoPrestamo = importePedidoPrestamo;
		}

		public Float getImportePagarPrestamo() {
			return importePagarPrestamo;
		}

		public void setImportePagarPrestamo(Float importePagarPrestamo) {
			this.importePagarPrestamo = importePagarPrestamo;
		}

		public int getPlazoMesesPrestamo() {
			return plazoMesesPrestamo;
		}

		public void setPlazoMesesPrestamo(int plazoMesesPrestamo) {
			this.plazoMesesPrestamo = plazoMesesPrestamo;
		}

		public Float getMontoMesPrestamo() {
			return montoMesPrestamo;
		}

		public void setMontoMesPrestamo(Float montoMesPrestamo) {
			this.montoMesPrestamo = montoMesPrestamo;
		}

		public String getEstadoPrestamo() {
			return estadoPrestamo;
		}

		public void setEstadoPrestamo(String estadoPrestamo) {
			this.estadoPrestamo = estadoPrestamo;
		}

		@Override
		public String toString() {
			return "Prestamo [codPrestamo=" + codPrestamo + ", dniPrestamo=" + dniPrestamo + ", numeroCuentaPrestamo="
					+ numeroCuentaPrestamo + ", codCuotaPrestamo=" + codCuotaPrestamo + ", fechaPrestamo="
					+ fechaPrestamo + ", importePedidoPrestamo=" + importePedidoPrestamo + ", importePagarPrestamo="
					+ importePagarPrestamo + ", plazoMesesPrestamo=" + plazoMesesPrestamo + ", montoMesPrestamo="
					+ montoMesPrestamo + ", estadoPrestamo=" + estadoPrestamo + "]";
		}
		
}
