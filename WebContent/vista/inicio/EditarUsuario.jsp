<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<h1>Editar Usuario</h1>

 <% 
    String mensaje = (String) request.getAttribute("mensaje");
    if (mensaje != null) {
	%>
	    <div class="text-green-600 font-bold mb-4">
	        <%= mensaje %>
	    </div>
	<%
	    }
	%>

<form action="ServletEditarUsuario" method="post">
 Nombre Usuario: <%= session.getAttribute("username") %> <br><!-- Aca va una expresion porque el nombre de usuario no debe ser editablr -->
 <input type="hidden" name="txtNombreUsuario" value="<%= session.getAttribute("username") %>">
 <br>
 Contraseña:  <input type="password" name="txtContrasenia"><br>
 <br>
 Repetir Contraseña:  <input type="password" name="txtRepetirContrasenia"><br>
 <br>
 <input type="submit" name="btnAceptar" value="Aceptar" onclick="return confirm('Estas seguro de que deseas modificar esta contraseña?');">   
 <input type="submit" name="btnCancelar" value="Cancelar" onclick="return confirm('Estas seguro de que deseas cancelar esta actualizacion?');"><br>
 </form>
 <br>
 
 <a href= Login.jsp >Volver a ingresar desde el Login</a>
</body>
</html>