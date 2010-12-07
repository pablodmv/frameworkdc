/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entities.Bitacora;
import entities.Contacto;
import entities.Direccion;
import entities.Usuario;
import java.util.Date;

import java.util.List;
import javax.ejb.EJB;
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


    @EJB
    private LoggerBeanLocal loggerBean;


    @PersistenceContext()
    private EntityManager em;

  

    @Override
    public String alta(String nombre, String apellido, String telefono, String movil, String email,List<Direccion>listaDir,Usuario user, String userLogin) {
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
            em.flush();

            //Logueo a Bitacora
             Bitacora bit = new Bitacora();
             bit.setFechaHora(new Date());
             bit.setTipoAccion("altaContacto");
             bit.setUserLogin(userLogin);

             loggerBean.log(bit);

            return "Success";
        } catch (Exception e) {
            return e.toString();
        }

    }

    @Override
    public String modificar(Long idContacto, String nombre, String apellido, String telefono, String movil, String email,List<Direccion>listaDir,Usuario user, String userLogin) {
       Contacto contact= this.obtener(idContacto);
        contact.setNombre(nombre);
        contact.setApellido(apellido);
        contact.setTelefono(telefono);
        contact.setMovil(movil);
        contact.setEmail(email);
        //user.setContactos(contact);
        List<Direccion> direcciones = contact.getDireccion();
        
        for (int i = 0; i < direcciones.size(); i++) {
            
            for (int j = 0; j < listaDir.size(); j++) {

                if (!direcciones.get(i).getCalle().equalsIgnoreCase(listaDir.get(j).getCalle())) {
                    direcciones.get(i).setCalle(listaDir.get(j).getCalle());
                }
                if (!direcciones.get(i).getNumero().equals(listaDir.get(j).getNumero())) {
                    direcciones.get(i).setNumero(listaDir.get(j).getNumero());
                }
                if (!direcciones.get(i).getTipoDir().equals(listaDir.get(j).getTipoDir())) {
                    direcciones.get(i).setTipoDir(listaDir.get(j).getTipoDir());
                }
            }

         }
        contact.setListDir(direcciones);



        try {
            em.merge(contact);
          //  em.merge(user);
            em.flush();

            //Logueo a Bitacora
             Bitacora bit = new Bitacora();
             bit.setFechaHora(new Date());
             bit.setTipoAccion("Modificar Contacto");
             bit.setUserLogin(userLogin);

             loggerBean.log(bit);

            return "Success";
        } catch (Exception e) {
            return e.toString();
        }
    }

  
    @Override
   public void eliminar(Long idContacto, Usuario user, String userLogin) {
        try {
            Contacto contact= this.obtener(idContacto);
            user.getContactos().remove(contact);
            contact.setListDir(null);
            em.merge(contact);
            em.merge(user);
            em.remove(contact);
            em.flush();

            //Logueo a Bitacora
             Bitacora bit = new Bitacora();
             bit.setFechaHora(new Date());
             bit.setTipoAccion("eliminarContacto");
             bit.setUserLogin(userLogin);

             loggerBean.log(bit);

        } catch (Exception e) {
            e.toString();
        }


    }


    @Override
    public Contacto obtener(Long id) {

        try {

            return em.find(Contacto.class, id);

        } catch (Exception e) {
            return null;
        }


    }
// String jpl = "Select u FROM Usuario u, Credenciales c WHERE c=u.credencial AND c.Login=:user";
    @Override
    public List<Contacto> consultar(String nombre, String apellido, Usuario user, String userLogin) {
        //String jpl = "SELECT c FROM Contacto c WHERE c.nombre LIKE :nom AND c.apellido LIKE :ape";
        String jpl="SELECT c FROM Contacto c, Usuario u WHERE c=u.contactos AND u.id=:idUsuario AND c.nombre LIKE :nom AND c.apellido LIKE :ape";
        Query q = em.createQuery(jpl);
        q.setParameter("nom", "%"+nombre+"%");
        q.setParameter("ape", "%"+apellido+"%");
        q.setParameter("idUsuario", user.getId());

        //Logueo a Bitacora
             Bitacora bit = new Bitacora();
             bit.setFechaHora(new Date());
             bit.setTipoAccion("consultarContacto");
             bit.setUserLogin(userLogin);

             loggerBean.log(bit);

        return (List<Contacto>)q.getResultList();
    }

    @Override
    public List<Contacto> traerTodos(Usuario user, String userLogin) {

        //Logueo a Bitacora
             Bitacora bit = new Bitacora();
             bit.setFechaHora(new Date());
             bit.setTipoAccion("obtenerTodosContactos");
             bit.setUserLogin(userLogin);

             loggerBean.log(bit);
             return user.getContactos();


    }
 
}
