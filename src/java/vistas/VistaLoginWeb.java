/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Mozo;
import mozoApp.controlador.LoginControladorMozo;
import mozoApp.controlador.LoginVistaMozo;

/**
 *
 * @author alumnoFI
 */
public class VistaLoginWeb implements LoginVistaMozo{
    
    private LoginControladorMozo controlador;
    private HttpServletResponse response; 
    private HttpServletRequest request; 
    
    public VistaLoginWeb() {
        controlador = new LoginControladorMozo(this);
    }
    
    @Override
    public void error(String mensaje) {
        try {
            response.sendRedirect("index.jsp?msg=" +mensaje );
        } catch (IOException ex) {
            System.out.println("Error:" + ex.getMessage());
        }
    }

    @Override
    public void ingresar(Mozo mozo) {
        try {
            request.getSession(true).setAttribute("mozoLogueado", mozo);
            response.sendRedirect("mozoMainApp.jsp");
        } catch (IOException ex) {
           
        }
    }
    
    public void login(HttpServletRequest req,HttpServletResponse res) {
        String usu = req.getParameter("usu");
        String pass = req.getParameter("pass");
        response = res;
        request = req;
        controlador.login(usu, pass);
    }
    
}
