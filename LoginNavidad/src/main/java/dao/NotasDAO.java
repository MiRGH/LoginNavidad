/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modelos.Nota;

import java.sql.Connection;
import java.util.List;

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

    public Nota getNota(Long id_alumno, Long id_asignatura) {
        Connection con = null;
        Nota n = null;
        try {
            con = DBConnection.getInstance().getConnection();
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<Nota> h = new BeanHandler<>(Nota.class);
            n = qr.query(con, "SELECT * FROM NOTAS WHERE ID_ALUMNO = ? AND ID_ASIGNATURA = ?", h, id_alumno, id_asignatura);
        } catch (Exception ex) {
            Logger.getLogger(NotasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }
        return n;
    }

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

    public int eliminarNota(Nota notas) {
        Connection con = null;
        int filas = 0;
        try {
            con = DBConnection.getInstance().getConnection();
            QueryRunner qr = new QueryRunner();
            filas = qr.update(con, "DELETE FROM NOTAS WHERE ID_ALUMNO = ? AND ID_ASIGNATURA = ?",
                    notas.getIdAlumno(), notas.getIdAsignatura());
        } catch (Exception ex) {
            Logger.getLogger(NotasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }
        return filas;
    }

    public List<Nota> getAllNotaByAlu(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
