
function setCheckBox(){

    if(document.formulario.getAll.checked == true){
        document.formulario.nombreOp.checked=false;
        document.formulario.nombre.disabled=true;
        document.formulario.nombre.eval("");

        document.formulario.apellidoOp.checked=false;
        document.formulario.apellido.disabled=true;
        document.formulario.apellido.eval("");
    }

    
}


