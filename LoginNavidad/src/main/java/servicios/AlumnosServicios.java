/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.AlumnosDAO;
import java.util.ArrayList;
import java.util.List;
import modelos.Alumno;

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

}
