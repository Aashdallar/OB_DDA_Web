<%-- 
    Document   : mozoMainApp
    Created on : Dec 6, 2017, 04:28:18 PM
    Author     : SG0219779
--%>

<%@page import="modelo.Mozo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Mozo mozo = (Mozo)request.getSession().getAttribute("mozoLogueado");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MozoApp</title>
    </head>
    <body>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script type="text/javascript">

            var vistaWeb = new EventSource("mozoApp?accion=new");
            console.log(vistaWeb);
                                                
            vistaWeb.onerror = function(evento) {
               //alert("Sin conexion con el servidor");
                //vistaWeb.close();
                //document.location="/OB_DDA_Web/";
            };
            vistaWeb.addEventListener("mesas", function (evento){
                document.title=evento.data;
                document.getElementById("divMesas").innerHTML = evento.data;
            },false);
            
        </script>
        <header>
            <h1>MOZO: <%= mozo.getNombreCompleto() %></h1>
        </header>
        <main>
            <div>
                <h2>Mesas</h2>
                <div id="divMesas" ></div>
        </main>
        
        
    </body>
</html>
