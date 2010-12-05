<%-- 
    Document   : EliminarContacto
    Created on : 04/12/2010, 09:04:57 PM
    Author     : Gustavo Leites
--%>

<%@page import="entities.Direccion"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administracion - Eliminar Contacto</title>
    </head>
    <body>
        <h1>Eliminar Contacto</h1>

        <form id="buscarForm" action="SearchContact.cmd" method="get">
            <table>
                <tr>
                    <td><input id="action" type="hidden" value="delete" name="action"/></td>
                    <td><label for="idContacto">Cod.Contacto:</label></td>
                    <td><input id="idContacto" value="" name="idContacto"/></td>
                    <td><button type="submit">Buscar</button></td>
                </tr>
            </table>
        </form>
        <form action="DeleteContact.cmd" id="formulario" method="get">
            <table>
                <tbody>
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
                     <td><label for="tel">Teléfono:</label></td>
                     <td>
                         <% if(request.getAttribute("tel") != null){%>
                         <input id="tel" disabled="true" value="<%=request.getAttribute("tel")%>" name="tel"/>
                         <%}else{%>
                         <input id="tel" disabled="true" value="" name="tel"/>
                         <%}%>
                     </td>
                 </tr>
                 <tr>
                     <td><label for="movil">Movil:</label></td>
                     <td>
                         <% if(request.getAttribute("movil") != null){%>
                         <input id="movil" disabled="true" value="<%=request.getAttribute("movil")%>" name="movil"/>
                         <%}else{%>
                         <input type="text" disabled="true" id="movil" value="" name="movil"/>
                         <%}%>
                     </td>
                 </tr>
                 <tr>
                     <td><label for="email">EMail:</label></td>
                     <td>
                         <% if(request.getAttribute("email") != null){%>
                         <input id="email" disabled="true" value="<%=request.getAttribute("email")%>" name="email"/>
                         <%}else{%>
                         <input id="email" disabled="true" maxlength="10" value="" name="email" />
                         <%}%>
                     </td>
                 </tr>

                 <%
                if(request.getAttribute("listaDirecciones") != null){
                List<Direccion> listaDirecciones = (List<Direccion>)request.getAttribute("listaDirecciones");
                if(listaDirecciones.size() > 0){
                    
                    for(Direccion dir : listaDirecciones){
                %>
                <tr>
                    <td><label>Dirección:</label></td>
                    <td><label for="calle">Calle</label></td>
                    <td><input id="calle" disabled="true" value="<%=dir.getCalle() %>" name="calle1" /></td>
                    <td><label for="num">Nº</label></td>
                    <td><input id="num" disabled="true" value="<%=dir.getNumero() %>" name="num1" /></td>
                    <td><label for="cbxTipo">Tipo.Dir:</label></td>
                    <td><input id="tipo" disabled="true" value="<%=dir.getTipoDir().toString() %>" name="tipo1" /></td>
                    
                </tr>
                <%}%>
                <%}%>
                <%}%>

                </tbody>

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
