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
public class Asignatura {
     private Long id;
     private String nombre;
     private Long id_curso;
     private Long id_profesor;
    
    public Asignatura(){
    }
    
    public void setId(Long id){
        this.id=id;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public Long getId_curso() {
        return id_curso;
    }

    public void setId_curso(Long id_curso) {
        this.id_curso = id_curso;
    }
    public Long getId(){
        return id;
    }
    public String getNombre(){
        return nombre;
    }

    public Long getId_profesor() {
        return id_profesor;
    }

    public void setId_profesor(Long id_profesor) {
        this.id_profesor = id_profesor;
    }
    
}
