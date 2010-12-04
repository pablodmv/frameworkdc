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



    @Override
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

        try {
            //em.persist(cred);
             em.persist(user);
             return "Success";
        } catch (Exception e) {
            return "Fail";
        }

    }


    @Override
    public void eliminar(Long idUsuario) {
        Usuario user= this.obtener(idUsuario);
        em.remove(user);
    }

    @Override
    public Usuario modificar(Usuario u) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

//    public Usuario obtener(String login) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }

    @Override
    public Usuario obtener(Long id) {

        try {

            return em.find(Usuario.class, id);

        } catch (Exception e) {
            return null;
        }


    }

    @Override
    public List<Usuario> consultar(String nombre, String apellido) {
        String jpl = "SELECT u FROM Usuario u WHERE u.nombre LIKE :nom AND u.apellido LIKE :ape";
        Query q = em.createQuery(jpl);
        q.setParameter("nom", "%"+nombre+"%");
        q.setParameter("ape", "%"+apellido+"%");
        return (List<Usuario>)q.getResultList();
    }

    @Override
    public List<Usuario> traerTodos() {
         String jpl = "SELECT u FROM Usuario u";
        Query q = em.createQuery(jpl);
        return (List<Usuario>)q.getResultList();
    }
    
  

 
}
