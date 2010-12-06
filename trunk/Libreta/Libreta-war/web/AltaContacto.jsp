<%-- 
    Document   : AltaContacto
    Created on : 04/12/2010, 03:13:13 PM
    Author     : Gustavo Leites
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/agregarRows.js" type="text/javascript"></script>
        <title>Administracion - Agregar Contacto</title>
    </head>
    <body>
        <h1>Agregar Contacto</h1>
       

        <form action="AddContact.cmd" id="formulario" name="formulario" method="get">
            <table>
                <tbody>
                   <!--  <tr><td><input id="idUsuario" type="hidden" value="1" name="idUsuario"/></td></tr>-->
                    <tr>
                     <td><label for="nombre">Nombre:</label></td>
                     <td><input id="nombre" value="" name="nombre"/></td>
                 </tr>
                 <tr>
                     <td><label for="apellido">Apellido:</label></td>
                     <td><input id="apellido"  value="" name="apellido"/></td>
                 </tr>

                 <tr>
                     <td><label for="tel">Teléfono:</label></td>
                     <td><input id="tel"  value="" name="tel"/></td>
                 </tr>
                 <tr>
                     <td><label for="movil">Movil:</label></td>
                     <td><input type="text" id="movil" value="" name="movil"/></td>
                 </tr>
                 <tr>
                     <td><label for="email">EMail:</label></td>
                     <td><input id="email" maxlength="10" value="" name="email" /></td>
                 </tr>

                 <tr>
                     <td><label>Dirección:</label></td>
                     <td><label for="calle1">Calle</label></td>
                     <td><input id="calle1"  value="" name="calle1" /></td>
                     <td><label for="num1">Nº</label></td>
                     <td><input id="num1"  value="" name="num1" /></td>
                     <td><label for="cbxTipo1">Tipo.Dir:</label></td>
                     <td>
                         <select id="cbxTipo1" name="tipo1">
                            <option>Casa</option>
                            <option>Trabajo</option>
                            <option>Otro</option>
                        </select>
                     </td>
                     <td><button type="button" onclick="addRows()">Add</button></td>
                 </tr>

                </tbody>
                
                <tr>
                    <td><button type="submit">Aceptar</button></td>
                </tr>
            </table>
            <%if(request.getAttribute("mensaje") != null){%>
            <label id="mensaje"><%=request.getAttribute("mensaje")%></label>
            <%}%>
        </form>

    </body>
</html>
