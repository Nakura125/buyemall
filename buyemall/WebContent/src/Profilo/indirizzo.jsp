<%@page import="it.unisa.bean.Indirizzo"%>
<%@page import="it.unisa.bean.Account"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% Account a2=(Account)request.getAttribute("info"); Indirizzo i=a2.getI(); if(i.getIdIndirizzo()==1)i=null;%>

<div id="Indirizzo" class="container-fluid pt-5">
                <div  class="text-center mb-4">
                    <h2  class="section-title px-5"><span class="px-2">Indirizzo</span></h2>
                    <div class="contact-form">
                        <div id="success"></div>
                        <form action="updateIndirizzo">
                            <div class="control-group">
                                <input name="provincia" <%if(i!=null){ %> value=<%= i.getProvincia() %> type="text"<%} %> class="form-control" id="nome" placeholder="Provincia" 
                                    required="required" data-validation-required-message="Inserisci il tuo nome" />
                                <p class="help-block text-danger"></p>
                            </div>
                            <div class="control-group">
                                <input name="via" <%if(i!=null){ %> value=<%= i.getVia() %> type="text"<%} %>  type="text" class="form-control" id="nome" placeholder="Via" 
                                    required="required" data-validation-required-message="Inserisci il tuo nome" />
                                <p class="help-block text-danger"></p>
                            </div>
                            <div class="control-group">
                                <input name="citta" <%if(i!=null){ %> value=<%= i.getCitta() %> type="text"<%} %>  type="text" class="form-control" id="cognome" placeholder="Citta"
                                     required="required"
                                    data-validation-required-message="Inserisci il tuo cognome" />
                                <p class="help-block text-danger"></p>
                            </div>
                            
                            
                            <div class="control-group">
                                <input name="n_civico" <%if(i!=null){ %> value=<%= i.getN_civico() %> type="text"<%} %>  type="text" class="form-control" id="password" placeholder="n_civico"
                                     required="required"
                                    data-validation-required-message="Inserisci la password" />
                                <p class="help-block text-danger"></p>
                            </div>
                            <div>
                                <button class="btn btn-primary py-2 px-4" type="submit"
                                    id="sendMessageButton">Modifica</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>