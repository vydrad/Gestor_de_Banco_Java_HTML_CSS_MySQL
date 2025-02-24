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
 * Servlet implementation class ServletListarClientes
 */
@WebServlet("/vista/banco/ServletListarClientes")
public class ServletListarClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final String ROUTE_JSP = "/vista/banco/";
    
    NegocioClientes ngc = new NegocioClientes();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListarClientes() {
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
        
        RequestDispatcher rd = request.getRequestDispatcher(ROUTE_JSP+"ListarClientes.jsp");
		rd.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        
       
        List<Clientes> listaClientes = new ArrayList<Clientes>();
      
        
        if (request.getParameter("btnBuscar") != null) {
        	String dniStr = request.getParameter("txtDniClientes");
            if (dniStr == null || dniStr.trim().isEmpty()) {
            	 listaClientes = ngc.ListarClientesNeg();
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
        
        
        RequestDispatcher rd = request.getRequestDispatcher(ROUTE_JSP+"ListarClientes.jsp");
		rd.forward(request, response);
	}
	}
