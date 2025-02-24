<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Agregar Cuentas</title>


<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">

</head>
<body class="bg-gray-100 text-gray-800 p-6">

<%
    final String LOGIN_ROUTE = "../../inicio/";
%>

    <p class="font-semibold mb-2">Usuario: <%= session.getAttribute("username") %></p>
    <a href="<%= LOGIN_ROUTE %>Login.jsp" class="text-blue-500 hover:underline block mb-2">Cerrar Sesión</a>
    <a href="<%= LOGIN_ROUTE %>EditarUsuario.jsp" class="text-blue-500 hover:underline">Editar Usuario</a>

<h1 class="text-2xl font-bold mb-6 text-center">Agregar Cuentas</h1>

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
<!-- Formulario de entrada de datos -->
<form action="ServletCargarCuentas" method="post">
<div class="space-y-4 max-w-md mx-auto bg-white p-6 rounded-lg shadow-lg">
    <div>
        <label class="block font-semibold">Número de Cuenta:</label>
        <input type="text" name="txtNumeroCuenta" class="w-full p-2 border border-gray-300 rounded" />
    </div>


    <div>
        <label class="block font-semibold">Tipo de Cuenta:</label>
        <select name="TipoCuenta" class="w-full p-2 border border-gray-300 rounded">
            <option value="1" >Caja Ahorro</option>
            <option value="2" >Cuenta Corriente</option>
        </select>
    </div>

    <div>
        <label class="block font-semibold">CBU:</label>
        <input type="text" name="txtCBU" class="w-full p-2 border border-gray-300 rounded" />
    </div>

    <%
        float valorInicial = 10000;
    %>

    <div>
        <label class="block font-semibold">Saldo:</label>
        <div class="flex items-center space-x-2">
            <input type="text" id="txtSaldo" name="txtSaldito" value="<%= valorInicial %>" readonly class="w-full p-2 border border-gray-300 rounded" />
            <button type="button" onclick="incrementar()" class="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600">+</button>
            <button type="button" onclick="decrementar()" class="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600">-</button>
        </div>
    </div>

  <!-- LO COMENTÉ porque use una funcion que saca la fecha actual en el servlet  <div>
        <label class="block font-semibold">Fecha de Creación:</label>
        <input type="text" name="txtFechaCreacion" class="w-full p-2 border border-gray-300 rounded" />
    </div>-->

    <div class="flex space-x-4 mt-4">
        <input type="submit" name="btnAgregar" value="Agregar" class="bg-blue-500 text-white px-4 py-2 rounded cursor-pointer hover:bg-blue-600">
        <a href="InicioCuentas.jsp" class="text-blue-500 hover:underline">Volver al Inicio</a>
    </div>
</div>
</form>

<script>
    function incrementar() {
        let input = document.getElementById("txtSaldo");
        let valorActual = parseFloat(input.value, 10) || 10000;
        input.value = valorActual + 100;
    }

    function decrementar() {
        let input = document.getElementById("txtSaldo");
        let valorActual = parseFloat(input.value, 10) || 10000;
        if (valorActual > 10000) {
            input.value = valorActual - 100;
        }
    }
</script>

</body>
</html>
