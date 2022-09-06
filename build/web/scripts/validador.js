/* global frmContato */

function validar() {
    //alert('teste');
    let nome = frmContato.tbxNome.value;
    let telefone = frmContato.tbxTelefone.value;
    let telemovel = frmContato.tbxTelemovel.value;
    let email = frmContato.tbxEmail.value;
    if(nome === '') {
        alert("Preencha o campo Nome.");
        frmContato.tbxNome.focus();
        return false;
    } else if(telemovel === '') {
        alert("Preencha o campo Telemóvel.");
        frmContato.tbxTelemovel.focus();
        return false;
    } else if(email === '') {
        alert("Preencha o campo E-mail.");
        frmContato.tbxEmail.focus();
        return false;
    } else {
        document.forms["frmContato"].submit();
    }
    
    
}


