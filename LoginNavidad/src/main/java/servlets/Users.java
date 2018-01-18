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
@WebServlet(name = "Users", urlPatterns = {"/users"})
public class Users extends HttpServlet {

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
        String paginaSalida = "login.ftl";
        String usuarioNombre;
        String usuarioPass;

        if (request.getParameter("opcion") != null) {
            String opcion = request.getParameter("opcion");

            switch (opcion) {
                case "registrar":
                    usuarioNombre = request.getParameter("nombre");
                    usuarioPass = request.getParameter("password");
                    String usuarioEmail = request.getParameter("email");
                    user.setNombre(usuarioNombre);
                    user.setEmail(usuarioEmail);
                    user.setPassword(usuarioPass);

                    int registro = us.registrar(user);

                    if (registro == 0) {
                        request.setAttribute("mensaje", "No ha sido posible completar el registro, pruebe otro nombre");
                    } else {
                        request.setAttribute("mensaje", "Registro completado, compruebe su email para validar su cuenta");
                    }
                    break;

                case "activar":
                    String codigoActivacion = request.getParameter("codigoActivacion");
                    int activar = us.activar(codigoActivacion);

                    switch (activar) {
                        case 0:
                            request.setAttribute("mensaje", "Error al activar la cuenta, se ha sobrepasado el limite de tiempo");
                            break;
                        case 1:
                            request.setAttribute("mensaje", "Cuenta activada correctamente");
                            break;
                        case 2:
                            request.setAttribute("mensaje", "La cuenta ya se encuentra activada");
                            break;
                        case 3:
                            request.setAttribute("mensaje", "Se ha producido un error al activar su cuenta, intentelo de nuevo");
                            break;
                    }

                    break;

                case "login":
                    usuarioNombre = request.getParameter("nombre");
                    usuarioPass = request.getParameter("password");
                    user.setNombre(usuarioNombre);
                    user.setPassword(usuarioPass);

                    int loguear = us.loguear(user);

                    switch (loguear) {
                        case 0:
                            int permiso = us.getPermiso(user);
                            request.getSession().setAttribute("usuarioNombre", usuarioNombre);
                            request.getSession().setAttribute("permiso", permiso);
                            paginaSalida = "navegacion.ftl";
                            break;
                        case 1:
                            request.setAttribute("mensaje", "Nombre o contraseña incorrectos, intentelo de nuevo");
                            break;
                    }

                    break;

                case "recuperar":
                    if(request.getParameter("password")!=null){
                    usuarioNombre = request.getParameter("nombre");
                    String nuevaPass=request.getParameter("password");
                    user.setNombre(usuarioNombre);
                    user.setPassword(nuevaPass);
                    us.cambiarPass(user);
                    }
                    break;

                case "modificar":
                    usuarioNombre =(String) request.getSession().getAttribute("usuarioNombre");
                    user.setNombre(usuarioNombre);
                    us.recuperarPass(user);
                    break;

                case "unLogin":
                    request.getSession().invalidate();
                    break;
            }

        }
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
