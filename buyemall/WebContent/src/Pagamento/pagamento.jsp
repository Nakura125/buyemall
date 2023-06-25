<%@page import="java.util.LinkedList"%>
<%@page import="it.unisa.bean.MetodiPagamento"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <% List<MetodiPagamento> mt=(List<MetodiPagamento>)request.getAttribute("MetodiPag");
    	if(mt==null) mt=new LinkedList<>();
    %>
<div class="container-fluid mb-5">
        <div class="row border-top px-xl-5">
            
            
            <div class="container-fluid pt-5">
                <div class="text-center mb-4">
                    <h2 id="Ordini" class="section-title px-5"><span class="px-2">Metodi Pagamento che possiedi</span></h2>
                    <div class="contact-form">
                    	  <table class="table table-bordered text-center mb-0">
                    	   <caption></caption>
		                    <thead class="bg-secondary text-dark">
		                        <tr>
		                            <th>numero</th>
		                            <th>Data</th>
		                            <th>cvc</th>
		                            <th>Azioni</th>
		                        </tr>
		                    </thead>
		                    
		                    <tbody class="align-middle">
		                    	
		                    		<%for(int i=0;i< mt.size();i++){ %>
		                    		<tr>
		                    			<td class="align-middle" ><%=mt.get(i).getNumero_carta() %></td>
		                    			<td class="align-middle" ><%=mt.get(i).getData_scadenza().toString() %></td>
		                    			<td class="align-middle" ><%=mt.get(i).getCvc().indexOf(0) %>**</td>
		                    			<td class="align-middle" ><a class="btn btn-primary" 
		                    				href="addOrdine?numcarta=<%= mt.get(i).getNumero_carta()%>&cvc=<%=mt.get(i).getCvc()%>&data=<%= mt.get(i).getData_scadenza().toString() %>"
		                    			>Usa Questo</a></td>
		                    		</tr>
		                    		<%} %>
		                    	
		                    </tbody>
		                 </table>
                    </div>
                </div>
            </div>
            
            <!-- Qui aggiungere un include  per ordine-->
            
            
            
            <!-- Metodo DI pagamento -->
            <%@ include file="pagordine.jsp" %>
        
        
        
        
        
        
        
        
        </div>
    </div>