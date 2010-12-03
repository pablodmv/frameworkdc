/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * prueba
 */

package concreteCommands;

import ejb.ABMUsuarioLocal;
import java.io.IOException;
import java.text.ParseException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import frameworkp.Command;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
/**
 *
 * @author pablo
 */
public class addUser implements Command {
   

    private String nombre;
    private String apellido;
    private String login;
    private String pwd;
    private Date fNac;
    private String rol;

    public addUser() {
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String Apellido) {
        this.apellido = Apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getfNac() {
        return fNac;
    }

    public void setfNac(Date fNac) {
        this.fNac = fNac;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

  @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      String outcome = "Fail";
      try {
            this.nombre = request.getParameter("nombre");
            this.apellido = request.getParameter("apellido");
            this.login = request.getParameter("usuario");
            this.pwd = request.getParameter("pwd");

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            if(!request.getParameter("fNac").equals("") && !this.nombre.equals("") && !this.apellido.equals("") && !this.login.equals("") && !this.pwd.equals("")){
                
                this.fNac = df.parse(request.getParameter("fNac"));
                this.rol = request.getParameter("rol");
                Context ctx = new InitialContext();
                ABMUsuarioLocal ejbUsuario = (ABMUsuarioLocal) ctx.lookup("java:comp/env/ABMUsuario");


                outcome = ejbUsuario.alta(this.login,this.pwd,this.nombre,this.apellido,this.fNac,this.rol);

                if(outcome.equals("Success")){
                    request.setAttribute("mensaje", "Usuario Guardado con Exito!");
                }else{
                    request.setAttribute("mensaje", "El Usuario no se pudo ingresar.");
                }
            }else{
                request.setAttribute("mensaje", "Faltan ingresar datos requeridos");
            }
            
            return outcome;
        
        } catch (ParseException ex) {
            Logger.getLogger(addUser.class.getName()).log(Level.SEVERE, null, ex);
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
