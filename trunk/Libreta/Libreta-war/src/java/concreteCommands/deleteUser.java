/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package concreteCommands;

import ejb.ABMUsuarioLocal;
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
public class deleteUser implements Command {

    private Long id;
    private String userLogin;


    public deleteUser(){
        
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

            if(!request.getParameter("selectId").equals("")){
                this.id = Long.parseLong(request.getParameter("selectId"));

                ABMUsuarioLocal ejbUsuario = this.lookupABMUsuarioLocal();

                this.userLogin = request.getRemoteUser();
                ejbUsuario.eliminar(this.id,this.userLogin);

                request.setAttribute("mensaje", "El usuario fue eliminado con exito!");

                outcome = "Success";
            }else{
                request.setAttribute("mensaje", "El usuario no fue eliminado!");
                outcome = "Fail";
            }

            return outcome;
            
        }catch(Exception ex){
            Logger.getLogger(deleteUser.class.getName()).log(Level.SEVERE, null, ex);
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
