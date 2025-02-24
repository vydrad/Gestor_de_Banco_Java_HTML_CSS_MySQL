package InterfacesNegocio;

import java.util.List;

import Entidades.Cuenta;

public interface InterTransferenciaNeg {
	
	public Cuenta ObtenerCBUxNombreUsuarioNeg(String nombreUsuario);
	public List<Cuenta> ObtenerTodosLosCBUNeg(String nombreUsuario);
	public String realizarTransferencia(String cbuRemitente, String cbuDestinatario, float monto,int numCuenta,int numCuenta2);

}
