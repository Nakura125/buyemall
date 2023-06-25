/**
 * 
 */

 function callRequest(page){
        			console.log(page)
        			let params=""
        			if(page !=undefined){
        				params+="pageForm="+page+"&";
        			}else{
        				params+="pageForm=1"+"&";
        			}
        			let checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
        			for (let i = 0; i < checkboxes.length; i++) {
        				let currentCheckbox = checkboxes[i];
        				if(currentCheckbox.value!="null")
	                    	params += currentCheckbox.name+'=' + currentCheckbox.value+"&";
	                	//console.log(params);
        			}
        			
        			var search = document.querySelectorAll('input[type="text"][name="SearchForm"]');
        			//console.log(search[0])
        			let pr=""
        			for(let i=0; i< search.length; i++){
        				let currentSearch=search[i]
        				if(currentSearch.value.trim()!="")
        					pr=currentSearch.value
        			}
        			params+="searchForm="+pr
        			
        			loadAjaxDoc('FilterRequest', "GET", params, handleFilter);
        		}
        		
        		$(document).ready(callRequest())