package InterfacesNegocio;

import java.util.List;

import Entidades.Cuenta;
import Entidades.Movimientos;
import Entidades.TipoCuenta;

public interface InterCuentaNeg {
	
	public List<Cuenta> ListarCuentas();
	public List<Cuenta> ListarCuentaFiltradaDni(int dni);
	public int agregarCuenta(Cuenta ct);
	public int eliminarCuenta(int ncuenta);
	public int modificarCuenta(Cuenta ct);
	public int AsignarCuenta(int Ncuenta, int Cdni);
	public int SacarDniPorNombreUsuarioNeg(String nombreUsuario);
	public List<TipoCuenta> obtenerTipoDeCuentaPorClienteNeg(int dniCliente);
	public List<Movimientos> obtenerDetalleMovimientoXTipoDeCuentaNe(int dniCliente,int codTipoCuenta);
	public int ObtenerCantidadCuentasNeg();
	public int ObtenerCantidadCuentasUltTrimestreNeg();
	public int ObtenerCantidadPrestamosNeg();
	public int ObtenerCantidadPrestamosAceptadosNeg();
	public int ObtenerCantidadPrestamosRechazadosNeg();
	public int SacarNumCuentaXCbu(String Cbu);

}
