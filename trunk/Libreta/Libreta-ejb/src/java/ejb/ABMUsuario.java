/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entities.Usuario;
import entities.Contacto;
import entities.Direccion;
import entities.Credenciales;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.TipoDireccion;

/**
 *
 * @author pablo
 */
@Stateless
public class ABMUsuario implements ABMUsuarioLocal {


     @PersistenceContext()
    private EntityManager em;



    public String alta(String login, String password, String nombre, String apellido, Date fNacimiento, String rol) {
        Usuario user= new Usuario();
        Credenciales cred = new Credenciales();
        cred.setLogin(login);
        cred.setPassword(password);
        cred.setRol(rol);
        user.setNombre(nombre);
        user.setApellido(apellido);
        user.setFechaNacimiento(fNacimiento);
        user.setCredencial(cred);

        //TODO:Prueba de guardar contacto ##### Borrar
        //TODO: Gustavo, esto hay que borrarlo junto con los import de contacto y direccion

        String cNombre="Pablo";
        String cApellido="Martinez";
        String cTelefono="098124806";
        String cMovil="09999999";
        String cEmail="pablodmv@gmail.com";
        Contacto contact=new Contacto();
        Direccion dir = new Direccion();
        dir.setCalle("Cuareim");
        dir.setNumero("2556");
        dir.setTipoDir(TipoDireccion.CASA);
        contact.setNombre(cNombre);
        contact.setApellido(cApellido);
        contact.setTelefono(cTelefono);
        contact.setMovil(cMovil);
        contact.setEmail(cEmail);
        contact.setDireccion(dir);
        user.setContactos(contact);
        List<Usuario> pepe = this.consultar("a", "a");

        if (!pepe.isEmpty() ) {
        Usuario prueba= this.obtener(pepe.get(0).getId());
        }
        


        //Fin prueba guardar contacto ### Borrar


        try {
            //em.persist(cred);
             em.persist(user);
             return "Success";
        } catch (Exception e) {
            return "Fail";
        }

    }


    public void eliminar(Long idUsuario) {
        Usuario user= this.obtener(idUsuario);
        em.remove(user);
    }

    public Usuario modificar(Usuario u) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

//    public Usuario obtener(String login) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }

    public Usuario obtener(Long id) {

        try {

            return em.find(Usuario.class, id);

        } catch (Exception e) {
            return null;
        }


    }

    public List<Usuario> consultar(String nombre, String apellido) {
        String jpl = "SELECT u FROM Usuario u WHERE u.nombre LIKE :nom AND u.apellido LIKE :ape";
        Query q = em.createQuery(jpl);
        q.setParameter("nom", "%"+nombre+"%");
        q.setParameter("ape", "%"+apellido+"%");
        return (List<Usuario>)q.getResultList();
    }

    public List<Usuario> traerTodos() {
         String jpl = "SELECT u FROM Usuario u";
        Query q = em.createQuery(jpl);
        return (List<Usuario>)q.getResultList();
    }
    
  

 
}
