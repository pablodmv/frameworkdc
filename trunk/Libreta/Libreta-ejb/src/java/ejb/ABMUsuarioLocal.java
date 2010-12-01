/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import javax.ejb.Local;
import entities.Usuario;
import java.util.Date;
import java.util.List;

/**
 *
 * @author pablo
 */
@Local
public interface ABMUsuarioLocal {
    
    public Usuario obtener(String login);
    public String alta(String login, String password, String nombre, String apellido, Date fNacimiento, String rol);
    public Usuario modificar(Usuario u);
    public void eliminar(Usuario u);
    public List<Usuario> consultar(String apellido);
}
