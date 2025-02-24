<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.List"%>
<%@ page import = "Entidades.Clientes"%>    


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificar Clientes</title>

<!-- Estilos y Scripts de DataTables -->
<link rel="stylesheet" href="https://cdn.datatables.net/1.13.1/css/jquery.dataTables.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js"></script>

<%

    final String INICIO_ROUTE = "../inicio/";
%>

<!-- TailwindCSS -->
<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">

</head>
<body class="bg-gray-100 text-gray-800 p-6">

    <p class="font-semibold mb-2">Usuario: <%= session.getAttribute("username") %></p>
    <a href="<%= INICIO_ROUTE %>Login.jsp" class="text-blue-500 hover:underline block mb-2">Cerrar Sesion</a>
    <a href="<%= INICIO_ROUTE %>EditarUsuario.jsp" class="text-blue-500 hover:underline">Editar Usuario</a>


<h1 class="text-2xl font-bold mb-6 text-center">Modificar Clientes</h1>



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


<!-- Formulario de busqueda -->
<form action="ServletModificarClientes" method="post" class="space-y-4">
<div class="mb-4">
    <label class="block font-semibold">DNI Cliente:</label>
    <input type="text" name="txtDniClientes" class="w-full p-2 border border-gray-300 rounded" />
    <input type="submit" name="btnBuscar" value="Buscar" class="bg-green-500 text-white px-4 py-2 rounded mt-2 cursor-pointer hover:bg-green-600">
    <input type="submit" name="btnVerTodo" value="Ver Todo /Actualizar" class="bg-blue-500 text-white px-4 py-2 rounded cursor-pointer hover:bg-blue-600">
</div>
</form>

<%
List<Clientes> listClientes = null;
if (request.getAttribute("Listado") != null){
    listClientes = (List<Clientes>) request.getAttribute("Listado");
}
%>


<div class="overflow-x-auto">
    <table id="tablaModificar" class="w-full text-center border border-gray-300 mt-4">
    
        <thead class="bg-gray-200">
            <tr> 
                <th class="p-2 border">Nombre</th>
                <th class="p-2 border">Apellido</th>
                <th class="p-2 border">DNI</th>
                <th class="p-2 border">Nacionalidad</th>
                <th class="p-2 border">Correo Electronico</th>
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
			if (listClientes != null) {
			    for (Clientes cs : listClientes) {
			%>
			        <tr>
			            <form action="ServletModificarClientes" method="post">
			                <td class="p-2 border">
			                    <input type="text" name="nombreModificar" value="<%= cs.getNombre() %>" class="w-full p-2 border border-gray-300 rounded" />
			                </td>
			                <td class="p-2 border">
			                    <input type="text" name="apellidoModificar" value="<%= cs.getApellido() %>" class="w-full p-2 border border-gray-300 rounded" />
			                </td>
			                <td class="p-2 border">
			                    <input type="text" name="dniModificar" value="<%= cs.getDni() %>" class="w-full p-2 border border-gray-300 rounded" readonly />
			                </td>
			                <td class="p-2 border">
			                    <input type="text" name="nacionalidadModificar" value="<%= cs.getNacionalidad() %>" class="w-full p-2 border border-gray-300 rounded" />
			                </td>
			                <td class="p-2 border">
			                    <input type="email" name="correoModificar" value="<%= cs.getCorreo() %>" class="w-full p-2 border border-gray-300 rounded" />
			                </td>
			                <td class="p-2 border">
			                    <input type="text" name="CuilModificar" value="<%= cs.getCuil() %>" class="w-full p-2 border border-gray-300 rounded" readonly/>
			                </td>
			                <td class="p-2 border">
			                    <input type="text" name="FechaNacModificar" value="<%= cs.getFechaNac() %>" class="w-full p-2 border border-gray-300 rounded" />
			                </td>
			                <td class="p-2 border">
			                    <input type="text" name="DireccionModificar" value="<%= cs.getDireccion() %>" class="w-full p-2 border border-gray-300 rounded" />
			                </td>
			                <td class="p-2 border">
			                    <input type="text" name="GeneroModificar" value="<%= cs.getSexo() %>" class="w-full p-2 border border-gray-300 rounded" />
			                </td>
			                <td class="p-2 border">
			                    <input type="text" name="TelefonoModificar" value="<%= cs.getTelefono() %>" class="w-full p-2 border border-gray-300 rounded" />
			                </td>
			                <td class="p-2 border">
			                    <input type="hidden" name="accionModificar" value="modificar">
			                    <input type="submit" name="btnModificar" value="Modificar" class="bg-red-500 text-white px-4 py-2 rounded cursor-pointer hover:bg-red-600" onclick="return confirm('Estas seguro de que deseas modificar este cliente?');">
			                </td>
			            </form>
			        </tr>
			<%
			    }
			}
			%>
			</tbody>
    </table>
</div>

<script>
    $(document).ready(function() {
        $('#tablaModificar').DataTable();
    });
</script>

<div class="text-center mt-6">
    <a href="InicioClientes.jsp" class="text-blue-500 hover:underline">Volver al Inicio</a>
</div>

</body>
</html>