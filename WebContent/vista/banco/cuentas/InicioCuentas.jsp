<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Gestión de Cuentas</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 text-gray-800 p-6">

<%
    final String LOGIN_ROUTE = "../../inicio/";
%>

<a href="<%= LOGIN_ROUTE %>Login.jsp" class="text-blue-500 hover:underline block mb-2">Cerrar Sesión</a>
<a href="<%= LOGIN_ROUTE %>EditarUsuario.jsp" class="text-blue-500 hover:underline">Editar Usuario</a>

    <div class="container mx-auto p-6">
        <h1 class="text-2xl font-bold mb-6 text-center">Gestión de Cuentas, Bienvenido/a <%= session.getAttribute("username")%></h1>

        <!-- OPCIONES -->
        <section class="bg-white p-4 shadow-md rounded-lg mb-6">
            <h2 class="text-xl font-semibold mb-4">Opciones de Cuentas</h2>
            <p>Selecciona una acción para gestionar las cuentas de los clientes.</p>

            <div class="flex flex-col space-y-4 mt-4">
                <a href="ServletCargarCuentas?Param=1" class="block text-blue-500 hover:underline">
                    Agregar Cuentas
                </a>
                <a href="ServletListarCuentas?Param=1" class="block text-blue-500 hover:underline">
                    Listar Cuentas
                </a>
                <a href="ServletModificarCuentas?Param=1" class="block text-blue-500 hover:underline">
                    Modificar Cuentas
                </a>
                <a href="ServletEliminarCuentas?Param=1" class="block text-blue-500 hover:underline">
                    Eliminar Cuentas
                </a>
                <a href="ServletAsignacionCuentas?Param=1" class="block text-blue-500 hover:underline">
				    Asignación de Cuentas
				</a>
            </div>
        </section>

        <!-- VOLVER -->
        <div class="text-center mt-6">
            <a href="../../inicio/InicioBanco.jsp" class="text-blue-500 hover:underline">
                Volver al Inicio
            </a>
        </div>
    </div>
</body>
</html>
