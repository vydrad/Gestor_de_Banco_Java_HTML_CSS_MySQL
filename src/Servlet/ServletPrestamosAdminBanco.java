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
import Entidades.Cuota;
import Entidades.Prestamo;
import Negocio.NegocioPrestamos;

/**
 * Servlet implementation class ServletPrestamosAdminBanco
 */
@WebServlet("/vista/banco/prestamos/ServletPrestamosAdminBanco")
public class ServletPrestamosAdminBanco extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final String ROUTE_JSP = "/vista/banco/prestamos/";
	NegocioPrestamos ngp = new NegocioPrestamos();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPrestamosAdminBanco() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Clientes> listClientesPrestamos = new ArrayList<Clientes>();
		List<Prestamo> listPrestamos = new ArrayList<Prestamo>();
		List<Cuota> listCuota = new ArrayList<Cuota>();
		
		listClientesPrestamos= ngp.ObtenerClientePrestamo();
		request.setAttribute("ListaClientesPrestamos", listClientesPrestamos);
		
		String dniStr = request.getParameter("ddlClientes");
			
		    if (dniStr != null && !dniStr.isEmpty()) {
		        try {
		        	
		            int dni = Integer.parseInt(dniStr);
		            int numCuenta = ngp.ObtenerNumeroCuenta(dni);
		            
		            if(request.getParameter("btnMostrarDatos") != null) {
		            	
		            	listPrestamos = ngp.ObtenerInfoPrestamo(numCuenta);
			            listCuota=ngp.ObtenerInfoCuota(numCuenta);
		            	
			            request.setAttribute("ListPrestamos", listPrestamos);
					    request.setAttribute("listCuota", listCuota);
		            }else if(request.getParameter("btnAprobar") != null) {
						
						if(ngp.autorizarPrestamosPorNumeroCuenta(numCuenta)) {
							request.setAttribute("mensaje", "El prestamo se autorizo Correctamente.");
						}else {
							
							request.setAttribute("mensaje", "El prestamo no se autorizo Correctamente.");
						}
				}else if(request.getParameter("btnRechazar") != null) {
					
					if(ngp.RechazarPrestamosPorNumeroCuenta(numCuenta)) {
						request.setAttribute("mensaje", "El prestamo se rechazo Correctamente.");
					}else {
						
						request.setAttribute("mensaje", "El prestamo no se rechazo Correctamente.");
					}
					
				}
		         

		            
		        } catch (NumberFormatException e) {
		            e.printStackTrace();
		        }
		    }
		   
		

        RequestDispatcher rd = request.getRequestDispatcher(ROUTE_JSP + "Prestamos.jsp");
        rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd = request.getRequestDispatcher(ROUTE_JSP + "Prestamos.jsp");
        rd.forward(request, response);
	}

}
