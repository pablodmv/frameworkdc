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

        <form id="buscarForm" action="" method="get">
            <table>
                <tr>
                    <td><label for="idUsuarios">Cod.Usuarios:</label></td>
                    <td><input id="idUsuarios" value="" name="idUsuarios"/></td>
                    <td><button type="submit">Buscar</button></td>
                </tr>
            </table>
        </form>
        <br/>
        <form id="editForm" action="" method="get">
            <table>
                <tr>
                     <td><label for="nombre">Nombre:</label></td>
                     <td><input id="nombre" disabled="true" value="" name="nombre"/></td>
                 </tr>
                 <tr>
                     <td><label for="apellido">Apellido:</label></td>
                     <td><input id="apellido" disabled="true" value="" name="apellido"/></td>
                 </tr>
                 <tr>
                     <td><label for="usuario">Usuario:</label></td>
                     <td><input id="usuario" disabled="true"  value="" name="usuario"/></td>
                 </tr>
                 <tr>
                     <td><label for="pwd">Password:</label></td>
                     <td><input type="password" disabled="true" id="pwd"  value="" name="pwd"/></td>
                 </tr>
                 <tr>
                     <td><label for="fNac">Fecha Nacimiento:</label></td>
                     <td><input id="fNac" disabled="true" value="" name="fNac"/></td>
                 </tr>
                 <tr>
                    <td><button type="submit">Editar</button></td>
                </tr>
            </table>
        </form>
    </body>
</html>
