<%-- 
    Document   : QueryUsuario
    Created on : 03/12/2010, 11:53:04 PM
    Author     : Gustavo Leites
--%>

<%@page import="entities.Usuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/checkBoxes.js" type="text/javascript"></script>
        <script src="js/checkNomBox.js" type="text/javascript"></script>
        <script src="js/checkApBox.js" type="text/javascript"></script>
        <title>Administracion - Consultar Usuarios</title>
    </head>
    <body>
        <h1>Consulta Usuarios</h1>

        <form action="QueryUser.cmd" name="formulario" method="get">

            <table>
                <tr>
                    <td><input id="nombreOp" type="checkbox" name="nombreOp" onclick="setNomCheck()"/></td>
                    <td><label for="nombre">Nombre:</label></td>
                    <td><input id="nombre" type="text" disabled name="nombre" /></td>
                </tr>
                <tr>
                    <td><input id="apellidoOp" type="checkbox" name="apellidoOp" onclick="setApCheck()"/></td>
                    <td><label for="apellido">Apellido:</label></td>
                    <td><input id="apellido" type="text" disabled name="apellido" /></td>
                </tr>
                <tr>
                    <td><input id="getAll" type="checkbox" name="getAll" onclick="setCheckBox()"/></td>
                    <td><label>Obtener Todos:</label>  </td>
                </tr>
                <tr>
                    <td><button type="submit">Buscar</button></td>
                </tr>
            </table>
            <br/>
            <br/>
            <table border="1" width="400px" >
                <tr>
                    <th>Id</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Fecha.Nac</th>
                    <th>Usuario</th>
                    <th>Rol</th>
                </tr>
                <%
                if(request.getAttribute("listaUsuarios") != null){
                List<Usuario> listaUsuarios = (List<Usuario>)request.getAttribute("listaUsuarios");
                if(listaUsuarios.size() > 0){
                    for(Usuario usr : listaUsuarios){
                %>
                <tr>
                    <td><%=usr.getId() %></td>
                    <td><%=usr.getNombre() %></td>
                    <td><%=usr.getApellido() %></td>
                    <td><%=usr.getFechaNacimiento() %></td>
                    <td><%=usr.getCredencial().getLogin() %></td>
                    <td><%=usr.getCredencial().getRol() %></td>
                </tr>
                <%}%>
                <%}%>
                <%}%>

            </table>
            <%if(request.getAttribute("mensaje") != null){%>
            <label id="mensaje"><%=request.getAttribute("mensaje")%></label>
            <%}%>
        </form>
    </body>
</html>