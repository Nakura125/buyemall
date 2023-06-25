/**
 * 
 */
console.log("sadsad");
callRequest()

function callRequest(page){
        			console.log(page)
        			let params=""
        			if(page !=undefined){
        				params+="pageForm="+page+"&";
        			}else{
        				params+="pageForm=1"+"&";
        			}
        			params+="searchForm="+""
        			loadAjaxDoc('FilterRequest', "GET", params, handleFilter);
        		}

 function handleFilter(request){

            	
            		let response = JSON.parse(request.responseText);
            		
            		
            		document.getElementById("Selettore").innerHTML=""
            		
            		    
            		    for(let i=0; i< response.result.length; i++){
            		    	let element=response.result[i];
            	
            		    	document.getElementById("Selettore").innerHTML+=
            		    		'<tr>' +
								    '<td>' + element.idProdotto + '</td>' +
								    '<td>' + element.Nome + '</td>' +
								    '<td>' + element.Prezzo + '</td>' +
								    '<td>' + element.Descrizione+ '</td>' +
								    '<td>' + element.Quantita + '</td>' +
								    '<td>' + element.Tipo + '</td>' +
							    '</tr>';
            		    	
            		    }
            		    
            		    document.getElementById("Selettore").innerHTML+=
            		    	'<div class="col-12 pb-1">' +
                            '  <nav aria-label="Page navigation">' +
                            '    <ul class="pagination justify-content-center mb-3" id="Trivia">' +
                            '      <li class="page-item active"><a class="page-link" href="#">1</a></li>' +
                            '      <li class="page-item"><a class="page-link"  href="#">2</a></li>' +
                            '      <li class="page-item"><a class="page-link" href="#">3</a></li>' +
                            '    </ul>' +
                            '  </nav>' +
                            '</div>';
            		    
            		    let count=response.count;
            		    
            		    document.getElementById("Trivia").innerHTML=""
            		    for(let i=response.page-1; i< count/8 && i < (response.page+2)  ; i++){
            		    	if(response.page==i)
            		    		document.getElementById("Trivia").innerHTML+=
            		    			'<li class="page-item active"><a class="page-link" onclick=pageSwap(this) >'+(i+1)+'</a></li>';
            		    	else{
								if(i!=-1){
            		    		document.getElementById("Trivia").innerHTML+=
            		    			'<li class="page-item "><a class="page-link" onclick=pageSwap(this)>'+(i+1)+'</a></li>';
            		    		}
            		    	}
            		    }
            		    	
            		    
            		    
            	}
            	
            	function pageSwap(event){
            		callRequest(event.textContent)
            	}