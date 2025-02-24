package Servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Dominio.ClienteDao;
import Entidades.Clientes;
import Negocio.NegocioCuenta;
import Entidades.Cuenta;

@WebServlet("/vista/banco/cuentas/ServletAsignacionCuentas")
public class ServletAsignacionCuentas extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final String ROUTE_JSP = "/vista/banco/cuentas/";
    private final ClienteDao clienteDao = new ClienteDao();
    private NegocioCuenta negC= new NegocioCuenta();
    public ServletAsignacionCuentas() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Clientes> listaClientes = clienteDao.ListarClientesDao();
        request.setAttribute("listaClientes", listaClientes);
        
        List<Cuenta> listaCuentas = negC.ListarCuentas();
        request.setAttribute("listaCuentas", listaCuentas);
      
        if(request.getParameter("ddlCuentas")!=null) {
        	
        	//int NumeroCuenta=Integer.parseInt(request.getParameter("ddlCuentas"));
        	String NumeroCuentaYcbu=request.getParameter("ddlCuentas").toString();
        	String[] partes = NumeroCuentaYcbu.split("-");

        	// Convertir NumeroCuenta a int y asignar el CBU a una variable String
        	boolean valido = true;
        	boolean DNIvalido = true;
        	int NumeroCuenta = 0;
        	try {
        		NumeroCuenta = Integer.parseInt(partes[0]);
        	} catch(Exception e) {
        		e.printStackTrace();
                request.setAttribute("mensaje", "No se han seleccionado un número de cuenta.");
                valido = false;
        	}
        	
        	if(valido) {
        		String cbu = partes[1];
           	 
            	if(request.getParameter("ddlClientes")!=null) {
                 	
                 	int dniCliente = 0;
            		try {
                 		dniCliente=Integer.parseInt(request.getParameter("ddlClientes"));
                 	} catch(Exception e) {
                 		e.printStackTrace();
                        request.setAttribute("mensaje", "No se han seleccionado un DNI.");
                        valido = false;
                 	}
            		
                 	if(valido) {
                 		int filas=0;
                     	filas=negC.AsignarCuenta(NumeroCuenta, dniCliente);
                     	
                     	
                     	request.setAttribute("NumeroCuenta",NumeroCuenta);
                    	request.setAttribute("DNI",dniCliente);
                    	request.setAttribute("CBU",cbu);
                    
                     	
                     	 if (filas == 1) {
                             request.setAttribute("mensaje", "La cuenta fué asignada a un nuevo cliente correctamente.");
                             request.setAttribute("filas",filas);
                         } else {
                             request.setAttribute("mensaje", "Error al reasignar una cuenta.");
                         }
                 	}
                 }
        	}
        }

        RequestDispatcher rd = request.getRequestDispatcher(ROUTE_JSP + "AsignacionCuentas.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
