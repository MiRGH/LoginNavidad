/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;
import dao.NotasDAO;
import modelos.Nota;
/**
 *
 * @author Dani
 */
public class NotaServicios {
     public Nota getNotas(Nota notas) {
        NotasDAO dao = new NotasDAO();

        return dao.selectNota(notas);
    }

    public Nota addNotas(Nota notas) {
        NotasDAO dao = new NotasDAO();

        return dao.insertNota(notas);
    }
}
