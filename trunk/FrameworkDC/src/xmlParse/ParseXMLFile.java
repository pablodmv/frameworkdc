/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package xmlParse;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 *
 * @author Gustavo Leites
 */
public class ParseXMLFile {

    private final String ATT_PATH = "path";
    private final String ATT_FORWARDTO = "forwardTo";
    private final String ATT_TYPE = "type";
    private final String ATT_NAME = "name";
    private final String TAG_CMD_MAP = "command-mappings";

    private CommandMappingDTD commandMapping = new CommandMappingDTD();


    public ParseXMLFile (){

    }

    public CommandMappingDTD getCommandMapping() {
        return commandMapping;
    }

    public void setCommandMapping(CommandMappingDTD commandMapping) {
        this.commandMapping = commandMapping;
    }


    public void parsing (String xmlFile){

        SAXBuilder builder = new SAXBuilder (true);
        Document doc;

        try {
            doc = builder.build(new FileInputStream(java.lang.System.getProperty("user.dir")+"/test/jdomTest/"+xmlFile));
            System.out.println(java.lang.System.getProperty("user.dir"));
            Element root = doc.getRootElement ();
            root.getName ();
            Element commMappingsXML = root.getChild(this.TAG_CMD_MAP);

            //Obtengo la lista de comandos
            List<Element> listaComandos = commMappingsXML.getChildren();

            for (int i=0; i < listaComandos.size(); i++){

                //Obtengo los atributos del comando
                List<Attribute> listaAttr = listaComandos.get(i).getAttributes();

                CommandDTD cmd = new CommandDTD();
                cmdAttribute(cmd,listaAttr);
                
                //Obtengo la lista de forwards
                List<Element> listaForward = listaComandos.get(i).getChildren();

                for(int q=0; q < listaForward.size(); q++){

                    //Obtengo los atributos de forward
                    listaAttr = listaForward.get(q).getAttributes();

                    ForwardDTD frw = new ForwardDTD();
                    frwAttribute(frw,listaAttr);

                    //Asigno el forward al comando correspondiente
                    cmd.getListForward().add(frw);
                }

                this.commandMapping.getListCommand().add(cmd);

            }

        } catch (JDOMException ex) {
            Logger.getLogger(ParseXMLFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ParseXMLFile.class.getName()).log(Level.SEVERE, null, ex);
        }


    }


    private void cmdAttribute (CommandDTD cmd, List<Attribute> listaAtributos){

        for(int j=0; j < listaAtributos.size();j++){

            if(listaAtributos.get(j).getName().equals(this.ATT_PATH)){
                cmd.setPath(listaAtributos.get(j).getValue());
            }else if(listaAtributos.get(j).getName().equals(this.ATT_FORWARDTO)){
                cmd.setForwardTo(listaAtributos.get(j).getValue());
            }else if(listaAtributos.get(j).getName().equals(this.ATT_TYPE)){
                cmd.setType(listaAtributos.get(j).getValue());
            }

        }
    }


    private void frwAttribute (ForwardDTD frw,List<Attribute> listaAtributos){

        for(int i=0; i < listaAtributos.size(); i++){

            if(listaAtributos.get(i).getName().equals(this.ATT_PATH)){
                frw.setPath(listaAtributos.get(i).getValue());
            }else if(listaAtributos.get(i).getName().equals(this.ATT_NAME)){
                frw.setName(listaAtributos.get(i).getValue());
            }

        }

    }


}
