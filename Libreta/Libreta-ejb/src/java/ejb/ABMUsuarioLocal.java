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
    
    public Usuario obtener(Long id);
    public Usuario obtener(String login);
    public String alta(String login, String password, String nombre, String apellido, Date fNacimiento, String rol, String userLogin);
    public String modificar(Long idUsuario, String nombre, String apellido, Date fNacimiento, String rol, String userLogin);
    public void eliminar(Long id,String userLogin);
    public List<Usuario> consultar(String nombre, String apellido, String userLogin);
    public List<Usuario> traerTodos(String userLogin);
}
