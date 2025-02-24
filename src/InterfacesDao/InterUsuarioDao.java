package InterfacesDao;

public interface InterUsuarioDao {
	
	public int ExisteUserDao(String userName, String userPass);
	public boolean actualizarContraseniaDao(String nombreUsuario, String Password);
	

}
