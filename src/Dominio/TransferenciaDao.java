package Dominio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Cuenta;
import InterfacesDao.InterTransferenciaDao;

public class TransferenciaDao implements InterTransferenciaDao {
	
	
	public Cuenta ObtenerCBUxNombreUsuarioDao(String nombreUsuario) {
	    Connection cn = null;
	    PreparedStatement pst;
	    String consulta = " SELECT cu.CBU_Cuenta AS cbu, cu.Saldo_Cuenta AS saldo " +
	                      " FROM cuenta AS cu " + 
	                      " INNER JOIN cliente AS cl " +
	                      " ON cu.DNI_Cuenta = cl.DNI_Cliente " + 
	                      " INNER JOIN usuario AS u " +
	                      " ON cl.CodUsuario_Cliente = u.Cod_Usuario " + 
	                      " WHERE u.Nombre_Usuario = ? ";

	    try {
	        cn = Conexion.getConexion().getSQLConexion();
	        pst = cn.prepareStatement(consulta);
	        pst.setString(1, nombreUsuario);
	        ResultSet rs = pst.executeQuery();

	        if (rs.next()) {
	            Cuenta cu = new Cuenta();
	            cu.setCbuCuenta(rs.getString("cbu"));
	            cu.setSaldoCuenta(rs.getFloat("saldo"));
	            return cu;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return null;
	}
	
	public List<Cuenta> ObtenerTodosLosCBUDao(String nombreUsuario) {
		
		List<Cuenta> listaCuenta= new ArrayList<Cuenta>();
		Connection cn = null;
        PreparedStatement pst;
        String consulta = " SELECT cu.CBU_Cuenta AS cbu " + 
		        		  " FROM cuenta AS cu "
		        	    + " WHERE CBU_Cuenta!= ?";

        try {
        	
        	cn = Conexion.getConexion().getSQLConexion();
        	pst = cn.prepareStatement(consulta);
        	pst.setString(1, nombreUsuario);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
            	Cuenta cu = new Cuenta();
            	cu.setCbuCuenta(rs.getString("cbu"));
               
            	listaCuenta.add(cu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		
		return listaCuenta;
	}

	public boolean transferir(String cbuRemitente, String cbuDestinatario, float monto, int numCuenta, int numCuenta2) {
        Connection cn = null;
        PreparedStatement descontarSaldo = null;
        PreparedStatement sumarSaldo = null;
        PreparedStatement realizarExtraccion = null;
        PreparedStatement realizarDeposito = null;

        String queryDescontar = 
            "UPDATE cuenta " +
            "SET Saldo_Cuenta = Saldo_Cuenta - ? " +
            "WHERE CBU_Cuenta = ? AND Saldo_Cuenta >= ?";

        String querySumar = 
            "UPDATE cuenta " +
            "SET Saldo_Cuenta = Saldo_Cuenta + ? " +
            "WHERE CBU_Cuenta = ?";
        


        try {
            cn = Conexion.getConexion().getSQLConexion();
            cn.setAutoCommit(false);

            descontarSaldo = cn.prepareStatement(queryDescontar);
            descontarSaldo.setFloat(1, monto);
            descontarSaldo.setString(2, cbuRemitente);
            descontarSaldo.setFloat(3, monto);
            int filasAfectadasRemitente = descontarSaldo.executeUpdate();

            if (filasAfectadasRemitente == 0) {
                cn.rollback();
                return false;
            }

            sumarSaldo = cn.prepareStatement(querySumar);
            sumarSaldo.setFloat(1, monto);
            sumarSaldo.setString(2, cbuDestinatario);
            int filasAfectadasDestinatario = sumarSaldo.executeUpdate();

            if (filasAfectadasDestinatario == 0) {
                cn.rollback();
                return false;
            }
            
            // Obtener el nuevo código de movimiento
            String obtenerCodMovimiento = "SELECT MAX(Cod_Movimiento) + 1 FROM movimiento";
            PreparedStatement obtenerCodMovStmt = cn.prepareStatement(obtenerCodMovimiento);
            ResultSet rs = obtenerCodMovStmt.executeQuery();
            int nuevoCodMovimiento = 1;
            if (rs.next()) {
                nuevoCodMovimiento = rs.getInt(1);
            }

            // Registrar extracción
            String queryRealizarExtraccion = 
                "INSERT INTO movimiento (Cod_Movimiento, CodTipo_Movimiento, NumeroCuenta_Movimiento, Fecha_Movimiento, Descripcion_Movimiento) " +
                "VALUES (?, 2, ?, CURDATE(), 'Transferencia realizada')";
            realizarExtraccion = cn.prepareStatement(queryRealizarExtraccion);
            realizarExtraccion.setInt(1, nuevoCodMovimiento);
            realizarExtraccion.setInt(2, numCuenta);
            realizarExtraccion.executeUpdate();

            // Registrar depósito
            String queryRealizarDeposito = 
                "INSERT INTO movimiento (Cod_Movimiento, CodTipo_Movimiento, NumeroCuenta_Movimiento, Fecha_Movimiento, Descripcion_Movimiento) " +
                "VALUES (?, 1, ?, CURDATE(), 'Transferencia recibida')";
            realizarDeposito = cn.prepareStatement(queryRealizarDeposito);
            realizarDeposito.setInt(1, nuevoCodMovimiento + 1);
            realizarDeposito.setInt(2, numCuenta2);
            realizarDeposito.executeUpdate();
            
            cn.commit();
            return true;

        } catch (SQLException e) {
            try {
                if (cn != null) cn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;

        }
    }
}
