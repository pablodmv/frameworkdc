<%-- 
    Document   : QueryContact
    Created on : 05/12/2010, 09:40:33 PM
    Author     : Gustavo Leites
--%>

<%@page import="entities.Contacto"%>
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
        <title>Administracion - Consultar Contacto</title>
    </head>
    <body>
        <h1>Consultar Contacto</h1>

        <form action="QueryContact.cmd" name="formulario" method="get">

            <table>
                <tr>
                    <td><input id="nombreOp" type="checkbox" name="nombreOp" onclick="setNomCheck()"/></td>
                    <td><label for="nombre">Nombre:</label></td>
                    <td><input id="nombre" type="text" disabled name="nombre" value="" /></td>
                </tr>
                <tr>
                    <td><input id="apellidoOp" type="checkbox" name="apellidoOp" onclick=""/></td>
                    <td><label for="apellido">Apellido:</label></td>
                    <td><input id="apellido" type="text" disabled name="apellido" value="" /></td>
                </tr>
                <tr>
                    <td><input id="getAll" type="checkbox" name="getAll" onclick="setApCheck()"/></td>
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
                    <th>Telefono</th>
                    <th>Movil</th>
                    <th>EMail</th>
                </tr>
                <%
                if(request.getAttribute("listaContactos") != null){
                List<Contacto> listaContactos = (List<Contacto>)request.getAttribute("listaUsuarios");
                if(listaContactos.size() > 0){
                    for(Contacto contact : listaContactos){
                %>
                <tr>
                    <td><%=contact.getId() %></td>
                    <td><%=contact.getNombre() %></td>
                    <td><%=contact.getApellido() %></td>
                    <td><%=contact.getTelefono() %></td>
                    <td><%=contact.getMovil() %></td>
                    <td><%=contact.getEmail() %></td>
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
