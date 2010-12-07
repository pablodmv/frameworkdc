/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package concreteCommands;

import ejb.ABMContactoLocal;
import ejb.ABMUsuarioLocal;
import entities.Usuario;
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

/**
 *
 * @author Gustavo Leites
 */
public class DeleteContact implements Command {


    private Long id;
    private String userLogin;
    private Usuario User;

    public Usuario getUser() {
        return User;
    }

    public void setUser(Usuario User) {
        this.User = User;
    }

   


    public DeleteContact(){

    }

    public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }




    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String outcome= "";

        try{
            this.userLogin = request.getRemoteUser();

            if(request.getParameter("selectId") != null){
                this.id = Long.parseLong(request.getParameter("selectId"));
                ABMContactoLocal ejbContacto = (ABMContactoLocal) this.lookupABMContactoLocal();
                ABMUsuarioLocal ejbUsuario = (ABMUsuarioLocal) this.lookupABMUsuarioLocal();
                
                Usuario objUser = ejbUsuario.obtener(userLogin);

                ejbContacto.eliminar(this.id,objUser,this.userLogin);

                request.setAttribute("mensaje", "El contacto fue eliminado con exito!");

                outcome = "Success";
            }else{
                request.setAttribute("mensaje", "El contacto no fue eliminado!");
                outcome = "Fail";
            }


            return outcome;

        }catch(Exception ex){
            Logger.getLogger(DeleteContact.class.getName()).log(Level.SEVERE, null, ex);
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
