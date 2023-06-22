/**
 * 
 */

 function typeFilter(checkbox) {
                	var checkboxes = document.querySelectorAll('input[type="checkbox"][name="typeForm"]');

                	  for (var i = 0; i < checkboxes.length; i++) {
                	    var currentCheckbox = checkboxes[i];
                	    
                	    if (currentCheckbox !== checkbox) {
                	      currentCheckbox.checked = false;
                	    }
                	  }
                	  
                	  callRequest();
                	  
                }