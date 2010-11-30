/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * prueba
 */

package concreteCommands;

import ejb.ABMUsuarioLocal;
import java.io.IOException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import frameworkp.Command;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
/**
 *
 * @author pablo
 */
public class addUser implements Command {
   

    private String Nombre;
    private String Apellido;

    public addUser() {
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }





  @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      try {
            this.Nombre = request.getParameter("nombre");
            this.Apellido = request.getParameter("apellido");
            Context ctx = new InitialContext();
            ABMUsuarioLocal ejbUsuario = (ABMUsuarioLocal) ctx.lookup("java:comp/env/ABMUsuario");
            //request.setAttribute("usuario", this.login);
            return ejbUsuario.alta(Nombre, Nombre, Nombre, Apellido);


        } catch (NamingException ex) {
            Logger.getLogger(ComandoConcreto.class.getName()).log(Level.SEVERE, "No se pudo encontrar el EJB", ex);
        }

        return "";





    }


    private ABMUsuarioLocal lookupABMUsuarioLocal() {
        try {
            Context c = new InitialContext();
            return (ABMUsuarioLocal) c.lookup("java:comp/env/ABMUsuario");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }



}
