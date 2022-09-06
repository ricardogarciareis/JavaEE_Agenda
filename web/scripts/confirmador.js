/* @param idcon */

function confirmar(idcon) {
    let resposta = confirm("Confirma a eliminação deste contato?");
    if (resposta === true) {
        //alert("Id do utilizador a eliminar: " + idcon);
        window.location.href = "delete?idcon=" + idcon;
    }
}


