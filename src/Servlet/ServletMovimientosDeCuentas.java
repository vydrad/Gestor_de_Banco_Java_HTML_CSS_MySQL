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
import javax.servlet.http.HttpSession;

import Entidades.Movimientos;
import Entidades.TipoCuenta;
import Negocio.NegocioCuenta;

/**
 * Servlet implementation class ServletMovimientosDeCuentas
 */
@WebServlet("/vista/clientes/ServletMovimientosDeCuentas")
public class ServletMovimientosDeCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String ROUTE_JSP = "/vista/clientes/";
	private NegocioCuenta ngc = new NegocioCuenta();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletMovimientosDeCuentas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<TipoCuenta> ListaTipoCuentas = new ArrayList<TipoCuenta>();
		List<Movimientos> ListaMovimientos = new ArrayList<Movimientos>();
		
		HttpSession session = request.getSession(false);
		String nombreUsuario = (String) session.getAttribute("username");
		
		int dniCliente = ngc.SacarDniPorNombreUsuarioNeg(nombreUsuario);
		
		
	    String codTipoCuentaStr = request.getParameter("TipoCuenta");
	    int codTipoCuenta = 0;

	    if (codTipoCuentaStr != null && !codTipoCuentaStr.isEmpty()) {
	        try {
	            codTipoCuenta = Integer.parseInt(codTipoCuentaStr);
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	        }
	    }
		
		if(request.getParameter("Param") != null) {
			ListaTipoCuentas = ngc.obtenerTipoDeCuentaPorClienteNeg(dniCliente);	
			request.setAttribute("ListadoTipoCuentas", ListaTipoCuentas);
		}else {
			ListaTipoCuentas = ngc.obtenerTipoDeCuentaPorClienteNeg(dniCliente);	
			request.setAttribute("ListadoTipoCuentas", ListaTipoCuentas);
		}
		
		if(request.getParameter("btnVerMovimientos") != null) {
			
		if(ListaTipoCuentas!=null) {
			ListaMovimientos=ngc.obtenerDetalleMovimientoXTipoDeCuentaNe(dniCliente,codTipoCuenta);
			request.setAttribute("ListadoMovimientos", ListaMovimientos);
		}
			
		}
		
		
		 RequestDispatcher rd = request.getRequestDispatcher(ROUTE_JSP + "MovimientosDeCuentas.jsp");
	     rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

        RequestDispatcher rd = request.getRequestDispatcher(ROUTE_JSP + "MovimientosDeCuentas.jsp");
        rd.forward(request, response);
	}

}
