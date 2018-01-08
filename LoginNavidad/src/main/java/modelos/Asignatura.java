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
}
