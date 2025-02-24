package Servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import Entidades.Clientes;
import Negocio.NegocioClientes;

/**
 * Servlet implementation class ServletModificarClientes
 */
@WebServlet("/vista/banco/ServletModificarClientes")
public class ServletModificarClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final String ROUTE_JSP = "/vista/banco/";
    
    NegocioClientes ngc = new NegocioClientes();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletModificarClientes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Clientes> listaClientes = new ArrayList<Clientes>();
		
		if(request.getParameter("Param") != null) {
		listaClientes = ngc.ListarClientesNeg();
		
		request.setAttribute("Listado",listaClientes);
        
        RequestDispatcher rd = request.getRequestDispatcher(ROUTE_JSP+"ModificarClientes.jsp");
		rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("accionModificar");
		
		if ("modificar".equals(action)) {

	        String nombre = request.getParameter("nombreModificar");
	        String apellido = request.getParameter("apellidoModificar");
	        int dni = Integer.parseInt(request.getParameter("dniModificar"));
	        String nacionalidad = request.getParameter("nacionalidadModificar");
	        String correo = request.getParameter("correoModificar");
	        
	        Date Fecha = null;
	        try {
				Fecha = Date.valueOf(request.getParameter("FechaNacModificar"));
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("mensaje", "Error en el ingreso de la fecha.");
			}
	        
	        String direccion = request.getParameter("DireccionModificar");
	        String sexo = request.getParameter("GeneroModificar");
	        String telefono = request.getParameter("TelefonoModificar");
	
	        Clientes cliente = new Clientes();
	        cliente.setNombre(nombre);
	        cliente.setApellido(apellido);
	        cliente.setDni(dni);
	        cliente.setNacionalidad(nacionalidad);
	        cliente.setCorreo(correo);
	        cliente.setFechaNac(Fecha);
	        cliente.setDireccion(direccion);
	        cliente.setSexo(sexo);
	        cliente.setTelefono(telefono);
	        
	        
	        int filasAfectadas=ngc.ModificarClienteNeg(cliente);
	        
	        if (filasAfectadas > 0) {
	            request.setAttribute("mensaje", "Cliente actualizado con exito.");
	        } else {
	            request.setAttribute("mensaje", "Error al actualizar el cliente.");
	        }
		}
		
 
        List<Clientes> listaClientes = ngc.ListarClientesNeg();
      
        
        if (request.getParameter("btnBuscar") != null) {
        	String dniStr = request.getParameter("txtDniClientes");
            if (dniStr == null || dniStr.trim().isEmpty()) {
                request.setAttribute("mensaje", "Cliente no agrego ningun DNI.");
            } else {
                try {
                    int dniCliente = Integer.parseInt(dniStr);
                    listaClientes = ngc.ListarClientesFiltro(dniCliente);
                } catch (NumberFormatException e) {
                    request.setAttribute("mensaje", "DNI invalido.");
                }
        }
            
        }else if (request.getParameter("btnVerTodo") != null) {
            listaClientes = ngc.ListarClientesNeg();
        }
		
		request.setAttribute("Listado",listaClientes);
        
        RequestDispatcher rd = request.getRequestDispatcher(ROUTE_JSP+"ModificarClientes.jsp");
		rd.forward(request, response);
	}

}