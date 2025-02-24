package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Entidades.Clientes;
import Negocio.NegocioClientes;

/**
 * Servlet implementation class ServletInformacionPersonal
 */
@WebServlet("/vista/clientes/ServletInformacionPersonal")
public class ServletInformacionPersonal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String ROUTE_JSP = "/vista/clientes/";
	NegocioClientes ngc = new NegocioClientes();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInformacionPersonal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if(request.getParameter("Param") != null) {

        if (session != null) {
        	String username = (String) session.getAttribute("username");
        	if(username != null) {
        	 List<Clientes> datosCliente = ngc.ObtenerClientesPorUsuario(username);

            if (!datosCliente.isEmpty()) {
                request.setAttribute("datosCliente", datosCliente);
            } else {
                request.setAttribute("mensaje", "No se encontró información del cliente.");
            }	
        	}
           
        } else {
            request.setAttribute("mensaje", "No se ha iniciado sesión.");
        }


        RequestDispatcher rd = request.getRequestDispatcher(ROUTE_JSP + "InformacionPersonal.jsp");
        rd.forward(request, response);
		}
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
