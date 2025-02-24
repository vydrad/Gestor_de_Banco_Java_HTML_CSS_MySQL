<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Inicio Usuario Banco</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 text-gray-800 p-6">

<%
    final String LOGIN_ROUTE = "../inicio/";
%>

<a href="<%= LOGIN_ROUTE %>Login.jsp" class="text-blue-500 hover:underline block mb-2">Cerrar Sesión</a>
<a href="<%= LOGIN_ROUTE %>EditarUsuario.jsp" class="text-blue-500 hover:underline">Editar Usuario</a>

    <div class="container mx-auto p-6">
        <h1 class="text-2xl font-bold mb-6 text-center">Inicio Usuario Banco, Bienvenido/a <%= session.getAttribute("username")%></h1>

        <!-- CLIENTES -->
        <section class="bg-white p-4 shadow-md rounded-lg mb-6">
            <h2 class="text-xl font-semibold mb-4">Gestión de Clientes</h2>
            <p>Accede a las opciones relacionadas con los clientes del banco.</p>
            <a href="../banco/InicioClientes.jsp" class="block text-blue-500 mt-2 hover:underline">Ir a Clientes</a>
        </section>

        <!-- CUENTAS -->
        <section class="bg-white p-4 shadow-md rounded-lg mb-6">
            <h2 class="text-xl font-semibold mb-4">Gestión de Cuentas</h2>
            <p>Administra las cuentas de los clientes y asigna nuevas cuentas.</p>
            <a href="../banco/cuentas/InicioCuentas.jsp" class="block text-blue-500 mt-2 hover:underline">Ir a Cuentas</a>
        </section>

        <!-- PRESTAMOS -->
        <section class="bg-white p-4 shadow-md rounded-lg mb-6">
            <h2 class="text-xl font-semibold mb-4">Autorización de Préstamos</h2>
            <p>Autoriza o rechaza las solicitudes de préstamos de los clientes.</p>
            <a href="../banco/prestamos/ServletPrestamosAdminBanco" class="block text-blue-500 mt-2 hover:underline">Ir a Préstamos</a>
        </section>

        <!-- REPORTES -->
        <section class="bg-white p-4 shadow-md rounded-lg">
            <h2 class="text-xl font-semibold mb-4">Reportes</h2>
            <p>Consulta los reportes para una visión general del banco.</p>
            <a href="../banco/reportes/ServletReportes" class="block text-blue-500 mt-2 hover:underline">Ir a Reportes</a>
        </section>
    </div>
</body>
</html>
