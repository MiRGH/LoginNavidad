/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

/**
 *
 * @author Rafa
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Alumno;
import modelos.User;
import modelos.Profesor;
import modelos.Tarea;
import modelos.Nota;
import servicios.AlumnosServicios;
import servicios.ProfesoresServicios;

/**
 *
 * @author Carlos
 */
@WebServlet(name = "Profesores", urlPatterns = {"/profesores"})
public class Profesores extends HttpServlet {

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

        User u = (User) request.getSession().getAttribute("usuario");

        AlumnosServicios as = new AlumnosServicios();
        ProfesoresServicios ps = new ProfesoresServicios();
        String op = request.getParameter("op");
        String addTarea = request.getParameter("addTarea");
        String addNota = request.getParameter("addNota");
        String nombreTarea = request.getParameter("nombreTarea");
        String id_asig = request.getParameter("id_asignatura");
        String id_alumno = request.getParameter("id_alumno");
        String nota = request.getParameter("nota");
        int dato = 0;
        Alumno alu = null;

        if (op == null) {
            op = "";
        }

        if (op != null) {/*si op existe*/
            if (u.getPermiso() == 3)/*permiso profe */ {
                ps.getAllAsigbyProf(u.getId());
            }
            switch (op) {
                case "VER_ASIG":
                    try {
                        ps.getAllAsigbyProf(Long.parseLong(id_asig));
                        if (addTarea.equals("si")) {
                            Tarea t = new Tarea();
                            t.setNombre(nombreTarea);
                            t.setId_asignatura(Long.parseLong(id_asig));
                            t.setFecha(LocalDateTime.MIN);
                            ps.addTarea(t);
                        }
                        if (addNota.equals("si")) {
                            Nota n = new Nota();
                            n.setIdAsignatura(Long.parseLong(id_asig));
                            n.setIdAlumno(Long.parseLong(id_alumno));     
                            n.setNota(Integer.parseInt(nota));
                            ps.addNota(n);
                        }
                    } catch (Exception e) {
                        System.out.println("Error en el formato de fecha");
                    }
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
