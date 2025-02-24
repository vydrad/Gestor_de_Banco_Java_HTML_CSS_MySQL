package Negocio;

import Dominio.UsuarioDao;
import InterfacesNegocio.InterUsuarioNeg;

public class NegocioUsuario implements InterUsuarioNeg{

	UsuarioDao ud = new UsuarioDao();
	
	public int ExisteUserNeg(String userName, String userPass) {
		return ud.ExisteUserDao(userName, userPass);
	}
	
	public boolean actualizarContraseniaNeg( String nombreUsuario, String Password) {
		return ud.actualizarContraseniaDao(nombreUsuario, Password);
	}
	
	
}