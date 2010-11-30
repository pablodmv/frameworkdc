/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import javax.ejb.Local;
import entities.Usuario;
import java.util.List;

/**
 *
 * @author pablo
 */
@Local
public interface ABMUsuarioLocal {
    
    Usuario obtener(String login);
    String alta(String login, String password, String nombre, String apellido);
    Usuario modificar(Usuario u);
    void eliminar(Usuario u);
    List<Usuario> consultar(String apellido);
}
