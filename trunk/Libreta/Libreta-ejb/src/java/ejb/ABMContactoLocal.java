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
    public String alta(String nombre, String apellido, String telefono, String movil, String email,List<Direccion>listaDir,Usuario user);
    public Contacto modificar(Contacto c);
    public void eliminar(Long id);
    public List<Contacto> consultar(String nombre,String apellido);
    public List<Contacto> traerTodos();
}
