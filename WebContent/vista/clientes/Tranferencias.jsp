<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="Entidades.Cuenta"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transferencias</title>

<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 text-gray-800 p-6">

<%
    final String LOGIN_ROUTE = "../inicio/";
    String mensaje = (String) request.getAttribute("mensaje");
    Cuenta cuenta = (Cuenta) request.getAttribute("CBU");
    List<Cuenta> listaCBU = (List<Cuenta>) request.getAttribute("TodosCBU");
%>

<p class="font-semibold">Usuario: <%= session.getAttribute("username") %></p>
<a href="<%= LOGIN_ROUTE %>Login.jsp" class="text-blue-500 hover:underline block mb-2">Cerrar Sesión</a>
<a href="<%= LOGIN_ROUTE %>EditarUsuario.jsp" class="text-blue-500 hover:underline">Editar Usuario</a>

<h1 class="text-2xl font-bold mb-6 text-center">Transferencias</h1>

<% if (mensaje != null) { %>
<div class="text-green-600 font-bold mb-4">
    <%= mensaje %>
</div>
<% } %>

<div class="max-w-md mx-auto bg-white p-6 rounded-lg shadow-lg space-y-4">
    <form action="ServletTransferencias" method="post">
        <!-- CBU Cuenta Remitente -->
        <div>
            <label class="block font-semibold mb-2">CBU Cuenta Remitente</label>
            <input type="text" name="cbuRemitente" value="<%= cuenta != null ? cuenta.getCbuCuenta() : "" %>"
                class="w-full p-2 border border-gray-300 rounded" readonly>
        </div>

        <!-- Saldo Disponible -->
        <div>
            <label class="block font-semibold mb-2">Saldo Disponible</label>
            <input type="text" value="<%= cuenta != null ? cuenta.getSaldoCuenta() : "" %>"
                class="w-full p-2 border border-gray-300 rounded" readonly>
        </div>

        <!-- CBU Cuenta Destinatario -->
        <div>
            <label class="block font-semibold mb-2">CBU Cuenta Destinatario</label>
            <select name="CuentaDestinatario" class="w-full p-2 border border-gray-300 rounded">
                <% if (listaCBU != null) { 
                    for (Cuenta cu : listaCBU) { %>
                <option value="<%= cu.getCbuCuenta() %>">
                    <%= cu.getCbuCuenta() %>
                </option>
                <% } } %>
            </select>
        </div>

        <!-- Monto a Transferir -->
        <div>
            <label class="block font-semibold mb-2">Monto a Transferir</label>
            <input type="text" name="txtMonto" oninput="this.value = this.value.replace(/[^0-9.]/g, '')"
                class="w-full p-2 border border-gray-300 rounded" />
        </div>

        <!-- Concepto de la Transferencia -->
        <div>
            <label class="block font-semibold mb-2">Concepto de la Transferencia</label>
            <input type="text" name="txtConceptoTransferencia" class="w-full p-2 border border-gray-300 rounded" />
        </div>

        <!-- Botón de Transferencia -->
        <div class="text-center mt-4">
            <button type="submit" name="BtnTransferencia"
                class="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600"
                onclick="return confirm('¿Estás seguro de que deseas realizar esta transferencia?');">
                Realizar Transferencia
            </button>
        </div>
    </form>
</div>

<div class="text-center mt-6">
    <a href="InicioCliente.jsp" class="text-blue-500 hover:underline">Volver al Inicio</a>
</div>

</body>
</html>
