/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import modelo.Item;
import modelo.Mesa;
import modelo.Mozo;
import modelo.Producto;
import modelo.Transferencia;

/**
 *
 * @author Dario
 */
public class Componentes {

    public static String lista(boolean multiple, String id, ArrayList opciones) {
        String lista = "<select " + (multiple ? " multiple " : "") + " id='" + id + "'>";
        int x = 0;
        for (Object obj : opciones) {
            lista += "<option value='" + x + "'>" + obj + "</option>";
            x++;
        }
        lista += "</select>";
        return lista;
    }
    
    public static String menuProductos(String id, ArrayList<Producto> productos) {
        String menu = "<span>Producto: </span><select id='" + id + "'>";
        for (Producto prod : productos) {
            menu += "<option value='" + prod.getCodigo() + "'>" + prod.getNombre() + "</option>";
        }
        menu += "</select>";
        if(productos.size()==0){
            menu = "<span>No hay productos en stock</span>";
        }
        return menu;
    }
    
    public static String menuMozos(String id, ArrayList<Mozo> mozos) {
        String menu = "<span>Mozo Destino: </span><select id='" + id + "'>";
        int index = 0;
        for (Mozo mozo : mozos) {
            menu += "<option value='" + index + "'>" + mozo.getNombreCompleto() + "</option>";
            index++;
        }
        menu += "</select>";
        if(mozos.size()==0){
            menu = "<span>No hay mozos disponibles</span>";
        }
        return menu;
    }
    
    public static String tablaItems(String id, ArrayList<Item> items) {
        String tabla = "<table border=1 id="+id+"><thead><tr><th>Cantidad</th><th>Producto</th><th>Estado</th><th>Precio</th></tr></thead><tbody>";
        for (Item item : items) {
            String estado = item.getEstado().toString();
            if(!item.getEstado().equals(Item.Estado.pendiente)){
                estado += " ("+item.getPedido().getGestor().getNombreCompleto()+")";
            }
            tabla += "<tr><td>"+item.getCantidad()+"</td><td>"+item.getProducto()+" ($"+item.getPrecioUnitario()+") </td><td>"+estado+"</td><td>"+item.getMonto()+"</td></tr>";
        }
        tabla += "</tbody></table>";
        if(items.size()==0){
            tabla = "<h4>No hay items en este servicio.</h4>";
        }
        return tabla;
    }

    public static String botonesMesas(String id, ArrayList<Mesa> mesas) {
        String botones = "";
        int x = 0;
        for (Mesa m : mesas) {
            String clase = "";
            if (m.estaAbierta()) {
                clase = "abierta";
            }
            botones += "<div class='divBtnMesa'><a class='aBtnMesa " + clase + "' onClick='seleccionarMesa(" + x + ")'>" + m.toString() + "</a></div>";
            x++;
        }
        if(mesas.size()==0){
            botones = "<h3>El mozo no tiene mesas.</h3>";
        }
        return botones;
    }
    
    public static String tablaDatosTransferenciaSolicitud(Transferencia trn){
        String estado = trn.getMesa().estaAbierta() ? "Abierta" : "Cerrada";
        
        String str = "<tr><td>Mozo:</td><td>" + trn.getMesa().getMozo().getNombreCompleto() + "</td></tr>" +
                "<tr><td>Mesa:</td><td>" + trn.getMesa().getNro() + "</td><tr>" +
                "<tr><td>Estado:</td><td>" + estado + "</td></tr>" +
                "<tr><td>Tiempo restante:</td><td id='tiempoRestante'>" + trn.getTiempoRestante() + "</td><tr>" + 
                "<tr><td><a class='aBtn' onClick='aceptarTransferencia()'>Aceptar</a></td>" +
                "<td><a class='aBtn' onClick='rechazarTransferencia()'>Rechazar</a></td></tr>";
        
        return str;
    }

}
