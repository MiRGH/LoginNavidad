/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Alumno;
import modelos.User;
import servicios.AlumnosServicios;

/**
 *
 * @author Carlos
 */
@WebServlet(name = "Alumnos", urlPatterns = {"/alumnos"})
public class Alumnos extends HttpServlet {

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
        
        User u = request.getSession().getAttribute("usuario");
        AlumnosServicios as = new AlumnosServicios();
        ProfesoresServicios ps = new ProfesoresServicios();
        String op = request.getParameter("op");
        int dato = 0;
        Alumno alu = null;
        
        if(u.getPermiso()== 2){//si tienes el permiso 2
            ps.getAllNotas(u.getId_nota());
        }
        if(op == null){
            op="";
        }
        
        if (op != null) {/*si op existe*/
            String nombre = request.getParameter("nombre");
            String fecha = request.getParameter("fecha");
            int id_tarea = Integer.parseInt(request.getParameter("id_tarea"));
            Date form_fecha = null;
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

            switch (op) {
                case "INSERT":
                    try {
                        Alumno a = new Alumno();
                        form_fecha = format.parse(fecha);
                        a.setNombre(nombre);
                        a.setFecha_nacimiento(form_fecha);
                        a.setMayor(0);//falta hacer comparacion de fechas 
                        a.setId_tarea(id_tarea);
                        as.insertarAlumnos(a);

                    } catch (Exception e) {
                        System.out.println("Error en el formato de fecha");
                    }
                    break;
                    
                case "GET_ALUMNO_ID":
                    int id_alumno = Integer.parseInt(request.getParameter("id_alumno"));
                    alu = as.getAlumnoId(id_alumno);
                    dato = 1;
                    break;
            }
        }
        if (dato == 0) {
            request.setAttribute("alumnos", as.getAllAlumnos());//para darme TODOS los alumnos
        } else {
            request.setAttribute("alu", alu);//parar darme un alumno concreto 
        }

        request.getRequestDispatcher("pintarListaAlumnos.jsp").forward(request, response);

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
