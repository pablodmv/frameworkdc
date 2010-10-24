/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package xmlParse;

import java.io.File;
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

    private CommandMappingDTD commandMapping;


    public ParseXMLFile (){

    }

    public CommandMappingDTD getCommandMapping() {
        return commandMapping;
    }

    public void setCommandMapping(CommandMappingDTD commandMapping) {
        this.commandMapping = commandMapping;
    }


    public void parsing (File xmlFile){

        SAXBuilder builder = new SAXBuilder (true);
        Document doc;

        try {
            doc = builder.build(new FileInputStream(java.lang.System.getProperty("user.dir")+"/test/jdomTest/"+xmlFile));

            Element root = doc.getRootElement ();
            root.getName ();
            Element commMappingsXML = root.getChild(this.TAG_CMD_MAP);

            List<Element> listaComandos = commMappingsXML.getChildren();

            for (int i=0; i < listaComandos.size(); i++){

                //Obtengo los atributos del tag command
                List<Attribute> listaAttr = listaComandos.get(i).getAttributes();

                CommandDTD cmd = cmdAttribute(listaAttr);
                
                //Obtengo los tag forward
                List<Element> listaForward = listaComandos.get(i).getChildren();

                ForwardDTD frw = new ForwardDTD();

                for(int q=0; q < listaForward.size(); q++){

                    listaAttr = listaForward.get(q).getAttributes();

                    for(int y=0; y < listaAttr.size(); y++){

                        if(listaAttr.get(y).getName().equals("path")){
                            frw.setPath(listaAttr.get(y).getValue());
                        }else if(listaAttr.get(y).getName().equals("name")){
                            frw.setName(listaAttr.get(y).getValue());
                        }

                    }
                    System.out.println(frw.getName() + "-" + frw.getPath());
                }
            }

        } catch (JDOMException ex) {
            Logger.getLogger(ParseXMLFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ParseXMLFile.class.getName()).log(Level.SEVERE, null, ex);
        }


    }


    private CommandDTD cmdAttribute (List<Attribute> listaAtributos){

        CommandDTD cmd = new CommandDTD();

        for(int j=0; j < listaAtributos.size();j++){

            if(listaAtributos.get(j).getName().equals(this.ATT_PATH)){
                cmd.setPath(listaAtributos.get(j).getValue());
            }else if(listaAtributos.get(j).getName().equals(this.ATT_FORWARDTO)){
                cmd.setForwardTo(listaAtributos.get(j).getValue());
            }else if(listaAtributos.get(j).getName().equals(this.ATT_TYPE)){
                cmd.setType(listaAtributos.get(j).getValue());
            }

        }
        return cmd;

    }




}
