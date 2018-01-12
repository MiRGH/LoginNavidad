/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import modelos.AsigProf;
import modelos.Asignatura;
import modelos.Profesor;
import modelos.Tarea;
import modelos.Nota;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Carlos
 */
public class ProfesoresDAO {

    Connection con = null;

    public List<Profesor> getAllProfesores() {
        List<Profesor> lista = null;

        try {
            con = DBConnection.getInstance().getConnection();
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<List<Profesor>> h = new BeanListHandler<>(Profesor.class);
            lista = qr.query(con,
                    "SELECT * FROM PROFESORES;", h);

        } catch (Exception ex) {
            Logger.getLogger(ProfesoresDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection db = DBConnection.getInstance();
            db.cerrarConexion(con);
        }
        return lista;
    }

    public int insertarProfesores(Profesor p) {
        int filas = 0;
        try {

            con = DBConnection.getInstance().getConnection();
            QueryRunner qr = new QueryRunner();

            filas = qr.update(con,
                    "INSERT INTO PROFESORES (NOMBRE, ID_USUARIO) VALUES(?, ?) ",
                    p.getNombre(), p.getId_usuario());

        } catch (Exception ex) {
            Logger.getLogger(Profesor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection db = DBConnection.getInstance();
            db.cerrarConexion(con);
        }
        return filas;
    }

    public Profesor getProfesorId(int id) {
        Profesor result = null;
        try {
            con = DBConnection.getInstance().getConnection();
            QueryRunner qr = new QueryRunner();
            BeanHandler<Profesor> h = new BeanHandler<>(Profesor.class);
            result = qr.query(
                    "SELECT * FROM PROFESORES WHERE id", h, id);

        } catch (Exception e) {
            System.out.println("Error en la conexión con la base de datos");
        } finally {
            DBConnection db = DBConnection.getInstance();
            db.cerrarConexion(con);
        }

        return result;
    }

    public AsigProf getAsigByProf(long id) {
        AsigProf result = null;
        try {
            con = DBConnection.getInstance().getConnection();
            QueryRunner qr = new QueryRunner();
            BeanHandler<AsigProf> h = new BeanHandler<>(AsigProf.class);
            result = qr.query(
                    "SELECT a.nombre, a.id_curso, p.nombre FROM asignatura a JOIN asigprof ap ON a.id = ap.id_asig JOIN profesor p "
                    + "ON p.id_profesor = ap.id_profesor where ap.id_profesor=? ;", h, id);

        } catch (Exception e) {
            System.out.println("Error en la conexión con la base de datos");
        } finally {
            DBConnection db = DBConnection.getInstance();
            db.cerrarConexion(con);
        }

        return result;

    }

    public Tarea addTarea(Tarea a) {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            QueryRunner qr = new QueryRunner();
            long id = qr.insert(con,
                    "INSERT INTO TAREA (NOMBRE,FECHA,ID_ASIGNATURA) VALUES(?,?,?,?,?)",
                    new ScalarHandler<Long>(), a.getNombre(), a.getFecha(), a.getId_asignatura());

            a.setId(id);
            con.commit();
        } catch (Exception ex) {
            Logger.getLogger(ProfesoresDAO.class.getName()).log(Level.SEVERE, null, ex);
            a = null;
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }
        return a;
    }

    public Nota addNota(Nota a) {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            QueryRunner qr = new QueryRunner();
            long id = qr.insert(con,
                    "INSERT INTO NOTAS (ID_ALUMNO,ID_ASIGNATURA,NOTA) VALUES(?,?,?)",
                    new ScalarHandler<Long>(), a.getIdAlumno(), a.getIdAsignatura(), a.getNota());
            con.commit();
        } catch (Exception ex) {
            Logger.getLogger(ProfesoresDAO.class.getName()).log(Level.SEVERE, null, ex);
            a = null;
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }
        return a;
    }

}
