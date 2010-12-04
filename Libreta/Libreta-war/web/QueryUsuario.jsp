<%-- 
    Document   : QueryUsuario
    Created on : 03/12/2010, 11:53:04 PM
    Author     : Gustavo Leites
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Administracion - Consultar Usuarios</title>
    </head>
    <body>
        <h1>Consulta Usuarios</h1>

        <form action="" name="formulario" method="get">

            <table>
                <tr>
                    <td><input id="nombreOp" type="checkbox" name="nombreOp" onclick="document.formulario.nombre.disabled=!document.formulario.nombre.disabled"/></td>
                    <td><label for="nombre">Nombre:</label></td>
                    <td><input id="nombre" type="text" disabled name="nombre" /></td>
                </tr>
                <tr>
                    <td><input id="apelldoOp" type="checkbox" name="apelldoOp" onclick="document.formulario.apellido.disabled=!document.formulario.apellido.disabled"/></td>
                    <td><label for="apellido">Apellido:</label></td>
                    <td><input id="apellido" type="text" disabled name="apellido" /></td>
                </tr>
                <tr>
                    <td><button type="submit">Buscar</button></td>
                </tr>
            </table>
            <br/>
            <br/>
            <table>
                <c:forEach var="person" items="">
                  <tr>
                      <td>${person.name}</td>
                      <td>${person.age}</td>
                      <td>${person.height}</td>
                  </tr>
                </c:forEach>
            </table>
        </form>
    </body>
</html>
