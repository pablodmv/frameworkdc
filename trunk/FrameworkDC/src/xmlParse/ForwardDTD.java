/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package xmlParse;

/**
 * Modela a objetos los tags <forward> del archivo mvc.xml
 * @author Gustavo Leites
 */
public class ForwardDTD {

    private String name;
    private String path;

    public ForwardDTD (){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


}
