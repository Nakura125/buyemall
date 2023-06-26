/**
 * 
 */

function allertLogin(message) {
	document.querySelector("#success").innerHTML=message
}

function validateLogin() {
	document.querySelector("#success").innerHTML=""

	let email = document.getElementById("email").value;
	let password = document.getElementById("password").value;
	let emailRegex = /^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$/;
	
	
	

	if (!email.match(emailRegex)) {
		
		allertLogin("Inserisci un'email valida.");
		return false;
	}
	
	if (password = "") {
		allertLogin("Inserisci la password.");
		return false;
	}
	
	return true;
}


function validateRegister() {
	
	document.querySelector("#success").innerHTML=""
	
	
	let nome = document.getElementById("nome").value
	let cognome = document.getElementById("cognome").value
	let username = document.getElementById("Username").value
	let email = document.getElementById("email").value;
	let password = document.getElementById("password").value;
	let confirmPassword = document.getElementById("passwordCheck").value;
	let emailRegex = /^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$/;


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

	return true;
}
