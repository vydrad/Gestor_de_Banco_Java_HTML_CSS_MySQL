<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.List"%>
<%@ page import = "Entidades.Clientes"%>    

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Eliminar Clientes</title>

<!-- Estilos y Scripts de DataTables -->
<link rel="stylesheet" href="https://cdn.datatables.net/1.13.1/css/jquery.dataTables.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js"></script>

<!-- TailwindCSS -->
<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">

</head>
<body class="bg-gray-100 text-gray-800 p-6">


<%
    final String INICIO_ROUTE = "../inicio/";
%>

    <p class="font-semibold">Usuario: <%= session.getAttribute("username") %></p>
    <a href="<%= INICIO_ROUTE %>Login.jsp" class="text-blue-500 hover:underline block mb-2">Cerrar Sesión</a>
    <a href="<%= INICIO_ROUTE %>EditarUsuario.jsp" class="text-blue-500 hover:underline">Editar Usuario</a>


<h1 class="text-2xl font-bold mb-6 text-center">Eliminar Clientes</h1>

<!-- Mostrar el mensaje si existe -->
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

<!-- Formulario de búsqueda -->
<form action="ServletEliminarClientes" method="post" class="space-y-4">
    <div>
        <label class="block font-semibold">DNI Cliente:</label>
        <input type="text" name="txtDniClientes" class="w-full p-2 border border-gray-300 rounded" />
    </div>
    <div class="flex space-x-2">
        <input type="submit" name="btnBuscar" value="Buscar" class="bg-green-500 text-white px-4 py-2 rounded cursor-pointer hover:bg-green-600">
        <input type="submit" name="btnVerTodo" value="Ver Todo" class="bg-blue-500 text-white px-4 py-2 rounded cursor-pointer hover:bg-blue-600">
    </div>
</form>

<%
List<Clientes> listClientes = null;
if (request.getAttribute("Listado") != null){
    listClientes = (List<Clientes>) request.getAttribute("Listado");
}
%>

<!-- Tabla de clientes -->
<div class="overflow-x-auto mt-6">
    <table id="tablaEliminar" class="w-full text-center border border-gray-300 mt-4">
        <thead class="bg-gray-200">
            <tr> 
                <th class="p-2 border">Nombre</th>
                <th class="p-2 border">Apellido</th>
                <th class="p-2 border">DNI</th>
                <th class="p-2 border">Nacionalidad</th>
                <th class="p-2 border">Correo Electrónico</th>
                <th class="p-2 border">Cuil</th>
	            <th class="p-2 border">Fecha de Nacimiento</th>
	            <th class="p-2 border">Direccion</th>
	            <th class="p-2 border">Sexo</th>
	            <th class="p-2 border">Telefono</th>
                <th class="p-2 border">Acciones</th>
            </tr>
        </thead>
        <tbody>
        <%
        if(listClientes != null)
        for(Clientes cs : listClientes){
        %>
            <tr> 
                <td class="p-2 border"><%= cs.getNombre() %></td>
                <td class="p-2 border"><%= cs.getApellido() %></td> 
                <td class="p-2 border"><%= cs.getDni() %></td>
                <td class="p-2 border"><%= cs.getNacionalidad() %></td>
                <td class="p-2 border"><%= cs.getCorreo() %></td>
                <td class="p-2 border"><%= cs.getCuil() %></td>
                <td class="p-2 border"><%= cs.getFechaNac() %></td>
                <td class="p-2 border"><%= cs.getDireccion() %></td>
                <td class="p-2 border"><%= cs.getSexo() %></td>
                <td class="p-2 border"><%= cs.getTelefono() %></td>
                <td class="p-2 border">
                    <form action="ServletEliminarClientes" method="post">
                        <input type="hidden" name="dniEliminar" value="<%= cs.getDni() %>">
                        <input type="submit" name="btnEliminar" value="Eliminar" class="bg-red-500 text-white px-4 py-2 rounded cursor-pointer hover:bg-red-600" onclick="return confirm('¿Estás seguro de que deseas eliminar este cliente?');">
                    </form>
                </td>
            </tr>
        <%
        }
        %>
        </tbody>
    </table>
</div>

<script>
    $(document).ready(function() {
        $('#tablaEliminar').DataTable();
    });
</script>

<!-- Enlace de retorno -->
<div class="text-center mt-6">
    <a href="InicioClientes.jsp" class="text-blue-500 hover:underline">Volver al Inicio</a>
</div>

</body>
</html>
