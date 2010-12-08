/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function validarEmail(valor,control) {

    var filtro=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i
    if (!filtro.test(valor)){
        alert("Email invalido!")
        control.focus()
       
    }
}

function validarFecha(Cadena,control){


{

   if (Cadena)

   {

      borrar = Cadena;

      if ((Cadena.substr(2,1) == "/") && (Cadena.substr(5,1) == "/"))

      {

         for (i=0; i<10; i++)

             {

            if (((Cadena.substr(i,1)<"0") || (Cadena.substr(i,1)>"9")) && (i != 2) && (i != 5))

                        {

               borrar = '';

               break;

                        }

         }

             if (borrar)

             {

                a = Cadena.substr(6,4);

                    m = Cadena.substr(3,2);

                    d = Cadena.substr(0,2);

                    if((a < 1900) || (a > 2050) || (m < 1) || (m > 12) || (d < 1) || (d > 31))

                       borrar = '';

                    else

                    {

                       if((a%4 != 0) && (m == 2) && (d > 28))

                          borrar = ''; // Año no Biciesto y es febrero y el dia es mayor a 28

                           else

                           {

                          if ((((m == 4) || (m == 6) || (m == 9) || (m==11)) && (d>30)) || ((m==2) && (d>29)))

                                 borrar = '';

                           }  // else

                    } // fin else

         } // if (error)

      } // if ((caja.substr(2,1) == "/") && (caja.substr(5,1) == "/"))

          else

             borrar = '';

          if (borrar == '')

             alert('Fecha erronea');
         control.focus()

   } // if (caja)

} // FUNCION

}