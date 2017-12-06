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

        <form method="get" action="login">
            <table>
                <thead>
                    <tr><th colspan=2>Ingrese</th></tr>
                </thead>
                <tbody>
                    <tr><td><label for="usu">Usuario: </label></td><td><input id="usu" type="text" name="usu"></input></td></tr>
                    <tr><td><label for="pass">Clave: </label></td><td><input id="pass" type="text" name="pass"></input></td></tr>
                </tbody>
                <tfoot>
                    <tr><th colspan=2><input type="submit" value="login"></input></th></tr>
                </tfoot>
            </table>

        </form>
    </center>
    <%
        if (msg != null) {
            out.println("Error:" + msg);
        }
    %>
</body>
</html>

