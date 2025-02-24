package InterfacesDao;

import java.util.List;

import Entidades.Clientes;
import Entidades.Localidad;
import Entidades.Provincia;

public interface InterClientesDao {
	
	public List<Clientes> ListarClientesDao();
	public List<Clientes> BuscarPorDni(int dni);
	public int EliminarClientesDao(int dni);
	public int ModificarCliente(Clientes cliente);
	public int AgregarClienteDao(Clientes cliente);
	public int obtenerUltimoCodUsuario();
	public List <Provincia> ListaProvincia();
	public List <Localidad> ListaLocalidad(String codProvincia);
	public boolean ExisteDniDao(int dni);
	public boolean ExisteCuilDao(String cuil);
	public List<Clientes> obtenerDatosClientePorUsuario(String nombreUsuario);
	public List<Clientes> obtenerResumenClientesDao();
	public List<Clientes> obtenerResumenClientesFiltradoDao(int montoMinimo);
	
	

}
