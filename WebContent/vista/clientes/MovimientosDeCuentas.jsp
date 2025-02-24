<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="Entidades.TipoCuenta"%>
<%@ page import="Entidades.Movimientos"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movimientos de Cuentas</title>

<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 text-gray-800 p-6">

<%
    final String LOGIN_ROUTE = "../inicio/";
%>

    <p class="font-semibold">Usuario: <%= session.getAttribute("username") %></p>
    <a href="<%= LOGIN_ROUTE %>Login.jsp" class="text-blue-500 hover:underline block mb-2">Cerrar Sesión</a>  
    <a href="<%= LOGIN_ROUTE %>EditarUsuario.jsp" class="text-blue-500 hover:underline">Editar Usuario</a>

<%
    List<TipoCuenta> listaTipoCuenta = (List<TipoCuenta>) request.getAttribute("ListadoTipoCuentas");
    List<Movimientos> listaMovimientos = (List<Movimientos>) request.getAttribute("ListadoMovimientos");
%>

<h1 class="text-2xl font-bold mb-6 text-center">Movimientos de Cuentas</h1>

<div class="max-w-md mx-auto bg-white p-6 rounded-lg shadow-lg space-y-4">
<form action="ServletMovimientosDeCuentas" method="get">
    <div>
        <label class="block font-semibold mb-2">Seleccione una Cuenta:</label>
        <select name="TipoCuenta" class="w-full p-2 border border-gray-300 rounded">
        <% if (listaTipoCuenta != null) {
              	for (TipoCuenta tc : listaTipoCuenta) { %>
            <option value="<%= tc.getCodTipoCuenta() %>" >
            <%= tc.getDescripcionTipoCuenta() %>
            </option>
            <% } } %>
        </select>
    </div>

    <div>
        <h3 class="text-lg font-semibold mb-2">Historial de Movimientos</h3>
        <select size="10" multiple class="w-full p-2 border border-gray-300 rounded">
        <% if (listaMovimientos != null) {
              	for (Movimientos mv : listaMovimientos) { %>
        			<option>
        			<%= mv.getFechaMovimiento() %> / <%= mv.getDescripcionMovimiento() %>
        			</option>
        <% } } %>
        </select>
    </div>
    <button type="submit" name="btnVerMovimientos" class="mt-4 p-2 bg-blue-500 text-white rounded">Ver Movimientos</button>
    </form>
</div>

<div class="text-center mt-6">
    <a href="InicioCliente.jsp" class="text-blue-500 hover:underline">Volver al Inicio</a>
</div>

</body>
</html>
