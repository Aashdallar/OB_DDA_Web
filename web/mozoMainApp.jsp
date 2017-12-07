<%-- 
    Document   : mozoMainApp
    Created on : Dec 6, 2017, 04:28:18 PM
    Author     : SG0219779
--%>

<%@page import="modelo.Mesa"%>
<%@page import="modelo.Mozo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Mozo mozo = (Mozo) request.getSession().getAttribute("mozoLogueado");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>MozoApp</title>
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script type="text/javascript">

            function mostrarModal(header,contenidoHTML) {
                document.getElementById("headerModal").innerHTML = header;
                document.getElementById("contenidoModal").innerHTML = contenidoHTML;
                document.getElementById("modal").style.display = "block";
            }
            function cerrarModal() {
                document.getElementById("headerModal").innerHTML = "";
                document.getElementById("contenidoModal").innerHTML = "";
                document.getElementById("opcionesModal").innerHTML = "";
                document.getElementById("modal").style.display = "none";
            }

            var vistaWeb = new EventSource("mozoApp?accion=new");

            vistaWeb.onerror = function (evento) {
                alert("Sin conexion con el servidor");
                vistaWeb.close();
                document.location = "/OB_DDA_Web/";
            };
            vistaWeb.addEventListener("mensaje", function (evento) {
                alert(evento.data);
            }, false);
            vistaWeb.addEventListener("mesas", function (evento) {
                document.getElementById("containerMesas").innerHTML = evento.data;
            }, false);
            vistaWeb.addEventListener("items", function (evento) {
                document.getElementById("items").innerHTML = evento.data;
            }, false);
            vistaWeb.addEventListener("nroMesa", function (evento) {
                document.getElementById("nroMesa").innerHTML = evento.data;
            }, false);
            vistaWeb.addEventListener("total", function (evento) {
                document.getElementById("total").innerHTML = evento.data;
            }, false);
            vistaWeb.addEventListener("descuento", function (evento) {
                document.getElementById("descuento").innerHTML = evento.data;
            }, false);
            vistaWeb.addEventListener("totalSinBeneficio", function (evento) {
                document.getElementById("totalSinBeneficio").innerHTML = evento.data;
            }, false);
            vistaWeb.addEventListener("cliente", function (evento) {
                document.getElementById("cliente").innerHTML = evento.data;
            }, false);
            vistaWeb.addEventListener("beneficio", function (evento) {
                document.getElementById("beneficio").innerHTML = evento.data;
            }, false);
            vistaWeb.addEventListener("mesa", function (evento) {
                document.getElementById("beneficio").innerHTML = evento.data;
            }, false);
            vistaWeb.addEventListener("limpiarMesa", function (evento) {
                document.getElementById("containerMesaSeleccionada").innerHTML = "No hay mesa seleccionada";
            }, false);
            vistaWeb.addEventListener("prepararMesa", function (evento) {
                document.getElementById("containerMesaSeleccionada").innerHTML = "<div><h4>Mesa: <span id='nroMesa'></span><h5></div><div id='items'></div><table><tbody><tr><td>Total a abonar:</td><td>$ <span id='total'></span></td></tr><tr><td>Descuento por beneficio:</td><td>$ <span id='descuento'></span></td></tr><tr><td>Total sin beneficio:</td><td>$ <span id='totalSinBeneficio'></span></td></tr><tr><td>Cliente:</td><td><span id='cliente'></span></td></tr><tr><td>Beneficio:</td><td><span id='beneficio'></span></td></tr></tbody></table>";
            }, false);
            vistaWeb.addEventListener("abierta", function (evento) {
                document.getElementById("opcionesMesaSeleccionada").innerHTML = "<table><tbody><tr><td><a class='aBtn' onClick='mostrarModalItem()' >Agregar Item</a></td><td><a class='aBtn' onClick='mostrarModalCliente()' >Agregar Cliente</a></td></tr><tr><td><a class='aBtn' onClick='cerrarMesa()' >Cerrar Mesa</a></td><td><a class='aBtn' onClick='mostrarModalTransferirMesa()' >Transferir Mesa</a></td></tr></tbody></table>";
            }, false);
            vistaWeb.addEventListener("cerrada", function (evento) {
                document.getElementById("containerMesaSeleccionada").innerHTML = "La mesa está cerrada.";
                document.getElementById("opcionesMesaSeleccionada").innerHTML = "<table><tbody><tr><td><a class='aBtn' onClick='abrirMesa()' >Abrir Mesa</a></td><td><a class='aBtn' onClick='mostrarModalTransferirMesa()' >Transferir Mesa</a></td></tr></tbody></table>";
            }, false);
            vistaWeb.addEventListener("productos", function (evento) {
                document.getElementById("opcionesModal").innerHTML = evento.data;
            }, false);
            vistaWeb.addEventListener("mozosDisponibles", function (evento) {
                document.getElementById("opcionesModal").innerHTML = evento.data;
            }, false);
            vistaWeb.addEventListener("inicio", function (evento) {
                vistaWeb.close();
                document.location = "/OB_DDA_Web/";
            }, false);
            vistaWeb.addEventListener("solicitudTransferenciaMesa", function (evento) {
                mostrarModal("Solicitud de Transferencia",evento.data);
            }, false);
            vistaWeb.addEventListener("solicitudTransferenciaMesaActualizar", function (evento) {
               document.getElementById("tiempoRestante").innerHTML = evento.data;
            }, false);
            vistaWeb.addEventListener("solicitudTransferenciaMesaTerminada", function (evento) {
               rechazarTransferencia();
            }, false);

            function seleccionarMesa(indexMesa) {
                $.get("mozoApp?accion=seleccionarMesa&indexMesa=" + indexMesa, function (data) {});
            }
            function mostrarModalCliente() {
                mostrarModal("Agregar Cliente","<tr><td>Ingrese ID</td><td><input type='text' id='idCliente' /></td></tr><tr><td colspan=2><a class='aBtn' onClick='agregarCliente()' >Agregar Cliente</a></td><td colspan=2><a class='aBtn' onClick='cerrarModal()' >Cancelar</a></td></tr>");
            }
            function mostrarModalItem() {
                $.get("mozoApp?accion=getProductos", function (data) {
                    mostrarModal("Agregar Item","<tr><td colspan=2>Cantidad: <input type='text' id='itemCantidad' /></td></tr><tr><td colspan=2>¿Alguna descripción o aclaración?</td></tr><tr><td colspan=2><textarea rows=5 style='width:100%' id='itemDescripcion'></textarea></td></tr><tr><td><a class='aBtn' onClick='agregarItem()' >Agregar Item</a></td><td><a class='aBtn' onClick='cerrarModal()' >Cancelar</a></td></tr>");
                });
            }
            function mostrarModalTransferirMesa() {
                $.get("mozoApp?accion=iniciarTransferencia", function (data) {
                    mostrarModal("Transferencia de Mesa","<tr><td colspan=2><a class='aBtn' onClick='transferirMesa()' >Transferir</a></td><td colspan=2><a class='aBtn' onClick='cerrarModal()' >Cancelar</a></td></tr>");
                });   
            }
            function abrirMesa() {
                $.get("mozoApp?accion=abrirMesa", function (data) {});
            }
            function transferirMesa() {
                var indexMozo = document.getElementById("menuMozos").selectedIndex;
                $.get("mozoApp?accion=transferirMesa&indexMozo=" + indexMozo, function (data) {});
                cerrarModal();
            }
            function cerrarMesa() {
                if (confirm("¿Está seguro que desea cerrar la mesa?")) {
                    $.get("mozoApp?accion=cerrarMesa", function (data) {});
                }
            }
            function agregarItem() {
                var indexProd = document.getElementById("menuProductos").selectedIndex;
                var cantidad = document.getElementById("itemCantidad").value;
                var descripcion = document.getElementById("itemDescripcion").value;
                if(!descripcion)
                    descripcion="";
                $.get("mozoApp?accion=agregarItem&indexProd=" + indexProd + "&cantidad=" + cantidad + "&descripcion=" + descripcion, function (data) {});
                cerrarModal();
            }
            function agregarCliente() {
                var id = document.getElementById("idCliente").value;
                $.get("mozoApp?accion=agregarCliente&id=" + id, function (data) {});
                cerrarModal();
            }
            function logout() {
                if (confirm("¿Está seguro que desea salir de la sesión?")) {
                    $.get("mozoApp?accion=logout", function (data) {});
                }
            }
            function aceptarTransferencia(){
                $.get("mozoApp?accion=aceptarTransferencia", function (data) {});
                cerrarModal();
            }
            function rechazarTransferencia(){
                $.get("mozoApp?accion=rechazarTransferencia", function (data) {});
                cerrarModal();
            }

        </script>
        <div class="container">
            <div class="item-a">
                <h1>MOZO: <%= mozo.getNombreCompleto()%></h1>
                <h3><a style="cursor: pointer" onClick="logout()">Logout</a></h3>
            </div>
            <div class="item-b" role="main">
                <center>
                    <h2>MESAS</h2>
                    <div id="containerMesas" ></div>
                </center>
            </div>
            <div class="item-c">
                <center>
                    <h2>MESA SELECCIONADA</h2>
                    <div id="containerMesaSeleccionada">
                    </div>
                    <div id="opcionesMesaSeleccionada">
                    </div>
                </center>
            </div>
        </div>

        <div id="modal" class="modal">
            <div class="modal-content">
                <div><h2 id="headerModal"></h2></div>
                <table>
                    <thead><tr><td id="textoOpciones"></td><span id="opcionesModal"></span></tr></thead>
                    <tbody id="contenidoModal">

                    </tbody>
                </table>
            </div>
        </div>

    </body>
</html>
