package InterfacesNegocio;

import java.util.List;

import Entidades.Clientes;
import Entidades.Localidad;
import Entidades.Provincia;

public interface InterClienteNeg {

	public List<Clientes> ListarClientesNeg();
	public List<Clientes> ListarClientesFiltro(int dni);
	public int EliminarClientesNeg(int dni);
	public int ModificarClienteNeg(Clientes cliente);
	public int AgregarClienteNeg(Clientes cs);
	public List<Provincia> ListarProvinciaNeg();
	public List<Localidad> ListarLocalidadNeg(String codProvincia);
	public boolean ExisteDniNeg(int dni);
	public boolean ExisteCuilNeg(String cuil);
	public List<Clientes> ObtenerClientesPorUsuario(String nombreUsuario);
	public List<Clientes> obtenerResumenClientesNeg();
	public List<Clientes> obtenerResumenClientesFiltradoNeg(int montoMinimo);
	
}
