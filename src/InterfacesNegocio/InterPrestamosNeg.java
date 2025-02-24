package InterfacesNegocio;

import java.util.List;

import Entidades.Clientes;
import Entidades.Cuota;
import Entidades.Prestamo;

public interface InterPrestamosNeg {
	
	public List<Prestamo> ObtenerNumPrestamos(int dniCliente);
	public List<Cuota> ObtenerNumCuota(int dniCliente);
	public List<Clientes> ObtenerClientePrestamo();
	public int ObtenerNumeroCuenta(int Dni);
	public List<Prestamo> ObtenerInfoPrestamo(int numCuenta);
	public List<Cuota> ObtenerInfoCuota(int numCuenta);
	public boolean autorizarPrestamosPorNumeroCuenta(int numeroCuenta);
	public int ObtenerNumCuentaPrestamo(int codPrestamoPagar);
	public int PedidoPrestamos(Prestamo presta);
	public int PedidoPrestamoAgregandoCuota(Cuota cuota);
	public int ContarCodCuotas();
	public boolean RechazarPrestamosPorNumeroCuenta(int numeroCuenta);
	public boolean realizarPagoCuota(int codPrestamo, int codCuota, String cuentaDebito);

}
