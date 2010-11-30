/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package concreteCommands;


import frameworkp.Command;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import miPaquete.LoginEjbLocal;

/**
 *
 * @author Gustavo Leites
 */
public class ComandoConcreto implements Command {


    private String login ;
    private String pwd;

    public ComandoConcreto (){
    
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


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            this.login = request.getParameter("usr");
            this.pwd = request.getParameter("pwd");
            Context ctx = new InitialContext();
            LoginEjbLocal miEjb = (LoginEjbLocal) ctx.lookup("java:comp/env/LoginEjb");

            request.setAttribute("usuario", this.login);
            return miEjb.verificarUsuario(login, pwd);

        } catch (NamingException ex) {
            Logger.getLogger(ComandoConcreto.class.getName()).log(Level.SEVERE, "No se pudo encontrar el EJB", ex);
        }

        return "";
    }

    private LoginEjbLocal lookupLoginEjbLocal() {
        try {
            Context c = new InitialContext();
            return (LoginEjbLocal) c.lookup("java:comp/env/LoginEjb");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }





}
