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
import modelos.User;
import servicios.UserServicios;

/**
 *
 * @author Dani
 */
@WebServlet(name = "AdministracionUsuarios", urlPatterns = {"/permiso/administracionUsuarios"})
public class AdministracionUsuarios extends HttpServlet {

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

        UserServicios us = new UserServicios();
        User user = new User();
        String paginaSalida = "administracion.ftl";
        long usuarioId;
        int permisoNuevo;

        if (request.getParameter("opcion") != null) {
            String opcion = request.getParameter("opcion");

            switch (opcion) {
                case "todos":

                    break;
                case "cambiarPermiso":
                    usuarioId = Long.parseLong(request.getParameter("id"));
                    permisoNuevo = Integer.parseInt(request.getParameter("permisoNuevo"));
                    if (permisoNuevo >= 1 & permisoNuevo <= 3) {
                        if (permisoNuevo == 1 & request.getSession().getAttribute("permiso").equals("1")) {
                            request.setAttribute("mensaje", "Solo el super administrador puede otorgar permisos de administracion");
                        } else {
                            if (request.getSession().getAttribute("permiso").equals("0")
                                    || request.getSession().getAttribute("permiso").equals("1")) {
                                user.setId(usuarioId);
                                user.setPermiso(permisoNuevo);
                                us.setPermiso(user);
                            }
                        }
                    } else {
                        request.setAttribute("mensaje", "Permiso incorrecto");
                    }

                    break;

                case "atras":
                    paginaSalida = "navegacion.ftl";

                    break;
            }

        }
        request.setAttribute("usuarios", us.getAllUsers());
        request.getRequestDispatcher(paginaSalida).forward(request, response);

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

}
