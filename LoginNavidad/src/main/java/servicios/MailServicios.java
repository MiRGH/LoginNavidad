/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;
import config.Configuration;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
/**
 *
 * @author Dani
 */
public class MailServicios {
     public void mandarMail(String to, String msg, String subject) {
        try {
            Email email = new SimpleEmail();

            email.setHostName(Configuration.getInstance().getSmtpServer());
            email.setSmtpPort(Integer.parseInt(Configuration.getInstance().getSmtpPort()));
            email.setAuthentication(Configuration.getInstance().getMailFrom(), Configuration.getInstance().getMailPass());
            email.setStartTLSEnabled(true);
            email.setFrom(Configuration.getInstance().getMailFrom());
            email.setSubject(subject);
            email.setContent("<html>"
                    + "<body>"
                    + "<h1>Registrado</h1>"
                    + "<p>Haz click para activarte</p>"
                    + "<a href='" + msg + "'>Pincha aqui</a>"
                    + "</body>"
                    + "</html>", "text/html");
            email.addTo(to);
            email.send();
        } catch (EmailException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(MailServicios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
