/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Dani
 */
public class UsersDAO {

    public List<User> getAllUsers() {

        JdbcTemplate jtm = new JdbcTemplate(DBConnection.getInstance().getDataSource());
        List<User> User = jtm.query("Select * from USERS", new BeanPropertyRowMapper(User.class));

        return User;
    }

    public User registrar(User u) {
        try {
            JdbcTemplate jtm = new JdbcTemplate(DBConnection.getInstance().getDataSource());

            int filas = jtm.update("INSERT INTO USERS (NOMBRE,PASSWORD,CODIGO_ACTIVACION,ACTIVO,FECHA_ACTIVACION,EMAIL,PERMISOS) VALUES(?,?,?,0,?,?,4)",
                    u.getNombre(), u.getPassword(), u.getCodigoActivacion(), u.getFechaActivacion(), u.getEmail());
            if (filas == 0) {
                u = null;
            }
        } catch (Exception ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
            u = null;
        }
        return u;
    }

    public void cambiarPass(User u) {
        try {
            JdbcTemplate jtm = new JdbcTemplate(DBConnection.getInstance().getDataSource());

            jtm.update("UPDATE USERS SET PASSWORD = ? WHERE NOMBRE = ?",
                    u.getPassword(), u.getNombre());
        } catch (Exception ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean comprobarNombres(String nombre) {
        boolean existe = false;
        try {
            JdbcTemplate jtm = new JdbcTemplate(DBConnection.getInstance().getDataSource());

            String resultado = jtm.queryForObject("SELECT NOMBRE FROM USERS WHERE NOMBRE = ?", String.class, nombre);

            if (resultado != null) {
                existe = true;
            }
        } catch (Exception ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }

    public int borrarUser(User user) {
        int valido = 3;
        JdbcTemplate jtm = new JdbcTemplate(DBConnection.getInstance().getDataSource());

        if (jtm.update("DELETE FROM USERS WHERE CODIGO_ACTIVACION = ?", user.getCodigoActivacion()) > 0) {
            valido = 0;
        }

        return valido;
    }

    public String recuperarPass(User user) {

        JdbcTemplate jtm = new JdbcTemplate(DBConnection.getInstance().getDataSource());

        String pass = (String) jtm.queryForObject("SELECT PASSWORD FROM USERS WHERE NOMBRE = ?", String.class, user.getNombre());

        return pass;
    }

    public User getUserCodigoActivacion(String codigoActivacion) {
        User u;
        try {
            JdbcTemplate jtm = new JdbcTemplate(DBConnection.getInstance().getDataSource());

            u = (User) jtm.queryForObject("SELECT * FROM USERS WHERE CODIGO_ACTIVACION = ?", new Object[]{codigoActivacion}, new BeanPropertyRowMapper(User.class));
        } catch (Exception ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
            u = null;
        }
        return u;
    }

    public int updateValido(User user) {
        int valido = 3;
        JdbcTemplate jtm = new JdbcTemplate(DBConnection.getInstance().getDataSource());

        if (jtm.update("UPDATE USERS SET ACTIVO = TRUE WHERE CODIGO_ACTIVACION = ?", user.getCodigoActivacion()) > 0) {
            valido = 1;
        }

        return valido;
    }

    public int getPermiso(String nombre) {
        JdbcTemplate jtm = new JdbcTemplate(DBConnection.getInstance().getDataSource());

        int permiso = jtm.queryForObject("SELECT PERMISO FROM USERS WHERE NOMBRE = ?", int.class, nombre);

        return permiso;
    }

    public void setPermiso(User user) {
        JdbcTemplate jtm = new JdbcTemplate(DBConnection.getInstance().getDataSource());

        jtm.update("UPDATE USERS SET PERMISO = ? WHERE ID = ?", user.getPermiso(), user.getId());

    }

    public User getUserNombre(String nombre) {
        User u;
        try {
            JdbcTemplate jtm = new JdbcTemplate(DBConnection.getInstance().getDataSource());

            u = (User) jtm.queryForObject("SELECT * FROM USERS WHERE NOMBRE = ?", new Object[]{nombre}, new BeanPropertyRowMapper(User.class));
        } catch (Exception ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
            u = null;
        }
        return u;
    }
}
