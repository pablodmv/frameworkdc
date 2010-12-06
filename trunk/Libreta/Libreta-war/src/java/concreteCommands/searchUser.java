/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package concreteCommands;

import ejb.ABMUsuarioLocal;
import entities.Usuario;
import frameworkp.Command;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class searchUser implements Command {

    private Long id;
    private String nombre;
    private String apellido;
    private String login;

    public searchUser(){
        
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String outcome = "";
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            if(!request.getParameter("idUsuario").equals("")){
                this.id = Long.parseLong(request.getParameter("idUsuario"));

                ABMUsuarioLocal ejbUsuario = this.lookupABMUsuarioLocal();

                Usuario usr = ejbUsuario.obtener(this.id);
                
                if(usr != null){
                
                    //El usuario fue obtenido con exito
                    Date fecha=usr.getFechaNacimiento();

                    request.setAttribute("nombre", usr.getNombre());
                    request.setAttribute("apellido", usr.getApellido());
                    request.setAttribute("usuario", usr.getCredencial().getLogin());
                    request.setAttribute("rol", usr.getCredencial().getRol().toString());
                    request.setAttribute("selectId", usr.getId().toString());
                    request.setAttribute("fechaNacimiento", df.format(fecha));
                    String Rol=usr.getCredencial().getRol().toString();

                    //Me fijo el rol y lo paso por parametro para el combobox
                    if (Rol.equalsIgnoreCase("Administrador")) {
                        request.setAttribute("admin", true);
                    
                    }
                    if (Rol.equalsIgnoreCase("Usuario")) {
                        request.setAttribute("user", true);
                    }
                    if (Rol.equalsIgnoreCase("")) {
                        request.setAttribute("default", true);
                    }





                    if(request.getParameter("action").equals("delete")){

                        outcome = "SuccessDelete";
                    }else{
                        outcome = "SuccessEdit";
                    }
                }else{// No se obtuvo el usuario

                    request.setAttribute("mensaje", "El usuario no existe!");
                    if(request.getParameter("action").equals("delete")){

                        outcome = "FailDelete";
                    }else{
                        outcome = "FailEdit";
                    }
                }
            }
            
            return outcome;
        
        } catch (Exception ex) {
            Logger.getLogger(searchUser.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("mensaje", "Dato no valido!");
        }

        return outcome;
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
