package InterfacesDao;

import java.util.List;

import Entidades.Cuenta;
import Entidades.Movimientos;
import Entidades.TipoCuenta;

public interface InterCuentaDao {
	
	public List<Cuenta> readAll() ;
	public List<Cuenta> BuscarPorDni(int dni);
	public int agregarCuenta(Cuenta ct);
	public int eliminarCuenta(int ncuenta);
	public int modificarCuenta(Cuenta ct);
	public int AsignarCuenta(int Ncuenta, int Cdni);
	public int SacarDniPorNombreUsuarioDao(String nombreUsuario);
	public List<TipoCuenta> obtenerTipoDeCuentaPorClienteDao(int dniCliente);
	public List<Movimientos> obtenerDetalleMovimientoXTipoDeCuentaDao(int dniCliente, int codTipoCuenta);
	public int ObtenerCantidadCuentasDao();
	public int ObtenerCantidadCuentasUltTrimestreDao();
	public int ObtenerCantidadPrestamosDao();
	public int ObtenerCantidadPrestamosAceptadosDao();
	public int ObtenerCantidadPrestamosRechazadosDao() ;
	public int SacarNumCuentaXCbu(String Cbu);
	

}
