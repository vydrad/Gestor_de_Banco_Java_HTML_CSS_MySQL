package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Clientes;
import Negocio.NegocioClientes;

/**
 * Servlet implementation class ServletEliminarClientes
 */
@WebServlet("/vista/banco/ServletEliminarClientes")
public class ServletEliminarClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final String ROUTE_JSP = "/vista/banco/";
    NegocioClientes ngc = new NegocioClientes();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEliminarClientes() {
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
	        
	        RequestDispatcher rd = request.getRequestDispatcher(ROUTE_JSP+"EliminarClientes.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        List<Clientes> listaClientes = ngc.ListarClientesNeg();
      
        
        if (request.getParameter("btnBuscar") != null) {
        	String dniStr = request.getParameter("txtDniClientes");
            if (dniStr == null || dniStr.trim().isEmpty()) {
                request.setAttribute("mensaje", "Cliente no agregó ningún DNI.");
            } else {
                try {
                    int dniCliente = Integer.parseInt(dniStr);
                    listaClientes = ngc.ListarClientesFiltro(dniCliente);
                } catch (NumberFormatException e) {
                    request.setAttribute("mensaje", "DNI inválido.");
                }
        }
            
        }else if (request.getParameter("btnVerTodo") != null) {
            listaClientes = ngc.ListarClientesNeg();
        }
        
        
		
		// Eliminar cliente si se presionó el botón "Eliminar"
        if (request.getParameter("btnEliminar") != null) {
            int dniEliminar = Integer.parseInt(request.getParameter("dniEliminar"));
            int filaAfectada = ngc.EliminarClientesNeg(dniEliminar); // Llamar una vez

            // Verificar si la eliminación fue exitosa
            if (filaAfectada == 1) {
                request.setAttribute("mensaje", "Cliente eliminado exitosamente.");
            } else {
                request.setAttribute("mensaje", "No se pudo eliminar el cliente. Verifique que el DNI sea válido.");
            }
            
            listaClientes = ngc.ListarClientesNeg();
        }
        
        
        
        
        
        request.setAttribute("Listado",listaClientes);
        
        RequestDispatcher rd = request.getRequestDispatcher(ROUTE_JSP+"EliminarClientes.jsp");
		rd.forward(request, response);
	}

}
