package InterfacesNegocio;

public interface InterUsuarioNeg {
	
	public int ExisteUserNeg(String userName, String userPass);
	public boolean actualizarContraseniaNeg( String nombreUsuario, String Password);

}
