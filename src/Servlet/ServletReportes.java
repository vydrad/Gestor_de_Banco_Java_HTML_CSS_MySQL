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
import Negocio.NegocioCuenta;

/**
 * Servlet implementation class ServletReportes
 */
@WebServlet("/vista/banco/reportes/ServletReportes")
public class ServletReportes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String ROUTE_JSP = "/vista/banco/reportes/";
	NegocioCuenta ngc = new NegocioCuenta();
	NegocioClientes nc = new NegocioClientes();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletReportes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Clientes> clientes = new ArrayList<Clientes>();
		
		int cantCuentas=ngc.ObtenerCantidadCuentasNeg();
		int cantPrestamos=ngc.ObtenerCantidadPrestamosNeg();
		int cantCuentasUltTrim= ngc.ObtenerCantidadCuentasUltTrimestreNeg();
		int cantPrestamosAcep= ngc.ObtenerCantidadPrestamosAceptadosNeg();
		int cantPrestamosRechazado= ngc.ObtenerCantidadPrestamosRechazadosNeg();
		
		clientes = nc.obtenerResumenClientesNeg();
		
		request.setAttribute("cantCuentas", cantCuentas);
		request.setAttribute("cantPrestamos", cantPrestamos);
		request.setAttribute("cantPrestamosAcep", cantPrestamosAcep);
		request.setAttribute("cantCuentasUltTrim", cantCuentasUltTrim);
		request.setAttribute("cantPrestamosRechazado", cantPrestamosRechazado);
		 
		
		if(request.getParameter("btnFiltrar") != null) {
			
			String MontoMinStr = request.getParameter("txtmontoMin");
            if (MontoMinStr == null || MontoMinStr.trim().isEmpty()) {
            	clientes =  nc.obtenerResumenClientesNeg();
                request.setAttribute("mensaje", " No agrego ningun Monto.");
            }else {
                try {
                    int MontoMin = Integer.parseInt(MontoMinStr);
                    clientes = nc.obtenerResumenClientesFiltradoNeg(MontoMin);
                } catch (NumberFormatException e) {
                    request.setAttribute("mensaje", "Monto invalido.");
                }
            }
		}else if (request.getParameter("btnVerTodo") != null) {
			clientes = nc.obtenerResumenClientesNeg();
        }
		
		request.setAttribute("ListaDatosClientes", clientes);
		
		RequestDispatcher rd = request.getRequestDispatcher(ROUTE_JSP + "Reportes.jsp");
	    rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

        RequestDispatcher rd = request.getRequestDispatcher(ROUTE_JSP + "Reportes.jsp");
        rd.forward(request, response);
	}

}
