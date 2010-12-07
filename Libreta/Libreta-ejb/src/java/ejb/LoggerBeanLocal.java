/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import javax.ejb.Local;

/**
 *
 * @author Gustavo Leites
 */
@Local
public interface LoggerBeanLocal {

    public void log(Object messageData);
    
}
