<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="Entidades.Cuota"%>
<%@ page import="Entidades.Prestamo"%>
<%@ page import="Entidades.Cuenta"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pagar Préstamos</title>
<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 text-gray-800 p-6">

<%
    final String LOGIN_ROUTE = "../inicio/";
	List<Prestamo> listaPrestamosCuentas = (List<Prestamo>) request.getAttribute("listaPrestamosCuentas");
    List<Prestamo> listaPrestamos = (List<Prestamo>) request.getAttribute("ListadoNumPrestamos");
    List<Cuota> listaCuota = (List<Cuota>) request.getAttribute("ListadoNumCuota");
    List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentas");
%>

<div class="mb-6">
    <p class="font-semibold">Usuario: <%= session.getAttribute("username") %></p>
    <a href="<%= LOGIN_ROUTE %>Login.jsp" class="text-blue-500 hover:underline">Cerrar Sesión</a>
    <br>
    <a href="<%= LOGIN_ROUTE %>EditarUsuario.jsp" class="text-blue-500 hover:underline">Editar Usuario</a>
</div>

<h1 class="text-2xl font-bold mb-6 text-center">Pagar Préstamos</h1>

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

<div class="max-w-lg mx-auto bg-white p-6 rounded-lg shadow-lg">
<form action="ServletPagoDePrestamos" method="get" class="space-y-4">
    <div>
	        <label class="block font-semibold mb-2">Elija una cuenta disponible con un prestamo activo</label>
	        <select name="CuentasConPrestamos" class="w-full p-2 border border-gray-300 rounded"  onchange="mostrarMonto(this)">
	        <option value="default">Seleccionar una cuenta con prestamo activo</option>
	            <% if(listaPrestamosCuentas !=null){
	            	for (Prestamo pre : listaPrestamosCuentas) { %>
	                <option value="<%= pre.getNumeroCuentaPrestamo() %>" >
	                <%= pre.getNumeroCuentaPrestamo() %>
	                </option>
	            <% } 
	            } %>
	        </select>
	    </div>
<input type="submit" name="btnElegirCuenta" value="Elegir Cuenta" class="bg-green-500 text-white px-4 py-2 rounded cursor-pointer hover:bg-green-600" >
</form>

<form action="ServletPagoDePrestamos" method="post" class="space-y-4">
    <% if (listaPrestamos != null && !listaPrestamos.isEmpty()) { %>

	    <!-- Seleccionar Préstamo -->
	    <div>
	        <label class="block font-semibold mb-2">Código de préstamo a pagar</label>
			    <% if (listaPrestamos != null && !listaPrestamos.isEmpty()) {
			        for (Prestamo pr : listaPrestamos) { %>
			         <input name="PrestamoAPagar" id="codCuotas" type="text" value="<%= pr.getCodPrestamo() %>" class="w-full p-2 border border-gray-300 rounded" readonly>
			        <% }
			    }  %>
	    </div>	
		<!-- Solo se muestra los elementos si hay que usarlos para pagar una cuota -->
	    <!-- Seleccionar Cuota -->
	    <div>
	        <label class="block font-semibold mb-2">Cuota a pagar</label>
	        <select name="CuotasAPagar" class="w-full p-2 border border-gray-300 rounded"  onchange="mostrarMonto(this)">
	        <option value="">Seleccionar una cuota</option>
	            <% for (Cuota cu : listaCuota) { %>
	                <option value="<%= cu.getNumeroCuotaPagar() %>" data-monto="<%= cu.getMontoCuota() %>">
	                <%= cu.getNumeroCuotaPagar() %>
	                </option>
	            <% } %>
	        </select>
	    </div>
	
	    <!-- Seleccionar Cuenta -->
	    <div>
	        <label class="block font-semibold mb-2">Cuenta a abonar</label>
	        <select id="cuenta" name="CuentaDeDondeDebita" class="w-full p-2 border border-gray-300 rounded" onchange="mostrarSaldo(this)">
	            <option value="">Seleccionar una cuenta</option>
	            <% if (cuentas != null) { 
	                for (Cuenta cuenta : cuentas) { %>
	                    <option value="<%= cuenta.getCbuCuenta() %>" data-saldo="<%= cuenta.getSaldoCuenta() %>">
	                        <%= cuenta.getCbuCuenta() %>
	                    </option>
	                <% } 
	            } %>
	        </select>
	    </div>
	
	    <!-- Mostrar Saldo Disponible -->
	    <div>
	        <label class="block font-semibold mb-2">Saldo Disponible</label>
	        <input id="saldo" type="text" class="w-full p-2 border border-gray-300 rounded" readonly>
	    </div>
	
	    <script>
	        function mostrarSaldo(select) {
	            const saldoInput = document.getElementById('saldo');
	            const selectedOption = select.options[select.selectedIndex];
	            saldoInput.value = selectedOption.getAttribute('data-saldo') || '';
	        }
	    </script>
	    
	    <!-- Mostrar Monto a Pagar-->
	    <div>
	        <label class="block font-semibold mb-2">Monto a pagar</label>
	        <input id="MontoPagar" type="text" class="w-full p-2 border border-gray-300 rounded" readonly>
	    </div>
	    
	    <script>
	    	function mostrarMonto(select) {
	    		const montoInput = document.getElementById('MontoPagar');
	    		const selectedOption = select.options[select.selectedIndex];
	    		montoInput.value = selectedOption.getAttribute('data-monto') || '';
	
	    	}
	    </script>
	    <input type="submit" name="btnPagarCuota" value="Abonar Cuota" class="bg-green-500 text-white px-4 py-2 rounded cursor-pointer hover:bg-green-600" onclick="return confirm('¿Estás seguro de que deseas abonar esta cuota?');">
    
    <% } else{ %>
    	<p class="text-red-500 flex items-center justify-center">Elija un prestamo Activo antes de proceder al pago</p>
    <% } %>
    
</form>

</div>

<div class="text-center mt-6">
    <a href="InicioCliente.jsp" class="text-blue-500 hover:underline">Volver al Inicio</a>
</div>

</body>
</html>
