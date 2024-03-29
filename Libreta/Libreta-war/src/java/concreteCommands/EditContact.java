/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package concreteCommands;
import ejb.ABMContactoLocal;
import ejb.ABMUsuarioLocal;
import entities.Direccion;
import entities.Usuario;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import frameworkp.Command;
import java.util.Enumeration;
import java.io.IOException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.TipoDireccion;

/**
 *
 * @author pablo
 */
public class EditContact implements Command {
    private String nombre;
    private String apellido;
    private String telefono;
    private String movil;
    private String email;
    private List<Direccion> listaDirecciones = new ArrayList();
    private String userLogin;
    public long idContacto;

    public long getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(long idContacto) {
        this.idContacto = idContacto;
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

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
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


    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String outcome = "Fail";

        try{

            
            this.nombre = request.getParameter("nombre");
            this.apellido = request.getParameter("apellido");
            this.telefono = request.getParameter("tel");
            this.movil = request.getParameter("movil");
            this.email = request.getParameter("email");
            this.userLogin = request.getRemoteUser();
            this.idContacto=Long.parseLong( request.getParameter("selectId"));


            if(!this.nombre.equals("") && !this.apellido.equals("") && !this.telefono.equals("") && !this.movil.equals("") && !this.email.equals("")){

                Enumeration enumeration = request.getParameterNames();

                while (enumeration.hasMoreElements()) {
                    String parameterName = (String) enumeration.nextElement();
//                    System.out.println(parameterName);
//                    System.out.println(request.getParameter(parameterName));
                    if(parameterName.startsWith("calle")){
                        Direccion dir = new Direccion();
                        dir.setCalle(request.getParameter(parameterName));
                    
                        String parameterNum = (String) enumeration.nextElement();
                        if(parameterNum.startsWith("num")){
                            dir.setNumero(request.getParameter(parameterNum));
                        }

                        String parameterTipo = (String) enumeration.nextElement();
                        if(parameterTipo.startsWith("tipo")){

                            String tipo = request.getParameter(parameterTipo);
                            //System.out.println("//"+tipo);
                            if(tipo.equals("Casa")){
                                dir.setTipoDir(TipoDireccion.CASA);
                            }else if(tipo.equals("Trabajo")){
                                dir.setTipoDir(TipoDireccion.TRABAJO);
                            }else{
                                dir.setTipoDir(TipoDireccion.OTRA);
                            }

                        }
                        listaDirecciones.add(dir);
                    }


                }

                ABMUsuarioLocal ejbUsuario = (ABMUsuarioLocal) this.lookupABMUsuarioLocal();
                Usuario usr = ejbUsuario.obtener(userLogin);

                ABMContactoLocal ejbContacto = (ABMContactoLocal) this.lookupABMContactoLocal();
                
                outcome = ejbContacto.modificar(idContacto, nombre, apellido, telefono, movil, email, listaDirecciones, usr, userLogin);

                if(outcome.equals("Success")){
                    request.setAttribute("mensaje", "Contacto Modificado con Exito!");
                }else{
                    request.setAttribute("mensaje", "El Contacto no se pudo Modificar.");
                }

            }else{
                request.setAttribute("mensaje", "Faltan ingresar datos requeridos");
            }

            return outcome;

        }catch(Exception ex){
            Logger.getLogger(AddContact.class.getName()).log(Level.SEVERE, null, ex);
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
