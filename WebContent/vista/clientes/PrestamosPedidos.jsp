<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="Entidades.Cuenta"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pedido de Pr�stamos</title>

<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 text-gray-800 p-6">

<%
    final String LOGIN_ROUTE = "../inicio/";
    
    List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("ListadoCuentasDNI");
%>

    <p class="font-semibold">Usuario:  <%= session.getAttribute("username") %></p>
    <a href="<%= LOGIN_ROUTE %>Login.jsp" class="text-blue-500 hover:underline block mb-2">Cerrar Sesi�n</a>
    <a href="<%= LOGIN_ROUTE %>EditarUsuario.jsp" class="text-blue-500 hover:underline">Editar Usuario</a>


<h1 class="text-2xl font-bold mb-6 text-center">Pedido de Pr�stamos</h1>


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

<div class="max-w-md mx-auto bg-white p-6 rounded-lg shadow-lg space-y-4">
    <form action="ServletPrestamosPedidos" method="post">
        <!-- Monto del pr�stamo -->
        <div>
            <label class="block font-semibold mb-2">Ingresar el monto del pr�stamo solicitado:</label>
            <input type="text" name="txtMontoPrestamo" class="w-full p-2 border border-gray-300 rounded" />
        </div>

        <!-- Fecha de solicitud -->
        <div>
            <label class="block font-semibold mb-2">Fecha de solicitud: <%= request.getAttribute("FechaActual") %> </label>
           
        </div>

        <!-- Cantidad de cuotas -->
        <div>
            <label class="block font-semibold mb-2">Seleccione la cantidad de cuotas:</label>
            <select name="ddlCantidadCuotas" class="w-full p-2 border border-gray-300 rounded">
                <option>Seleccione la cantidad de cuotas</option>
                <option>3</option>
                <option>6</option>
                <option>12</option>
            </select>
        </div>

       
                <div>
            <label class="block font-semibold mb-2">Seleccione la cuenta para depositar:</label>
            <select name= "ddlNumeroCuenta" class="w-full p-2 border border-gray-300 rounded">
                <option value="">Numero de cuenta</option>
                <% if (cuentas != null) { 
                for (Cuenta cuenta : cuentas) { %>
                    <option value="<%= cuenta.getNumeroCuenta() %>">
                        <%= cuenta.getNumeroCuenta() %>
                    </option>
                <% } 
            } %>
            </select>
        </div>

        <!-- Porcentaje de Inter�s -->
        <div>
            <p class="font-semibold mb-2">Porcentaje de Inter�s: 10%</p>
        </div>

        <!-- Bot�n de solicitud de pr�stamo -->
        <div class="text-center mt-4">
            <input type="submit" name="btnSolicitarPrestamo" value="Solicitar Pr�stamo" class="bg-green-500 text-white px-4 py-2 rounded cursor-pointer hover:bg-green-600" onclick="return confirm('�Est�s seguro de que deseas solicitar este prestamo?');">
        </div>
    </form>
</div>

<div class="text-center mt-6">
    <a href="InicioCliente.jsp" class="text-blue-500 hover:underline">Volver al Inicio</a>
</div>

</body>
</html>
