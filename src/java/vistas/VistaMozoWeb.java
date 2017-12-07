/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Item;
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
    private Transferencia trn;

    public VistaMozoWeb(Mozo mozo, AsyncContext c) {
        contexto = c;
        try {
            out = contexto.getResponse().getWriter();
            controlador = new MainControladorMozo(this, mozo);
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

    public void seleccionarMesa(HttpServletRequest request) {
        int indexMesa = Integer.parseInt(request.getParameter("indexMesa"));
        controlador.seleccionarMesa(indexMesa);
    }

    @Override
    public void mostrarMesas(Mesa mesa, Mozo mozo) {
        enviar("mesas", Componentes.botonesMesas("btnMesas", mozo.getMesas()));
        if (mesa != null) {
            enviar("prepararMesa","");
            if(mesa.estaAbierta()){
                enviar("abierta","");
            }else{
                enviar("cerrada","");
            }
            enviar("items", Componentes.tablaItems("tablaItems",mesa.getServicio().getItems()));
            enviar("nroMesa", mesa.getNro() + "");
            enviar("total", mesa.getServicio().getMontoTotalAAbonar() + "");
            enviar("descuento", mesa.getServicio().getMontoDescontado() + "");
            enviar("totalSinBeneficio", mesa.getServicio().getMontoTotalSinDescuento() + "");
            enviar("cliente", mesa.getClienteNombre());
            enviar("beneficio", mesa.getClienteBeneficioTexto());
        } else {
            enviar("limpiarMesa","");
        }
    }

    @Override
    public void mostrarAlerta(String mensaje) {
        enviar("mensaje",mensaje);
    }

    @Override
    public void mostrarTransferirMesa(ArrayList<Mozo> mozosLogueados, Mesa mesa) {
        enviar("mozosDisponibles",Componentes.menuMozos("menuMozos",mozosLogueados));
    }

    @Override
    public void mostrarTransferenciaSolicitud(Transferencia transferencia) {
        trn = transferencia;
        enviar("solicitudTransferenciaMesa",Componentes.tablaDatosTransferenciaSolicitud(trn));
    }

    @Override
    public void mostrarTransferenciaActualizada() {
        enviar("solicitudTransferenciaMesaActualizar",trn.getTiempoRestante() + "");
    }

    @Override
    public void terminarTransferenciaPorTiempoTerminado() {
        enviar("solicitudTransferenciaMesaTerminada","");
    }

    public void agregarItem(HttpServletRequest request) {
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        int prod = Integer.parseInt(request.getParameter("indexProd"));
        String descripcion = request.getParameter("descripcion");
        controlador.agregarItemALaMesa(prod,cantidad,descripcion);    
    }
    
    public void agregarCliente(HttpServletRequest request) {
        int idCli = Integer.parseInt(request.getParameter("id"));
        controlador.agregarClienteALaMesa(idCli);
    }

    public void transferirMesa(HttpServletRequest request) {
        int indexMozo = Integer.parseInt(request.getParameter("indexMozo"));
            controlador.transferirMesa(indexMozo);
    }

    public void abrirMesa(HttpServletRequest request) {
        controlador.abrirMesa();
    }

    public void cerrarMesa(HttpServletRequest request) {
        controlador.cerrarMesa();
    }

    public void iniciarTransferencia(HttpServletRequest request) {
        controlador.iniciarTransferirMesa();
    }
    
    public void getProductos(HttpServletRequest request) {
        enviar("productos",Componentes.menuProductos("menuProductos",controlador.getProductosConStock()));
    }

    public void desloguear(HttpServletRequest request) {
        if(controlador.desloguearMozo()){
            enviar("inicio","");
            request.getSession(true).setAttribute("mozoLogueado", null);
        }
    }
    
    public void aceptarTransferencia(){
        controlador.transferenciaAceptar();
    }
    public void rechazarTransferencia(){
        controlador.transferenciaRechazar();
    }
}
