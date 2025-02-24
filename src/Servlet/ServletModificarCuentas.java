package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.sql.Date;
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
 * Servlet implementation class ServletModificarCuentas
 */
@WebServlet("/vista/banco/cuentas/ServletModificarCuentas")
public class ServletModificarCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final String ROUTE_JSP = "/vista/banco/cuentas/";
	NegocioCuenta ngc = new NegocioCuenta();

    public ServletModificarCuentas() {
        super();
  
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
		
		if(request.getParameter("Param") != null) {
		listaCuentas = ngc.ListarCuentas();
		request.setAttribute("ListadoCuentas",listaCuentas);
        
        RequestDispatcher rd = request.getRequestDispatcher(ROUTE_JSP+"ModificarCuentas.jsp");
		rd.forward(request, response);
		
		
		///response.getWriter().append("Served at: ").append(request.getContextPath());
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String action = request.getParameter("accionModificar");
	    List<Cuenta> listaCuentas = new ArrayList<Cuenta>();

	    if(request.getParameter("btnModificar") != null) {
	        request.setAttribute("mensaje", "ENTRE AL BTNMODIFICAR.");
	        
	        if ("modify".equals(action)) {
	            int numeroCuenta = Integer.parseInt(request.getParameter("numeroCuenta"));
	            int DNICuenta = Integer.parseInt(request.getParameter("dniCuenta"));
	            int TipoCuenta = Integer.parseInt(request.getParameter("TipoCuentaModificar"));
	            String cbuCuenta = request.getParameter("cbuCuenta");
	            float Saldo = Float.parseFloat(request.getParameter("SaldoModificar"));
	            boolean estado = request.getParameter("SaldoModificar") != null;

	            String fechaCreacionStr = request.getParameter("cbufecha");
	            Date fechaCreacion = null;
	            if (fechaCreacionStr != null && !fechaCreacionStr.isEmpty()) {
	                try {
	                    fechaCreacion = Date.valueOf(fechaCreacionStr);
	                } catch (IllegalArgumentException e) {
	                    System.out.println("Error de formato en la fecha de creación: " + e.getMessage());
	                }
	            }

	            Cuenta cuenta = new Cuenta();
	            cuenta.setNumeroCuenta(numeroCuenta);
	            cuenta.setDniCuenta(DNICuenta);
	            cuenta.setTipoCuenta(TipoCuenta);
	            cuenta.setCbuCuenta(cbuCuenta);
	            cuenta.setSaldoCuenta(Saldo);
	            cuenta.setFechaCreacionCuenta(fechaCreacion);
	            cuenta.setEstadoCuenta(estado);

	            System.out.println(cuenta);

	            int filasAfectadas = ngc.modificarCuenta(cuenta);
	            listaCuentas = ngc.ListarCuentas();
	            if (filasAfectadas > 0) {
	                request.setAttribute("mensaje", "Cuenta Actualizada con Exito.");
	            } else {
	                request.setAttribute("mensaje", "Error al actualizar la Cuenta");
	            }
	        }
	    }

	    

	    if (request.getParameter("btnBuscar") != null) {
	        String dniStr = request.getParameter("txtDniClientes");
	        if (dniStr == null || dniStr.trim().isEmpty()) {
	            request.setAttribute("mensaje", "No agregó ningún DNI a buscar.");
	        } else {
	            try {
	                int dniCliente = Integer.parseInt(dniStr);
	                listaCuentas = ngc.ListarCuentaFiltradaDni(dniCliente);
	            } catch (NumberFormatException e) {
	                request.setAttribute("mensaje", "DNI inválido.");
	            }
	        }
	    } else if (request.getParameter("btnVerTodo") != null) {
	        listaCuentas = ngc.ListarCuentas();
	    }

	    request.setAttribute("ListadoCuentas", listaCuentas);
	    RequestDispatcher rd = request.getRequestDispatcher(ROUTE_JSP + "ModificarCuentas.jsp");
	    rd.forward(request, response);
	}


}
