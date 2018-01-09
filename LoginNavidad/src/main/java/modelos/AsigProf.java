/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author daw
 */
public class AsigProf {
    private Long id_profesor;
    private Long id_asig;

    public AsigProf() {
    }

    public Long getId_profesor() {
        return id_profesor;
    }

    public void setId_profesor(Long id_profesor) {
        this.id_profesor = id_profesor;
    }

    public Long getId_asig() {
        return id_asig;
    }

    public void setId_asig(Long id_asig) {
        this.id_asig = id_asig;
    }
    
}
