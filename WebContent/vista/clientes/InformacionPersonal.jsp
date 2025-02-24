<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.List"%>
<%@ page import = "Entidades.Clientes"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Información Personal</title>

<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 text-gray-800 p-6">

<%
    final String LOGIN_ROUTE = "../inicio/";
%>

    <p class="font-semibold">Usuario: <%= session.getAttribute("username") %></p>
    <a href="<%= LOGIN_ROUTE %>Login.jsp" class="text-blue-500 hover:underline block mb-2">Cerrar Sesión</a>
    <a href="<%= LOGIN_ROUTE %>EditarUsuario.jsp" class="text-blue-500 hover:underline">Editar Usuario</a>


<h1 class="text-2xl font-bold mb-6 text-center">Información Personal</h1>

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


<%
    List<Clientes> listClientes = null;
    if (request.getAttribute("datosCliente") != null){
        listClientes = (List<Clientes>) request.getAttribute("datosCliente");
    }
%>

<div class="max-w-md mx-auto bg-white p-6 rounded-lg shadow-lg space-y-4">
 <%
    if(listClientes != null)
    for(Clientes cs : listClientes){
    	
    %>
    <div><span class="font-semibold">Nombre:  </span> <%= cs.getNombre() %> </div>
    <div><span class="font-semibold">Apellido:  </span> <%= cs.getApellido() %> </div>
    <div><span class="font-semibold">DNI:  </span> <%= cs.getDni() %> </div>
    <div><span class="font-semibold">Cuil:  </span> <%= cs.getCuil() %> </div>
    <div><span class="font-semibold">Sexo:  </span> <%= cs.getSexo() %> </div>
    <div><span class="font-semibold">Nacionalidad:  </span> <%= cs.getNacionalidad() %> </div>
    <div><span class="font-semibold">Fecha de Nacimiento:  </span> <%= cs.getFechaNac() %> </div>
    <div><span class="font-semibold">Dirección: </span> <%= cs.getDireccion() %>  </div>
    <div><span class="font-semibold">Correo Electrónico:  </span> <%= cs.getCorreo() %> </div>
    <div><span class="font-semibold">Teléfono:  </span> <%= cs.getTelefono() %> </div>
    <div><span class="font-semibold">Nombre Usuario:  </span> <%= session.getAttribute("username") %> </div>
    <div><span class="font-semibold">Contraseña:   </span> <%= session.getAttribute("passwordCliente") %> </div>
    <div><span class="font-semibold">Provincia:</span> <%= cs.getPrivincia()%> </div>
    <div><span class="font-semibold">Localidad:</span> <%= cs.getLocalidad() %> </div>
    <%
    } 
    %>
</div>

<div class="text-center mt-6">
    <a href="InicioCliente.jsp" class="text-blue-500 hover:underline">Volver al Inicio</a>
</div>

</body>
</html>
