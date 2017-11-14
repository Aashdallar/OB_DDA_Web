<%-- 
    Document   : index
    Created on : 07/11/2017, 09:37:37 AM
    Author     : alumnoFI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String msg = request.getParameter("msg");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agenda Home</title>
    </head>
    <body><center>
        <h1>AGENDA </h1>
    </center>
    <form method="get" action="login">
        Usuario: <input type="text" name="nombre"></input><br><br>
        Password: <input type="text" name="pass"></input><br><br>
        <input type="submit" value="login"></input>
    </form>
    <%
        if(msg!=null){
            out.println("Error:" + msg);
        }
    %>
    </body>
</html>
