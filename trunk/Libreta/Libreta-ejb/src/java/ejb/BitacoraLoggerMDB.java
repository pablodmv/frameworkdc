/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entities.Bitacora;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gustavo Leites
 */
@MessageDriven(mappedName = "jms/bitacoraLogger", activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
    })
public class BitacoraLoggerMDB implements MessageListener {

    @PersistenceContext
    private EntityManager em;

    public BitacoraLoggerMDB() {
    }

    @Override
    public void onMessage(Message message) {

        try {
            ObjectMessage om = (ObjectMessage) message;
            Bitacora bitacora = (Bitacora)om.getObject();
            

            em.persist(bitacora);
        } catch (JMSException ex) {
            Logger.getLogger(BitacoraLoggerMDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
