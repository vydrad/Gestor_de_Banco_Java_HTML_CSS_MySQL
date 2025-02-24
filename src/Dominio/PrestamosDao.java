package Dominio;

import Entidades.Clientes;
import Entidades.Cuota;
import Entidades.Prestamo;
import InterfacesDao.InterPrestamosDao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PrestamosDao implements InterPrestamosDao {
	
	public List<Prestamo> ObtenerNumPrestamos(int dniCliente) {
	    List<Prestamo> ListPrestamos = new ArrayList<>();
	    Connection cn = null;
	    PreparedStatement pst;
	    String consulta = "SELECT *"+
	    		"FROM prestamo p "+
	    		"JOIN cuota c ON p.CodCuota_Prestamo = c.Cod_Cuota "+
	    		"WHERE p.DNI_Prestamo = ? AND Estado_Prestamo= 'Activo'";
	    try {
	        cn = Conexion.getConexion().getSQLConexion();
	        pst = cn.prepareStatement(consulta);
	        pst.setInt(1, dniCliente);

	        ResultSet rs = pst.executeQuery();

	        while (rs.next()) {
	            Prestamo ps = new Prestamo();
	            ps.setCodPrestamo(rs.getInt("Cod_Prestamo"));
	            ps.setDniPrestamo(rs.getInt("DNI_Prestamo"));
	            ps.setNumeroCuentaPrestamo(rs.getInt("NumeroCuenta_Prestamo"));
	            ps.setCodCuotaPrestamo(rs.getInt("CodCuota_Prestamo"));
	            ps.setFechaPrestamo(rs.getDate("Fecha_Prestamo"));
	            ps.setImportePedidoPrestamo(rs.getFloat("ImportePedido_Prestamo"));
	            ps.setImportePagarPrestamo(rs.getFloat("MontoMes_Prestamo"));
	            ps.setEstadoPrestamo("Pendiente");
	            ListPrestamos.add(ps);
	        }
	        System.out.println("Prestamos obtenidos: " + ListPrestamos.size()); // LOG
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return ListPrestamos;
	}

	
	public List<Prestamo> ObtenerNumPrestamoPorCuenta(int numeroCuenta) {
	    List<Prestamo> ListPrestamos = new ArrayList<>();
	    Connection cn = null;
	    PreparedStatement pst;
	    String consulta = "SELECT * " +
	            "FROM prestamo p " +
	            "JOIN cuota c ON p.CodCuota_Prestamo = c.Cod_Cuota " +
	            "WHERE p.NumeroCuenta_Prestamo = ? AND Estado_Prestamo = 'Activo'";
	    try {
	        cn = Conexion.getConexion().getSQLConexion();
	        pst = cn.prepareStatement(consulta);
	        pst.setInt(1, numeroCuenta);

	        ResultSet rs = pst.executeQuery();

	        while (rs.next()) {
	            Prestamo ps = new Prestamo();
	            ps.setCodPrestamo(rs.getInt("Cod_Prestamo"));
	            ps.setDniPrestamo(rs.getInt("DNI_Prestamo"));
	            ps.setNumeroCuentaPrestamo(rs.getInt("NumeroCuenta_Prestamo"));
	            ps.setCodCuotaPrestamo(rs.getInt("CodCuota_Prestamo"));
	            ps.setFechaPrestamo(rs.getDate("Fecha_Prestamo"));
	            ps.setImportePedidoPrestamo(rs.getFloat("ImportePedido_Prestamo"));
	            ps.setImportePagarPrestamo(rs.getFloat("MontoMes_Prestamo"));
	            ps.setEstadoPrestamo("Pendiente");
	            ListPrestamos.add(ps);
	        }
	        System.out.println("Prestamos obtenidos: " + ListPrestamos.size()); // LOG
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return ListPrestamos;
	}

	
	public List<Cuota> ObtenerNumCuota(int NumCuenta) {
	    System.out.println("Buscando cuotas para la cuenta: " + NumCuenta); // LOG

	    List<Cuota> ListCuota = new ArrayList<>();
	    Connection cn = null;
	    PreparedStatement pst;
	    String consulta = "SELECT \r\n" + 
	    		"    c.NumeroCuotaPagar_Cuota AS NumCuota, " + 
	    		"    c.Monto_Cuota AS MontoCuota " + 
	    		"FROM  " + 
	    		"    prestamo p  " + 
	    		"JOIN  " + 
	    		"    cuota c ON p.CodCuota_Prestamo = c.Cod_Cuota " + 
	    		"JOIN  " + 
	    		"    cliente cl ON p.DNI_Prestamo = cl.DNI_Cliente " + 
	    		"WHERE  " + 
	    		"    c.Estado_Cuota = 'Pendiente'  " + 
	    		"    AND p.Estado_Prestamo = 'Activo'  " + 
	    		"    AND p.NumeroCuenta_Prestamo = ?";

	    try {
	        cn = Conexion.getConexion().getSQLConexion();
	        pst = cn.prepareStatement(consulta);
	        pst.setInt(1, NumCuenta);

	        ResultSet rs = pst.executeQuery();

	        while (rs.next()) {
	            Cuota c = new Cuota();
	            c.setNumeroCuotaPagar(rs.getInt("NumCuota"));
	            c.setMontoCuota(rs.getFloat("MontoCuota"));
	            System.out.println("Cuota encontrada: NumCuota=" + rs.getInt("NumCuota") + ", Monto=" + rs.getFloat("MontoCuota")); // LOG
	            ListCuota.add(c);
	        }
	        System.out.println("Total cuotas encontradas: " + ListCuota.size()); // LOG
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return ListCuota;
	}
	
	public List<Clientes> ObtenerClientePrestamo(){
		
		List<Clientes> ListCliente = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst;
        String consulta = "  SELECT " + 
        		" cl.DNI_Cliente AS DNI, " + 
        		" cl.Nombre_Cliente AS nombre, " + 
        		" cl.Apellido_Cliente AS apellido " +  
        		" FROM cliente cl " + 
        		" INNER JOIN prestamo AS p ON cl.DNI_Cliente = p.DNI_Prestamo " + 
        		" WHERE P.Estado_Prestamo = 'Pendiente'; ";

        try {
        	
        	cn = Conexion.getConexion().getSQLConexion();
        	pst = cn.prepareStatement(consulta);
            
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
            	Clientes c = new Clientes();
            	c.setDni(rs.getInt("DNI"));
            	c.setNombre(rs.getString("nombre"));
            	c.setApellido(rs.getString("apellido"));
            	
            	ListCliente.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

		return ListCliente;
	}
	
	public int ObtenerNumeroCuenta(int Dni) {
		
		int numCuenta = 0;
		Connection cn = null;
	    PreparedStatement pst = null;
	    ResultSet  rs = null;
	    
	    try {
			cn = Conexion.getConexion().getSQLConexion();
			String query = " SELECT " + 
						   " cu.NumeroCuenta_Cuenta AS numCuenta " + 
						   " FROM " + 
						   " cuenta cu " + 
						   " INNER JOIN " + 
						   " cliente cl ON cu.DNI_Cuenta = cl.DNI_Cliente " + 
						   " INNER JOIN " + 
						   " prestamo pr ON cu.NumeroCuenta_Cuenta = pr.NumeroCuenta_Prestamo " + 
						   " WHERE "
						 + " Estado_Prestamo='Pendiente' AND cl.DNI_Cliente = ? ";
			 pst = cn.prepareStatement(query);
		        pst.setInt(1, Dni);
		        rs = pst.executeQuery();

		        if (rs.next()) {
		        	numCuenta = rs.getInt("numCuenta");
		        }


		} catch (Exception e) {
			e.printStackTrace();
			}
		return numCuenta;
		
	}
	
	public List<Prestamo> ObtenerInfoPrestamo(int numCuenta){
		
		List<Prestamo> ListPrestamo = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst;
        String consulta = "  SELECT " + 
        		" p.Fecha_Prestamo AS FechaSolicitud, " + 
        		" p.ImportePedido_Prestamo AS MontoPedido, " + 
        		" p.ImportePagar_Prestamo AS MontoTotal, " + 
        		" p.MontoMes_Prestamo AS montoMes " + 
        		" FROM prestamo AS p " + 
        		" INNER JOIN cuota AS c ON p.CodCuota_Prestamo = c.Cod_Cuota " + 
        		" WHERE P.Estado_Prestamo = 'Pendiente' AND p.NumeroCuenta_Prestamo= ?  ";

        try {
        	
        	cn = Conexion.getConexion().getSQLConexion();
        	pst = cn.prepareStatement(consulta);
           
        	pst.setInt(1, numCuenta);
            
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
            	Prestamo p = new Prestamo();
            	p.setFechaPrestamo(rs.getDate("FechaSolicitud"));
            	p.setImportePedidoPrestamo(rs.getFloat("MontoPedido"));
            	p.setImportePagarPrestamo(rs.getFloat("MontoTotal"));
            	p.setMontoMesPrestamo(rs.getFloat("montoMes"));
            	
            	ListPrestamo.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

		return ListPrestamo;
	}
	
	public int ObtenerNumCuentaPrestamoDAO(int codPrestamoPagar) {
		
		int numCuenta = 0;
		Connection cn = null;
        PreparedStatement pst;
        String consulta = " SELECT *" +
        		" FROM prestamo " +
        		" WHERE Cod_Prestamo = ? ";
        
        try {
        	cn = Conexion.getConexion().getSQLConexion();
        	pst = cn.prepareStatement(consulta);
        	
        	pst.setInt(1, codPrestamoPagar);
        	
        	ResultSet rs = pst.executeQuery();
        	
        	while (rs.next()) {
        		numCuenta = rs.getInt("NumeroCuenta_Prestamo");
        	}
        	
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return numCuenta;
	}
	
	
	public List<Cuota> ObtenerInfoCuota(int numCuenta){
		List<Cuota> ListCuota = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst;
        String consulta = " SELECT " + 
        		" NumeroCuotaPagar_Cuota AS NumCuota, " + 
        		" Monto_Cuota " + 
        		" FROM cuota AS c  " + 
        		" INNER JOIN prestamo AS p ON p.CodCuota_Prestamo = c.Cod_Cuota " + 
        		" WHERE P.Estado_Prestamo = 'Pendiente' AND p.NumeroCuenta_Prestamo= ?";

        try {
        	
        	cn = Conexion.getConexion().getSQLConexion();
        	pst = cn.prepareStatement(consulta);
           
        	pst.setInt(1, numCuenta);
        	
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
            	
            	Cuota c = new Cuota();
            	c.setNumeroCuotaPagar(rs.getInt("NumCuota"));
            	c.setMontoCuota(rs.getFloat("Monto_Cuota"));
            	
            	ListCuota.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return ListCuota;
	}

	
		public boolean autorizarPrestamosPorNumeroCuenta(int numeroCuenta) {
		String sql = "UPDATE prestamo SET Estado_Prestamo = 'Activo' WHERE NumeroCuenta_Prestamo = ?";
        boolean estado = false;

		Connection cn = null;
		try {
			cn = Conexion.getConexion().getSQLConexion();
			
			PreparedStatement stmt = cn.prepareStatement(sql);
	        stmt.setInt(1, numeroCuenta);
	        
	        int filasActualizadas = stmt.executeUpdate();
	        cn.commit();
	        if (filasActualizadas > 0) {
	        	return estado=true;
	        }

			

		} catch (Exception e) {
			e.printStackTrace();
			return estado=false;
			}
		return estado;
    }
		
		
		public int VerificarPedidoPrestamo(Prestamo presta) {
		    PreparedStatement select = null;
			Connection cn = null;
			ResultSet rs = null;
			
			try {
	        	 cn = Conexion.getConexion().getSQLConexion();
	             cn.setAutoCommit(false); // Iniciar transacción

	             // Consulta para validar que no existan préstamos activos o pendientes para el cliente
	             String selectQuery = "SELECT 1 FROM prestamo WHERE NumeroCuenta_Prestamo = ? AND Estado_Prestamo IN ('Pendiente', 'Activo')";
	             select = cn.prepareStatement(selectQuery);
	             select.setInt(1, presta.getNumeroCuentaPrestamo());
	             rs = select.executeQuery();

	             if (rs.next()) {
	                 // Si hay resultados, el cliente ya tiene un préstamo activo o pendiente
	                 System.out.println("El cliente en ESTA CUENTA ya tiene un préstamo activo o pendiente. No se puede procesar.");
	                 return 0; // Salir sin realizar el insert
	             }
			
			} catch (SQLException e) {
	            e.printStackTrace();
	        }
			return 1;
		}
		
		public int PedidoPrestamo(Prestamo presta) {
			
			int filas = 0;
	        PreparedStatement realizarDeposito = null;
	        PreparedStatement select = null;
	        ResultSet rs = null;
			Connection cn = null;
	        PreparedStatement pst;
	        
	        try {
	        	 cn = Conexion.getConexion().getSQLConexion();
	             cn.setAutoCommit(false); // Iniciar transacción

	        /*     // Consulta para validar que no existan préstamos activos o pendientes para el cliente
	             String selectQuery = "SELECT 1 FROM prestamo WHERE NumeroCuenta_Prestamo = ? AND Estado_Prestamo IN ('Pendiente', 'Activo')";
	             select = cn.prepareStatement(selectQuery);
	             select.setInt(1, presta.getNumeroCuentaPrestamo());
	             rs = select.executeQuery();

	             if (rs.next()) {
	                 // Si hay resultados, el cliente ya tiene un préstamo activo o pendiente
	                 System.out.println("El cliente en ESTA CUENTA ya tiene un préstamo activo o pendiente. No se puede procesar.");
	                 return 0; // Salir sin realizar el insert
	             }*/

	             // Si no hay resultados, proceder con el INSERT
	             String insertQuery = "INSERT INTO prestamo (Cod_prestamo, DNI_Prestamo, NumeroCuenta_Prestamo, CodCuota_Prestamo, "
	                     + "Fecha_Prestamo, ImportePedido_Prestamo, ImportePagar_Prestamo, PlazoMeses_Prestamo, MontoMes_Prestamo, Estado_Prestamo) "
	                     + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        	pst = cn.prepareStatement(insertQuery);
	        	
	        	pst.setInt(1,presta.getCodPrestamo());
	        	pst.setInt(2,presta.getDniPrestamo());
	        	pst.setInt(3,presta.getNumeroCuentaPrestamo());
	        	pst.setInt(4,presta.getCodCuotaPrestamo());
	        	pst.setDate(5,presta.getFechaPrestamo());
	        	pst.setFloat(6,presta.getImportePedidoPrestamo());
	        	pst.setFloat(7,presta.getImportePagarPrestamo());
	        	pst.setInt(8,presta.getPlazoMesesPrestamo());
	        	pst.setFloat(9,presta.getMontoMesPrestamo());
	        	pst.setString(10, "Pendiente");
	        	
		        // Obtener el nuevo cï¿½digo de movimiento
	            String obtenerCodMovimiento = "SELECT MAX(Cod_Movimiento) + 1 FROM movimiento";
	            PreparedStatement obtenerCodMovStmt = cn.prepareStatement(obtenerCodMovimiento);
	            ResultSet rs1 = obtenerCodMovStmt.executeQuery();
	            int nuevoCodMovimiento = 1;
	            if (rs1.next()) {
	                nuevoCodMovimiento = rs1.getInt(1);
	            }
	            
	            
	         // Registrar depï¿½sito del pedido de prestamo
	            String queryRealizarDeposito = 
	                    "INSERT INTO movimiento (Cod_Movimiento, CodTipo_Movimiento, NumeroCuenta_Movimiento, Fecha_Movimiento, Descripcion_Movimiento) " +
	                    "VALUES (?, 1, ?, CURDATE(), 'Pedido de prestamo')";
	                realizarDeposito = cn.prepareStatement(queryRealizarDeposito);
	                realizarDeposito.setInt(1, nuevoCodMovimiento);
	                realizarDeposito.setInt(2,presta.getNumeroCuentaPrestamo() );
	                realizarDeposito.executeUpdate();
	            
				
				filas =pst.executeUpdate();
				cn.commit();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
			return filas;
		}
		
		public int PedidoPrestamoAgregandoCuota(Cuota cuota) {
			
			int filas = 0;
			Connection cn = null;
	        PreparedStatement pst;
	        String consulta = " INSERT INTO cuota (Cod_Cuota, NumeroCuotaPagar_Cuota, Monto_Cuota,"
	        		+ "FechaVencimiento_Cuota, FechaPago_Cuota, Estado_Cuota)"
	        		+ "VALUES (?,?,?,?,?,?)";
	     
	        try {
	        	cn = Conexion.getConexion().getSQLConexion();
	        	cn.setAutoCommit(false);
	        	pst = cn.prepareStatement(consulta);
	        	
        		
	        	pst.setInt(1,cuota.getCodCuota());
	        	pst.setInt(2,cuota.getNumeroCuotaPagar());
	        	pst.setFloat(3,cuota.getMontoCuota());
	        	pst.setDate(4,cuota.getFechaVencimientoCuota());
	        	pst.setDate(5,cuota.getFechaPagoCuota());
	        	pst.setString(6,"Pendiente");
				
				filas =pst.executeUpdate();
				cn.commit();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
			return filas;
		}
		
		public int ContarCodCuotas() {
			Connection cn = null;
		    PreparedStatement pst = null;
		    ResultSet  rs = null;
		    int cont=1;
		    try {
				cn = Conexion.getConexion().getSQLConexion();
				String query ="SELECT * FROM cuota";

				pst = cn.prepareStatement(query);
			    rs = pst.executeQuery();
			    
			    while (rs.next()) { // Verificar si hay un resultado
			        cont++;
			    }


			} catch (Exception e) {
				e.printStackTrace();
				}
			return cont;
		}
		
		
		public boolean RechazarPrestamosPorNumeroCuenta(int numeroCuenta) {
			String sql = "UPDATE prestamo SET Estado_Prestamo = 'Inactivo' WHERE NumeroCuenta_Prestamo = ?";
	        boolean estado = false;

			Connection cn = null;
			try {
				cn = Conexion.getConexion().getSQLConexion();
				
				PreparedStatement stmt = cn.prepareStatement(sql);
		        stmt.setInt(1, numeroCuenta);
		        
		        int filasActualizadas = stmt.executeUpdate();
		        cn.commit();
		        if (filasActualizadas > 0) {
		        	return estado=true;
		        }

				

			} catch (Exception e) {
				e.printStackTrace();
				return estado=false;
				}
			return estado;
	    }
		
	
		public boolean realizarPagoCuota(int codPrestamo, int NumeroCuota, String cuentaDebito) {
		    Connection cn = null;
		    boolean pagoExitoso = false;

		    try {
		        cn = Conexion.getConexion().getSQLConexion();
		        cn.setAutoCommit(false);

		        Date actual = Date.valueOf(LocalDate.now());

		        // Obtengo el proximo codigo de movimiento
		        int codMovimiento = getProximoCodMovimiento(cn);

		        /// Obtengo la info de esa cuota
		        Cuota cuota = getCuotaPorCodCuota(cn, codPrestamo);

		        // Valido si es la ultima cuota
		        boolean esUltimaCuota = verificarUltimaCuota(cn, codPrestamo, cuota.getNumeroCuotaPagar());

		        /// Actualizar cuota o préstamo según corresponda, si actualizo la ultima cuota tengo que actualizar el prestamo como finalizado
		        actualizarCuotaOPrestamo(cn, codPrestamo, cuota, esUltimaCuota,actual);
		        // Actualizar Prestamo a Finalizado si es la ultima cuota
		        actualizarPrestamo(cn, codPrestamo, esUltimaCuota);
		       
		        /// Registro el movimiento
		        registrarMovimiento(cn, codMovimiento, cuentaDebito, cuota, actual, codPrestamo);

		        // Actualizo el saldo de la cuenta
		        actualizarSaldoCuenta(cn, codPrestamo, cuentaDebito);

		        // Confirmo la transaccion 
		        cn.commit();
		        pagoExitoso = true;

		    } catch (SQLException e) {
		        e.printStackTrace();
		        if (cn != null) {
		            try {
		                cn.rollback();
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		            }
		        }
		    } 

		    return pagoExitoso;
		}
		
		private int getProximoCodMovimiento(Connection cn) throws SQLException {
			int cont =1;
			  String query = "SELECT * FROM  movimiento";
		    try (Statement st = cn.createStatement();
		         ResultSet rs = st.executeQuery(query)) {
		        while (rs.next()) {
		            cont++;
		        }
		    }
		    return cont; 
		}

		private Cuota getCuotaPorCodCuota(Connection cn, int codCuota) throws SQLException {
		    String query = "SELECT * FROM cuota WHERE Cod_Cuota = ?";
		    try (PreparedStatement pst = cn.prepareStatement(query)) {
		        pst.setInt(1, codCuota);
		        try (ResultSet rs = pst.executeQuery()) {
		            if (rs.next()) {
		                Cuota cuota = new Cuota();
		                cuota.setCodCuota(rs.getInt("Cod_Cuota"));
		                cuota.setNumeroCuotaPagar(rs.getInt("NumeroCuotaPagar_Cuota"));
		                cuota.setMontoCuota(rs.getFloat("Monto_Cuota"));
		                cuota.setFechaVencimientoCuota(rs.getDate("FechaVencimiento_Cuota"));
		                return cuota;
		            }
		        }
		    }
		    throw new SQLException("Cuota no encontrada para el código: " + codCuota);
		}
		
		private boolean verificarUltimaCuota(Connection cn, int codPrestamo, int numCuotaActual) throws SQLException {
		    String query = "SELECT PlazoMeses_Prestamo FROM prestamo WHERE Cod_Prestamo = ?";
		    try (PreparedStatement pst = cn.prepareStatement(query)) {
		        pst.setInt(1, codPrestamo);
		        try (ResultSet rs = pst.executeQuery()) {
		            if (rs.next()) {
		                return numCuotaActual >= rs.getInt("PlazoMeses_Prestamo");
		            }
		        }
		    }
		    return false;
		}

		private void actualizarCuotaOPrestamo(Connection cn, int codCuota, Cuota cuota, boolean esUltimaCuota, Date actual) throws SQLException {
		    int filas= 0;
			String query;
		    Date FechaProxVencimiento =FechaProximoVencimiento(actual);
		    if (esUltimaCuota) {
		        query = "UPDATE cuota SET Estado_Cuota = 'Pagada', "
		        		+ " FechaVencimiento_Cuota = ? , "
		        		+ " FechaPago_Cuota = ? WHERE Cod_Cuota = ? ";
		    } else {
		        query = "UPDATE cuota SET NumeroCuotaPagar_Cuota = ?, "
		        		+ " FechaVencimiento_Cuota= ?, "
		        		+ " FechaPago_Cuota= ? WHERE Cod_Cuota = ? ";
		    }
		    try (PreparedStatement pst = cn.prepareStatement(query)) {
		        if (esUltimaCuota) {
		        	pst.setDate(1, actual);
		        	pst.setDate(2, actual);
		            pst.setInt(3, codCuota);
		        } else {
		            pst.setInt(1, cuota.getNumeroCuotaPagar() + 1);
		            pst.setDate(2, FechaProxVencimiento);
		            pst.setDate(3, actual);
		            pst.setInt(4, codCuota);
		        }
		        filas=pst.executeUpdate();
		     
		        if(filas >0) {
		        cn.commit();
		        }
		        else {
		        cn.rollback();
		        }
		    }
		}
		
		private Date FechaProximoVencimiento(Date actual) {
			 Calendar calendario = Calendar.getInstance();
	            calendario.setTime(actual);
	            int dia =calendario.get(Calendar.DAY_OF_MONTH);
	            int mes =calendario.get(Calendar.MONTH);
	            int anio = calendario.get(Calendar.YEAR);
	            
	            if (mes == 11) { // Diciembre (11 porque los meses son 0-indexed)
	                mes = 0; // Enero
	                anio++;
	            } else {
	            	mes++;
	            }
	            calendario.set(Calendar.YEAR, anio);
	            calendario.set(Calendar.MONTH, mes);
	            calendario.set(Calendar.DAY_OF_MONTH, dia);

	            // Convertir el Calendar a java.sql.Date
	            Date FechaVencimiento = new Date(calendario.getTimeInMillis());
			
	            return FechaVencimiento;
		}
		
		
		private void actualizarPrestamo(Connection cn, int codCuota, boolean esUltimaCuota) throws SQLException {
			int filas=0;
		    String query;
		    if (esUltimaCuota) {
		    	 query = "UPDATE prestamo SET Estado_Prestamo='Finalizado' WHERE Cod_Prestamo = ?";
		    
		    try (PreparedStatement pst = cn.prepareStatement(query)) {
		    	pst.setInt(1, codCuota);
		    	filas=pst.executeUpdate();
			    if(filas >0) {
			    cn.commit();
			    }
			    else {
			    cn.rollback();
			    }
		    }
		}
		}
		
		
		private void registrarMovimiento(Connection cn, int codMovimiento, String cuentaDebito, Cuota cuota, Date actual,int codPrestamo) throws SQLException {
			int filas= 0;
			String query = "INSERT INTO movimiento (Cod_Movimiento, CodTipo_Movimiento, NumeroCuenta_Movimiento, Fecha_Movimiento, " +
		                   "Importe_Movimiento, Descripcion_Movimiento) " +
		                   "VALUES (?, ?, ?, ?, ?, ?)";
			int NumeroCuentaMovimiento= NumeroCuentaPrestamoByCodPrestamo(cn,codPrestamo);
		    try (PreparedStatement pst = cn.prepareStatement(query)) {
		        pst.setInt(1, codMovimiento);
		        pst.setInt(2, 2); // Código de tipo de movimiento
		        pst.setInt(3, NumeroCuentaMovimiento);
		        pst.setDate(4, actual);
		        pst.setFloat(5, cuota.getMontoCuota());
		        pst.setString(6, "Extracción por pago del CodCuota: "+cuota.getCodCuota()
		        		+ " Numero de pago de cuota: " + cuota.getNumeroCuotaPagar()
		        		+ " Fecha de Pago:"+actual
		        		+ " De la cuenta: "+ NumeroCuentaMovimiento
		        		+ "  y CBU: "+cuentaDebito);
		        filas=pst.executeUpdate();
		        if(filas >0) {
		        cn.commit();
		        System.out.println("Extracción por pago del CodCuota: "+cuota.getCodCuota()
		        		+ " Numero de pago de cuota: " + cuota.getNumeroCuotaPagar()
		        		+ " Fecha de Pago:"+actual
		        		+ " De la cuenta: "+ NumeroCuentaMovimiento
		        		+ "  y CBU: "+cuentaDebito);
		        }
		        else {
		        cn.rollback();
		        }
		    }
		}
		
		private int NumeroCuentaPrestamoByCodPrestamo(Connection cn, int codPrestamo) throws SQLException {
			int NumeroCuentaPrestamo=0;
			
			String query= "SELECT NumeroCuenta_Prestamo FROM prestamo WHERE Cod_Prestamo = ?";
		
		    try (PreparedStatement pst = cn.prepareStatement(query)) {
		    	pst.setInt(1, codPrestamo);
		        try (ResultSet rs = pst.executeQuery()) {
		    	
			        if (rs.next()) {
			            NumeroCuentaPrestamo=rs.getInt(("NumeroCuenta_Prestamo"));
			        }
			    }
		    }
			return NumeroCuentaPrestamo;
		}

		private void actualizarSaldoCuenta(Connection cn, int codCuota, String cuentaDebito) throws SQLException {
			int filas=0;
		    String query = "UPDATE cuenta SET Saldo_Cuenta = Saldo_Cuenta - " +
		                   "(SELECT Monto_Cuota FROM cuota WHERE Cod_Cuota = ?) WHERE CBU_Cuenta = ?";
		    try (PreparedStatement pst = cn.prepareStatement(query)) {
		        pst.setInt(1, codCuota);
		        pst.setString(2, cuentaDebito);
		        filas=pst.executeUpdate();
		        if(filas >0) {
		        cn.commit();
		        }
		        else {
		        cn.rollback();
		        }
		    }
		}
		
		
		
}
