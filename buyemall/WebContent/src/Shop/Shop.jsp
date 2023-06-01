<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    
<div class="container-fluid pt-5">
        <div class="row px-xl-5">
        
        	<%@ include file="SideBar/SideBar.jsp" %>
        	
        	
        	<%@ include file="ShopProduct.jsp" %>
        	
        	<script type="text/javascript">
        		function callRequest(page){
        			console.log(page)
        			var params=""
        			if(page !=undefined){
        				params+="pageForm="+page+"&";
        			}else{
        				params+="pageForm=1"+"&";
        			}
        			var checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
        			for (var i = 0; i < checkboxes.length; i++) {
        				var currentCheckbox = checkboxes[i];
        				if(currentCheckbox.value!="null")
	                    	params += currentCheckbox.name+'=' + currentCheckbox.value+"&";
	                	console.log(params);
        			}
        			
        			var search = document.querySelectorAll('input[type="text"][name="SearchForm"]');
        			console.log(search[0])
        			var pr=""
        			for(var i=0; i< search.length; i++){
        				var currentSearch=search[i]
        				if(currentSearch.value.trim()!="")
        					pr=currentSearch.value
        			}
        			params+="searchForm="+pr
        			
        			loadAjaxDoc('FilterRequest', "GET", params, handleFilter);
        		}
        	</script>
        </div>
</div>