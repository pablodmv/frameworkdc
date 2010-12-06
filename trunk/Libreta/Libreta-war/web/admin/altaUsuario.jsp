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
        <script src="js/maskDate.js" type="text/javascript"></script>
        <title>Administracion - Agregar Usuario</title>
    </head>
    <body>
        <h1>Agregar usuario</h1>

         <form action="AddUser.cmd" method="get">
             <table>
                 <tr>
                     <td><label for="nombre">Nombre:</label></td>
                     <td><input id="nombre" value="" name="nombre"/></td>
                 </tr>
                 <tr>
                     <td><label for="apellido">Apellido:</label></td>
                     <td><input id="apellido"  value="" name="apellido"/></td>
                 </tr>
                 <tr>
                     <td><label for="usuario">Usuario:</label></td>
                     <td><input id="usuario"  value="" name="usuario"/></td>
                 </tr>
                 <tr>
                     <td><label for="pwd">Password:</label></td>
                     <td><input type="password" id="pwd"  value="" name="pwd"/></td>
                 </tr>
                 <tr>
                     <td><label for="fNac">Fecha Nacimiento:</label></td>
                     <td><input id="fNac" maxlength="10" value="" name="fNac" onFocus="javascript:vDateType='3'" onKeyUp="DateFormat(this,this.value,event,false,'3')" onBlur="DateFormat(this,this.value,event,false,'3')"/></td>
                 </tr>
                 <tr>
                     <td><label for="cbxRoles">Rol:</label></td>
                     <td>
                         <select id="cbxRoles" name="rol">
                             <option>Seleccionar</option>
                            <option>Administrador</option>
                            <option>Usuario</option>
                        </select>

                     </td>
                </tr>
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
