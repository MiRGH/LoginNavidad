/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.ProfesoresDAO;
import java.util.ArrayList;
import java.util.List;
import modelos.AsigProf;
import modelos.Profesor;

/**
 *
 * @author Carlos
 */
public class ProfesoresServicios {

    public List<Profesor> getAllProfesores() {
        ProfesoresDAO profesores = new ProfesoresDAO();
        return profesores.getAllProfesores();
    }

    public Profesor getProfesorId(int id) {
        ProfesoresDAO profesores = new ProfesoresDAO();
        return profesores.getProfesorId(id);
    }

    public int insertarProfesores(Profesor p) {
        ProfesoresDAO profesores = new ProfesoresDAO();
        return profesores.insertarProfesores(p);
    }
public AsigProf getAllAsigbyProf(int id) {
        ProfesoresDAO dao = new ProfesoresDAO();

        return dao.getAsigByProf(id);
    }
}
