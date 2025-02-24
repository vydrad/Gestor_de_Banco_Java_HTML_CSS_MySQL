<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.List"%>
<%@ page import = "Entidades.Cuenta"%>
<%@ page import = "Entidades.Clientes"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Asignación de Cuentas</title>

<!-- TailwindCSS -->
<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">

</head>
<body class="bg-gray-100 text-gray-800 p-6">

<%
    final String LOGIN_ROUTE = "../../inicio/";
%>

    <p class="font-semibold">Usuario: <%= session.getAttribute("username") %></p>
    <a href="<%= LOGIN_ROUTE %>Login.jsp" class="text-blue-500 hover:underline block mb-2">Cerrar Sesión</a>
    <a href="<%= LOGIN_ROUTE %>EditarUsuario.jsp" class="text-blue-500 hover:underline">Editar Usuario</a>


<h1 class="text-2xl font-bold mb-6 text-center">Asignación de Cuentas</h1>


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

<form action="ServletAsignacionCuentas" method="post">
<div class="max-w-md mx-auto bg-white p-6 rounded-lg shadow-lg space-y-4">
  

    <div>
        <label class="block font-semibold">Seleccione un Numero de Cuenta para re asignarle un cliente:</label>
        <select name="ddlCuentas" class="w-full p-2 border border-gray-300 rounded">
         <option value="">Seleccione un Numero de Cuenta</option>
              <%
            // Obtener la lista de clientes desde el request
            List<Cuenta> listaCuentas = (List<Cuenta>) request.getAttribute("listaCuentas");
            if (listaCuentas != null) {
                for (Cuenta cuenta : listaCuentas) {
        %>
                    <option value="<%= cuenta.getNumeroCuenta() %>-<%=cuenta.getCbuCuenta()%>">
                
                       NumeroCuenta: <%= cuenta.getNumeroCuenta() %> - DNI: <%= cuenta.getDniCuenta() %> - Tipo: <%= cuenta.getTipoCuenta() %>
                    </option>
        <%
                }
            }
        %>
            
        </select>
    </div>
    
     
    </div>
<div class="max-w-md mx-auto bg-white p-6 rounded-lg shadow-lg space-y-4">
    <div>
    <label class="block font-semibold">Seleccione el cliente al cual quiere asignarle la cuenta:</label>
    <select name="ddlClientes" class="w-full p-2 border border-gray-300 rounded">
        <option value="">Seleccione un DNI_Cliente</option>
        <%
            // Obtener la lista de clientes desde el request
            List<Clientes> listaClientes = (List<Clientes>) request.getAttribute("listaClientes");
            if (listaClientes != null) {
                for (Clientes cliente : listaClientes) {
        %>
                    <option value="<%= cliente.getDni() %>">
                        <%= cliente.getNombre() %> <%= cliente.getApellido() %> - DNI: <%= cliente.getDni() %>
                    </option>
        <%
                }
            }
        %>
    </select>
    
     <div>
        <label class="block font-semibold">
		 <%if (request.getAttribute("filas")!=null){
        
         %>
        
        El numero de cuenta: <%=request.getAttribute("NumeroCuenta")%> <br>
        de CBU:  <%=request.getAttribute("CBU")%> <br>
        Fué asignado correctamente al Cliente con DNI: <%=request.getAttribute("DNI")%>
        <%
        }
        else{
         %>
         Elija una cuenta
         <%}
          %>

		</label>
        <p class="bg-gray-200 p-2 rounded">
      
         </p> <!-- Aca Lo podemos agregar o lo podemos sacar -->
    </div>
    
    
</div>

    <div class="flex space-x-4">
        <input type="submit" name="btnAsignar" value="Asignar Cuenta" class="bg-green-500 text-white px-4 py-2 rounded cursor-pointer hover:bg-green-600" onclick="return confirm('¿Estás seguro de que deseas asignar esta cuenta?');">
        <!--  <input type="submit" name="btnCancelar" value="Cancelar" class="bg-red-500 text-white px-4 py-2 rounded cursor-pointer hover:bg-red-600" onclick="return confirm('¿Estás seguro de que deseas cancelar esta operacion?');"> -->
    </div>	<!--  HAY QUE HACERLE UNA VALIDACION DE QUE ESE CLIENTE NO TENGA YA 3 CUENTAS ASIGNADAS, OJO -->
    
</div>
 </form>
<div class="text-center mt-6">
    <a href="InicioCuentas.jsp" class="text-blue-500 hover:underline">Volver al Inicio</a>
</div>

</body>
</html>
