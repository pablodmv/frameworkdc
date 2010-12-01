<%-- 
    Document   : AddUser
    Created on : 30/11/2010, 02:57:27 PM
    Author     : pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administracion - Agregar Usuario</title>
    </head>
    <body>
        <h1>Agregar usuario</h1>

         <form action="AddUser.cmd" method="get">
            <label for="nombre">Nombre:</label>
            <input id="nombre" value="" name="nombre"/><br/>

            <label for="apellido">Apellido:</label>
            <input id="apellido"  value="" name="apellido"/><br/>

            <label for="usuario">Usuario:</label>
            <input id="usuario"  value="" name="usuario"/><br/>

            <label for="pwd">Password:</label>
            <input id="pwd"  value="" name="pwd"/><br/>

            <label for="fNac">Fecha Nacimiento:</label>
            <input id="fNac"  value="" name="fNac"/><br/>

            <label for="cbxRoles">Rol:</label>
            <select id="cbxRoles" name="rol">
                <option>Administrador</option>
                <option>Usuario</option>
            </select>

            <button type="submit">Aceptar</button>
        </form>
    </body>
</html>
