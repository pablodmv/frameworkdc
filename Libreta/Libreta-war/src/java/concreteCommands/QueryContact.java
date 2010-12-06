/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package concreteCommands;

import ejb.ABMContactoLocal;
import entities.Contacto;
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
public class QueryContact implements Command {

    private String nombre = "";
    private String apellido = "";
    
    
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
            if(this.nombre.equals("") && this.apellido.equals("")){
                listaContactos = ejbContacto.traerTodos();
            }else{
                listaContactos = ejbContacto.consultar(nombre, apellido);
            }

            if(listaContactos.size() > 0){
                    request.setAttribute("listaContactos", listaContactos);
                    outcome = "Success";

                }else{
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
}
