package Entidades;

public class Provincia {
	
	 private String codProvincia;
	 private String descripcionProvincia;
	 
	 
	public Provincia() {
	
	}


	public Provincia(String codProvincia, String descripcionProvincia) {
		
		this.codProvincia = codProvincia;
		this.descripcionProvincia = descripcionProvincia;
	}


	public String getCodProvincia() {
		return codProvincia;
	}


	public void setCodProvincia(String codProvincia) {
		this.codProvincia = codProvincia;
	}


	public String getDescripcionProvincia() {
		return descripcionProvincia;
	}


	public void setDescripcionProvincia(String descripcionProvincia) {
		this.descripcionProvincia = descripcionProvincia;
	}
	 
	 
}
