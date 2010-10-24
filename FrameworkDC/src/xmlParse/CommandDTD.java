/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package xmlParse;

import java.util.ArrayList;
import java.util.List;

/**
 * Modela a objeto Command los tags <command> del archivo mvc.xml
 * @author Gustavo Leites
 */
public class CommandDTD {

    private String path;
    private String type;
    private String forwardTo;
    private List<ForwardDTD> listForward = new ArrayList<ForwardDTD>();

    public CommandDTD (){

    }

    public String getForwardTo() {
        return forwardTo;
    }

    public void setForwardTo(String forwardTo) {
        this.forwardTo = forwardTo;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ForwardDTD> getListForward() {
        return listForward;
    }

    public void setListForward(List<ForwardDTD> listForward) {
        this.listForward = listForward;
    }


}
