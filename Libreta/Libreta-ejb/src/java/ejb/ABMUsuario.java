/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entities.Usuario;
import entities.Credenciales;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author pablo
 */
@Stateless
public class ABMUsuario implements ABMUsuarioLocal {

    public String alta(String login, String password, String nombre, String apellido) {
        Usuario user= new Usuario();
        Credenciales cred = new Credenciales();
        cred.setLogin(login);
        cred.setPassword(password);
        user.setNombre(nombre);
        user.setApellido(apellido);
        user.setCredencial(cred);
        return "Success";

    }

    public List<Usuario> consultar(String apellido) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void eliminar(Usuario u) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Usuario modificar(Usuario u) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Usuario obtener(String login) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
  

 
}