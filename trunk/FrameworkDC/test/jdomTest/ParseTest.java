/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jdomTest;

import xmlParse.ParseXMLFile;




/**
 * Ejemplo de proceso del archivo mvc.xml y la obtención de los objetos correspondientes
 * @author Gustavo Leites
 */
public class ParseTest {

    public static void main(String args[]) {
        
        ParseXMLFile pxml = new ParseXMLFile();

        pxml.parsing("mvcTest.xml","mvc.dtd");
        System.out.println(pxml.getCommandMapping().getListCommand().size());
    }

}
