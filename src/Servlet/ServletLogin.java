package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import Negocio.NegocioUsuario;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/vista/inicio/ServletLogin")
public class ServletLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final String ROUTE_JSP = "/vista/inicio/";
    NegocioUsuario ngUser = new NegocioUsuario();

       
    public ServletLogin() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String user = request.getParameter("txtUser");
        String password = request.getParameter("txtPassword");
        String resRoute = "";

        /*// Verifica las credenciales, por ahora hardcodeado, luego debe ir a la base de datos y consultar alla :)
        if ("admin".equals(user) && "1234".equals(password)) {
            session.setAttribute("role", "admin");            
            resRoute = ROUTE_JSP + "InicioBanco.jsp";
        } else if ("user".equals(user) && "1234".equals(password)) {
            session.setAttribute("role", "client");
            resRoute = "/vista/clientes/InicioCliente.jsp";
        } else {
            // Si las credenciales no son valiidas, redirige al login con un mensaje de error
            request.setAttribute("errorMessage", "Usuario o contraseÃ±a incorrectos");
            resRoute = ROUTE_JSP + "Login.jsp";
        }
        
        RequestDispatcher rd = request.getRequestDispatcher(resRoute);
        rd.forward(request, response);
        */
        
        
        // Si "ngUser.ExisteUserNeg" = 0 (No existe), = 1 (Existe y es cliente), = 2 (Existe y es admin)
        int existencia = ngUser.ExisteUserNeg(user, password);
        if (existencia!=0) {
            if(existencia==2)
            {
	        	session.setAttribute("role", "admin");
	        	session.setAttribute("username", user); // Guarda el nombre de usuario en la sesión
	        	
	            resRoute = ROUTE_JSP + "InicioBanco.jsp";
            }
            if (existencia==1) {
                session.setAttribute("role", "client");
                session.setAttribute("username", user); // Guarda el nombre de usuario en la sesión
                session.setAttribute("passwordCliente", password);
                resRoute = "/vista/clientes/InicioCliente.jsp";
            }
        }
        else {
            request.setAttribute("errorMessage", "Usuario o contraseÃ±a incorrectos");
            resRoute = ROUTE_JSP + "Login.jsp";
        }
        
        RequestDispatcher rd = request.getRequestDispatcher(resRoute);
        rd.forward(request, response);
        
        /*if(request.getParameter("btnIniciarSesion")!=null)
		{
			Usuario user = new Usuario();
			user.setNombreUsuario(request.getParameter("txtUser"));
			user.setPassUsuario(request.getParameter("txtPassvord"));
			
			
		}*/
    }
}
