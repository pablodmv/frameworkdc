<%-- 
    Document   : EditarUsuario
    Created on : 30/11/2010, 10:09:57 PM
    Author     : Gustavo Leites
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administracion - Editar Usuario</title>
        <script src="js/maskDate.js" type="text/javascript"></script>
    </head>
    <body>
        <h1>Editar usuario</h1>
<!--
        <form id="buscarForm" action="SearchUser.cmd" method="get">
            <table>
                <tr>
                    <td><input id="action" type="hidden" value="edit" name="action"/></td>
                    <td><label for="idUsuarios">Cod.Usuarios:</label></td>
                    <td><input id="idUsuarios" value="" name="idUsuario"/></td>
                    <td><button type="submit">Buscar</button></td>
                </tr>
            </table>
        </form>
-->
        <br/>
        <form id="editForm" action="EditUser.cmd" method="get">
            <table>
                <tr>
                    <td><input id="selectId" type="hidden" value="<%=request.getAttribute("selectId")%>" name="selectId"/></td>
                </tr>
                <tr>
                     <td><label for="nombre">Nombre:</label></td>
                    <% if(request.getAttribute("nombre") != null){%>
                     <td><input id="nombre" value="<%=request.getAttribute("nombre")%>" name="nombre"/></td>
                     <%}else{%>
                     <td><input id="nombre" value="" name="nombre"/></td>
                     <%}%>
                 </tr>
                 <tr>
                     <td><label for="apellido">Apellido:</label></td>
                     <% if(request.getAttribute("apellido") != null){%>
                     <td><input id="apellido"  value="<%=request.getAttribute("apellido")%>" name="apellido"/></td>
                     <%}else{%>
                      <td><input id="apellido"  value="" name="apellido"/></td>
                     <%}%>
                 </tr>
                 <tr>
                     <td><label for="fNac">Fecha Nacimiento:</label></td>
                     <% if(request.getAttribute("fechaNacimiento") != null){%>
                     <td><input id="fNac"  value="<%=request.getAttribute("fechaNacimiento")%>" name="fNac"/></td>
                     <%}else{%>
                     <td><input id="fNac"  value="" name="fNac"/></td>
                     <%}%>
                 </tr>

              
                 <tr>
                     <td><label for="cbxRoles"  >Rol:</label></td>
                     <td>
                         <select id="cbxRoles" name="rol" >
                             <% if(request.getAttribute("default") != null){%>
                             <option selected="true" >Seleccionar</option>
                              <%}else{%>
                              <option >Seleccionar</option>
                             <%}%>
                             <% if(request.getAttribute("admin") != null){%>
                             <option selected="true" > Administrador</option>
                             <%}else{%>
                             <option > Administrador</option>
                             <%}%>
                             <% if(request.getAttribute("user") != null){%>
                            <option selected="true">Usuario</option>
                            <%}else{%>
                            <option>Usuario</option>
                             <%}%>
                        </select>

                     </td>
                </tr>
         
                 <tr>
                    <td><button type="submit">Editar</button></td>
                </tr>
            </table>
             <%if(request.getAttribute("mensaje") != null){%>
            <label id="mensaje"><%=request.getAttribute("mensaje")%></label>
            <%}%>
        </form>

    </body>
</html>
