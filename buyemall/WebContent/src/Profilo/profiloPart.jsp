<%@page import="it.unisa.bean.Account"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <% Account a=(Account)request.getAttribute("info"); %>
<div id="Profilo" class="container-fluid pt-5">
                <div  class="text-center mb-4">
                    <h2  class="section-title px-5"><span class="px-2">Profilo</span></h2>
                    <div class="contact-form">
                        <div id="success"></div>
                        <form action="updateInfo">
                            <div class="control-group">
                                <input name="nome" value=<%= a.getNome() %> type="text" class="form-control" id="nome" placeholder="Nome Dato" 
                                    required="required" data-validation-required-message="Inserisci il tuo nome" />
                                <p class="help-block text-danger"></p>
                            </div>
                            <div class="control-group">
                                <input name="cognome" value=<%= a.getCognome() %> type="text" class="form-control" id="cognome" placeholder="Cognome Dato"
                                     required="required"
                                    data-validation-required-message="Inserisci il tuo cognome" />
                                <p class="help-block text-danger"></p>
                            </div>
                            
                            
                            <div class="control-group">
                                <input name="password" type="password" class="form-control" id="password" placeholder="Password data"
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