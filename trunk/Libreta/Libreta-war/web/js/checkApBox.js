function setApCheck(){
    if(document.formulario.apellidoOp.checked==true){
        //alert("apellido");
        document.formulario.apellido.disabled=false;
        if(document.formulario.getAll.checked == true){
            document.formulario.getAll.checked=false;
        }

    }else{
        document.formulario.apellido.disabled=true;
    }
}

