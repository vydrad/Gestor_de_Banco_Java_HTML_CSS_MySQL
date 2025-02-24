package Dominio;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entidades.Cuenta;
import Entidades.Movimientos;
import Entidades.TipoCuenta;
import InterfacesDao.InterCuentaDao;

public class CuentaDao implements InterCuentaDao {
	
	
	private final static String readAll = "select * from cuenta WHERE Estado_Cuenta = 1";
	private final static String CantCuentas = "SELECT * FROM cuenta WHERE DNI_Cuenta = ? and Estado_cuenta = 1";
	private final static String ExisteCliente = "SELECT DNI_Cliente FROM cliente WHERE DNI_Cliente = ?";
	private final static String ExisteCuenta = "SELECT * FROM cuenta WHERE Numero_Cuenta= ? and CBU_Cuenta = ?";
	
	public List<Cuenta> readAll() {
		List<Cuenta> listaCuentas = new ArrayList<>();

		Connection cn = null;
		PreparedStatement statement;

		try {
			cn = Conexion.getConexion().getSQLConexion();
			
			statement = cn.prepareStatement(readAll);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Cuenta cuenta = new Cuenta();
				
				cuenta.setNumeroCuenta(rs.getInt("NumeroCuenta_Cuenta"));
				cuenta.setCbuCuenta(rs.getString("CBU_Cuenta"));
				cuenta.setDniCuenta(rs.getInt("DNI_Cuenta"));
				cuenta.setTipoCuenta(rs.getInt("Tipo_Cuenta"));
				cuenta.setFechaCreacionCuenta(rs.getDate("FechaCreacion_Cuenta"));
				cuenta.setSaldoCuenta(rs.getFloat("Saldo_Cuenta"));
				cuenta.setEstadoCuenta(true);// la consulta ya trae los que estan en true..

				listaCuentas.add(cuenta);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaCuentas;
	}
	
public List<Cuenta> BuscarPorDni(int dni){
		
		Connection cn= null;
		PreparedStatement pst;
		List<Cuenta> ListaFiltrada = new ArrayList<>();
		
		try {
			
			cn= Conexion.getConexion().getSQLConexion();
			pst=cn.prepareStatement("Select NumeroCuenta_Cuenta AS numCuenta , CBU_Cuenta AS cbu , DNI_Cuenta AS dni , Tipo_Cuenta AS tipoCuenta , FechaCreacion_Cuenta AS fecha , Saldo_Cuenta AS saldo , Estado_Cuenta FROM cuenta where DNI_Cuenta = '"+ dni+"' and Estado_Cuenta = true ");
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
			Cuenta cuenta = new Cuenta();	
				
			cuenta.setNumeroCuenta(rs.getInt("numCuenta"));
			cuenta.setCbuCuenta(rs.getString("cbu"));
			cuenta.setDniCuenta(rs.getInt("dni"));
			cuenta.setTipoCuenta(rs.getInt("tipoCuenta"));
			cuenta.setFechaCreacionCuenta(rs.getDate("fecha"));
			cuenta.setSaldoCuenta(rs.getFloat("saldo"));
			
			ListaFiltrada.add(cuenta);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return ListaFiltrada;
	}
	
	///devuelve 1 en la correcta insercion
	//agregar cuenta primero CORROBORA QUE EXISTA EL CLIENTE, LUEGO QUE "NO"  EXISTA LA CUENTA Y CBU INGRESADA
	/// Y SINO EXISTEN PROCEDE A CONTAR CUANTAS CUENTAS TIENE ESE DNI, Y SINO EXCEDE EL LIMITE LO REGISTRA.
	public int agregarCuenta(Cuenta ct) {
		int filas = -1;
		int bandera =0;
		Connection cn = null;
		Statement st = null;
		PreparedStatement Cuentas = null;
		PreparedStatement Cliente = null;
		PreparedStatement ExisteCuentas=null;

		try {
			
			cn = Conexion.getConexion().getSQLConexion();
			
	/*        // Verificar si el DNI_Cuenta Ingresado existe en la tabla cliente
	        Cliente = cn.prepareStatement(ExisteCliente);
	        Cliente.setInt(1, ct.getDniCuenta());
	        ResultSet rs3 = Cliente.executeQuery();
	        if(rs3.next()) {
			
	        	ExisteCuentas = cn.prepareStatement(ExisteCuenta);
		        ExisteCuentas.setInt(1, ct.getNumeroCuenta());
		        ExisteCuentas.setString(2, ct.getCbuCuenta());
		        ResultSet rs2 = Cliente.executeQuery();
		        /// CON ESTO
		        if(!rs2.isBeforeFirst()) {
		        	System.out.println("El NUMERO DE CUENTA y el CBU ingresado YA estan registrados");
		        }
		        else {

		        	Cuentas = cn.prepareStatement(CantCuentas);
		        	Cuentas.setInt(1, ct.getDniCuenta());
		        	ResultSet rs = Cuentas.executeQuery();
		        	/// contando los registros verifico que no sobrepase el limite de cuentas para el mismo DNI ingresado.
		        	while (rs.next()) {
		        		bandera=bandera+1;
		        		/// System.out.println("bandera vale:"+bandera);
		        	}

		        	///Sino supera el limite de cuentas, se crea la Cuenta Nueva
		        	if(bandera<3) {
		   */
		        		cn = Conexion.getConexion().getSQLConexion();
		        		String query = "INSERT INTO cuenta (NumeroCuenta_Cuenta, CBU_Cuenta, DNI_Cuenta, Tipo_Cuenta, FechaCreacion_Cuenta, Saldo_Cuenta, Estado_Cuenta)"
		        				+ " VALUES ("+ct.getNumeroCuenta()+","+ct.getCbuCuenta()+","+null+","+ct.getTipoCuenta()+",'"+ct.getFechaCreacionCuenta()+"',"+ct.getSaldoCuenta()+",true)";
		        		///System.out.println(query);
		        		cn.setAutoCommit(false);
		        		st = cn.createStatement();
			
			
		        		filas = st.executeUpdate(query);
		        		
		        		if (filas >0) {
		        		cn.commit();
		        		System.out.println("La cuenta se creo correctamente");
		        		}
		        		else {
		        		cn.rollback();
		        		System.out.println("Hubo un problema al Crear la cuenta.");
		        		}
			/*
		        	}
		        	else { 
		        		System.out.println("Limite de cuentas alcanzado, no se pueden crear mas cuenta con el DNI ingresado");
		        	}   	
		        }
			 
	        }
	        else {
	        	System.out.println("No está registrado el DNI del cliente ingresado");
	        }*/
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return filas;
	}
	
	/// 1 si se pudo modificar 0 sino hubo cambios en la bd
	public int eliminarCuenta(int ncuenta) {
		int filas = 0;

		Connection cn = null;
		try {
			cn = Conexion.getConexion().getSQLConexion();
			cn.setAutoCommit(false);

			Statement st = cn.createStatement();
			String query = "UPDATE cuenta SET Estado_Cuenta = 0 WHERE NumeroCuenta_Cuenta = '" + ncuenta + "'";
			filas = st.executeUpdate(query);
			cn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			}
		return filas;
		}


	public int modificarCuenta(Cuenta ct) {
	    int filas = 0;
	    Connection cn = null;
	    PreparedStatement Cliente = null;
	    PreparedStatement CuentaAct = null;


	    try {
	        cn = Conexion.getConexion().getSQLConexion();
			cn.setAutoCommit(false);
	        
	        // Verificar si el DNI_Cuenta existe en la tabla cliente
	        Cliente = cn.prepareStatement(ExisteCliente);
	        Cliente.setInt(1, ct.getDniCuenta());
	        ResultSet rs = Cliente.executeQuery();
	        
	        if (rs.next()) {
	        	
	           /// int DNI_Cliente = rs.getInt("DNI_Cliente");
	            ///System.out.println("\n\n\nmodificarCuenta, DNI_Cuenta: " + DNI_Cliente + "\n\n\n");
	            
	            // Si existe el DNI, se ejecuta la actualizaciÃ³n
	            String Update = "UPDATE cuenta SET Tipo_Cuenta = ?, Saldo_Cuenta = ? WHERE NumeroCuenta_Cuenta = ?";
	            CuentaAct = cn.prepareStatement(Update);
	            CuentaAct.setInt(1, ct.getTipoCuenta());
	            CuentaAct.setFloat(2, ct.getSaldoCuenta());
	            CuentaAct.setInt(3, ct.getNumeroCuenta());
	            
	            filas = CuentaAct.executeUpdate();
				cn.commit();
				
				if(filas>0) {
				System.out.println("Cuenta modificada correctamente.");
				}
				else {
	            System.out.println("El DNI ingresado no se encuentra en un cliente registrado");
				}
	        }
	        
	   }catch (Exception e) {
	        e.printStackTrace();
	    }
	    return filas;
	}
	
	///ASIGNAR CUENTA VERIFICA QUE EL DNI INGRESADO NO SUPERE EL LIMITE DE CUENTA ASIGNADAS Y LUEGO LA MODIFICA
	public int AsignarCuenta(int Ncuenta, int Cdni) {
	    int filas = 0;
	    int bandera=0;
	    Connection cn = null;
	    PreparedStatement CuentaAct = null;
	    PreparedStatement Cuentas = null;
	 
	   /// System.out.println("Numero de cuenta:"+Ncuenta + " / DNI Cliente: "+Cdni);
	    
	    try {
	        cn = Conexion.getConexion().getSQLConexion();
			cn.setAutoCommit(false);
				
			// Verificar si el DNI_Cuenta existe en la tabla cliente
	        Cuentas = cn.prepareStatement(CantCuentas);
	        Cuentas.setInt(1, Cdni);
	        ResultSet rs = Cuentas.executeQuery();
	        
	        /// contando los registros verifico que no sobrepase el limite de cuentas para un mismo cliente.
	        while (rs.next()) {
	        	bandera=bandera+1;
	        }
	        
	        ///System.out.println("bandera vale FINALMENTE:"+bandera);
	        
	        ///Sino super el limite de cuentas asignadas, se puede asignar una nueva a ese DNI
			if (bandera<3) {
	            String Update = "UPDATE cuenta SET DNI_Cuenta = ? WHERE NumeroCuenta_Cuenta = ?";
	            CuentaAct = cn.prepareStatement(Update);
	            CuentaAct.setInt(1, Cdni);
	            CuentaAct.setFloat(2, Ncuenta);
	            
	            filas = CuentaAct.executeUpdate();
				cn.commit();
				
				if(filas>0) {
					System.out.println("La cuenta fué asignada a un nuevo cliente correctamente.");
		        } else {
		            System.out.println("Error al reasignar una cuenta.");
				}	
				
			}
			else {System.out.println("Error al Asignar cuenta, Limite de cuentas alcanzado para el DNI ingresado");
	
			}
				
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return filas;
	}
	
	public int SacarDniPorNombreUsuarioDao(String nombreUsuario) {
		
		int dni=0;
		Connection cn = null;
	    PreparedStatement pst = null;
	    ResultSet  rs = null;
	    
	    try {
			cn = Conexion.getConexion().getSQLConexion();
			String query = " SELECT c.DNI_Cliente AS DNI FROM cliente AS c WHERE c.NombreUsuario_Cliente = ?";
			 pst = cn.prepareStatement(query);
		        pst.setString(1, nombreUsuario); // Evita inyección SQL
		        rs = pst.executeQuery();

		        if (rs.next()) { // Verificar si hay un resultado
		            dni = rs.getInt("DNI"); // Obtener el valor del campo
		        }


		} catch (Exception e) {
			e.printStackTrace();
			}
		
		return dni;
		
	}
	
	public List<TipoCuenta> obtenerTipoDeCuentaPorClienteDao(int dniCliente) {
        List<TipoCuenta> Tipocuentas = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst;
        String consulta = " SELECT tc.Descripcion_TipoCuenta AS descripcion, tc.Cod_TipoCuenta AS codTipo" +
                       " FROM cuenta AS c " +
                       " INNER JOIN tipo_cuenta AS tc " +
                       " ON c.Tipo_Cuenta = tc.Cod_TipoCuenta " +
                       " WHERE c.DNI_Cuenta = ? ";

        try {
        	
        	cn = Conexion.getConexion().getSQLConexion();
        	pst = cn.prepareStatement(consulta);
           
            pst.setInt(1, dniCliente);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                TipoCuenta tc = new TipoCuenta();
                tc.setCodTipoCuenta(rs.getInt("codTipo"));
                tc.setDescripcionTipoCuenta(rs.getString("descripcion"));
                
                Tipocuentas.add(tc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Tipocuentas;
    }
	
	public List<Movimientos> obtenerDetalleMovimientoXTipoDeCuentaDao(int dniCliente, int codTipoCuenta) {
        List<Movimientos> Listmovimientos = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst;
        String consulta = " SELECT " + 
        		" m.Fecha_Movimiento AS fechaMovimiento, " +  
        		" m.Descripcion_Movimiento AS movimiento" + 
        		" FROM " + 
        		" tipo_cuenta AS tc " + 
        		" INNER JOIN " + 
        		" cuenta AS c ON tc.Cod_TipoCuenta = c.Tipo_Cuenta " + 
        		" INNER JOIN " + 
        		" movimiento AS m ON c.NumeroCuenta_Cuenta = m.NumeroCuenta_Movimiento " + 
        		" WHERE " + 
        		" c.DNI_Cuenta=? AND tc.Cod_TipoCuenta=? ";

        try {
        	
        	cn = Conexion.getConexion().getSQLConexion();
        	pst = cn.prepareStatement(consulta);
           
        	pst.setInt(1, dniCliente);
            pst.setInt(2, codTipoCuenta);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
            	Movimientos mv = new Movimientos();
            	mv.setDescripcionMovimiento(rs.getString("movimiento"));
            	mv.setFechaMovimiento(rs.getDate("fechaMovimiento"));
                
                Listmovimientos.add(mv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Listmovimientos;
    }
	
	public int ObtenerCantidadCuentasDao() {
		Connection cn = null;
        int CantCuentas=0;
        
        try {
			cn = Conexion.getConexion().getSQLConexion();

			Statement st = cn.createStatement();
	        String query = "SELECT COUNT(*) AS CantidadTotalCuentas FROM cuenta;";
	        ResultSet rs = st.executeQuery(query);

	        if (rs.next()) { 
	            CantCuentas = rs.getInt("CantidadTotalCuentas"); // Obtener el valor
	        }

		} catch (Exception e) {
			e.printStackTrace();
			}
		
		
		return CantCuentas;
	}
	
	public int ObtenerCantidadCuentasUltTrimestreDao() {
		Connection cn = null;
        int CantCuentasUltTrim=0;
        
        try {
			cn = Conexion.getConexion().getSQLConexion();

			Statement st = cn.createStatement();
	        String query = " SELECT COUNT(*) AS CantidadCuentasUltimoTrimestre " + 
			        	   " FROM cuenta " + 
			        	   " WHERE FechaCreacion_Cuenta >= DATE_ADD(CURDATE(), INTERVAL -3 MONTH); ";
	        ResultSet rs = st.executeQuery(query);

	        if (rs.next()) { 
	        	CantCuentasUltTrim = rs.getInt("CantidadCuentasUltimoTrimestre"); 
	        }

		} catch (Exception e) {
			e.printStackTrace();
			}
		
		
		return CantCuentasUltTrim;
	}
	
	public int ObtenerCantidadPrestamosDao() {
		Connection cn = null;
        int CantPrestamos=0;
        
        try {
			cn = Conexion.getConexion().getSQLConexion();

			Statement st = cn.createStatement();
	        String query = "SELECT COUNT(*) AS TotalPrestamosSolicitados FROM prestamo ";
	        ResultSet rs = st.executeQuery(query);

	        if (rs.next()) { 
	        	CantPrestamos = rs.getInt("TotalPrestamosSolicitados"); 
	        }

		} catch (Exception e) {
			e.printStackTrace();
			}
		
		
		return CantPrestamos;
	}
	
	public int ObtenerCantidadPrestamosAceptadosDao() {
		Connection cn = null;
        int CantPrestamosAcep=0;
        
        try {
			cn = Conexion.getConexion().getSQLConexion();

			Statement st = cn.createStatement();
	        String query = " SELECT COUNT(*) AS TotalPrestamosAcep " + 
			        	   " FROM prestamo " + 
			        	   " WHERE Estado_Prestamo = 'Activo'; ";
	        ResultSet rs = st.executeQuery(query);

	        if (rs.next()) { 
	        	CantPrestamosAcep = rs.getInt("TotalPrestamosAcep"); 
	        }

		} catch (Exception e) {
			e.printStackTrace();
			}
		
		
		return CantPrestamosAcep;
	}
	
	public int ObtenerCantidadPrestamosRechazadosDao() {
		Connection cn = null;
        int CantPrestamosRechazados=0;
        
        try {
			cn = Conexion.getConexion().getSQLConexion();

			Statement st = cn.createStatement();
	        String query = " SELECT COUNT(*) AS TotalPrestamosRechazados " + 
			        	   " FROM prestamo " + 
			        	   " WHERE Estado_Prestamo = 'Inactivo'; ";
	        ResultSet rs = st.executeQuery(query);

	        if (rs.next()) { 
	        	CantPrestamosRechazados = rs.getInt("TotalPrestamosRechazados"); 
	        }

		} catch (Exception e) {
			e.printStackTrace();
			}
		
		
		return CantPrestamosRechazados;
	}
	
	public int SacarNumCuentaXCbu(String Cbu) {
		
		int cbu=0;
		Connection cn = null;
	    PreparedStatement pst = null;
	    ResultSet  rs = null;
	    
	    try {
			cn = Conexion.getConexion().getSQLConexion();
			String query = " SELECT NumeroCuenta_Cuenta AS NumCuenta FROM cuenta WHERE CBU_Cuenta = ?";
			 pst = cn.prepareStatement(query);
		        pst.setString(1, Cbu); // Evita inyección SQL
		        rs = pst.executeQuery();

		        if (rs.next()) { // Verificar si hay un resultado
		            cbu = rs.getInt("NumCuenta"); // Obtener el valor del campo
		        }


		} catch (Exception e) {
			e.printStackTrace();
			}
		
		return cbu;
		
	}

}

