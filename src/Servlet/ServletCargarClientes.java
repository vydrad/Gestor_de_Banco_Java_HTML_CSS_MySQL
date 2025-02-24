package Servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Entidades.Clientes;
import Entidades.Localidad;
import Entidades.Provincia;
import Excepciones.ApellidoInvalidoException;
import Excepciones.CorreoInvalidoException;
import Excepciones.CuilInvalidoException;
import Excepciones.NacionalidadInvalidoException;
import Excepciones.NombreInvalidoException;
import Excepciones.TelefonoInvalidoException;
import Negocio.NegocioClientes;

@WebServlet("/vista/banco/ServletCargarClientes")
public class ServletCargarClientes extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final String ROUTE_JSP = "/vista/banco/CargarClientes.jsp";
    NegocioClientes ngc = new NegocioClientes();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	List<Provincia> ListaProvincia = new ArrayList<Provincia>();
    	
    	if(request.getParameter("Param") != null) {
    	ListaProvincia = ngc.ListarProvinciaNeg();
    	}
    	
    	ListaProvincia = ngc.ListarProvinciaNeg();
        request.setAttribute("ListadoProv", ListaProvincia);

        // Cargar localidades si una provincia esta seleccionada
        String codProvincia = request.getParameter("provincia");
        if (codProvincia != null && !codProvincia.isEmpty()) {
            List<Localidad> Listalocalidades = ngc.ListarLocalidadNeg(codProvincia);
            request.setAttribute("Listaloc", Listalocalidades);
        }

        RequestDispatcher rd = request.getRequestDispatcher(ROUTE_JSP);
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("btnAgregar") != null) {
        	
            // Guardar cliente en la base de datos
            String contrasenia = request.getParameter("txtContraseniaUsuario");
            String repetirContrasenia = request.getParameter("txtRepetirContrasenia");
            
            String nombre = request.getParameter("txtNombre");
            String apellido = request.getParameter("txtApellido");
            String dni = request.getParameter("txtDni");
            String cuil = request.getParameter("txtCuil");
            String provincia = request.getParameter("provincia");
            String localidad = request.getParameter("localidad");
            String genero = request.getParameter("genero");
            String nacionalidad = request.getParameter("txtNacionalidad");
            String fechaNacimiento = request.getParameter("txtFechaNacimiento");
            String direccion = request.getParameter("txtDireccion");
            String correo = request.getParameter("txtCorreo");
            String telefono = request.getParameter("txtTelefono");
            String nombreUsuario = request.getParameter("txtNombreUsuario");



            // Validar que todos los campos obligatorios estï¿½n completos
            if (nombre == null || apellido == null ||
                dni == null || cuil == null || provincia == null ||
                localidad == null || genero == null || fechaNacimiento == null|| direccion == null ||
                correo == null || telefono == null || nombreUsuario == null || contrasenia == null || repetirContrasenia == null) {
                
                request.setAttribute("mensaje", "Por favor, complete todos los campos");
                
            }else if (contrasenia.equals(repetirContrasenia)) {
            	
            	int valido = 0;
            	Clientes clienteNuevo = new Clientes();
            	boolean DNIvalido = true;
            	clienteNuevo.setNombre(nombre);
                clienteNuevo.setApellido(apellido);
            	
            	try {
            		if(clienteNuevo.verificarNombreInvalido())
            			valido++;
            	} catch (NombreInvalidoException e) {
            		System.out.println("\n" + e.getMessage() + "\n");
            		request.setAttribute("mensaje", "Por favor, solamente letras en el nombre");
            	}
            	
            	try {
            		if(clienteNuevo.verificarApellidoInvalido())
            			valido++;
            	} catch (ApellidoInvalidoException e) {
            		System.out.println("\n" + e.getMessage() + "\n");
            		request.setAttribute("mensaje", "Por favor, solamente letras en el apellido");
            	}
            	
            	/// verificamos si ya existe el DNI
            	
            	try {
            		if(!ngc.ExisteDniNeg(Integer.parseInt(dni))) {
            			DNIvalido = true;
            		}
            	} catch (Exception e) {
            		e.printStackTrace();
                    DNIvalido = false;
            	}
            	
            	if(DNIvalido) {
            		
            	/// verificamos si ya existe el CUIL
            		
            		if(!ngc.ExisteCuilNeg(cuil) && cuil.length() > 0) {
            			
            			clienteNuevo.setDni(Integer.parseInt(dni));
		                
            			clienteNuevo.setCuil(cuil);
		                try {
		            		if(clienteNuevo.verificarCuilInvalido())
		            			valido++;
		            	} catch (CuilInvalidoException e) {
		            		System.out.println("\n" + e.getMessage() + "\n");
		            		request.setAttribute("mensaje", "Por favor, solamente números en el CUIL");
		            	}
		                
		                clienteNuevo.setSexo(genero);
		                
		                clienteNuevo.setNacionalidad(nacionalidad);
		                try {
		            		if(clienteNuevo.verificarNacionalidadInvalido())
		            			valido++;
		            	} catch (NacionalidadInvalidoException e) {
		            		System.out.println("\n" + e.getMessage() + "\n");
		            		request.setAttribute("mensaje", "Por favor, solamente letras en la nacionalidad");
		            	}
		                
		                clienteNuevo.setFechaNac(parseDate(fechaNacimiento));
		                clienteNuevo.setDireccion(direccion);
		                
		                clienteNuevo.setCorreo(correo);
		                try {
		            		if(clienteNuevo.verificarCorreoInvalido())
		            			valido++;
		            	} catch (CorreoInvalidoException e) {
		            		System.out.println("\n" + e.getMessage() + "\n");
		            		request.setAttribute("mensaje", "Por favor, el correo debe ser uno válido con un '@' y un '.'");
		            	}
		                
		                clienteNuevo.setTelefono(telefono);
		                try {
		                	if(clienteNuevo.verificarTelefonoInvalido())
		                	valido++;	
		                }catch (TelefonoInvalidoException e) {
		                	System.out.println("\n" + e.getMessage() + "\n");
		                	request.setAttribute("mensaje", "Por favor, el telefono solo debe contener números");
		                }
		                clienteNuevo.setNombreUsuario(nombreUsuario);
		                clienteNuevo.setContraseniaUsuario(contrasenia);
		                clienteNuevo.setPrivincia(provincia);
		                clienteNuevo.setLocalidad(localidad);
		                
		        		System.out.println("\nprotected void doPost\n " + clienteNuevo);// LOG

		        		if(valido==6) {
		        			int filas = ngc.AgregarClienteNeg(clienteNuevo);
			                if(filas>0) {
			                	request.setAttribute("mensaje", "El cliente se agrego correctamente.");
			                	
			                }else {
			                	request.setAttribute("mensaje", "El cliente no se pudo agregar correctamente.");
			                }
		        		}
		            } else {
		                request.setAttribute("mensaje", "No fue posible agregar al Cliente. El CUIL ingresado ya existe o no se ingresó de manera correcta.");
		            }
            			
            	}else {
            		request.setAttribute("mensaje", "No fue posible agregar al Cliente. El DNI ingresado ya existe o no se ingresó de manera correcta.");
            	}
            	
            }else {
            	request.setAttribute("mensaje", "Las contraseñas no coinciden.");
            }
               
        }


            
            
        // Cargar nuevamente las provincias y localidades para el formulario
        doGet(request, response);
    }

    private Date parseDate(String dateStr) {
        try {
            return new Date(new SimpleDateFormat("yyyy-MM-dd").parse(dateStr).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}