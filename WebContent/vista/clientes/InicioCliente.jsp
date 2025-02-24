<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Inicio Clientes</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 text-gray-800 p-6">

<%
    final String LOGIN_ROUTE = "../inicio/";
%>

<a href="<%= LOGIN_ROUTE %>Login.jsp" class="text-blue-500 hover:underline block mb-2">Cerrar Sesión</a>
<a href="<%= LOGIN_ROUTE %>EditarUsuario.jsp" class="text-blue-500 hover:underline">Editar Usuario</a>

    <div class="container mx-auto p-6">
        <h1 class="text-2xl font-bold mb-6 text-center">Inicio Clientes, Bienvenido/a <%= session.getAttribute("username")%></h1>

        <!-- MOVIMIENTOS DE CUENTAS -->
        <section class="bg-white p-4 shadow-md rounded-lg mb-6">
            <h2 class="text-xl font-semibold mb-4">Movimiento de Cuentas</h2>
            <p>Consulta los movimientos de tus cuentas bancarias.</p>
            <a href="../clientes/ServletMovimientosDeCuentas?Param=1" class="block text-blue-500 mt-2 hover:underline">Ir a Movimientos de Cuentas</a>
        </section>

        <!-- TRANSFERENCIAS -->
        <section class="bg-white p-4 shadow-md rounded-lg mb-6">
            <h2 class="text-xl font-semibold mb-4">Transferencias</h2>
            <p>Realiza transferencias entre tus cuentas o hacia otros clientes.</p>
            <a href="../clientes/ServletTransferencias" class="block text-blue-500 mt-2 hover:underline">Ir a Transferencias</a>
        </section>

        <!-- PEDIDO DE PRÉSTAMO -->
        <section class="bg-white p-4 shadow-md rounded-lg mb-6">
            <h2 class="text-xl font-semibold mb-4">Pedido de Préstamo</h2>
            <p>Solicita un préstamo y revisa tus solicitudes.</p>
            <a href="../clientes/ServletPrestamosPedidos?Param=1" class="block text-blue-500 mt-2 hover:underline">Ir a Pedido de Préstamo</a>
        </section>
        
        <!-- PAGO DE PRESTAMOS -->
        <section class="bg-white p-4 shadow-md rounded-lg">
            <h2 class="text-xl font-semibold mb-4">Pago de Prestamo</h2>
            <p>Abona la cuota mensual del prestamo solicitado.</p>
            <a href="../clientes/ServletPagoDePrestamos" class="block text-blue-500 mt-2 hover:underline">Ir a Pago de Prestamo</a>
        </section>

        <!-- INFORMACIÓN PERSONAL -->
        <section class="bg-white p-4 shadow-md rounded-lg">
            <h2 class="text-xl font-semibold mb-4">Información Personal</h2>
            <p>Consulta y actualiza tu información personal.</p>
            <a href="../clientes/ServletInformacionPersonal?Param=1" class="block text-blue-500 mt-2 hover:underline">Ir a Información Personal</a>
        </section>
    </div>
</body>
</html>
