package Negocio;

import java.util.List;

import Dominio.CuentaDao;
import Entidades.Cuenta;
import Entidades.Movimientos;
import Entidades.TipoCuenta;
import InterfacesNegocio.InterCuentaNeg;

public class NegocioCuenta implements InterCuentaNeg{
	
	CuentaDao Cdao= new CuentaDao();
	
	public List<Cuenta> ListarCuentas()
	{
		return Cdao.readAll();
	}
	
	public List<Cuenta> ListarCuentaFiltradaDni(int dni)
	{
		return Cdao.BuscarPorDni(dni);
	}
	
	public int agregarCuenta(Cuenta ct) {
		return Cdao.agregarCuenta(ct);
	}
	
	public int eliminarCuenta(int ncuenta) {
		return Cdao.eliminarCuenta(ncuenta);
	}
	
	public int modificarCuenta(Cuenta ct) {
		return Cdao.modificarCuenta(ct);
	}
	public int AsignarCuenta(int Ncuenta, int Cdni) {
		
		return Cdao.AsignarCuenta(Ncuenta, Cdni);
	}
	
	public int SacarDniPorNombreUsuarioNeg(String nombreUsuario) {
		return Cdao.SacarDniPorNombreUsuarioDao(nombreUsuario);
	}
	
	public List<TipoCuenta> obtenerTipoDeCuentaPorClienteNeg(int dniCliente){
		return Cdao.obtenerTipoDeCuentaPorClienteDao(dniCliente);
	}
	
	public List<Movimientos> obtenerDetalleMovimientoXTipoDeCuentaNe(int dniCliente,int codTipoCuenta){
		return Cdao.obtenerDetalleMovimientoXTipoDeCuentaDao(dniCliente,codTipoCuenta);
	}
	
	public int ObtenerCantidadCuentasNeg() {
		return Cdao.ObtenerCantidadCuentasDao();
	}
	
	public int ObtenerCantidadCuentasUltTrimestreNeg() {
		return Cdao.ObtenerCantidadCuentasUltTrimestreDao();
	}
	
	public int ObtenerCantidadPrestamosNeg(){
		return Cdao.ObtenerCantidadPrestamosDao();
	}
	
	public int ObtenerCantidadPrestamosAceptadosNeg() {
		return Cdao.ObtenerCantidadPrestamosAceptadosDao();
	}
	
	public int ObtenerCantidadPrestamosRechazadosNeg() {
		return Cdao.ObtenerCantidadPrestamosRechazadosDao();
	}
	
	public int SacarNumCuentaXCbu(String Cbu) {
		return Cdao.SacarNumCuentaXCbu(Cbu);
	}
}
