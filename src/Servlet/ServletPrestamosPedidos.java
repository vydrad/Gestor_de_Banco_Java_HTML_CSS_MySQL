package Servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Entidades.Cuenta;
import Entidades.Prestamo;
import Entidades.Cuota;
import Negocio.NegocioCuenta;
import Negocio.NegocioPrestamos;



/**
 * Servlet implementation class ServletPrestamosPedidos
 */
@WebServlet("/vista/clientes/ServletPrestamosPedidos")
public class ServletPrestamosPedidos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String ROUTE_JSP = "/vista/clientes/";
	NegocioPrestamos negocio= new NegocioPrestamos();
	private NegocioCuenta ngc = new NegocioCuenta();
	
    public ServletPrestamosPedidos() {
        super();
    
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		if(request.getParameter("Param")!=null) {
		HttpSession session = request.getSession(false);
		String nombreUsuario = (String) session.getAttribute("username");
		String FechaCreacion=( Date.valueOf(LocalDate.now())).toString();
	    List<Cuenta> cuentas = new ArrayList<>();
	    
	    if (nombreUsuario != null) {
            int dniCliente = ngc.SacarDniPorNombreUsuarioNeg(nombreUsuario);
            
            cuentas = ngc.ListarCuentaFiltradaDni(dniCliente);
            
	    }
	    System.out.println(FechaCreacion);
		request.setAttribute("ListadoCuentasDNI", cuentas);
		request.setAttribute("FechaActual",FechaCreacion);
	
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(ROUTE_JSP + "PrestamosPedidos.jsp");
        rd.forward(request, response);
		///response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession(false);
	     String nombreUsuario = (String) session.getAttribute("username");
	     
		if(request.getParameter("btnSolicitarPrestamo")!=null) {
			
				int SigPrestamo = negocio.ContarCodCuotas();
	        	int filas =0;
	        	int filas2= 0;
	        	int  dniCliente = ngc.SacarDniPorNombreUsuarioNeg(nombreUsuario);
	        	boolean valido = true;
	        	float MontoPedido = 0;
	        	int NumeroCuenta = 0;
	        	int CantidadCuotas = 0;
	        	
	        	try {
	        		MontoPedido=Float.parseFloat(request.getParameter("txtMontoPrestamo").toString());
		        	NumeroCuenta=Integer.parseInt(request.getParameter("ddlNumeroCuenta").toString());/// SOBRE ESTE NUMERO DE CUENTA TENGO QUE VERIFICAR QUE NO EXISTA UN PRESTAMO PENDIENTE O ACTIVO
		        	CantidadCuotas= Integer.parseInt(request.getParameter("ddlCantidadCuotas").toString());
	        	} catch (Exception e) {
	        		e.printStackTrace();
                    request.setAttribute("mensaje", "No se han ingreasdo o seleccionado los datos necesarios");
                    valido = false;
	        	}
	        
	        	Date FechaCreacion= Date.valueOf(LocalDate.now()); ///Con esta linea deberia sacar el control de ingreso de fecha
	        	
	        	if(valido) {
		        	
		        	Float MontoPagar=(float) (MontoPedido*1.10);
		        	Float MontoMensual= (float)(MontoPagar/CantidadCuotas);
		        	
		               // Generar fechas de vencimiento
		        	
		            Calendar calendario = Calendar.getInstance();
		            calendario.setTime(FechaCreacion);
		            int dia =calendario.get(Calendar.DAY_OF_MONTH);
		            int mes =calendario.get(Calendar.MONTH);
		            int anio = calendario.get(Calendar.YEAR);
		            
		            if (mes == 11) { // Diciembre (11 porque los meses son 0-indexed)
		                mes = 0; // Enero
		                anio++;
		            } else {
		            	mes++;
		            }
		            calendario.set(Calendar.YEAR, anio);
		            calendario.set(Calendar.MONTH, mes);
		            calendario.set(Calendar.DAY_OF_MONTH, dia);

		            // Convertir el Calendar a java.sql.Date
		            Date FechaVencimiento = new Date(calendario.getTimeInMillis());
		          
		            Prestamo prestamo= new Prestamo();
		        	
	        		prestamo.setCodPrestamo(SigPrestamo); 
	        		prestamo.setDniPrestamo(dniCliente); 
	        		prestamo.setNumeroCuentaPrestamo(NumeroCuenta);
	        		prestamo.setCodCuotaPrestamo(SigPrestamo);// *tiene que existir en cuotas* HACER INSERT EN CUOTAS con Mismo "numero de CodPrestamo"
	        		prestamo.setFechaPrestamo(FechaCreacion);
	        		prestamo.setImportePedidoPrestamo(MontoPedido);
	        		prestamo.setImportePagarPrestamo(MontoPagar);
	        		prestamo.setPlazoMesesPrestamo(CantidadCuotas);
	        		prestamo.setMontoMesPrestamo(MontoMensual);
	        		prestamo.setEstadoPrestamo("Pendiente");
		            
		        	int TienePrestamos= negocio.VerificarPedidoPrestamos(prestamo);///Devuelve 1 si NO tiene prestamos esa cuenta
		            
		        	if(TienePrestamos==1) {
		        		
		        	Cuota cuota = new Cuota();
		        	
		        	/// el cod de cuota va a coincidir con el cod de prestamo, para evitar errores.
		        	cuota.setCodCuota(SigPrestamo);
		        	cuota.setNumeroCuotaPagar(1);
		        	cuota.setMontoCuota(MontoMensual);
		        	cuota.setFechaVencimientoCuota(FechaVencimiento);
		        	cuota.setFechaPagoCuota(null);
		        	cuota.setEstadoCuota("0");
		        	///A PARTIR DE ACA LOS 2 INSERT DEBERIAN INSERTARSE CORRECTAMENTE O SINO SE ROMPE. LA BD DEBERIA TENER LOS REGISTROS CARGADOS CORRECTAMENTE.
		        	filas2= negocio.PedidoPrestamoAgregandoCuota(cuota);

		        	filas=negocio.PedidoPrestamos(prestamo);
		            
		        		if(filas>0 & filas2>0) {
		        			request.setAttribute("mensaje", "La Solicitud de Prestamo se procesó correctamente. Esté pendiente de su aprobación");	
		        		}else {
		        			request.setAttribute("mensaje", "Error, este cliente ya realizo una solicitud de prestamo.");
		        		}

		        	}
		        	else {
		        		request.setAttribute("mensaje", "La cuenta seleccionada aún tiene un préstamo pendiente. No se pudo completar el pedido.");
		        		List<Cuenta> cuentas = new ArrayList<>();
		        		cuentas = ngc.ListarCuentaFiltradaDni(dniCliente);
		        		
		        		request.setAttribute("ListadoCuentasDNI", cuentas);
		        		request.setAttribute("FechaActual",FechaCreacion);
		        	}
	        	}
	        	else {
	        		System.out.println(FechaCreacion);
	        		
	        		List<Cuenta> cuentas = new ArrayList<>();
	        		cuentas = ngc.ListarCuentaFiltradaDni(dniCliente);
	        		
	        		request.setAttribute("ListadoCuentasDNI", cuentas);
	        		request.setAttribute("FechaActual",FechaCreacion);
	        	}
	        	
		}
        RequestDispatcher rd = request.getRequestDispatcher(ROUTE_JSP + "PrestamosPedidos.jsp");
        rd.forward(request, response);
	}

}
