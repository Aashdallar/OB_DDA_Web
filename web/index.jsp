<%-- 
    Document   : index
    Created on : Nov 14, 2017, 9:16:37 AM
    Author     : SG0208533
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String msg = request.getParameter("msg"); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MozoApp</title>
    </head>
    <body><center>
        <h1>Bienvenido a MozoApp</h1>
    </center>
    <form method="get" action="login">
        <table>
            <tr>
                <th>Usuario
            </tr>
        </table>
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

