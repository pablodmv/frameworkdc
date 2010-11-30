<%-- 
    Document   : Bienvenido
    Created on : 20/11/2010, 11:29:06 AM
    Author     : Gustavo Leites
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bienvenido</title>
    </head>
    <body>
        <%if(request.getAttribute("usuario") != null){%>
        <h1>Bienvenido Usuario <%=request.getAttribute("usuario")%></h1>
        <%}else{%>
        <h1> Bienvenido Usuario Invitado</h1>
        <%}%>
    </body>
</html>
