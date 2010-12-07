<%-- 
    Document   : EditarContacto
    Created on : 05/12/2010, 04:38:36 PM
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
        <script src="js/agregarRowsEdit.js" type="text/javascript"></script>
        <title>Administracion - Editar Usuario</title>
    </head>
    <body>
        <h1>Editar Usuario</h1>

        <form id="buscarForm" action="SearchContact.cmd" method="get">
            <table>
                <tr>
                    <td><input id="action" type="hidden" value="edit" name="action"/></td>
                    <td><label for="idContacto">Cod.Contacto:</label></td>
                    <td><input id="idContacto" value="" name="idContacto"/></td>
                    <td><button type="submit">Buscar</button></td>
                </tr>
            </table>
        </form>
        <form action="EditContact.cmd" id="formulario" method="get">
            <table>
                <tbody>
                    <tr>
                        <td><input id="selectId" type="hidden" value="<%=request.getAttribute("selectId")%>" name="selectId"/></td>
                    </tr>
                    <tr>
                     <td><label for="nombre">Nombre:</label></td>
                     <td>
                        <% if(request.getAttribute("nombre") != null){%>
                         <input id="nombre" value="<%=request.getAttribute("nombre")%>" name="nombre"/>
                         <%}else{%>
                         <input id="nombre" value="" name="nombre"/>
                         <%}%>
                     </td>
                 </tr>
                 <tr>
                     <td><label for="apellido">Apellido:</label></td>
                     <td>
                         <% if(request.getAttribute("apellido") != null){%>
                         <input id="apellido" value="<%=request.getAttribute("apellido")%>" name="apellido"/>
                         <%}else{%>
                         <input id="apellido" value="" name="apellido"/>
                         <%}%>
                     </td>
                 </tr>

                 <tr>
                     <td><label for="tel">Teléfono:</label></td>
                     <td>
                         <% if(request.getAttribute("tel") != null){%>
                         <input id="tel" value="<%=request.getAttribute("tel")%>" name="tel"/>
                         <%}else{%>
                         <input id="tel" value="" name="tel"/>
                         <%}%>
                     </td>
                 </tr>
                 <tr>
                     <td><label for="movil">Movil:</label></td>
                     <td>
                         <% if(request.getAttribute("movil") != null){%>
                         <input id="movil" value="<%=request.getAttribute("movil")%>" name="movil"/>
                         <%}else{%>
                         <input type="text" id="movil" value="" name="movil"/>
                         <%}%>
                     </td>
                 </tr>
                 <tr>
                     <td><label for="email">EMail:</label></td>
                     <td>
                         <% if(request.getAttribute("email") != null){%>
                         <input id="email" value="<%=request.getAttribute("email")%>" name="email"/>
                         <%}else{%>
                         <input id="email" maxlength="10" value="" name="email" />
                         <%}%>
                     </td>
                 </tr>

                 <%
                 int cont = 1;
                if(request.getAttribute("listaDirecciones") != null){
                List<Direccion> listaDirecciones = (List<Direccion>)request.getAttribute("listaDirecciones");
                if(listaDirecciones.size() > 0){
                    
                    for(Direccion dir : listaDirecciones){
                %>
                <tr>
                    <td><label>Dirección:</label></td>
                    <td><label for="calle<%=cont%>">Calle</label></td>
                    <td><input id="calle<%=cont%>" value="<%=dir.getCalle() %>" name="calle<%=cont%>" /></td>
                    <td><label for="num<%=cont%>">Nº</label></td>
                    <td><input id="num<%=cont%>" value="<%=dir.getNumero() %>" name="num<%=cont%>" /></td>
                    <td><label for="cbxTipo<%=cont%>">Tipo.Dir:</label></td>
                    <td><input id="tipoAux" disabled="true" value="<%=dir.getTipoDir().toString() %>" name="tipo<%=cont%>" /></td>
                    <td>
                         <select id="cbxTipo1" name="tipo<%=cont%>">
                            <option>Casa</option>
                            <option>Trabajo</option>
                            <option>Otro</option>
                        </select>
                     </td>
                </tr>
                <%
                 cont += 1;
                    }%>
                <%}%>
                <%}%>

                <tr>
                     
                     <td><button type="button" onclick="addRows(<%=cont%>)">Add</button></td>
                 </tr>
                </tbody>

                <tr>
                    <td><button type="submit">Modificar</button></td>
                </tr>
            </table>
            <%if(request.getAttribute("mensaje") != null){%>
            <label id="mensaje"><%=request.getAttribute("mensaje")%></label>
            <%}%>
        </form>

    </body>
</html>
