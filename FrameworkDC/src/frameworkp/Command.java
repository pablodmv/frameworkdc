/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package frameworkp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gustavo Leites
 */
public interface Command {

    public String execute (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
