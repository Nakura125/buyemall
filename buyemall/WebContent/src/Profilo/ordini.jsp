<%@page import="it.unisa.bean.Stato"%>
<%@page import="it.unisa.bean.Prodotto"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.List"%>
<%@page import="it.unisa.bean.Ordine"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <% List<Ordine> orco=(List<Ordine>)request.getAttribute("ordiniPr"); 
    
    if(orco==null || orco.size()==0){orco=new LinkedList<>();orco.add(Ordine.nullOrdine());}
   
    %>
<div id="Ordini"  class="container-fluid pt-5">
                <div class="text-center mb-4">
                    <h2  class="section-title px-5"><span class="px-2">Ordini</span></h2>
                    <div class="contact-form">
                    	<table class="table table-bordered text-center mb-0">
                    	 <caption></caption>
		                    <thead class="bg-secondary text-dark">
		                        <tr>
		                            <th>image</th>
		                            <th>Data</th>
		                            <th>Stato</th>
		                            <th>Prezzo</th>
		                            <th>Azione</th>
		                        </tr>
		                    </thead>
		                    
		                    <tbody class="align-middle">
		                    <%for(int i=0;i<orco.size();i++) {%>
		                    <%String image=null;if(orco.get(i).getList().size()!=0 ){image=orco.get(i).getList().get(0).getSprites().get(0).getLink();} %>
		                    	<tr>
		                    		<td class="align-middle" ><img style="width: 50px;" alt=<%=Prodotto.nullProduct()%> src=<%=image %>></td>
		                    		<td class="align-middle"><%= orco.get(i).getData().toString() %></td>
		                    		<td class="align-middle"><%= orco.get(i).getStato().toString()%></td>
		                    		<td class="align-middle"><%= orco.get(i).getPrezzo()%></td>
		                    		<%if(orco.get(i).getStato()!=Stato.annullato && orco.get(i).getStato()!=Stato.rimborsato){ %><td><a href="updateOrdineStato?idordine=<%=orco.get(i).getIdOrdine() %>" class="btn btn-primary py-2 px-4 "> Annulla</a></td><%}else{ %>
		                    		<td>Già rimborsato</td><%} %>
		                    	</tr>
		                    	<%} %>
		                    </tbody>
                    	</table>
                    </div>
                </div>
            </div>