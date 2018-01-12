/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import config.Configuration;
import dao.UsersDAO;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.User;
import utils.PasswordHash;
import utils.Utils;

/**
 *
 * @author Dani
 */
public class UserServicios {

    public List<User> getAllUsers() {
        UsersDAO dao = new UsersDAO();

        return dao.getAllUsers();
    }

    public boolean comprobarNombres(String nombre) {
        UsersDAO dao = new UsersDAO();

        return dao.comprobarNombres(nombre);
    }

    public int registrar(User user) {
        boolean cogido = comprobarNombres(user.getNombre());

        int registro = 0;
        if (!cogido) {
            try {
                UsersDAO dao = new UsersDAO();

                user.setPassword(PasswordHash.getInstance().createHash(user.getPassword()));
                user.setCodigoActivacion(Utils.randomAlphaNumeric(Configuration.getInstance().getLongitudCodigo()));
                LocalDateTime fechaActual = LocalDateTime.now();
                user.setFechaActivacion(fechaActual);
                user = dao.registrar(user);
                if (user != null) {
                    MailServicios mail = new MailServicios();
                    mail.mandarMail(user.getEmail(), "http://localhost:8080/users?opcion=activar&codigoActivacion=" + user.getCodigoActivacion(), "Activar cuenta");
                    registro = 1;
                }
            } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                Logger.getLogger(UserServicios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return registro;
    }

    public int activar(String codigo) {
        UsersDAO dao = new UsersDAO();

        User user = dao.getUserCodigoActivacion(codigo);

        int activar = 3;
        if (!user.getActivado()) {
            LocalDateTime fechaActivacion = user.getFechaActivacion();
            LocalDateTime fechaActual = LocalDateTime.now().minusMinutes(Configuration.getInstance().getMinutosParaValidar());
            if (fechaActivacion.isAfter(fechaActual)) {
                activar = dao.updateValido(user);
            } else {
                activar = dao.borrarUser(user);
            }
        }
        return activar;
    }

    public int loguear(User user) {
        int correcto = 0;
        try {
            UsersDAO dao = new UsersDAO();

            User u = dao.getUserNombre(user.getNombre());
            boolean passwordCorrecta = PasswordHash.getInstance().validatePassword(user.getPassword(), u.getPassword());
            if (passwordCorrecta == true) {
                if (u.getActivado() == true) {
                    correcto = 1;
                }
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(UserServicios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return correcto;
    }

    public int getPermiso(User user) {
        UsersDAO dao = new UsersDAO();

        int permiso = dao.getPermiso(user.getNombre());

        return permiso;
    }

    public void setPermiso(User user) {
        UsersDAO dao = new UsersDAO();

        dao.setPermiso(user);
    }
}
