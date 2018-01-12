/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import modelos.Nota;

import java.sql.Connection;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
/**
 *
 * @author Dani
 */
public class NotasDAO {
     

    public Nota insertNota(Nota notas) {
        DBConnection db = null;
        Connection con = null;
        try {
            con = db.getInstance().getConnection();
            QueryRunner qr = new QueryRunner();
            qr.insert(con, "INSERT INTO NOTAS (ID_ALUMNO,ID_ASIGNATURA,NOTA) VALUES(?,?,?)",
                    new ScalarHandler<>(), notas.getIdAlumno(), notas.getIdAsignatura(), notas.getNota());

        } catch (Exception e) {
            Logger.getLogger(NotasDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            db.getInstance().cerrarConexion(con);
        }
        return notas;
    }
}
