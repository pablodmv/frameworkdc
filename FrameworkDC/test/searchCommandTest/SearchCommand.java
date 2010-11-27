/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package searchCommandTest;

import xmlParse.CommandDTD;
import xmlParse.ForwardDTD;
import xmlParse.ParseXMLFile;

/**
 *
 * @author Gustavo Leites
 */
public class SearchCommand {

    public static CommandDTD sCommand (String command){

        CommandDTD com = null;

        ParseXMLFile pxml = new ParseXMLFile();
        pxml.parsing("mvcTest.xml","mvc.dtd");

        for(CommandDTD comAux : pxml.getCommandMapping().getListCommand()){

            if(comAux.getPath().equals("/"+command)){
                if(comAux.getForwardTo() != null){
                     System.out.println("forwardTo: "+comAux.getForwardTo());
                }
                if(comAux.getType() != null){
                    System.out.println("type: "+comAux.getType());
                }
                for(ForwardDTD frw : comAux.getListForward()){
                    System.out.println("forward: "+frw.getName()+" - path: " +frw.getPath());
                }
                
                com = comAux;
                return com;
            }
        }
        return com;
    }

    public static void main(String args[]) {

        CommandDTD command = sCommand("LoginSubmit");
        System.out.println("El comando es: " + command.getPath());

    }

}
