<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.List"%>
<%@ page import = "Entidades.Cuenta"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listar Cuentas</title>

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

<h1 class="text-2xl font-bold mb-6 text-center">Listar Cuentas</h1>

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

<form action="ServletListarCuentas" method="post" class="mb-4">
    <div class="max-w-md mx-auto mb-4">
        <label class="block font-semibold">DNI Cliente:</label>
        <input type="text" name="txtDniClientes" class="w-full p-2 border border-gray-300 rounded mb-2" />
        <input type="submit" name="btnBuscar" value="Buscar" class="bg-green-500 text-white px-4 py-2 rounded cursor-pointer hover:bg-green-600 w-full">
        <input type="submit" name="btnVerTodo" value="Ver todo" class="bg-green-500 text-white px-4 py-2 rounded cursor-pointer hover:bg-green-600 w-full">
    </div>
</form>

<%
    List<Cuenta> listCuentas = null;
    if (request.getAttribute("ListadoCuentas") != null){
        listCuentas = (List<Cuenta>)request.getAttribute("ListadoCuentas");
    }
%>

<div class="overflow-x-auto">
    <table id="tablaCuentas" class="w-full text-center border border-gray-300 mt-4">
        <thead class="bg-gray-200">
            <tr>
                <th class="p-2 border">Número de Cuenta</th>
                <th class="p-2 border">DNI Cliente</th>
                <th class="p-2 border">Tipo de Cuenta</th>
                <th class="p-2 border">CBU</th>
                <th class="p-2 border">Saldo</th>
            </tr>
        </thead>
        <tbody>
            <%
            if (listCuentas != null) {
                for (Cuenta cu : listCuentas) {
                    String tipoCuentaTexto = (cu.getTipoCuenta() == 1) ? "Caja de ahorro" : "Cuenta corriente";
            %>
            <tr>
                <td class="p-2 border"> <%= cu.getNumeroCuenta() %></td>
                <td class="p-2 border"> <%= cu.getDniCuenta() %></td>
                <td class="p-2 border"> <%= tipoCuentaTexto %> </td>
                <td class="p-2 border"> <%= cu.getCbuCuenta() %></td>
                <td class="p-2 border"> <%= cu.getSaldoCuenta() %></td>
            </tr>
            <% } } %>
        </tbody>
    </table>
</div>

<script>
    $(document).ready(function() {
        $('#tablaCuentas').DataTable();
    });
</script>

<div class="text-center mt-6">
    <a href="InicioCuentas.jsp" class="text-blue-500 hover:underline">Volver al Inicio</a>
</div>

</body>
</html>
