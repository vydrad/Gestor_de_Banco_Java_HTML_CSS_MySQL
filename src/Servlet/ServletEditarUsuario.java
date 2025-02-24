package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio.NegocioUsuario;

/**
 * Servlet implementation class ServletEditarUsuario
 */
@WebServlet("/vista/inicio/ServletEditarUsuario")
public class ServletEditarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String ROUTE_JSP = "/vista/inicio/";
    NegocioUsuario ngu = new NegocioUsuario();
    NegocioUsuario ngUser = new NegocioUsuario();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEditarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nombreUsuario = request.getParameter("txtNombreUsuario");
        String nuevaContrasenia = request.getParameter("txtContrasenia");
        String repetirContrasenia = request.getParameter("txtRepetirContrasenia");
        
        if(request.getParameter("btnAceptar") != null) {
        	
         if (nuevaContrasenia.equals(repetirContrasenia)) {
            
        	boolean actualizado = ngu.actualizarContraseniaNeg(nombreUsuario, nuevaContrasenia);
        	
        	 if (actualizado) {
        		 request.setAttribute("mensaje", "Se actualizo correctamente la contrasenia.");
             } else {
                 request.setAttribute("mensaje", "Error al actualizar la contrasenia.");
             }
        	
        }else {
        	request.setAttribute("mensaje", "Las contrasenias no coinciden, vuelva a intentarlo.");
        }
 	
        }else if(request.getParameter("btnCancelar") != null) {
        	
        	 request.setAttribute("mensaje", "Se cancelo la actualizacion de la contrasenia.");
        	
        }

       
        RequestDispatcher rd = request.getRequestDispatcher(ROUTE_JSP + "/EditarUsuario.jsp");
		rd.forward(request, response);
	}

}
