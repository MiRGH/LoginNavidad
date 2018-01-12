/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Dani
 */
public class Nota {

    public Nota() {

    }
    private long id_alumno;
    private long id_asignatura;
    private int nota;

    public long getIdAlumno() {
        return id_alumno;
    }

    public long getIdAsignatura() {
        return id_asignatura;
    }

    public int getNota() {
        return nota;
    }

    public void setIdAlumno(long idAlumno) {
        this.id_alumno = id_alumno;
    }

    public void setIdAsignatura(long idAsignatura) {
        this.id_asignatura = id_asignatura;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
}
