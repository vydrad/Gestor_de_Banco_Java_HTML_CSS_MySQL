package Negocio;

import Dominio.PrestamosDao;
import Entidades.Clientes;
import Entidades.Cuota;
import Entidades.Prestamo;
import InterfacesNegocio.InterPrestamosNeg;

import java.util.List;

public class NegocioPrestamos implements InterPrestamosNeg{
	
	PrestamosDao pd = new PrestamosDao();
			
	public List<Prestamo> ObtenerNumPrestamos(int dniCliente){
		return pd.ObtenerNumPrestamos(dniCliente);
	}
	
	public List<Prestamo> ObtenerNumPrestamoPorCuenta(int NumeroCuenta){
		return pd.ObtenerNumPrestamoPorCuenta(NumeroCuenta);
	}
	
	public List<Cuota> ObtenerNumCuota(int NumCuenta){
		return pd.ObtenerNumCuota(NumCuenta);
	}
	
	public List<Clientes> ObtenerClientePrestamo(){
		return pd.ObtenerClientePrestamo();
	}
	
	public int ObtenerNumeroCuenta(int Dni) {
		return pd.ObtenerNumeroCuenta(Dni);
	}
	
	public List<Prestamo> ObtenerInfoPrestamo(int numCuenta){
		return pd.ObtenerInfoPrestamo(numCuenta);
	}
	
	public List<Cuota> ObtenerInfoCuota(int numCuenta){
		return pd.ObtenerInfoCuota(numCuenta);
	}
	
	public boolean autorizarPrestamosPorNumeroCuenta(int numeroCuenta) {
		return pd.autorizarPrestamosPorNumeroCuenta(numeroCuenta);
	}
	
	public int ObtenerNumCuentaPrestamo(int codPrestamoPagar) {
		return pd.ObtenerNumCuentaPrestamoDAO(codPrestamoPagar);
	}

	public int VerificarPedidoPrestamos(Prestamo presta) {
		return pd.VerificarPedidoPrestamo(presta);
	}
	
	public int PedidoPrestamos(Prestamo presta) {
		return pd.PedidoPrestamo(presta);
	}
	
	public int PedidoPrestamoAgregandoCuota(Cuota cuota) {
		return pd.PedidoPrestamoAgregandoCuota(cuota);
	}
	
	public int ContarCodCuotas() {
		return pd.ContarCodCuotas();
	}
	
	public boolean RechazarPrestamosPorNumeroCuenta(int numeroCuenta) {
		return pd.RechazarPrestamosPorNumeroCuenta(numeroCuenta);
	}

	public boolean realizarPagoCuota(int codPrestamo, int codCuota, String cuentaDebito) {
	    return pd.realizarPagoCuota(codPrestamo, codCuota, cuentaDebito);
	}
}

