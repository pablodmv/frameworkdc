/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package concreteCommands;

import ejb.ABMContactoLocal;
import entities.Contacto;
import entities.Direccion;
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
public class SearchContact implements Command{

    private Long id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String mobil;
    private String email;
    private List<Direccion> listaDirecciones = new ArrayList<Direccion>();


    public SearchContact(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Direccion> getListaDirecciones() {
        return listaDirecciones;
    }

    public void setListaDirecciones(List<Direccion> listaDirecciones) {
        this.listaDirecciones = listaDirecciones;
    }

    public String getMobil() {
        return mobil;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String outcome= "Fail";

        try{

            if(request.getParameter("idContacto") != null){
                this.id = Long.parseLong(request.getParameter("idContacto"));

                ABMContactoLocal ejbContacto = (ABMContactoLocal) this.lookupABMContactoLocal();

                Contacto contact = ejbContacto.obtener(this.id);

                if(contact !=null){

                    request.setAttribute("nombre", contact.getNombre());
                    request.setAttribute("apellido", contact.getApellido());
                    request.setAttribute("tel", contact.getTelefono());
                    request.setAttribute("email", contact.getEmail());
                    request.setAttribute("movil", contact.getMovil());

                    request.setAttribute("selectId", contact.getId().toString());



                }else{

                }

            }
            


        }catch(Exception ex){
            Logger.getLogger(SearchContact.class.getName()).log(Level.SEVERE, null, ex);
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
