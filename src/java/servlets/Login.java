/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Mozo;
import modelo.Usuario;
import mozoApp.controlador.LoginControladorMozo;
import mozoApp.controlador.LoginVistaMozo;
import utils.Inicio;

/**
 *
 * @author alumnoFI
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet implements LoginVistaMozo {
    
    private LoginControladorMozo controlador;
    private  HttpServletResponse response;
    private HttpServletRequest request;

    public Login() {
        Inicio.main(null);
        controlador = new LoginControladorMozo(this);
        
    }
    
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                        
        String usr = request.getParameter("nombre");
        String pass = request.getParameter("pass");
        this.response=response;
        this.request=request;
        controlador.login(usr, pass);
        
        
                
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

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
            response.sendRedirect("Agenda.jsp");
        } catch (IOException ex) {
           
        }
    }
}
