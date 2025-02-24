package Entidades;

public class Usuario {
	   private int codUsuario;
	   private String nombreUsuario;
	   private String passUsuario;
	   private boolean administradorUsuario;
	   private int estadoUsuario;
	   
	public Usuario() {
		
	}

	public Usuario(int codUsuario, String nombreUsuario, String passUsuario, boolean administradorUsuario,
			int estadoUsuario) {
		this.codUsuario = codUsuario;
		this.nombreUsuario = nombreUsuario;
		this.passUsuario = passUsuario;
		this.administradorUsuario = administradorUsuario;
		this.estadoUsuario = estadoUsuario;
	}

	public int getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassUsuario() {
		return passUsuario;
	}

	public void setPassUsuario(String passUsuario) {
		this.passUsuario = passUsuario;
	}

	public boolean isAdministradorUsuario() {
		return administradorUsuario;
	}

	public void setAdministradorUsuario(boolean administradorUsuario) {
		this.administradorUsuario = administradorUsuario;
	}

	public int getEstadoUsuario() {
		return estadoUsuario;
	}

	public void setEstadoUsuario(int estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}
	   
	   
}
