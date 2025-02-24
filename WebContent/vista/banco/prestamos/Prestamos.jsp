<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="Entidades.Clientes"%>
<%@ page import="Entidades.Prestamo"%>
<%@ page import="Entidades.Cuota"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
<title>Prestamos</title>

</head>
<body class="bg-gray-100 text-gray-800 p-6">

<%
	final String LOGIN_ROUTE = "../../inicio/";
	List<Clientes> listaClientes = (List<Clientes>) request.getAttribute("ListaClientesPrestamos");
	List<Prestamo> listaPrestamo = (List<Prestamo>) request.getAttribute("ListPrestamos");
	List<Cuota> listaCuota = (List<Cuota>) request.getAttribute("listCuota");
%>

        <p class="font-semibold">Usuario: <%= session.getAttribute("username") %></p>
        <a href="<%= LOGIN_ROUTE %>Login.jsp" class="text-blue-500 hover:underline block mb-2" >Cerrar Session</a>
        <a href="<%= LOGIN_ROUTE %>EditarUsuario.jsp" class="text-blue-500 hover:underline" >Editar Usuario</a>

    <div class="container mx-auto p-6">
        <h1 class="text-2xl font-bold mb-6 text-center">Autorización de prestamos</h1>
   
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
        
   
	<form action="ServletPrestamosAdminBanco" method="get">
	<section class="bg-white p-4 shadow-md rounded-lg mb-6">

	<div class="flex flex-column justify-center bg-blue-100 p-2 rounded-sm">
		<p class="mr-4">
			Seleccione un Cliente:
		</p>
		<select name="ddlClientes"class="bg-blue-200 rounded-sm">
		<%
        // Obtener el valor seleccionado del parámetro recibido
        String selectedDni = request.getParameter("ddlClientes");
        for (Clientes c : listaClientes) {
            boolean isSelected = selectedDni != null && selectedDni.equals(String.valueOf(c.getDni()));
    %>
        <option value="<%= c.getDni() %>" <%= isSelected ? "selected" : "" %>>
            <%= c.getNombre() %> - <%= c.getApellido() %>
        </option>
    <%
        }
    %>
		</select><br>
		
	</div>
	<input type="submit" name="btnMostrarDatos" value="Mostrar Datos/ Actualizar" class="px-4 py-2 bg-red-500 text-white rounded cursor-pointer hover:bg-red-600">
	
	<div class="select-none text-slate-900">
	<% if (listaPrestamo != null && !listaPrestamo.isEmpty()) { %>
	<%
		for(Prestamo p : listaPrestamo){
	%>
		<div class="flex flex-column mt-4">
			<p class="mr-2">
				Fecha de solicitud:
			</p>
			<p class="text-slate-700">
				 <%= p.getFechaPrestamo() %>
			</p>
		</div>
		<div class="flex flex-column mt-4">
			<p class="mr-2">
				Importe solicitado:  
			</p>
			<p class="text-slate-700">
				<%= p.getImportePedidoPrestamo() %>
			</p>
		</div>
		<div class="flex flex-column mt-4">
			<p class="mr-2">
				Importe con Intereses: 
			</p>
			<p class="text-slate-700">
				 <%= p.getImportePagarPrestamo()%>
			</p>
		</div>
		<div class="flex flex-column mt-4">
			<p class="mr-2">
				Monto por mes:  
			</p>
			<p class="text-slate-700">
				<%= p.getMontoMesPrestamo() %>
			</p>
		</div>
		<%} %>
		<% } else { %>
        <p>Presiona el boton para visualizar los Datos.</p>
    	<% } %>
	</div>
 <div class="flex items-center justify-center my-4">    
    <div class="border-2 border-black bg-blue-100 rounded-md w-96">
        <h3 class="text-lg font-semibold bg-blue-200 w-full text-center">Cuotas</h3>
        <table class="w-full text-center">
            <thead class="bg-blue-200">
                <tr>
                    <th class="p-2">Número de Cuota</th>
                    <th class="p-2">Monto</th>
                </tr>
            </thead>
            <tbody>
			    <%
			    if(listaCuota != null)
			    for(Cuota c : listaCuota){
			    %>
                <tr>
                    <td class="p-2"> <%= c.getNumeroCuotaPagar() %> </td>
                    <td class="p-2"> <%= c.getMontoCuota() %> </td>
                </tr>
                <%
			    } 
			    %>
            </tbody>
        </table>
        <div class="flex justify-between p-4">
            <input type="submit" name="btnAprobar" value="Aprobar" class="px-4 py-2 bg-green-500 text-white rounded cursor-pointer hover:bg-green-600" onclick="return confirm('¿Estás seguro de que deseas Aprobar este prestamo??');">
            <input type="submit" name="btnRechazar" value="Rechazar" class="px-4 py-2 bg-red-500 text-white rounded cursor-pointer hover:bg-red-600" onclick="return confirm('¿Estás seguro de que deseas Rechazar este prestamo?');">
        </div>
    </div>
</div>
</section>
</form>
	<div class="text-center mt-6">
		<a href="../../inicio/InicioBanco.jsp" class="text-blue-500 hover:underline">Volver al Inicio</a>
	</div>
</body>
</html>