
package frameworkp;

/**
 *
 * @author Gustavo Leites
 */
public class CommandFactory {

    public static Command createCommand(String commandName) throws ClassNotFoundException, InstantiationException, IllegalAccessException{

        return (Command)Thread.currentThread().getContextClassLoader().loadClass(commandName).newInstance();
    }
}
