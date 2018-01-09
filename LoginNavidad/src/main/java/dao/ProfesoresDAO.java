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
import modelos.Asignatura;
import modelos.Profesor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
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

    public Asignatura getAsigByProf(int id) {
        Asignatura result = null;
        try {
            con = DBConnection.getInstance().getConnection();
            QueryRunner qr = new QueryRunner();
            BeanHandler<Asignatura> h = new BeanHandler<>(Asignatura.class);
            result = qr.query(
                    "SELECT a.nombre, a.id, p.id_profesor FROM asignatura a JOIN profesor p ON a.id_profesor = p.id_profesor ;", h, id);

        } catch (Exception e) {
            System.out.println("Error en la conexión con la base de datos");
        } finally {
            DBConnection db = DBConnection.getInstance();
            db.cerrarConexion(con);
        }

        return result;
    }

}
