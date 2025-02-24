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
import Negocio.NegocioCuenta;
import Negocio.NegocioTransferencia;

/**
 * Servlet implementation class ServletTransferencias
 */
@WebServlet("/vista/clientes/ServletTransferencias")
public class ServletTransferencias extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final String ROUTE_JSP = "/vista/clientes/";
    NegocioTransferencia ngt = new NegocioTransferencia();
    NegocioCuenta ngc = new NegocioCuenta();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTransferencias() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        actualizarDatosYDespachar(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        String nombreUsuario = (String) session.getAttribute("username");
        String CbuStr = request.getParameter("cbuRemitente");
        String CbuStr2 = request.getParameter("CuentaDestinatario");;
        boolean valido = true;
        
        System.out.print("CBU STRING "+CbuStr +"-"+ CbuStr2);
        
       // BigInteger Cbu = new BigInteger(CbuStr);
       // BigInteger Cbu2 = new BigInteger(CbuStr2);
        
        //System.out.print("CBU BigInteger "+Cbu +"-"+ Cbu2);
        
        int numCuenta = ngc.SacarNumCuentaXCbu(CbuStr);
        int numCuenta2= ngc.SacarNumCuentaXCbu(CbuStr2);
        
        System.out.print("CBU STRING "+numCuenta +"-"+ numCuenta2);

        if (nombreUsuario != null) {
            String cbuRemitente = request.getParameter("cbuRemitente");
            String cbuDestinatario = request.getParameter("CuentaDestinatario");
            float monto = 0;
            
            try {
            	monto = Float.parseFloat(request.getParameter("txtMonto"));
            } catch (Exception e) {
            	e.printStackTrace();
                request.setAttribute("mensaje", "No se ha seleccionado o ingresado un monto válido");
                valido = false;
            }
            
            if(valido) {
            	String mensaje = ngt.realizarTransferencia(cbuRemitente, cbuDestinatario, monto,numCuenta,numCuenta2);
                request.setAttribute("mensaje", mensaje);
            }
        } else {
            request.setAttribute("mensaje", "Usuario no autenticado.");
        }

        actualizarDatosYDespachar(request, response);
    }

    /**
     * Actualiza los datos del remitente y destinatarios para mostrarlos en el JSP
     */
    private void actualizarDatosYDespachar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String nombreUsuario = (String) session.getAttribute("username");
        Cuenta cu = null;
        List<Cuenta> listaCBU = new ArrayList<>();

        if (nombreUsuario != null) {
            cu = ngt.ObtenerCBUxNombreUsuarioNeg(nombreUsuario);

            if (cu != null) {
                listaCBU = ngt.ObtenerTodosLosCBUNeg(cu.getCbuCuenta());
                request.setAttribute("CBU", cu);
                request.setAttribute("TodosCBU", listaCBU);
            } else {
                request.setAttribute("mensaje", "Cuenta no encontrada");
            }
        } else {
            request.setAttribute("mensaje", "Usuario no autenticado.");
        }

        RequestDispatcher rd = request.getRequestDispatcher(ROUTE_JSP + "Tranferencias.jsp");
        rd.forward(request, response);
    }
}
