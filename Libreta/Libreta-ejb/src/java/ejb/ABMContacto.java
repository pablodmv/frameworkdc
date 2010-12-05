/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entities.Contacto;
import entities.Direccion;
import entities.Usuario;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 *
 * @author pablo
 */
@Stateless
public class ABMContacto implements ABMContactoLocal {


       @PersistenceContext()
    private EntityManager em;

  

    @Override
    public String alta(String nombre, String apellido, String telefono, String movil, String email,List<Direccion>listaDir,Usuario user) {
        Contacto contact= new Contacto();
        contact.setNombre(nombre);
        contact.setApellido(apellido);
        contact.setTelefono(telefono);
        contact.setMovil(movil);
        contact.setEmail(email);
        contact.setListDir(listaDir);
        user.setContactos(contact);
        try {
            em.persist(contact);
            em.merge(user);
            return "Success";
        } catch (Exception e) {
            return e.toString();
        }

    }

    @Override
    public Contacto modificar(Contacto c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void eliminar(Contacto c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

 
    
    @Override
   public void eliminar(Long idContacto) {
        Contacto contact= this.obtener(idContacto);
        em.remove(contact);
    }


    @Override
    public Contacto obtener(Long id) {

        try {

            return em.find(Contacto.class, id);

        } catch (Exception e) {
            return null;
        }


    }

    @Override
    public List<Contacto> consultar(String nombre, String apellido) {
        String jpl = "SELECT c FROM Contacto c WHERE c.nombre LIKE :nom AND c.apellido LIKE :ape";
        Query q = em.createQuery(jpl);
        q.setParameter("nom", "%"+nombre+"%");
        q.setParameter("ape", "%"+apellido+"%");
        return (List<Contacto>)q.getResultList();
    }

    @Override
    public List<Contacto> traerTodos() {
         String jpl = "SELECT c FROM Usuario c";
        Query q = em.createQuery(jpl);
        return (List<Contacto>)q.getResultList();
    }
 
}