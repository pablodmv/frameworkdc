/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jdomTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import xmlParse.CommandDTD;
import xmlParse.ForwardDTD;


/**
 * Ejemplo de proceso del archivo mvc.xml y la obtención de los objetos correspondientes
 * @author Gustavo Leites
 */
public class ParseTest {

    public static void main(String args[]) {
        SAXBuilder builder = new SAXBuilder (true);
        Document doc;
        try {
            doc = builder.build(new FileInputStream(java.lang.System.getProperty("user.dir")+"/test/jdomTest/mvcTest.xml"));
            
            Element root = doc.getRootElement ();
            root.getName ();
            Element commMappingsXML = root.getChild("command-mappings");

            List<Element> listaComandos = commMappingsXML.getChildren();
            System.out.println(root.getName());
            System.out.println(listaComandos.size());

            for (int i=0; i < listaComandos.size(); i++){

                //Obtengo los atributos del tag command
                List<Attribute> listaAttr = listaComandos.get(i).getAttributes();

                CommandDTD cmd = new CommandDTD();
                //Colocar en un método diferente que obtenga los atributos de los tags
                
                for(int j=0; j < listaAttr.size();j++){

                    if(listaAttr.get(j).getName().equals("path")){
                        cmd.setPath(listaAttr.get(j).getValue());
                    }else if(listaAttr.get(j).getName().equals("forwardTo")){
                        cmd.setForwardTo(listaAttr.get(j).getValue());
                    }else if(listaAttr.get(j).getName().equals("type")){
                        cmd.setType(listaAttr.get(j).getValue());
                    }

                }
                System.out.println(cmd.getPath()+" - " + cmd.getForwardTo() +" - " + cmd.getType());

                //Obtengo los tag forward
                System.out.println(listaComandos.get(i).getChildren().size());

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
            Logger.getLogger(ParseTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ParseTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
