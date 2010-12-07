
function setCheckBox(){

    if(document.formulario.getAll.checked == true ){
        //alert("getAll");
        if(document.formulario.nombreOp.checked==true && document.formulario.apellidoOp.checked==true){
            document.formulario.nombreOp.checked=false;
            document.formulario.nombre.disabled=true;


            document.formulario.apellidoOp.checked=false;
            document.formulario.apellido.disabled=true;
        }else if(document.formulario.nombreOp.checked==true){
            document.formulario.nombreOp.checked=false;
            document.formulario.nombre.disabled=true;
        }else if(document.formulario.apellidoOp.checked==true){
            document.formulario.apellidoOp.checked=false;
            document.formulario.apellido.disabled=true;
        }
        
    }

    
}


