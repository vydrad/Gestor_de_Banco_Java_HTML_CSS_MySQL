package Dominio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entidades.Clientes;
import Entidades.Localidad;
import Entidades.Provincia;
import InterfacesDao.InterClientesDao;

public class ClienteDao implements InterClientesDao{
	
	
	private String consulta="Select * From cliente Where Estado_Cliente=1";

	private String readAll="Select * From provincia";
	
	public List<Clientes> ListarClientesDao(){
		
		Connection cn = null;
		PreparedStatement pst;
		List<Clientes> ListaClientes = new ArrayList<>();
		
		try {
			cn = Conexion.getConexion().getSQLConexion();
			pst = cn.prepareStatement(consulta);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				
				Clientes cliente = new Clientes();
				
				if(rs.getBoolean("Estado_Cliente")) {				
					cliente.setNombre(rs.getString("Nombre_Cliente"));
					cliente.setApellido(rs.getString("Apellido_Cliente"));
					cliente.setDni(rs.getInt("DNI_Cliente"));
					cliente.setNacionalidad(rs.getString("Nacionalidad_Cliente"));
					cliente.setCorreo(rs.getString("Email_Cliente"));
					cliente.setCuil(rs.getString("CUIL_Cliente"));
					cliente.setFechaNac(rs.getDate("FechaNacimiento_Cliente"));
					cliente.setDireccion(rs.getString("Direccion_Cliente"));
					cliente.setSexo(rs.getString("Sexo_Cliente"));
					cliente.setTelefono(rs.getString("Telefono_Cliente"));
					
					ListaClientes.add(cliente);
				}				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return ListaClientes;
		
	}
	
	public List<Clientes> BuscarPorDni(int dni){
	    Connection cn = null;
	    PreparedStatement pst;
	    List<Clientes> ListaFiltro = new ArrayList<>();

	    try {
	        cn = Conexion.getConexion().getSQLConexion();
	        pst = cn.prepareStatement("Select * From cliente Where DNI_Cliente LIKE ?");
	        pst.setString(1, dni + "%");
	        ResultSet rs = pst.executeQuery();

	        while(rs.next()) {
	            Clientes cliente = new Clientes();    
	            
	            if(rs.getBoolean("Estado_Cliente")) {				
					cliente.setNombre(rs.getString("Nombre_Cliente"));
		            cliente.setApellido(rs.getString("Apellido_Cliente"));
		            cliente.setDni(rs.getInt("DNI_Cliente"));
		            cliente.setNacionalidad(rs.getString("Nacionalidad_Cliente"));
		            cliente.setCorreo(rs.getString("Email_Cliente"));
		            cliente.setCuil(rs.getString("CUIL_Cliente"));
					cliente.setFechaNac(rs.getDate("FechaNacimiento_Cliente"));
					cliente.setDireccion(rs.getString("Direccion_Cliente"));
					cliente.setSexo(rs.getString("Sexo_Cliente"));
					cliente.setTelefono(rs.getString("Telefono_Cliente"));
		            
		            ListaFiltro.add(cliente);
	            }
	        }

	    } catch(Exception e) {
	        e.printStackTrace();
	    }

	    return ListaFiltro;
	}
	
	public int EliminarClientesDao(int dni) {
		int filas=0;
		Connection cn = null;
		
		try {
			cn= Conexion.getConexion().getSQLConexion();
			cn.setAutoCommit(false);
			Statement st = cn.createStatement();
			String Consulta = "UPDATE banco.cliente SET Estado_Cliente = 0 WHERE DNI_Cliente = '" + dni + "'";
			filas=st.executeUpdate(Consulta);	
			cn.commit();
					
		}catch(Exception e) {
			e.printStackTrace();
		}
		return filas;
	
	}
	
	public int ModificarCliente(Clientes cliente) {
	    int filas = 0;
	    Connection cn = null;

	    try {
	        cn = Conexion.getConexion().getSQLConexion();
	        cn.setAutoCommit(false);
	        Statement st = cn.createStatement();

	        String consulta = "UPDATE cliente SET Nombre_Cliente =  '" + cliente.getNombre() + "', Apellido_Cliente =  '" + cliente.getApellido() + "', Nacionalidad_Cliente =  '" + cliente.getNacionalidad() + "', Email_Cliente =  '" + cliente.getCorreo() + "', FechaNacimiento_Cliente = '" + cliente.getFechaNac() + "', Direccion_Cliente = '" + cliente.getDireccion() + "', Sexo_Cliente = '" + cliente.getSexo() + "', Telefono_Cliente = '" + cliente.getTelefono() +  "' WHERE DNI_Cliente = '" + cliente.getDni() + "'";
	        
	        filas = st.executeUpdate(consulta);
	        cn.commit();

	    } catch (Exception e) {
	        try {
	            if (cn != null) cn.rollback();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        e.printStackTrace();
	    }
	    return filas;
	}

	
	public int AgregarClienteDao(Clientes cliente) {
	    int filas = 0;
	    Connection cn = null;
	    PreparedStatement st = null;
	    
	    try {
	        cn = Conexion.getConexion().getSQLConexion();
	        cn.setAutoCommit(false); // Iniciar transaccion

	        // Insertar el usuario
	        String consultaUsuario = "INSERT INTO usuario (Nombre_Usuario, Pass_Usuario, Administrador_Usuario, Estado_Usuario) "
	                               + "VALUES (?, ?, ?, ?)";

	        st = cn.prepareStatement(consultaUsuario);
	        st.setString(1, cliente.getNombreUsuario());
	        st.setString(2, cliente.getContraseniaaUsuario());
	        st.setInt(3, 0);  // No administrador
	        st.setBoolean(4, true);  // Estado activo
	        
	        filas = st.executeUpdate();
	        cn.commit();

	        // Confirmar si la insercion de usuario fue exitosa
	        if (filas > 0) {
	            int codUsuario = obtenerUltimoCodUsuario();

	            // Insertar el cliente
	            String consultaCliente = "INSERT INTO cliente (DNI_Cliente, CodUsuario_Cliente, CUIL_Cliente, Provincia_Cliente, Localidad_Cliente, NombreUsuario_Cliente, Pass_Cliente, Nombre_Cliente, Apellido_Cliente, Sexo_Cliente, Nacionalidad_Cliente, FechaNacimiento_Cliente, Direccion_Cliente, Email_Cliente, Telefono_Cliente, Estado_Cliente) "
	                                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	            
	            st = cn.prepareStatement(consultaCliente);

	            st.setInt(1, cliente.getDni());
	            st.setInt(2, codUsuario);
	            st.setString(3, cliente.getCuil());
	            st.setString(4, cliente.getPrivincia());
	            st.setString(5, cliente.getLocalidad());
	            st.setString(6, cliente.getNombreUsuario());
	            st.setString(7, cliente.getContraseniaaUsuario());
	            st.setString(8, cliente.getNombre());
	            st.setString(9, cliente.getApellido());
	            st.setString(10, cliente.getSexo());
	            st.setString(11, cliente.getNacionalidad());
	            st.setDate(12, cliente.getFechaNac());
	            st.setString(13, cliente.getDireccion());
	            st.setString(14, cliente.getCorreo());
	            st.setString(15, cliente.getTelefono());
	            st.setBoolean(16, true);
	            
	            filas = st.executeUpdate();
	            cn.commit();
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if (cn != null) cn.rollback();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	    return filas;
	}


	
	public int obtenerUltimoCodUsuario() {
	    int ultimoCodUsuario = 0;
	    Connection cn = null;
	    PreparedStatement pst = null;
	    ResultSet rs = null;

	    try {
	        cn = Conexion.getConexion().getSQLConexion();
	        cn.setAutoCommit(false);

	        String consulta = "SELECT MAX(Cod_Usuario) AS maxCod FROM banco.usuario";
	        pst = cn.prepareStatement(consulta);

	        rs = pst.executeQuery();
	        cn.commit();

	        if (rs.next()) {
	            ultimoCodUsuario = rs.getInt("maxCod");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return ultimoCodUsuario;
	}

	
	
	public List <Provincia> ListaProvincia(){

		List<Provincia> listaProv = new ArrayList<>();
		PreparedStatement pst;
		
		try {
			Connection conexion = Conexion.getConexion().getSQLConexion();
			pst = conexion.prepareStatement(readAll);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Provincia prov = new Provincia();
				prov.setCodProvincia(rs.getString("Cod_Provincia"));
				prov.setDescripcionProvincia(rs.getString("Descripcion_Provincia"));

				listaProv.add(prov);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaProv;	
		
	}

	
	
	public List <Localidad> ListaLocalidad(String codProvincia){

		List<Localidad> listaLoc = new ArrayList<>();
		PreparedStatement pst;
		
		try {
			Connection conexion = Conexion.getConexion().getSQLConexion();
			pst = conexion.prepareStatement("Select Cod_Localidad AS localidad, Descripcion_Localidad AS descripcion From localidad Where CodProvincia_Localidad ='" + codProvincia + "'");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Localidad loc = new Localidad();
				loc.setCodLocalidad(rs.getString("localidad"));
				loc.setDescripcionLocalidad(rs.getString("descripcion"));

				listaLoc.add(loc);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaLoc;	
		
	}
	
	public boolean ExisteDniDao(int dni) {
		
		boolean existe = false;
		PreparedStatement pst;
	    String query = "Select * From cliente Where DNI_Cliente = ?";
	    
	    try {
			Connection conexion = Conexion.getConexion().getSQLConexion();
			pst = conexion.prepareStatement(query);
			pst.setInt(1, dni);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				existe=true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return existe;
	}
	
	public boolean ExisteCuilDao(String cuil) {
		
		boolean existe = false;
		PreparedStatement pst;
	    String query = "Select * From cliente Where CUIL_Cliente = ?";
	    
	    try {
			Connection conexion = Conexion.getConexion().getSQLConexion();
			pst = conexion.prepareStatement(query);
			pst.setString(1, cuil);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				existe=true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return existe;
	}
	
	public List<Clientes> obtenerDatosClientePorUsuario(String nombreUsuario) {
        List<Clientes> datosCliente = new ArrayList<>();
        PreparedStatement pst;

        		String sql = " SELECT "+
                " c.Nombre_Cliente AS Nombre,"+
                " c.Apellido_Cliente AS Apellido,"+
                " c.DNI_Cliente AS DNI,"+
                " c.Cuil_Cliente AS Cuil,"+
                " c.Sexo_Cliente AS Sexo,"+
                " c.Nacionalidad_Cliente AS Nacionalidad,"+
                " c.FechaNacimiento_Cliente AS FechaNacimiento,"+
                " c.Direccion_Cliente AS Direccion,"+
                " c.Email_Cliente AS Email, "+
                " c.Telefono_Cliente AS Telefono,"+
                " c.NombreUsuario_Cliente AS NombreUsuario, "+
                "  p.Descripcion_Provincia AS Provincia, "+
                "  l.Descripcion_Localidad AS Localidad "+
                " FROM "+
                	" cliente AS c "+
                " INNER JOIN "+
                	" usuario AS u "+
                " ON "+
                " c.CodUsuario_Cliente = u.Cod_Usuario "+
                " AND c.NombreUsuario_Cliente = u.Nombre_Usuario "+
                " INNER JOIN provincia AS p ON c.Provincia_Cliente = p.Cod_Provincia " +
                " INNER JOIN localidad AS l ON p.Cod_Provincia =l.CodProvincia_Localidad "+
                " WHERE "+
                " u.Nombre_Usuario = ? ";

        try {
        		Connection conexion = Conexion.getConexion().getSQLConexion();
    			pst = conexion.prepareStatement(sql);
    			pst.setString(1, nombreUsuario);
    			ResultSet rs = pst.executeQuery();	

    			while(rs.next()) {
    			Clientes cs = new Clientes();
    			
    			cs.setNombre(rs.getString("Nombre"));
    			cs.setApellido(rs.getString("Apellido"));
    			cs.setDni(rs.getInt("DNI"));
    			cs.setNacionalidad(rs.getString("Nacionalidad"));
    			cs.setCorreo(rs.getString("Email"));
    			cs.setCuil(rs.getString("Cuil"));
    			cs.setFechaNac(rs.getDate("FechaNacimiento"));
    			cs.setDireccion(rs.getString("Direccion"));
    			cs.setSexo(rs.getString("Sexo"));
    			cs.setTelefono(rs.getString("Telefono"));
    			cs.setPrivincia(rs.getString("Provincia"));
    			cs.setLocalidad(rs.getString("Localidad"));
    			
    			datosCliente.add(cs);
    				
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return datosCliente;
    }
	
	public List<Clientes> obtenerResumenClientesDao() {
        List<Clientes> Listclientes = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst;
        String query =  "SELECT " + 
		        		" c.Nombre_Cliente AS Cliente, " + 
		        		" COUNT(DISTINCT cu.DNI_Cuenta) AS NumeroCuentas, " + 
		        		" COUNT(DISTINCT p.DNI_Prestamo) AS PrestamosActivos " + 
		        		" FROM cliente c " + 
		        		" INNER JOIN cuenta cu ON c.DNI_Cliente = cu.DNI_Cuenta " + 
		        		" INNER JOIN prestamo p ON c.DNI_Cliente = p.DNI_Prestamo "
		        		+ "WHERE p.Estado_Prestamo = 'Activo' " + 
		        		" GROUP BY c.Nombre_Cliente;";
        
        try {
        	
        	cn = Conexion.getConexion().getSQLConexion();
        	pst = cn.prepareStatement(query);
           
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
            	Clientes cl = new Clientes();
            	cl.setNombre(rs.getString("Cliente"));
            	cl.setNumeroCuentas(rs.getInt("NumeroCuentas"));
            	cl.setPrestamosActivos(rs.getInt("PrestamosActivos"));

            	Listclientes.add(cl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return Listclientes;
    }
	
	
	public List<Clientes> obtenerResumenClientesFiltradoDao(int montoMinimo) {
        List<Clientes> Listclientes = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst;
        String query =  "SELECT " + 
		        		" c.Nombre_Cliente AS Cliente, " + 
		        		" COUNT(DISTINCT cu.DNI_Cuenta) AS NumeroCuentas, " + 
		        		" COUNT(DISTINCT p.DNI_Prestamo) AS PrestamosActivos " + 
		        		" FROM cliente c " + 
		        		" INNER JOIN cuenta cu ON c.DNI_Cliente = cu.DNI_Cuenta " + 
		        		" INNER JOIN prestamo p ON c.DNI_Cliente = p.DNI_Prestamo "
		        		+ "WHERE p.Estado_Prestamo = 'Activo' "
		        		+ " AND Saldo_Cuenta >= ? " +
		        		" GROUP BY c.Nombre_Cliente;";
        
        try {
        	
        	cn = Conexion.getConexion().getSQLConexion();
        	pst = cn.prepareStatement(query);
        	pst.setInt(1, montoMinimo);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
            	Clientes cl = new Clientes();
            	cl.setNombre(rs.getString("Cliente"));
            	cl.setNumeroCuentas(rs.getInt("NumeroCuentas"));
            	cl.setPrestamosActivos(rs.getInt("PrestamosActivos"));

            	Listclientes.add(cl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return Listclientes;
    }
}