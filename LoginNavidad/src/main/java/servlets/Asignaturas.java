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
import java.text.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import modelos.Asignatura;
import servicios.AsignaturaServicios;

/**
 *
 * @author oscar
 */
@WebServlet(name = "Asignaturas", urlPatterns ={"/secure/asignaturas"})
public class Asignaturas extends HttpServlet
{

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
            throws ServletException, IOException, ParseException
    {

        Asignatura a = new Asignatura();
        AsignaturaServicios as = new AsignaturaServicios();
        String op = request.getParameter("op");
        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String curso = request.getParameter("id_curso");
        if(op==null){
            op="";
        }
        switch (op)
        {
            case "insert":
                a.setNombre(nombre);
                a.setId_curso(Long.parseLong(curso));
               as.addAsig(a);
                break;
           
            default:
              request.setAttribute("asignaturas", as.getAllAsignaturas());
                request.getRequestDispatcher("/pintarListaAsignaturas.jsp").forward(request, response);

        }
        request.setAttribute("asignaturas", as.getAllAsignaturas());
        request.getRequestDispatcher("/pintarListaAsignaturas.jsp").forward(request, response);
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
            throws ServletException, IOException
    {
        try
        {
            processRequest(request, response);
        } catch (ParseException ex)
        {
            Logger.getLogger(Asignaturas.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            throws ServletException, IOException
    {
        try
        {
            processRequest(request, response);
        } catch (ParseException ex)
        {
            Logger.getLogger(Asignaturas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
