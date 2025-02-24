<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="Entidades.Clientes"%>

<!DOCTYPE html>
<html lang="es">
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reportes Estadísticos - Banco</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 text-gray-800 p-6">

    <%
        final String LOGIN_ROUTE = "../../inicio/";
    	
    Integer cantCu = (Integer) request.getAttribute("cantCuentas");
    Integer cantPrestamos = (Integer) request.getAttribute("cantPrestamos");
    Integer cantPrestamosAcep = (Integer) request.getAttribute("cantPrestamosAcep");
    Integer cantCuentUltTrim = (Integer) request.getAttribute("cantCuentasUltTrim");
    Integer cantPrestamosRechazado = (Integer) request.getAttribute("cantPrestamosRechazado");
    
    List<Clientes> listaDatosClientes = (List<Clientes>) request.getAttribute("ListaDatosClientes");
    %>
    
    
        <p class="font-semibold">Usuario: <%= session.getAttribute("username") %></p>
        <a href="<%= LOGIN_ROUTE %>Login.jsp" class="text-blue-500 hover:underline block mb-2">Cerrar Sesión</a>
        <a href="<%= LOGIN_ROUTE %>EditarUsuario.jsp" class="text-blue-500 hover:underline">Editar Usuario</a>

    <div class="container mx-auto p-6">
    
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
        <h1 class="text-2xl font-bold mb-6 text-center">Reportes del Banco</h1>
        
<form action="ServletReportes" method="get" class="space-y-4">
        <!-- FILTRO -->
        <h2><label class="text-xl font-semibold mb-4">Ingrese un monto minimo de Saldo:</label></h2>
        <input type="text" name="txtmontoMin" oninput="this.value = this.value.replace(/[^0-9]/g, '')" class="w-full p-2 border border-gray-300 rounded" />
        <input type="submit" name="btnFiltrar" value="Filtrar" class="bg-blue-500 text-white px-4 py-2 rounded cursor-pointer hover:bg-blue-600">
        <input type="submit" name="btnVerTodo" value="Ver Todo" class="bg-blue-500 text-white px-4 py-2 rounded cursor-pointer hover:bg-blue-600">
                
        <!-- CLIENTES -->
        <section class="bg-white p-4 shadow-md rounded-lg mb-6">
            <h3 class="text-xl font-semibold mb-4">Resumen de Clientes</h3>
            <table class="min-w-full bg-white mt-4 border border-gray-300">
                <thead class="bg-gray-200">
                    <tr>
                        <th class="py-2 px-4 border">Cliente</th>
                        <th class="py-2 px-4 border">Número de Cuentas</th>
                        <th class="py-2 px-4 border">Préstamos Activos</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                    if(listaDatosClientes!=null){
                    	for(Clientes cl : listaDatosClientes){
                    %>
                    <tr class="text-center">
                        <td class="py-2 px-4 border"> <%= cl.getNombre() %> </td>
                        <td class="py-2 px-4 border"> <%= cl.getNumeroCuentas() %> </td>
                        <td class="py-2 px-4 border"> <%= cl.getPrestamosActivos() %> </td>
                    </tr>
                    <%	
                    	}
                    }
                    %>
                </tbody>
            </table>
        </section>
      </form>

        <!-- CUENTAS -->
        <section class="bg-white p-4 shadow-md rounded-lg mb-6">
            <h2 class="text-xl font-semibold mb-4">Informe de Cuentas</h2>
            <p>Cantidad total de cuentas:  <span class="font-bold"> <%= cantCu %> </span></p>
            <p>Cuentas abiertas el último trimestre: <span class="font-bold"> <%= cantCuentUltTrim %> </span></p>
        </section>

        <!-- PRESTAMOS -->
        <section class="bg-white p-4 shadow-md rounded-lg my-6">
            <h2 class="text-xl font-semibold mb-4">Resumen de Préstamos</h2>
            <div class="mt-4">
                <p>Total de Préstamos Solicitados: <span class="font-bold"> <%= cantPrestamos %> </span></p>
                <p>Préstamos Aprobados: <span class="font-bold"> <%= cantPrestamosAcep %> </span></p>
                <p>Préstamos Rechazados: <span class="font-bold"> <%= cantPrestamosRechazado %> </span></p>
            </div>
        </section>

        <!-- VOLVER -->
        <div class="text-center mt-6">
            <a href="../../inicio/InicioBanco.jsp" class="text-blue-500 hover:underline">Volver al Inicio</a>
        </div>
    </div>
</body>
</html>
