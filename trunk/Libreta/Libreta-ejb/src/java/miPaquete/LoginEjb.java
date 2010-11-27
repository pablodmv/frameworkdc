/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package miPaquete;

import javax.ejb.Stateless;

/**
 *
 * @author Gustavo Leites
 */
@Stateless
public class LoginEjb implements LoginEjbLocal {

    public String verificarUsuario(String login, String pwd) {

        if(login.equals("Pablo") && pwd.equals("Pablo")){
            return "Success";
        }

        return "Fail";

    }
    
    
}
