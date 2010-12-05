/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package concreteCommands;

import ejb.ABMUsuarioLocal;
import entities.Usuario;
import frameworkp.Command;
import java.io.IOException;
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
public class QueryUser implements Command {

    private String nombre = "";
    private String apellido = "";

    public QueryUser(){
        
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


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String outcome="Fail";

        List<Usuario> listaUsuarios;
        try{

            if(request.getParameter("nombre") != null){
                this.nombre = request.getParameter("nombre");

            }

            if(request.getParameter("apellido") != null){
                this.apellido = request.getParameter("apellido");
            }

            if(this.nombre.equals("") && this.apellido.equals("")){

                ABMUsuarioLocal ejbUsuario = this.lookupABMUsuarioLocal();

                listaUsuarios = ejbUsuario.traerTodos();

                if(listaUsuarios.size() > 0){
                    request.setAttribute("listaUsuarios", listaUsuarios);
                    outcome = "Success";
                    
                }else{
                    request.setAttribute("mensaje", "No se encontraron usuarios bajo los filtros determinados.");
                    outcome="Fail";
                }
                
            }else{
                ABMUsuarioLocal ejbUsuario = this.lookupABMUsuarioLocal();

                listaUsuarios = ejbUsuario.consultar(this.nombre, this.apellido);

                if(listaUsuarios.size() > 0){
                    request.setAttribute("listaUsuarios", listaUsuarios);
                    outcome = "Success";

                }else{
                    request.setAttribute("mensaje", "No se encontraron usuarios bajo los filtros determinados.");
                    outcome="Fail";
                }
            }

            return outcome;

        }catch(Exception ex){
            Logger.getLogger(QueryUser.class.getName()).log(Level.SEVERE, null, ex);
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
