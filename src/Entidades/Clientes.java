package Entidades;

import java.sql.Date;

import Excepciones.ApellidoInvalidoException;
import Excepciones.CorreoInvalidoException;
import Excepciones.CuilInvalidoException;
import Excepciones.NacionalidadInvalidoException;
import Excepciones.NombreInvalidoException;
import Excepciones.TelefonoInvalidoException;

public class Clientes {
	
	private String Nombre;
	private String Apellido;
	private int Dni;
	private String Cuil;
	private String Sexo;
	private String Nacionalidad;
	private Date FechaNac;
	private String Direccion;
	private String Correo;
	private String Telefono;
	private String NombreUsuario;
	private String ContraseniaUsuario;
	private String Privincia;
	private String Localidad;
	
	// Atributos para el informe
    private int numeroCuentas;
    private int prestamosActivos;

	public Clientes() {
	
	}



	public Clientes(String nombre, String apellido, int dni, String nacionalidad, String correo, Date fecha, String direccion, String sexo, String telefono) {
		super();
		Nombre = nombre;
		Apellido = apellido;
		Dni = dni;
		Nacionalidad = nacionalidad;
		Correo = correo;
		FechaNac = fecha;
		Direccion = direccion;
		Sexo = sexo;
		Telefono = telefono;
	}



	public String getNombre() {
		return Nombre;
	}


	public void setNombre(String nombre) {
		Nombre = nombre;
	}


	public String getApellido() {
		return Apellido;
	}


	public void setApellido(String apellido) {
		Apellido = apellido;
	}


	public int getDni() {
		return Dni;
	}


	public void setDni(int dni) {
		Dni = dni;
	}


	public String getCuil() {
		return Cuil;
	}


	public void setCuil(String cuil) {
		Cuil = cuil;
	}


	public String getSexo() {
		return Sexo;
	}


	public void setSexo(String sexo) {
		Sexo = sexo;
	}


	public String getNacionalidad() {
		return Nacionalidad;
	}


	public void setNacionalidad(String nacionalidad) {
		Nacionalidad = nacionalidad;
	}


	public Date getFechaNac() {
		return FechaNac;
	}


	public void setFechaNac(Date fechaNac) {
		FechaNac = fechaNac;
	}


	public String getDireccion() {
		return Direccion;
	}


	public void setDireccion(String direccion) {
		Direccion = direccion;
	}


	public String getCorreo() {
		return Correo;
	}


	public void setCorreo(String correo) {
		Correo = correo;
	}


	public String getTelefono() {
		return Telefono;
	}


	public void setTelefono(String telefono) {
		Telefono = telefono;
	}


	public String getNombreUsuario() {
		return NombreUsuario;
	}


	public void setNombreUsuario(String nombreUsuario) {
		NombreUsuario = nombreUsuario;
	}


	public String getContraseniaaUsuario() {
		return ContraseniaUsuario;
	}


	public void setContraseniaUsuario(String contraseniaUsuario) {
		ContraseniaUsuario = contraseniaUsuario;
	}


	public String getPrivincia() {
		return Privincia;
	}


	public void setPrivincia(String privincia) {
		Privincia = privincia;
	}


	public String getLocalidad() {
		return Localidad;
	}


	public void setLocalidad(String localidad) {
		Localidad = localidad;
	}



	public int getNumeroCuentas() {
		return numeroCuentas;
	}



	public void setNumeroCuentas(int numeroCuentas) {
		this.numeroCuentas = numeroCuentas;
	}



	public int getPrestamosActivos() {
		return prestamosActivos;
	}



	public void setPrestamosActivos(int prestamosActivos) {
		this.prestamosActivos = prestamosActivos;
	}



	@Override
	public String toString() {
		return "Clientes [Nombre=" + Nombre + ", Apellido=" + Apellido + ", Dni=" + Dni + ", Cuil=" + Cuil + ", Sexo="
				+ Sexo + ", Nacionalidad=" + Nacionalidad + ", FechaNac=" + FechaNac + ", Direccion=" + Direccion
				+ ", Correo=" + Correo + ", Telefono=" + Telefono + ", NombreUsuario=" + NombreUsuario
				+ ", ContraseniaUsuario=" + ContraseniaUsuario + ", Privincia=" + Privincia + ", Localidad=" + Localidad
				+ "]";
	}
	
	/// Validaciones
	
	public boolean verificarNombreInvalido() throws NombreInvalidoException {
		
		boolean contieneNumero = false;
		
		for (int i = 0; i < Nombre.length(); i++) {
			if (!Character.isLetter(Nombre.charAt(i))) {
				contieneNumero = true;
			}
		}
		if (contieneNumero)
			throw new NombreInvalidoException();
			
		return true;
	}
	
	public boolean verificarApellidoInvalido() throws ApellidoInvalidoException {
	
		boolean contieneNumero = false;
		
		for (int i = 0; i < Apellido.length(); i++) {
			if (!Character.isLetter(Apellido.charAt(i))) {
				contieneNumero = true;
			}
		}
		if (contieneNumero)
			throw new ApellidoInvalidoException();
			
		return true;
	}
	
	public boolean verificarNacionalidadInvalido() throws NacionalidadInvalidoException {
		
		boolean contieneNumero = false;
		
		for (int i = 0; i < Nacionalidad.length(); i++) {
			if (!Character.isLetter(Nacionalidad.charAt(i))) {
				contieneNumero = true;
			}
		}
		if (contieneNumero)
			throw new NacionalidadInvalidoException();
			
		return true;
	}
	
	public boolean verificarCuilInvalido() throws CuilInvalidoException {
		
		boolean contieneLetra = false;
		
		for (int i = 0; i < Cuil.length(); i++) {
			if (!Character.isDigit(Cuil.charAt(i))) {
				contieneLetra = true;
			}
		}
		if (contieneLetra)
			throw new CuilInvalidoException();
			
		return true;
	}
	
	public boolean verificarCorreoInvalido() throws CorreoInvalidoException {
		
		boolean contienePunto = false;
		boolean contieneArroba = false;
		
		for (int i = 0; i < Correo.length(); i++) {
			if (Correo.charAt(i) == '.')
				contienePunto = true;
			if (Correo.charAt(i) == '@')
				contieneArroba = true;
		}
		if (contienePunto == false || contieneArroba == false)
			throw new CorreoInvalidoException();
			
		return true;
	}
	
	public boolean verificarTelefonoInvalido() throws TelefonoInvalidoException {
		
		boolean contieneLetra = false;
		
	    for (int i = 0; i < Telefono.length(); i++) {
	        if (!Character.isDigit(Telefono.charAt(i))) {
	            contieneLetra = true;
	        }
	    }
	    if(contieneLetra) {
	    	throw new TelefonoInvalidoException();
	    }
	    return true;
	}
	
}
