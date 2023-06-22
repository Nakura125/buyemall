<%@page import="java.util.LinkedList"%>
<%@page import="it.unisa.bean.MetodiPagamento"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <% List<MetodiPagamento> mt=(List<MetodiPagamento>)request.getAttribute("metodi");
    	if(mt==null) mt=new LinkedList<>();
    %>
<div id="MetodoPagamento" class="container-fluid pt-5">
                <div  class="text-center mb-4">
                    <h2  class="section-title px-5"><span class="px-2">Metodi di Pagamento</span></h2>
                    <div class="contact-form">
                    	<table class="table table-bordered text-center mb-0">
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
		                    			<td class="align-middle" ><a class="btn btn-primary" href="deletePag?num=<%=mt.get(i).getNumero_carta()%>">Delete</a></td>
		                    		</tr>
		                    		<%} %>
		                    	
		                    </tbody>
		                 </table>
                    
                    
                        <div id="success"></div>
                        <form action="addMetodo"  >
                            <div class="control-group">
                                <input name="numcarta" type="text" placeholder="0000 0000 0000 0000" class="form-control" id="numcarta" placeholder="Numero Carta" value=""
                                    required="required" data-validation-required-message="Inserisci il numero della carta" />
                                <p class="help-block text-danger"></p>
                            </div>
                            <div class="control-group">
                                <input name="data" type="date"  class="form-control" id="datascad" placeholder="Data di Scadenza"
                                     required="required"
                                    data-validation-required-message="Inserisci la data di scadenza" />
                                <p class="help-block text-danger"></p>
                            </div>
                            <div class="control-group">
                                <input name="cvc" type="password" class="form-control" id="cvc" placeholder="CVC"
                                     required="required"
                                    data-validation-required-message="Inserisci il CVC" />
                                <p class="help-block text-danger"></p>
                            </div>
                            <div>
                                <button class="btn btn-primary py-2 px-4" type="submit"
                                    id="sendMessageButton">Add</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>