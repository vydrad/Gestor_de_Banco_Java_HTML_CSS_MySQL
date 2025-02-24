package Dominio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import InterfacesDao.InterUsuarioDao;

public class UsuarioDao implements InterUsuarioDao {
	
	public int ExisteUserDao(String userName, String userPass) {
		
		int existe = 0;
		PreparedStatement pst;
		Connection cn = null;
		
		try {
			cn= Conexion.getConexion().getSQLConexion();
			String consulta = "SELECT * FROM banco.usuario WHERE Nombre_Usuario = '" + userName + "' AND Pass_Usuario = '" + userPass + "' AND Estado_Usuario = 1";
			pst = cn.prepareStatement(consulta);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.isBeforeFirst()) {
				existe = 1;
				while(rs.next()) {
					Boolean admin = rs.getBoolean("Administrador_Usuario");
					if(admin==true) {
						existe = 2;
					}
				}
			}
			else existe = 0;
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return existe;
	}
	
	public boolean actualizarContraseniaDao(String nombreUsuario, String Password) {
		boolean editar=false;
		PreparedStatement pst;
        String sql = "UPDATE usuario SET Pass_Usuario = ? WHERE Nombre_Usuario = ?";
        
        try {
        	Connection conexion = Conexion.getConexion().getSQLConexion();
    		pst = conexion.prepareStatement(sql);
 
            pst.setString(1, Password);
            pst.setString(2, nombreUsuario);

            int filasAfectadas = pst.executeUpdate();
            conexion.commit();
            
            if(filasAfectadas > 0) {
            	editar=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
        return editar;
    }

	
	
}
