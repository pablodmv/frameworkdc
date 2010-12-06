/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package concreteCommands;

import frameworkp.Command;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pablo
 */
public class logoutUser implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         request.getSession().invalidate();
         return "Success";
    }

}
