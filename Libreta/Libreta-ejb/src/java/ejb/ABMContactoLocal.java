/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import javax.ejb.Local;
import entities.Contacto;
import entities.Direccion;
import entities.Usuario;
import java.util.List;

/**
 *
 * @author pablo
 */
@Local
public interface ABMContactoLocal {

    public Contacto obtener(Long id);
    public String alta(String nombre, String apellido, String telefono, String movil, String email,List<Direccion>listaDir,Usuario user, String userLogin);
    public String modificar(Long idContacto,String nombre, String apellido, String telefono, String movil, String email,List<Direccion>listaDir,Usuario user, String userLogin);
    public void eliminar(Long id, Usuario user, String userLogin);
    public List<Contacto> consultar(String nombre,String apellido, Usuario user, String userLogin);
    public List<Contacto> traerTodos(Usuario user, String userLogin);
}
