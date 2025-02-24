package Servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Excepciones.CbuInvalidoException;
import Entidades.Cuenta;
import Negocio.NegocioCuenta;

/**
 * Servlet implementation class ServletCargarCuentas
 */
@WebServlet("/vista/banco/cuentas/ServletCargarCuentas")
public class ServletCargarCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final String ROUTE_JSP = "/vista/banco/cuentas/";
	NegocioCuenta negocio= new NegocioCuenta();
	
    public ServletCargarCuentas() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher(ROUTE_JSP + "CargarCuentas.jsp");
		rd.forward(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        if(request.getParameter("btnAgregar")!=null) {
        	int filas =0;
        	int flag = 0;
        	
        	boolean valido = true;
        	int NumeroCuenta = 0;
        	int TipoCuenta = 0;
        	
        	try {
        		NumeroCuenta=Integer.parseInt(request.getParameter("txtNumeroCuenta"));
            	///int DniCuenta=Integer.parseInt(request.getParameter("txtDniCliente"));
            	TipoCuenta=Integer.parseInt(request.getParameter("TipoCuenta"));
        	} catch(Exception e) {
        		e.printStackTrace();
                request.setAttribute("mensaje", "No se han ingresado datos necesarios.");
                valido = false;
        	}
        	
        	if(valido) {
        		String Cbu = request.getParameter("txtCBU");
            	float Saldo= Float.parseFloat(request.getParameter("txtSaldito").toString());
            	Date FechaCreacion= Date.valueOf(LocalDate.now()); ///Con esta linea deberia sacar el control de ingreso de fecha
            	
            	Cuenta cuenta = new Cuenta();
            	
                cuenta.setNumeroCuenta(NumeroCuenta);
                ///cuenta.setDniCuenta();
                cuenta.setTipoCuenta(TipoCuenta);
                cuenta.setCbuCuenta(Cbu);
                try {
            		if(cuenta.verificarCbuInvalido())
            			flag++;
            	} catch (CbuInvalidoException e) {
            		System.out.println("\n" + e.getMessage() + "\n");
            		request.setAttribute("mensaje", "Por favor, el CBU solo debe contener caracteres numericos");
            	}
                if(flag==1) {
                	cuenta.setSaldoCuenta(Saldo);
                    cuenta.setFechaCreacionCuenta(FechaCreacion);/// PROBABLEMENTE SAQUE EL CONTROL DE LA FECHA A INGRESAR
                    cuenta.setEstadoCuenta(true);
                    
                    filas=negocio.agregarCuenta(cuenta);
                    
                    if(filas>0) {
            			request.setAttribute("mensaje", "La cuenta se agregó correctamente.");	
            		}else {
            			request.setAttribute("mensaje", "Error al agregar la Cuenta.");
            		}
                }
        	}
        }
        	

        RequestDispatcher rd = request.getRequestDispatcher(ROUTE_JSP + "CargarCuentas.jsp");
        rd.forward(request, response);
	}

}
