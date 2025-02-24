package Negocio;

import java.util.List;

import Dominio.ClienteDao;
import Entidades.Clientes;
import Entidades.Localidad;
import Entidades.Provincia;
import InterfacesNegocio.InterClienteNeg;

public class NegocioClientes implements InterClienteNeg{
	
	ClienteDao cd = new ClienteDao();
	
	public List<Clientes> ListarClientesNeg(){
		return cd.ListarClientesDao();
	}
	
	public List<Clientes> ListarClientesFiltro(int dni){
		return cd.BuscarPorDni(dni);
	}
	
	public int EliminarClientesNeg(int dni) {
		return cd.EliminarClientesDao(dni);
	}
	
	public int ModificarClienteNeg(Clientes cliente) {
        return cd.ModificarCliente(cliente);
    }
	
	public int AgregarClienteNeg(Clientes cs) {	    
		return cd.AgregarClienteDao(cs);
	}
	
	public List<Provincia> ListarProvinciaNeg(){
		return cd.ListaProvincia();
	}
	
	public List<Localidad> ListarLocalidadNeg(String codProvincia){
		return cd.ListaLocalidad(codProvincia);
	}
	
	public boolean ExisteDniNeg(int dni) {
		return cd.ExisteDniDao(dni);
	}
	
	public boolean ExisteCuilNeg(String cuil) {
		return cd.ExisteCuilDao(cuil);
	}
	
	public List<Clientes> ObtenerClientesPorUsuario(String nombreUsuario){
		return cd.obtenerDatosClientePorUsuario(nombreUsuario);
	}
	
	public List<Clientes> obtenerResumenClientesNeg(){
		return cd.obtenerResumenClientesDao();
	}
	
	public List<Clientes> obtenerResumenClientesFiltradoNeg(int montoMinimo){
		return cd.obtenerResumenClientesFiltradoDao(montoMinimo);
	}
}
