/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package concreteCommands;

import ejb.ABMUsuarioLocal;
import frameworkp.Command;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pablo
 */
public class editUser implements Command {



    private Long id;
    private String nombre;
    private String apellido;
    private Date fnac;
    private String rol;
    private String userLogin;

    public editUser(){

    }

    

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
              String outcome = "Fail";
             
      try {
            this.id= Long.parseLong( request.getParameter("selectId"));
            this.nombre = request.getParameter("nombre");
            this.apellido = request.getParameter("apellido");
            this.rol=request.getParameter("rol");

             DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            if(!request.getParameter("fNac").equals("") && !this.nombre.equals("") && !this.apellido.equals("") && !this.rol.equals("")){

             this.fnac = df.parse(request.getParameter("fNac"));
                rol = request.getParameter("rol");
                Context ctx = new InitialContext();
                ABMUsuarioLocal ejbUsuario = (ABMUsuarioLocal) ctx.lookup("java:comp/env/ABMUsuario");

                this.userLogin = request.getRemoteUser();
                outcome = ejbUsuario.modificar(id, nombre, apellido, fnac, rol,this.userLogin);

                if(outcome.equals("Success")){
                    request.setAttribute("mensaje", "Usuario actualizado con Exito!");
                }else{
                    request.setAttribute("mensaje", "El Usuario no se pudo Actualizar.");
                }
            }else{
                request.setAttribute("mensaje", "Faltan ingresar datos requeridos");
            }

            return outcome;

        } catch (ParseException ex) {
            Logger.getLogger(addUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ComandoConcreto.class.getName()).log(Level.SEVERE, "No se pudo encontrar el EJB", ex);
        }

      return "";
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

            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet updateUser</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updateUser at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
        } finally { 
            out.close();
        }
    }

    private ABMUsuarioLocal lookupABMUsuarioLocal() {
        try {
            Context c = new InitialContext();
            return (ABMUsuarioLocal) c.lookup("java:comp/env/ABMUsuario");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
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
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        processRequest(request, response);
//    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        processRequest(request, response);
//    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>



}
