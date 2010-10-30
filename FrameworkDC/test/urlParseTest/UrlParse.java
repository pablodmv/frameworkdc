/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package urlParseTest;

/**
 *
 * @author Gustavo Leites
 */
public class UrlParse {

    public static void main(String args[]) {

        String url = "http://localhost:8080/<appRoot>/GuardarUsuario.cmd?id=10";

        String[] urlParsed = url.split("/");
        String comandoTemp = "";
        
        for(int i= 0; i< urlParsed.length;i++){
            comandoTemp = urlParsed[i];
        }
        //System.out.println(comandoTemp);

        String[] comandoTemp1 = comandoTemp.split("\\.");
        System.out.println("El comando es: " + comandoTemp1[0]);
    }

}
