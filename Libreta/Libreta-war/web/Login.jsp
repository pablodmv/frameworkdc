<%-- 
    Document   : Login
    Created on : 27/11/2010, 06:59:54 PM
    Author     : Gustavo Leites
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>

         <form action="j_security_check" method="POST">
            Usuario:<input type="text" name="j_username"><br>
            Contrase&ntilde;a:<input type="password" name="j_password">
            <input type="submit" value="Ingresar">
        </form>

    </body>
</html>
