/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import modelo.Mesa;
import modelo.Mozo;
import modelo.Transferencia;
import mozoApp.controlador.MainControladorMozo;
import mozoApp.controlador.MainVistaMozo;
import utils.Componentes;

/**
 *
 * @author alumnoFI
 */
public class VistaMozoWeb implements MainVistaMozo {
    
    private MainControladorMozo controlador;
    private AsyncContext contexto;
    private PrintWriter out;

    public VistaMozoWeb(Mozo mozo,AsyncContext c) {
        contexto = c;
        try {
            out = contexto.getResponse().getWriter();
            controlador = new MainControladorMozo(this,mozo);
        } catch (IOException ex) {
            System.out.println("Error al obtener el writer");
        }

    }
    
    //Comunicación SSE
    public void enviar(String evento, String dato) {
        out.write("event: " + evento + "\n");
        dato = dato.replace("\n", "");
        out.write("data: " + dato + "\n\n");
        if (out.checkError()) { 
            System.out.println("Error");
        } else {
             System.out.println("Enviado");
        }
    }
    
    
    @Override
    public void mostrarMesas(Mesa mesa, Mozo mozo) {
        enviar("mesas", Componentes.lista(false, "listaMesas", mozo.getMesas()));
    }

    @Override
    public void mostrarAlerta(String mensaje) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mostrarTransferirMesa(ArrayList<Mozo> mozosLogueados, Mesa mesa) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mostrarTransferenciaSolicitud(Transferencia transferencia) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mostrarTransferenciaActualizada() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void terminarTransferenciaPorTiempoTerminado() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
//    @Override
//    public void mostrarContactos(ArrayList<Contacto> contactos) {
//        enviar("contactos",Componentes.lista(true, "lstContactos", contactos));
//    }
//
//    @Override
//    public void mostrarTitulo(String nombreCompleto, int cantidadContactos) {
//        enviar("titulo", nombreCompleto + " - (" + cantidadContactos + ") contactos");
//    }
//
//    @Override
//    public void error(String message) {
//       enviar("mensaje", message);
//    }
//
//    @Override
//    public void mostrarTelefonos(ArrayList<Telefono> telefonos) {
//       enviar("telefonos",Componentes.lista(true,"lstTelefonos", telefonos));
//    }
//
//    @Override
//    public void limpiarCamposTelefono() {
//     enviar("limpiarCamposTelefono","");
//    }
//
//    @Override
//    public void limpiarCamposContacto() {
//        enviar("limpiarCamposContacto","");
//    }
//
//    @Override
//    public void cargarTipos(ArrayList<Tipo> tipos) {
//       this.tipos = tipos;
//       enviar("tipos",Componentes.lista(false, "lstTipos", tipos));
//    }
//
//    public void nuevoTel(HttpServletRequest request) {
//        
//        String nro = request.getParameter("numero");
//        int idx = Integer.parseInt(request.getParameter("tipo"));
//        controlador.agregarTelefono(nro, tipos.get(idx));
//    }
//
//    public void agregarContacto(HttpServletRequest request) {
//        String nombre = request.getParameter("nombre");
//        controlador.crearContacto(nombre);
//    }
    
}
