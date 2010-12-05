/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package searchCommandTest;

/**
 *
 * @author Gustavo Leites
 */
public class isCommandTest {


    public static void main(String args[]) {

        String text1 = "/Bienvenido";
        String text2 = "/paginas/Login.jsp";

        if(text1.endsWith(".jsp")){
            System.out.println("es comando");
        }else{
            System.out.println("No es comando");
        }

    }

}
