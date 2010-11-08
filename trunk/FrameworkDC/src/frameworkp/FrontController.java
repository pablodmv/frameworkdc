/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package frameworkp;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import xmlParse.CommandDTD;
import xmlParse.ParseXMLFile;


/**
 *
 * @author pablo
 */
public class FrontController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            //Obtenemos el comando de la URL
            String comando = obtenerComando(request.getRequestURL().toString());

            //Buscamos el comando en el archivo mvc.xml, y obtenemos el CommandDTD
            CommandDTD comDTD = sCommand(comando);
            
            if(comDTD != null){
                /*/*TODO- Invocar al metodo createCommand del CommandFactory*/
                
            }
            
        } finally { 
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

private String obtenerComando (String url){

    String[] urlParsed = url.split("/");
    String comandoTemp = "";

    for(int i= 0; i< urlParsed.length;i++){
        comandoTemp = urlParsed[i];
    }

    String[] comando = comandoTemp.split("\\.");

    return comando[0];
}

public CommandDTD sCommand (String command){

        CommandDTD com = null;

        ParseXMLFile pxml = new ParseXMLFile();
        pxml.parsing("mvcTest.xml");

        for(CommandDTD comAux : pxml.getCommandMapping().getListCommand()){

            if(comAux.getPath().equals("/"+command)){
                com = comAux;
                return com;
            }
        }
        return com;
    }


}
