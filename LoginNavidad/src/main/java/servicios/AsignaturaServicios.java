/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.AsignaturasDAO;
import java.util.List;
import modelos.Asignatura;
import modelos.Curso;
import modelos.Tarea;

/**
 *
 * @author Dani
 */
public class AsignaturaServicios {
     public List<Asignatura> getAllAsignaturas() {
        AsignaturasDAO dao = new AsignaturasDAO();

        return dao.getAllAsignaturas();
    }

    public Asignatura addAsig(Asignatura asignaturaNuevo) {
        AsignaturasDAO dao = new AsignaturasDAO();

        return dao.addAsignatura(asignaturaNuevo);
    }
    public Curso addCurso(Curso cursoNuevo) {
        AsignaturasDAO dao = new AsignaturasDAO();

        return dao.addCurso(cursoNuevo);
    }
    public Tarea addTarea(Tarea tareaNuevo) {
        AsignaturasDAO dao = new AsignaturasDAO();

        return dao.addTarea(tareaNuevo);
    }
}
