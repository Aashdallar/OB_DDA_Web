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
        String menu = "<select id='" + id + "'>";
        for (Producto prod : productos) {
            menu += "<option value='" + prod.getCodigo() + "'>" + prod.getNombre() + "</option>";
        }
        menu += "</select>";
        return menu;
    }
    
    public static String menuMozos(String id, ArrayList<Mozo> mozos) {
        String menu = "<select id='" + id + "'>";
        int index = 0;
        for (Mozo mozo : mozos) {
            menu += "<option value='" + index + "'>" + mozo.getNombreCompleto() + "</option>";
            index++;
        }
        menu += "</select>";
        return menu;
    }
    
    public static String tablaItems(String id, ArrayList<Item> items) {
        String tabla = "<table id="+id+"><thead><tr><th>Cantidad</th><th>Item</th><th>Precio</th></tr></thead><tbody>";
        for (Item item : items) {
            tabla += "<tr><td>"+item.getCantidad()+"</td><td>"+item.getProducto()+"</td><td>"+item.getMonto()+"</td></tr>";
        }
        tabla += "</tbody></table>";
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
        return botones;
    }

}
