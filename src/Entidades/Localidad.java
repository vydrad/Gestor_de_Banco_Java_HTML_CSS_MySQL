package Entidades;

public class Localidad {
	
	   private String codLocalidad;
	   private String codProvinciaLocalidad;
	   private String descripcionLocalidad;
	   
	public Localidad() {
		
	}

	public Localidad(String codLocalidad, String codProvinciaLocalidad, String descripcionLocalidad) {
	
		this.codLocalidad = codLocalidad;
		this.codProvinciaLocalidad = codProvinciaLocalidad;
		this.descripcionLocalidad = descripcionLocalidad;
	}

	public String getCodLocalidad() {
		return codLocalidad;
	}

	public void setCodLocalidad(String codLocalidad) {
		this.codLocalidad = codLocalidad;
	}

	public String getCodProvinciaLocalidad() {
		return codProvinciaLocalidad;
	}

	public void setCodProvinciaLocalidad(String codProvinciaLocalidad) {
		this.codProvinciaLocalidad = codProvinciaLocalidad;
	}

	public String getDescripcionLocalidad() {
		return descripcionLocalidad;
	}

	public void setDescripcionLocalidad(String descripcionLocalidad) {
		this.descripcionLocalidad = descripcionLocalidad;
	}
	   
}
