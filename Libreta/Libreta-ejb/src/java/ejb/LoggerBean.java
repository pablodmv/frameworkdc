/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 *
 * @author Gustavo Leites
 */
@Stateless
public class LoggerBean implements LoggerBeanLocal {
    
    @Resource(name = "jms/bitacoraLogger")
    private Queue bitacoraLogger;

    @Resource(name = "jms/bitacoraLoggerFactory")
    private ConnectionFactory bitacoraLoggerFactory;


    @Override
    public void log(Object messageData) {
        try {
            sendJMSMessageToBitacoraLogger(messageData);
        } catch (JMSException ex) {
            Logger.getLogger(LoggerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Message createJMSMessageForjmsBitacoraLogger(Session session, Object messageData) throws JMSException {
        // TODO create and populate message to send
        ObjectMessage om = session.createObjectMessage();
        om.setObject((Serializable) messageData);
        //TextMessage tm = session.createTextMessage();
        //tm.setText(messageData.toString());
        return om;
    }

    private void sendJMSMessageToBitacoraLogger(Object messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = bitacoraLoggerFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(bitacoraLogger);
            messageProducer.send(createJMSMessageForjmsBitacoraLogger(session, messageData));
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    
 
}
