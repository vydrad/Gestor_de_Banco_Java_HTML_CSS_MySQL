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

import Entidades.Cuenta;
import Entidades.Cuota;
import Entidades.Prestamo;
import Negocio.NegocioCuenta;
import Negocio.NegocioPrestamos;

/**
 * Servlet implementation class ServletPagoDePrestamos
 */
@WebServlet("/vista/clientes/ServletPagoDePrestamos")
public class ServletPagoDePrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final String ROUTE_JSP = "/vista/clientes/";
	
	private NegocioPrestamos ngp = new NegocioPrestamos();
	private NegocioCuenta ngc = new NegocioCuenta();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPagoDePrestamos() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String nombreUsuario = (String) session.getAttribute("username");
      ///  System.out.println("Usuario logueado: " + nombreUsuario);// LOG
  
        List<Prestamo> ListPrestamos = new ArrayList<>();
        List<Cuota> ListCuota = new ArrayList<>();
        List<Cuenta> cuentas = new ArrayList<>();
        List<Prestamo> ListPrestamos2 = new ArrayList<>();
        boolean valido = true;
        
        if (nombreUsuario != null) {
       	 int dniCliente = ngc.SacarDniPorNombreUsuarioNeg(nombreUsuario);
       	 ListPrestamos2 = ngp.ObtenerNumPrestamos(dniCliente);
       	
          }
        
        	if(request.getParameter("CuentasConPrestamos")!=null) {
 
        		int NumCuentaConPrestamo = 0;
        		try {
        			NumCuentaConPrestamo = Integer.parseInt(request.getParameter("CuentasConPrestamos"));
        		}
        		catch (Exception e) {
        			e.printStackTrace();
                    request.setAttribute("mensaje", "No se ha seleccionado una cuenta con un pr�stamo v�lido");
                    valido = false;
        		}
        		
        		if(valido) {
        			int dniCliente = ngc.SacarDniPorNombreUsuarioNeg(nombreUsuario);
        	        ListPrestamos = ngp.ObtenerNumPrestamoPorCuenta(NumCuentaConPrestamo);
                   	 
                       // System.out.println("DNI del cliente: " + dniCliente);// LOG
                    ListCuota = ngp.ObtenerNumCuota(NumCuentaConPrestamo);
                    cuentas = ngc.ListarCuentaFiltradaDni(dniCliente);
        		}
        	}

        request.setAttribute("ListadoNumPrestamos", ListPrestamos);
        request.setAttribute("ListadoNumCuota", ListCuota);
        request.setAttribute("cuentas", cuentas);
        request.setAttribute("listaPrestamosCuentas", ListPrestamos2);
        RequestDispatcher rd = request.getRequestDispatcher(ROUTE_JSP + "PagoDePrestamos.jsp");
        rd.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener parámetros del formulario
        String prestamoAPagar = request.getParameter("PrestamoAPagar");
        String cuotaAPagar = request.getParameter("CuotasAPagar");
        String cuentaDeDebito = request.getParameter("CuentaDeDondeDebita");

     /*   System.out.println("Prestamo seleccionado: " + prestamoAPagar); // LOG
        System.out.println("Cuota seleccionada: " + cuotaAPagar); // LOG
        System.out.println("Cuenta seleccionada para debitar: " + cuentaDeDebito); // LOG 
    */
        // Validar que todos los parámetros sean válidos
        if (prestamoAPagar != null && cuotaAPagar != null && cuentaDeDebito != null && !cuentaDeDebito.isEmpty()) {
            try {
                // Aquí deberías implementar la lógica para procesar el pago
                // Ejemplo: Actualizar estado de la cuota, debitar de la cuenta, etc.

                // Llamar al método del negocio correspondiente para registrar el pago
                boolean pagoExitoso = ngp.realizarPagoCuota(Integer.parseInt(prestamoAPagar), Integer.parseInt(cuotaAPagar), cuentaDeDebito);
                

                if (pagoExitoso) {
                    System.out.println("Pago realizado con exito.");
                    ///Una vez actualizado UPDATE EN NumeroCuotaPagar_Cuota en Cuota.        CUOTAS
                    ///Generar un Movimiento de Extraccion por pago de cuota de un prestamo. MOVIMIENTO
                    ///
                    
                    request.setAttribute("mensaje", "El pago se ha realizado correctamente.");
                } else {
                    System.out.println("Error al realizar el pago.");
                    request.setAttribute("mensaje", "Ocurrio un error al realizar el pago.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("mensaje", "Error interno al procesar el pago.");
            }
        } else {
            request.setAttribute("mensaje", "Por favor, complete todos los campos.");
        }

        // Redirigir de nuevo al JSP con el mensaje
        doGet(request, response);
    }

}
