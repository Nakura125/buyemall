
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
    	List<Integer> generazione=(List<Integer>) request.getAttribute("generation");
    	List<Integer> countGeneration=(List<Integer>) request.getAttribute("countGeneration");
		Integer count2=(Integer) request.getAttribute("count");
    
    	if(generazione==null){
    		generazione=new LinkedList<>();
    	}
    	if(count2==null){
    		count2=0;}
    %>
<!-- Generation Start -->
                <div class="mb-5">
                    <h5 class="font-weight-semi-bold mb-4">Filtra per Generazione</h5>
                    <form action="javascript:void(0)">
                        <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                            <input type="checkbox" name="generazioneForm" value="null" class="custom-control-input" onchange="genFilter(this)" checked id="size-all">
                            <label class="custom-control-label" for="size-all">All Generation</label>
                            <span class="badge border font-weight-normal"><%= count2 %></span>
                        </div>
                        <%for(int i=0;i< generazione.size();i++){ %>
                        <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                            <input  type="checkbox" onclick="genFilter(this)" name="generazioneForm" value=<%= generazione.get(i) %> class="custom-control-input" id="size-<%=generazione.get(i) %>" ><!--ricordati di mettere i value-->
                            <label class="custom-control-label" for="size-<%=generazione.get(i) %>"><%= generazione.get(i) %></label>
                            <span class="badge border font-weight-normal"><%= countGeneration.get(i)%></span>
                        </div>
                        <%} %>
                        
                    </form>
                </div>
                <!-- Generation End -->
                
                <script>
	                function genFilter(checkbox) {
	                	console.log(checkbox)
	                	var checkboxes = document.querySelectorAll('input[type="checkbox"][name="generazioneForm"]');

	                	  for (var i = 0; i < checkboxes.length; i++) {
	                	    var currentCheckbox = checkboxes[i];
	                	    
	                	    if (currentCheckbox !== checkbox) {
	                	      currentCheckbox.checked = false;
	                	    }
	                	  }
	                	  
	                	  callRequest();
	                }
                </script>