<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.List"%>
<%@ page import = "Entidades.Cuenta"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificar Cuentas</title>

<!-- TailwindCSS y DataTables -->
<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.datatables.net/1.13.1/css/jquery.dataTables.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js"></script>

</head>
<body class="bg-gray-100 text-gray-800 p-6">

<%
    final String LOGIN_ROUTE = "../../inicio/";
%>

<p class="font-semibold">Usuario: <%= session.getAttribute("username") %></p>
<a href="<%= LOGIN_ROUTE %>Login.jsp" class="text-blue-500 hover:underline block mb-2">Cerrar Sesión</a>
<a href="<%= LOGIN_ROUTE %>EditarUsuario.jsp" class="text-blue-500 hover:underline">Editar Usuario</a>

<h1 class="text-2xl font-bold mb-6 text-center">Modificar Cuentas</h1>

<form action="ServletModificarCuentas" method="post">
    <div class="max-w-md mx-auto mb-4">
        <label class="block font-semibold">DNI Cliente:</label>
        <input type="text" name="txtDniClientes" class="w-full p-2 border border-gray-300 rounded mb-2" />
        <input type="submit" name="btnBuscar" value="Buscar" class="bg-green-500 text-white px-4 py-2 rounded cursor-pointer hover:bg-green-600 w-full">
        <input type="submit" name="btnVerTodo" value="Ver todo" class="bg-green-500 text-white px-4 py-2 rounded cursor-pointer hover:bg-green-600 w-full">
    </div>
</form>

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
    List<Cuenta> listCuentas = null;
    if (request.getAttribute("ListadoCuentas") != null){
        listCuentas = (List<Cuenta>)request.getAttribute("ListadoCuentas");
    }
%>

<div class="overflow-x-auto">
    <table id="tablaModificar" class="w-full text-center border border-gray-300 mt-4">
        <thead class="bg-gray-200">
            <tr>
                <th class="p-2 border">Nï¿½mero de Cuenta</th>
                <th class="p-2 border">DNI Cliente</th>
                <th class="p-2 border">Tipo de Cuenta</th>
                <th class="p-2 border">CBU</th>
                <th class="p-2 border">Saldo</th>
                <th class="p-2 border">Acciones</th>
            </tr>
        </thead>
        <tbody>
            <%
            if (listCuentas != null) {
                for (Cuenta cu : listCuentas) {
            %>
            <tr>
                <form action="ServletModificarCuentas" method="post">
                    <td><%= cu.getNumeroCuenta() %></td>
                    
                    <td><%= cu.getDniCuenta() %></td>
                    
                    <td>
                        <select name="TipoCuentaModificar" class="w-full p-2 border border-gray-300 rounded">
                            <option value="1" <%= cu.getTipoCuenta() == 1 ? "selected" : "" %>>Caja de ahorro</option>
                            <option value="2" <%= cu.getTipoCuenta() == 2 ? "selected" : "" %>>Cuenta corriente</option>
                        </select>
                    </td>
                    
                    <td><%= cu.getCbuCuenta() %></td>
                    
                    <td>
                        <input type="text" name="SaldoModificar" value="<%= cu.getSaldoCuenta() %>" class="w-full p-2 border border-gray-300 rounded">
                    </td>
                    
                    <td>
                        <input type="hidden" name="accionModificar" value="modify">
                        <input type="hidden" name="numeroCuenta" value="<%= cu.getNumeroCuenta() %>">
                        <input type="hidden" name="cbuCuenta" value="<%= cu.getCbuCuenta() %>">
                        <input type="hidden" name="cbufecha" value="<%= cu.getFechaCreacionCuenta() %>">
                        <input type="hidden" name="dniCuenta" value="<%= cu.getDniCuenta() %>">
                        <input type="hidden" name="cbuEstado" value="<%= cu.getEstadoCuenta() %>">
                        
                        <input type="submit" name="btnModificar" value="Modificar" class="bg-red-500 text-white px-4 py-2 rounded cursor-pointer hover:bg-red-600" onclick="return confirm('Estas seguro de que deseas modificar esta cuenta?');">
                    </td>
                </form>
            </tr>
            <% } } %>
        </tbody>
    </table>
</div>

<script>
    $(document).ready(function() {
        $('#tablaModificar').DataTable();
    });
</script>

<div class="text-center mt-6">
    <a href="InicioCuentas.jsp" class="text-blue-500 hover:underline">Volver al Inicio</a>
</div>

</body>
</html>
