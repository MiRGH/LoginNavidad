/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.AlumnosDAO;
import dao.AsignaturasDAO;
import dao.NotasDAO;
import java.util.ArrayList;
import java.util.List;
import modelos.AluTarea;
import modelos.Alumno;
import modelos.Nota;
import modelos.Tarea;

/**
 *
 * @author Carlos
 */
public class AlumnosServicios {

    public List<Alumno> getAllAlumnos() {
        AlumnosDAO alumnos = new AlumnosDAO();
        return alumnos.getAllAlumnos();
    }

    public Alumno getAlumnoId(int id) {
        AlumnosDAO alumnos = new AlumnosDAO();
        return alumnos.getAlumnoId(id);
    }

    public int insertarAlumnos(Alumno a) {
        AlumnosDAO alumnos = new AlumnosDAO();
        return alumnos.insertarAlumnos(a);
    }

    public List<Alumno> buscarAlumnos(String nombre) {
        AlumnosDAO alumnos = new AlumnosDAO();
        return alumnos.buscarAlumnos(nombre);
    }

    public AluTarea getTareaByAlu(long id) {
        AlumnosDAO dao = new AlumnosDAO();

        return dao.getTareaByAlu(id);
    }

    public List<Nota> getAllNotaByAlu(long id) {
        NotasDAO notas = new NotasDAO();
        return notas.getAllNotaByAlu(id);
    }

}
