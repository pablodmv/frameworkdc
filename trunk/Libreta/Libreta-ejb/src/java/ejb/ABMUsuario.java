/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entities.Bitacora;
import entities.Usuario;
import entities.Credenciales;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ABMUsuario implements ABMUsuarioLocal {

    @EJB
    private LoggerBeanLocal loggerBean;


     @PersistenceContext()
    private EntityManager em;



    @Override
    public String alta(String login, String password, String nombre, String apellido, Date fNacimiento, String rol, String userLogin) {

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
             em.flush();

             //Logueo a Bitacora
             Bitacora bit = new Bitacora();
             bit.setFechaHora(new Date());
             bit.setTipoAccion("altaUsuario");
             bit.setUserLogin(userLogin);

             loggerBean.log(bit);

             return "Success";
        } catch (Exception e) {
            return "Fail";
        }

    }

//    public String getEncoded(String texto, String algoritmo){
//         String output="";
//
//        try {
//
//            byte[] textBytes;
//           textBytes = texto.getBytes("UTF-8");
//
//            MessageDigest md = MessageDigest.getInstance(algoritmo);
//            md.update(textBytes);
//            byte[] codigo = md.digest();
//            output = new String(codigo);
//
//        } catch (UnsupportedEncodingException ex) {
//                Logger.getLogger(ABMUsuario.class.getName()).log(Level.SEVERE, null, ex);
//
//        } catch (NoSuchAlgorithmException ex) {
//            Logger.getLogger(ABMUsuario.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return output;
//
//    }

    @Override
    public void eliminar(Long idUsuario, String userLogin) {
        Usuario user= this.obtener(idUsuario);
        em.remove(user);
        em.flush();

        //Logueo a Bitacora
             Bitacora bit = new Bitacora();
             bit.setFechaHora(new Date());
             bit.setTipoAccion("eliminarUsuario");
             bit.setUserLogin(userLogin);

             loggerBean.log(bit);
    }

    @Override
    public String modificar(Long idUsuario, String nombre, String apellido, Date fNacimiento, String rol,String userLogin) {

        Usuario user = this.obtener(idUsuario);
        user.setNombre(nombre);
        user.setApellido(apellido);
        user.setFechaNacimiento(fNacimiento);
        user.getCredencial().setRol(rol);
        try {
        em.merge(user);
        em.flush();
        
        //Logueo a Bitacora
             Bitacora bit = new Bitacora();
             bit.setFechaHora(new Date());
             bit.setTipoAccion("modificarUsuario");
             bit.setUserLogin(userLogin);

             loggerBean.log(bit);
        return "Success";
        } catch (Exception e) {
            return "Fail";
        }

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
    public List<Usuario> consultar(String nombre, String apellido, String userLogin) {
        String jpl = "SELECT u FROM Usuario u WHERE u.nombre LIKE :nom AND u.apellido LIKE :ape";
        Query q = em.createQuery(jpl);
        q.setParameter("nom", "%"+nombre+"%");
        q.setParameter("ape", "%"+apellido+"%");

        //Logueo a Bitacora
             Bitacora bit = new Bitacora();
             bit.setFechaHora(new Date());
             bit.setTipoAccion("consultarUsuario");
             bit.setUserLogin(userLogin);

             loggerBean.log(bit);

        return (List<Usuario>)q.getResultList();
    }

    @Override
    public List<Usuario> traerTodos(String userLogin) {
         String jpl = "SELECT u FROM Usuario u";
        Query q = em.createQuery(jpl);

        //Logueo a Bitacora
             Bitacora bit = new Bitacora();
             bit.setFechaHora(new Date());
             bit.setTipoAccion("obtenerTodosUsuario");
             bit.setUserLogin(userLogin);

             loggerBean.log(bit);

        return (List<Usuario>)q.getResultList();
    }

    @Override
    public Usuario obtener(String login) {
        String jpl = "Select u FROM Usuario u,Credenciales c WHERE u.CREDENCIAL_ID=c.ID AND c.login=:usuario";
        Query q=em.createNamedQuery(jpl);
        q.setParameter("usuario", login);
        List<Usuario> userlist = (List<Usuario>) q.getResultList();
        return userlist.get(0);

    }
    
  

 
}
