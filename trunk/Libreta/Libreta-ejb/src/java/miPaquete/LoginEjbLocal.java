/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package miPaquete;

import javax.ejb.Local;

/**
 *
 * @author Gustavo Leites
 */
@Local
public interface LoginEjbLocal {

    String verificarUsuario(String login, String pwd);
    
}
