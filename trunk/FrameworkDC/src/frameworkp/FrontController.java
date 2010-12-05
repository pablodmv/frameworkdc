/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package frameworkp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import xmlParse.CommandDTD;
import xmlParse.ForwardDTD;
import xmlParse.ParseXMLFile;


/**
 *
 * @author pablo
 */
public class FrontController extends HttpServlet {

    private ServletContext sc;
    private RequestDispatcher dispatcher;

    @Override
     public void init(ServletConfig config) throws ServletException{
        sc = config.getServletContext();
     }
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
            this.dispatcher = navigator(comDTD,request,response);

            this.dispatcher.forward(request, response);
            
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

// Obtiene el comando de una url
private String obtenerComando (String url){

    String[] urlParsed = url.split("/");
    String comandoTemp = "";

    for(int i= 0; i< urlParsed.length;i++){
        comandoTemp = urlParsed[i];
    }

    String[] comando = comandoTemp.split("\\.");
//System.out.println("El comando es"+comando[0]);
    return comando[0];
}

//Busca el comando en el xml parseado.
private CommandDTD sCommand (String command){

        CommandDTD com = null;

        ParseXMLFile pxml = new ParseXMLFile();
//        System.out.println("EL PATH: "+this.sc.getRealPath("/WEB-INF/mvc.xml"));
//
//        System.out.println("EL PATH dtd: "+this.sc.getRealPath("/WEB-INF/mvc.dtd"));
        pxml.parsing(this.sc.getRealPath("/WEB-INF/mvc.xml"),"file:"+this.sc.getRealPath("/WEB-INF/mvc.dtd"));
        for(CommandDTD comAux : pxml.getCommandMapping().getListCommand()){

            if(comAux.getPath().equals("/"+command)){
                com = comAux;
                return com;
            }
        }
        return com;
    }

//Verifica si el parametro text es un comando o un pagina jsp
private Boolean isPage(String text){
    
    return text.endsWith(".jsp");
    
}

//Es el encargado de determinar la navegacion de las paginas.
private RequestDispatcher navigator(CommandDTD comDTD, HttpServletRequest request, HttpServletResponse response){

    //System.out.println("Metodo navigator");
    RequestDispatcher reqDis = null;
    if(comDTD != null){
            
                try {
                    if (comDTD.getForwardTo() != null) {
                        if (isPage(comDTD.getForwardTo())) {
                            //System.out.println("Es pagina");
                            return sc.getRequestDispatcher(comDTD.getForwardTo());
                        } else {
                            //System.out.println("No es pagina");
                            return navigator(sCommand(comDTD.getForwardTo()),request, response);
                        }
                    } else {
                        //Generamos la instacia concreta del comando, cuyo nombre se encuentra en
                        //la variable comDTD en el atributo type de la clase CommandDTD
                        Command currentCommand = CommandFactory.createCommand(comDTD.getType());
                        String output = currentCommand.execute(request, response);
                        //Busco en la lista de ForwardDTD el name que coincida con la variable output
                        //para luego obtener el path a realizar el forward correspondiente.
                        boolean find = false;
                        int cont = 0;
                        ForwardDTD currentForward = null;
                        while ((cont < comDTD.getListForward().size()) && !find) {
                            if (comDTD.getListForward().get(cont).getName().equals(output)) {
                                currentForward = comDTD.getListForward().get(cont);
                                find = true;
                            }
                            cont += 1;
                        }
                        if (find) {
                            if (isPage(currentForward.getPath())) {
                                return sc.getRequestDispatcher(currentForward.getPath());
                            } else {
                                return navigator(sCommand(currentForward.getPath()), request, response);
                            }
                        }
                    }
                } catch (ServletException ex) {
                    Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        return reqDis;
    }

}
