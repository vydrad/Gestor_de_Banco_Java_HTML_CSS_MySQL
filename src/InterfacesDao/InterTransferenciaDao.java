package InterfacesDao;

import java.util.List;

import Entidades.Cuenta;

public interface InterTransferenciaDao {
	
	public Cuenta ObtenerCBUxNombreUsuarioDao(String nombreUsuario);
	public List<Cuenta> ObtenerTodosLosCBUDao(String nombreUsuario);
	public boolean transferir(String cbuRemitente, String cbuDestinatario, float monto, int numCuenta, int numCuenta2);
	

}
