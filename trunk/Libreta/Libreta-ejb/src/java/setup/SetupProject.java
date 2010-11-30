///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package setup;
//
//import javax.ejb.Stateless;
//import ejb.ABMUsuario;
//import javax.ejb.EJB;
//import javax.annotation.security.DeclareRoles;
//import javax.annotation.security.RunAs;
//
///**
// *
// * @author pablo
// */
//@DeclareRoles({"ADMINISTRADOR"})
//@RunAs("ADMINISTRADOR")
//@Stateless
//public class SetupProject implements SetupProjectLocal {
//
//    @EJB
//    private ABMUsuario abmUsuario;
//
//    public void setup() {
//
//    abmUsuario.alta("usuario", "usuario", "Usuario", "Prueba");
//
//
//
//    }
//
//     Add business logic below. (Right-click in editor and choose
//     "Insert Code > Add Business Method")
//
//}
