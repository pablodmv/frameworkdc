/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package xmlParse;

import java.util.ArrayList;
import java.util.List;

/**
 * Modela a objeto CommandMapping los tags <command-mappings> del archivo mvc.xml
 * @author Gustavo Leites
 */
public class CommandMappingDTD {


    private List<CommandDTD> listCommand = new ArrayList<CommandDTD>();

    
    public CommandMappingDTD (){
        
    }

    public List<CommandDTD> getListCommand() {
        return listCommand;
    }

    public void setListCommand(List<CommandDTD> listCommand) {
        this.listCommand = listCommand;
    }



}
