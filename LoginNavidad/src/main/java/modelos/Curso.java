/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Rafa
 */
public class Curso {
    private String nombre;
     private Long id_curso;
    
   
    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public Long getId_curso() {
        return id_curso;
    }

    public void setId_curso(Long id_curso) {
        this.id_curso = id_curso;
    }
   
    public String getNombre(){
        return nombre;
    }
}
