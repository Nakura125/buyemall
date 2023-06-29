/**
 * 
 */

 function genFilter(checkbox) {
	                	console.log(checkbox)
	                	let checkboxes = document.querySelectorAll('input[type="checkbox"][name="generazioneForm"]');

	                	  for (let i = 0; i < checkboxes.length; i++) {
	                	    let currentCheckbox = checkboxes[i];
	                	    
	                	    if (currentCheckbox !== checkbox) {
	                	      currentCheckbox.checked = false;
	                	    }
	                	  }
	                	  
	                	  callRequest();
	                }