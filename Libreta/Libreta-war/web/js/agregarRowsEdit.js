var cont = 0;

function addRows(cont){
    cont = cont + 1;
    //alert(cont);

    var myTable = document.getElementsByTagName("tbody")[1];

    //Creo la nueva fila
    var row = document.createElement("TR");

    //Creo el td del dir label
    var td1 = document.createElement("TD");
    dirLabel = document.createElement("label");
    dirLabel.innerHTML = "Dirección:";
    td1.appendChild(dirLabel);

    //Creo el td del label calle
    var td2 = document.createElement("TD");
    dirLabel = document.createElement("label");
    dirLabel.innerHTML = "Calle";
    dirLabel.setAttribute("for", "calle"+cont);
    td2.appendChild(dirLabel);

    //Creo el td del input
    var td3 = document.createElement("TD");
    dirLabel = document.createElement("input");
    dirLabel.setAttribute("id", "calle"+cont);
    dirLabel.setAttribute("name", "calle"+cont);
    dirLabel.setAttribute("value", "");
    td3.appendChild(dirLabel);

    //Creo el label de num
    var td4 = document.createElement("TD");
    dirLabel = document.createElement("label");
    dirLabel.innerHTML = "Nº";
    dirLabel.setAttribute("for", "num"+cont);
    td4.appendChild(dirLabel);

    //Creo el input de num
    var td5 = document.createElement("TD");
    dirLabel = document.createElement("input");
    dirLabel.setAttribute("id", "num"+cont);
    dirLabel.setAttribute("name", "num"+cont);
    dirLabel.setAttribute("value", "");
    td5.appendChild(dirLabel);

    //Creo el label de tipo
    var td6 = document.createElement("TD");
    dirLabel = document.createElement("label");
    dirLabel.innerHTML = "Tipo.Dir:";
    dirLabel.setAttribute("for", "cbxTipo"+cont);
    td6.appendChild(dirLabel);


    var td7 = document.createElement("TD");
    dirLabel = document.createElement("select");
    dirLabel.setAttribute("name", "tipo"+cont);
    option1 = document.createElement("option");
    option2 = document.createElement("option");
    option3 = document.createElement("option");

    option1.innerHTML = "Casa";
    option2.innerHTML = "Trabajo";
    option3.innerHTML = "Otro";
    dirLabel.appendChild(option1);
    dirLabel.appendChild(option2);
    dirLabel.appendChild(option3);
    td7.appendChild (dirLabel);

    row.appendChild(td1);
    row.appendChild(td2);
    row.appendChild(td3);
    row.appendChild(td4);
    row.appendChild(td5);
    row.appendChild(td6);
    row.appendChild(td7);

    myTable.appendChild(row);


}




