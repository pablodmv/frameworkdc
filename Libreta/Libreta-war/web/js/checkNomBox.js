
function setNomCheck(){
    if(document.formulario.nombreOp.checked==true){
        //alert("nombre");
        document.formulario.nombre.disabled=false;
        if(document.formulario.getAll.checked == true){
            document.formulario.getAll.checked=false;
        }

    }else{
        document.formulario.nombre.disabled=true;
    }
}



