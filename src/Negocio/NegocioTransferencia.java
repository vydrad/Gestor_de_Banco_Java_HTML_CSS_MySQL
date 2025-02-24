package Negocio;

import java.util.List;

import Dominio.TransferenciaDao;
import Entidades.Cuenta;
import InterfacesNegocio.InterTransferenciaNeg;

public class NegocioTransferencia implements InterTransferenciaNeg{
	
	TransferenciaDao td = new TransferenciaDao();
	
	public Cuenta ObtenerCBUxNombreUsuarioNeg(String nombreUsuario) {
		return td.ObtenerCBUxNombreUsuarioDao(nombreUsuario);
	}

	public List<Cuenta> ObtenerTodosLosCBUNeg(String nombreUsuario) {
		return td.ObtenerTodosLosCBUDao( nombreUsuario);
	}
	
    public String realizarTransferencia(String cbuRemitente, String cbuDestinatario, float monto,int numCuenta,int numCuenta2) {
        if (monto <= 0) {
            return "El monto debe ser mayor a cero.";
        }

        boolean exito = td.transferir(cbuRemitente, cbuDestinatario, monto,numCuenta, numCuenta2);
        return exito ? "Transferencia realizada con éxito." : "Error: saldo insuficiente o cuenta destino inválida.";
    }
}
