<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="Entidades.Provincia"%>
<%@ page import="Entidades.Localidad"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Panel de Usuario</title>
<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 text-gray-800 p-6">

<%
    List<Provincia> listaProv = (List<Provincia>) request.getAttribute("ListadoProv");
    List<Localidad> listaLoc = (List<Localidad>) request.getAttribute("Listaloc");
%>

<div class="container mx-auto max-w-lg p-6 bg-white shadow-md rounded-lg">

<%
    final String INICIO_ROUTE = "../inicio/";
%>

<p class="font-semibold">Usuario: <%= session.getAttribute("username") %></p>
    <a href="<%= INICIO_ROUTE %>Login.jsp" class="text-blue-500 hover:underline block mb-2">Cerrar Sesión</a>
    <a href="<%= INICIO_ROUTE %>EditarUsuario.jsp" class="text-blue-500 hover:underline">Editar Usuario</a>


    <h1 class="text-2xl font-bold mb-6 text-center">Agregar Clientes</h1>
    
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

    <form action="ServletCargarClientes" method="post" class="space-y-4">
        <div>
            <label class="block font-semibold">Nombre:</label>
            <input type="text" name="txtNombre" class="w-full p-2 border border-gray-300 rounded" value="<%= (request.getParameter("txtNombre") != null && request.getAttribute("mensaje") == null) ? request.getParameter("txtNombre") : "" %>" />
        </div>

        <div>
            <label class="block font-semibold">Apellido:</label>
            <input type="text" name="txtApellido" class="w-full p-2 border border-gray-300 rounded" value="<%= (request.getParameter("txtApellido") != null && request.getAttribute("mensaje") == null) ? request.getParameter("txtApellido") : "" %>" />
        </div>

        <div>
            <label class="block font-semibold">DNI:</label>
            <input type="text" name="txtDni" maxlength="8" oninput="this.value = this.value.replace(/[^0-9]/g, '')" class="w-full p-2 border border-gray-300 rounded" value="<%= (request.getParameter("txtDni") != null && request.getAttribute("mensaje") == null) ? request.getParameter("txtDni") : "" %>" />
        </div>

        <div>
            <label class="block font-semibold">CUIL:</label>
            <input type="text" name="txtCuil"  class="w-full p-2 border border-gray-300 rounded" value="<%= (request.getParameter("txtCuil") != null && request.getAttribute("mensaje") == null) ? request.getParameter("txtCuil") : "" %>" />
        </div>
        
         <div>
            <label class="block font-semibold">Provincia:</label>
            <select name="provincia" onchange="this.form.submit()" class="w-full p-2 border border-gray-300 rounded">
                <option value="" selected >Seleccione una provincia</option>
                <% if (listaProv != null) {
                    for (Provincia prov : listaProv) { %>
                        <option value="<%= prov.getCodProvincia() %>" <%= prov.getCodProvincia().equals(request.getParameter("provincia")) ? "selected" : "" %>>
                            <%= prov.getDescripcionProvincia() %>
                        </option>
                <% } } %>
            </select>
        </div>

        <div>
            <label class="block font-semibold">Localidad:</label>
            <select name="localidad" class="w-full p-2 border border-gray-300 rounded">
                <option selected>Seleccione una Localidad</option>
                <% if (listaLoc != null) {
                    for (Localidad loc : listaLoc) { %>
                        <option value="<%= loc.getCodLocalidad() %>" <%= loc.getCodLocalidad().equals(request.getParameter("localidad")) ? "selected" : "" %>>
                            <%= loc.getDescripcionLocalidad() %>
                        </option>
                <% } } %>
            </select>
        </div>

        <div>
            <label class="block font-semibold">Sexo:</label>
            <select name="genero" class="w-full p-2 border border-gray-300 rounded">
                <option value="" selected>Seleccione un Género</option>
                <option value="Femenino" <%= "Femenino".equals(request.getParameter("genero")) ? "selected" : "" %>>Femenino</option>
                <option value="Masculino" <%= "Masculino".equals(request.getParameter("genero")) ? "selected" : "" %>>Masculino</option>
            </select>
        </div>

        <div>
            <label class="block font-semibold">Nacionalidad:</label>
            <input type="text" name="txtNacionalidad" class="w-full p-2 border border-gray-300 rounded" value="<%= (request.getParameter("txtNacionalidad") != null && request.getAttribute("mensaje") == null) ? request.getParameter("txtNacionalidad") : "" %>" />
        </div>

        <div>
            <label class="block font-semibold">Fecha de Nacimiento:</label>
            <input type="date" name="txtFechaNacimiento" class="w-full p-2 border border-gray-300 rounded" value="<%= (request.getParameter("txtFechaNacimiento") != null && request.getAttribute("mensaje") == null) ? request.getParameter("txtFechaNacimiento") : "" %>" />
        </div>

        <div>
            <label class="block font-semibold">Dirección:</label>
            <input type="text" name="txtDireccion" class="w-full p-2 border border-gray-300 rounded" value="<%= (request.getParameter("txtDireccion") != null && request.getAttribute("mensaje") == null) ? request.getParameter("txtDireccion") : "" %>" />
        </div>

        <div>
            <label class="block font-semibold">Correo Electrónico:</label>
            <input type="email" name="txtCorreo" class="w-full p-2 border border-gray-300 rounded" value="<%= (request.getParameter("txtCorreo") != null && request.getAttribute("mensaje") == null) ? request.getParameter("txtCorreo") : "" %>" />
        </div>

        <div>
            <label class="block font-semibold">Teléfono:</label>
            <input type="text" name="txtTelefono" maxlength="10" oninput="this.value = this.value.replace(/[^0-9]/g, '')" class="w-full p-2 border border-gray-300 rounded" value="<%= (request.getParameter("txtTelefono") != null && request.getAttribute("mensaje") == null) ? request.getParameter("txtTelefono") : "" %>" />
        </div>

        <div>
            <label class="block font-semibold">Nombre Usuario:</label>
            <input type="text" name="txtNombreUsuario" class="w-full p-2 border border-gray-300 rounded" value="<%= (request.getParameter("txtNombreUsuario") != null && request.getAttribute("mensaje") == null) ? request.getParameter("txtNombreUsuario") : "" %>" />
        </div>

        <div>
            <label class="block font-semibold">Contraseña:</label>
            <input type="password" name="txtContraseniaUsuario" class="w-full p-2 border border-gray-300 rounded" value="<%= (request.getParameter("txtContraseniaUsuario") != null && request.getAttribute("mensaje") == null) ? request.getParameter("txtContraseniaUsuario") : "" %>" />
        </div>

        <div>
            <label class="block font-semibold">Repetir Contraseña:</label>
            <input type="password" name="txtRepetirContrasenia" class="w-full p-2 border border-gray-300 rounded" value="<%= (request.getParameter("txtRepetirContrasenia") != null && request.getAttribute("mensaje") == null) ? request.getParameter("txtRepetirContraseña") : "" %>"/>
        </div>

        <div class="flex items-center justify-between">
            <input type="submit" name="btnAgregar" value="Agregar" class="bg-green-500 text-white px-4 py-2 rounded cursor-pointer hover:bg-green-600">
            <a href="InicioClientes.jsp" class="text-blue-500 hover:underline">Volver al Inicio</a>
        </div>
    </form>
</div>
</body>
</html>
