/**
 * 
 */
function validateLogin() {
	
  let email = document.getElementById("email").value;
  let password = document.getElementById("password").value;
  let emailRegex = /^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$/;


  if (!email.match(emailRegex)) {
    allertLogin("Inserisci un'email valida.");
    return false;
  }
  return true;
}

function allertLogin(message){
	document.querySelector("#success").innerHTML(message)
	
}


function validateRegister() {
var nome = document.getElementById("nome").value
var cognome = document.getElementById("cognome").value
var username = document.getElementById("username").value
var email = document.getElementById("email").value;
  var password = document.getElementById("password").value;
  var confirmPassword = document.getElementById("passwordCheck").value;
  var emailRegex = /^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$/;


 if (nome = "") {
    allertLogin("Inserisci il nome.");
    return false;
  }

 if (cognome = "") {
    allertLogin("Inserisci il cognome.");
    return false;
  }

 if (username = "") {
    allertLogin("Inserisci l'username.");
    return false;
  }

  if (!email.match(emailRegex)) {
    allertLogin("Inserisci un'email valida.");
    return false;
  }
  
  if (password !== confirmPassword) {
    allertLogin("Le password non corrispondono");
    return false;
  }

  return true;
}
 