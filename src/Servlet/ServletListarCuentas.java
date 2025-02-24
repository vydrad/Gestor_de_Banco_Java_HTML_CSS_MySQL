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

import Entidades.Cuenta;
import Negocio.NegocioCuenta;

/**
 * Servlet implementation class ServletListarCuentas
 */
@WebServlet("/vista/banco/cuentas/ServletListarCuentas")
public class ServletListarCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String ROUTE_JSP = "/vista/banco/cuentas/";
	
	NegocioCuenta ngc = new NegocioCuenta();
	
    public ServletListarCuentas() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
		
		if(request.getParameter("Param") != null) {
		listaCuentas = ngc.ListarCuentas();
		request.setAttribute("ListadoCuentas",listaCuentas);
        
        RequestDispatcher rd = request.getRequestDispatcher(ROUTE_JSP+"ListarCuentas.jsp");
		rd.forward(request, response);
		
		///response.getWriter().append("Served at: ").append(request.getContextPath());
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        
        List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
      
        
        if (request.getParameter("btnBuscar") != null) {
        	String dniStr = request.getParameter("txtDniClientes");
            if (dniStr == null || dniStr.trim().isEmpty()) {
                request.setAttribute("mensaje", "no agregó ningún DNI A buscar.");
            } else {
                try {
                    int dniCliente = Integer.parseInt(dniStr);
                    listaCuentas = ngc.ListarCuentaFiltradaDni(dniCliente);
                } catch (NumberFormatException e) {
                    request.setAttribute("mensaje", "DNI inválido.");
                }
        }
            
        }else if (request.getParameter("btnVerTodo") != null) {
            listaCuentas = ngc.ListarCuentas();
        }
		
		request.setAttribute("ListadoCuentas",listaCuentas);
        
        
        

        RequestDispatcher rd = request.getRequestDispatcher(ROUTE_JSP + "ListarCuentas.jsp");
        rd.forward(request, response);
	}

}
