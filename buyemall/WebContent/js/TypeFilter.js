/**
 * 
 */

 function typeFilter(checkbox) {
                	let checkboxes = document.querySelectorAll('input[type="checkbox"][name="typeForm"]');

                	  for (let i = 0; i < checkboxes.length; i++) {
                	    let currentCheckbox = checkboxes[i];
                	    
                	    if (currentCheckbox !== checkbox) {
                	      currentCheckbox.checked = false;
                	    }
                	  }
                	  
                	  callRequest();
                	  
                }