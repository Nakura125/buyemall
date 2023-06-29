/**
 * 
 */

 function handleFilter(request){
            	
            		let response = JSON.parse(request.responseText);
            		
            		let search = document.querySelector('input[type="text"][name="SearchForm"]');
        			console.log(search)
            		
            		document.getElementById("Selettore").innerHTML=""
            		document.getElementById("Selettore").innerHTML=
            			'<div class="col-12 pb-1">' +
            		    '<div class="d-flex align-items-center justify-content-between mb-4">' +
            		    '    <form action="javascript:void(0)">' +
            		    '        <div class="input-group">' +
            		    '            <input value="'+search.value+'" type="text" name="SearchForm"  class="form-control" placeholder="Cerca per nome">' +
            		    '            <div class="input-group-append" onclick="callRequest()">' +
            		    '                <span class="input-group-text bg-transparent text-primary">' +
            		    '                    <i class="fa fa-search"></i>' +
            		    '                </span>' +
            		    '            </div>' +
            		    '        </div>' +
            		    '    </form>' +
            		    
            		    '    </div>' +
            		    '</div>';
            		    
            		    for(let i=0; i< response.result.length; i++){
            		    	let element=response.result[i];
            		    	
            		    	document.getElementById("Selettore").innerHTML+=
            		    		'<div class="col-lg-4 col-md-6 col-sm-12 pb-1">' +
            		    	    '    <div class="card product-item border-0 mb-4">' +
            		    	    '        <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0 bordertopproduct">' +
            		    	    '            <img class="img-fluid w-100" src='+element.sprites[0].link+' alt="" style="height: 250px; object-fit: contain;">' +
            		    	    '        </div>' +
            		    	    '        <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">' +
            		    	    '            <h6 class="text-truncate mb-3">'+element.Nome+'</h6>' +
            		    	    '            <div class="d-flex justify-content-center">' +
            		    	    '                <h6>'+element.Prezzo+'</h6><h6 class="text-muted ml-2"></h6>' +
            		    	    '            </div>' +
            		    	    '        </div>' +
            		    	    '        <div class="card-footer d-flex justify-content-between bg-light border borderbottomproduct">' +
            		    	    '            <a href="Detail?idProdotto='+element.idProdotto+'" class="btn btn-sm text-dark p-0"><i class="far mr-1"><img src="img/lente.png" width="25" height="25" style="padding: 10%;"></i>Dettagli</a>' +
            		    	    '            <a href="Detail?idProdotto='+element.idProdotto+'" class="btn btn-sm text-dark p-0"><i class="far mr-1"><img src="img/cart.png" width="25" height="25"></i>Acquista</a>' +
            		    	    '        </div>' +
            		    	    '    </div>' +
            		    	    '</div>';
            		    	
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
            		    for(let i=response.page-1; i< count/8 && i < (response.page+2) ; i++){
            		    	if(response.page==i)
            		    		document.getElementById("Trivia").innerHTML+=
            		    			'<li class="page-item active"><a class="page-link" onclick=pageSwap(this) >'+(i+1)+'</a></li>';
            		    	else
            		    		if(i!=-1){
            		    		document.getElementById("Trivia").innerHTML+=
            		    			'<li class="page-item "><a class="page-link" onclick=pageSwap(this)>'+(i+1)+'</a></li>';
            		    		}
            		    }
            		    	
            		    
            		    
            	}
            	
            	function pageSwap(event){
            		
            		//console.log(event.textContent)
            		callRequest(event.textContent)
            		let btn=document.querySelector(".back-to-top");
            		btn.click();
            	}