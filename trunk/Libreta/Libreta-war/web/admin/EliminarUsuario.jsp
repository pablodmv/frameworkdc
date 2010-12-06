<%-- 
    Document   : EliminarUsuario
    Created on : 01/12/2010, 11:31:15 PM
    Author     : Gustavo Leites
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administracion - Eliminar Usuario</title>
    </head>
    <body>
        <h1>Eliminar Usuario</h1>

        <form id="buscarForm" action="SearchUser.cmd" method="get">
            <table>
                <tr>
                    <td><input id="action" type="hidden" value="delete" name="action"/></td>
                    <td><label for="idUsuarios">Cod.Usuario:</label></td>
                    <td><input id="idUsuarios" value="" name="idUsuario"/></td>
                    <td><button type="submit">Buscar</button></td>
                </tr>
            </table>
        </form>
        <br/>
        <form id="editForm" action="DeleteUser.cmd" method="get">
            <table>
                <tr>
                    <td><input id="selectId" type="hidden" value="<%=request.getAttribute("selectId")%>" name="selectId"/></td>
                </tr>
                <tr>
                     <td><label for="nombre">Nombre:</label></td>
                     <td>
                        <% if(request.getAttribute("nombre") != null){%>
                         <input id="nombre" disabled="true" value="<%=request.getAttribute("nombre")%>" name="nombre"/>
                         <%}else{%>
                         <input id="nombre" disabled="true" value="" name="nombre"/>
                         <%}%>
                     </td>
                 </tr>
                 <tr>
                     <td><label for="apellido">Apellido:</label></td>
                     <td>
                         <% if(request.getAttribute("apellido") != null){%>
                         <input id="apellido" disabled="true" value="<%=request.getAttribute("apellido")%>" name="apellido"/>
                         <%}else{%>
                         <input id="apellido" disabled="true" value="" name="apellido"/>
                         <%}%>
                     </td>
                 </tr>
                 <tr>
                     <td><label for="usuario">Usuario:</label></td>
                     <td>
                         <% if(request.getAttribute("usuario") != null){%>
                         <input id="usuario" disabled="true"  value="<%=request.getAttribute("usuario")%>" name="usuario"/>
                         <%}else{%>
                         <input id="usuario" disabled="true"  value="" name="usuario"/>
                         <%}%>
                     </td>
                 </tr>
                 <tr>
                    <td><button type="submit">Eliminar</button></td>
                </tr>
            </table>
            <%if(request.getAttribute("mensaje") != null){%>
            <label id="mensaje"><%=request.getAttribute("mensaje")%></label>
            <%}%>
        </form>
    </body>
</html>
