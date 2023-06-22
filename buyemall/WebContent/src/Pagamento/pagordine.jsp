<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <% String err=(String)request.getAttribute("errorMsg"); %>
<div class="container-fluid pt-5">
                <div class="text-center mb-4">
                    <h2 id="MetodoPagamento" class="section-title px-5"><span class="px-2">Aggiungi Metodo di Pagamento</span></h2>
                    <div class="contact-form">
                        <div id="success"></div>
                        <form action="addOrdine">
                        <%if(err!=null) {%>
                        	<div style="color:red"> <%= err %></div>
                        	<%} %>
                            <div class="control-group">
                                <input type="text" name="numcarta" class="form-control" id="numcarta" placeholder="0000 0000 0000 0000" 
                                    required="required" data-validation-required-message="Inserisci il numero della carta" />
                                <p class="help-block text-danger"></p>
                            </div>
                            <div class="control-group">
                                <input type="date" name="data" class="form-control" id="datascad" placeholder="Data di Scadenza"
                                     required="required"
                                    data-validation-required-message="Inserisci la data di scadenza" />
                                <p class="help-block text-danger"></p>
                            </div>
                            <div class="control-group">
                                <input type="password" name="cvc" class="form-control" id="cvc" placeholder="CVC"
                                     required="required"
                                    data-validation-required-message="Inserisci il CVC" />
                                <p class="help-block text-danger"></p>
                            </div>
                            <div>
                                <button  class="btn btn-primary py-2 px-4" type="submit"
                                    id="sendMessageButton">Ordina</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>