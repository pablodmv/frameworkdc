/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package concreteCommands;

import ejb.ABMContactoLocal;
import ejb.ABMUsuarioLocal;
import entities.Contacto;
import entities.Usuario;
import frameworkp.Command;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gustavo Leites
 */
public class QueryContact implements Command {

    private String nombre = "";
    private String apellido = "";
    private String userLogin;
    private Usuario User;

    public Usuario getUser() {
        return User;
    }

    public void setUser(Usuario User) {
        this.User = User;
    }
    
    
    public QueryContact(){
        
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }



    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String outcome="Fail";


        try{


            if(request.getParameter("nombre") != null){
                this.nombre = request.getParameter("nombre");

            }

            if(request.getParameter("apellido") != null){
                this.apellido = request.getParameter("apellido");
            }

            List<Contacto> listaContactos;
            ABMContactoLocal ejbContacto = (ABMContactoLocal) this.lookupABMContactoLocal();
            ABMUsuarioLocal ejbUsuario = (ABMUsuarioLocal) this.lookupABMUsuarioLocal();
            this.userLogin = request.getRemoteUser();
            User = ejbUsuario.obtener(userLogin);

            if(this.nombre.equals("") && this.apellido.equals("")){
                listaContactos = ejbContacto.traerTodos(User, userLogin);
            }else{
                listaContactos = ejbContacto.consultar(nombre, apellido, User, userLogin);
            }
            //System.out.println("QueryContact execute");
            if(listaContactos.size() > 0){
                
                    request.setAttribute("listaContactos", listaContactos);
                    outcome = "Success";

            }else{
                System.out.println("else - QueryContact execute");
                    request.setAttribute("mensaje", "No se encontraron contactos bajo los filtros determinados.");
                    outcome="Fail";
            }

            return outcome;

        }catch(Exception ex){
            Logger.getLogger(QueryContact.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "";
    }

    private ABMContactoLocal lookupABMContactoLocal() {
        try {
            Context c = new InitialContext();
            return (ABMContactoLocal) c.lookup("java:comp/env/ABMContacto");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
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
