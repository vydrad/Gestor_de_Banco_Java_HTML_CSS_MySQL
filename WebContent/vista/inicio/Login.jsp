<%@ page import="Entidades.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Iniciar Sesión</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 text-gray-800">
    <div class="container mx-auto p-6 max-w-md">
        <h1 class="text-2xl font-bold mb-6 text-center">Iniciar Sesión</h1>
        
        <form action="ServletLogin" method="post" class="bg-white p-6 rounded-lg shadow-md">
            <label class="block mb-2">Usuario:</label>
            <input type="text" name="txtUser" class="w-full p-2 mb-4 border rounded" required/>

            <label class="block mb-2">Contraseña:</label>
            <input type="password" name="txtPassword" class="w-full p-2 mb-4 border rounded" required/>

            <input type="submit" name="btnIniciarSesion" value="Iniciar Sesión" class="w-full bg-blue-500 text-white p-2 rounded cursor-pointer"/>
        </form>
		<% 
			String errorMessage = (String) request.getAttribute("errorMessage");
			if (errorMessage != null) {
		%>
			<div class="bg-red-100 text-red-700 text-center p-3 rounded mb-4">
			    <%= errorMessage %>
			</div>
		<% 
		} 
		%>
		
	</div>
</body>
</html>
