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
import modelos.Alumno;
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
public class AlumnosDAO {

    Connection con = null;

    public List<Alumno> getAllAlumnos() {
        List<Alumno> lista = null;

        try {
            con = DBConnection.getInstance().getConnection();
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<List<Alumno>> h = new BeanListHandler<>(Alumno.class);
            lista = qr.query(con,
                    "SELECT * FROM ALUMNOS;", h);

        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection db = DBConnection.getInstance();
            db.cerrarConexion(con);
        }
        return lista;
    }

    public int insertarAlumnos(Alumno a) {
        int filas = 0;
        try {

            con = DBConnection.getInstance().getConnection();
            QueryRunner qr = new QueryRunner();

            filas = qr.update(con,
                    "INSERT INTO ALUMNOS (NOMBRE, MAYOR, FECHA_NACIMIENTO, ID_TAREA) VALUES(?, ?, ?, ?) ",
                    a.getNombre(), a.getMayor(), a.getFecha_nacimiento(), a.getId_tarea());

        } catch (Exception ex) {
            Logger.getLogger(Alumno.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection db = DBConnection.getInstance();
            db.cerrarConexion(con);
        }
        return filas;
    }

    public Alumno getAlumnoId(int id) {
        Alumno result = null;
        try {
            con = DBConnection.getInstance().getConnection();
            QueryRunner qr = new QueryRunner();
            BeanHandler<Alumno> h = new BeanHandler<>(Alumno.class);
            result = qr.query(
                    "SELECT * FROM ALUMNOS WHERE id", h, id);

        } catch (Exception e) {
            System.out.println("Error en la conexión con la base de datos");
        }  finally {
            DBConnection db = DBConnection.getInstance();
            db.cerrarConexion(con);
        }

        return result;
    }

}