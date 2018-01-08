/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Asignatura;
import modelos.Curso;
import modelos.Tarea;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

/**
 *
 * @author Dani
 */
public class AsignaturasDAO {
     public List<Asignatura> getAllAsignaturas() {
        List<Asignatura> lista = null;
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<List<Asignatura>> h = new BeanListHandler<Asignatura>(Asignatura.class);
            lista = qr.query(con, "select * FROM ASIGNATURAS", h);

        } catch (Exception ex) {
            Logger.getLogger(AsignaturasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }
        return lista;
    }

    public Asignatura addAsignatura(Asignatura a) {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            QueryRunner qr = new QueryRunner();
            long id = qr.insert(con,
                    "INSERT INTO ASIGNATURAS (NOMBRE,ID_CURSO) VALUES(?,?,?)",
                    new ScalarHandler<Long>(), a.getNombre(), a.getId_curso());
           
            a.setId(id);
            con.commit();
        } catch (Exception ex) {
            Logger.getLogger(AsignaturasDAO.class.getName()).log(Level.SEVERE, null, ex);
            a = null;
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }
        return a;
    }
     public Curso addCurso(Curso a) {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            QueryRunner qr = new QueryRunner();
            long id = qr.insert(con,
                    "INSERT INTO CURSO (NOMBRE) VALUES(?)",
                    new ScalarHandler<Long>(), a.getNombre());
           
            a.setId_curso(id);
            con.commit();
        } catch (Exception ex) {
            Logger.getLogger(AsignaturasDAO.class.getName()).log(Level.SEVERE, null, ex);
            a = null;
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }
        return a;
    }
     public Tarea addTarea(Tarea a) {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            QueryRunner qr = new QueryRunner();
            long id = qr.insert(con,
                    "INSERT INTO TAREA (NOMBRE) VALUES(?)",
                    new ScalarHandler<Long>(), a.getNombre());
           
            a.setId(id);
            con.commit();
        } catch (Exception ex) {
            Logger.getLogger(AsignaturasDAO.class.getName()).log(Level.SEVERE, null, ex);
            a = null;
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }
        return a;
    }
}
