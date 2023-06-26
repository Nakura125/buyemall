<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- Register Start -->
    <div class="container-fluid pt-5">
        <div class="text-center mb-4">
            <h2 class="section-title px-5"><span class="px-2">Register</span></h2>
                <div class="contact-form">
                    <div id="success">
                    	 <%String errMsg;
                        if((errMsg=(String) request.getAttribute("errorMsg"))!=null){ %>
                        <p style="color: red"><%=errMsg %></p>
                        <%} %>
                    </div>
                    <form action="Register" onsubmit="return validateRegister()">
                        <div class="control-group">
                            <input name="nome" type="text" class="form-control" id="nome" placeholder="Nome"
                                required="required" data-validation-required-message="Inserisci il tuo nome"/>
                            <p class="help-block text-danger"></p>
                        </div>
                        <div class="control-group">
                            <input name="cognome" type="text" class="form-control" id="cognome" placeholder="Cognome"
                                required="required" data-validation-required-message="Inserisci il tuo cognome"/>
                            <p class="help-block text-danger"></p>
                        </div>
                        <div class="control-group">
                            <input name="Username" type="text" class="form-control" id="Username" placeholder="Username"
                                required="required" data-validation-required-message="Inserisci il tuo username"/>
                            <p class="help-block text-danger"></p>
                        </div>
                        <div class="control-group">
                            <input name="email" type="email" class="form-control" id="email" placeholder="Email"
                                required="required" data-validation-required-message="Inserisci la tua email"/>
                            <p class="help-block text-danger"></p>
                        </div>
                        <div class="control-group">
                            <input name="password" type="password" class="form-control" id="password" placeholder="Password"
                                required="required" data-validation-required-message="Inserisci la password"/>
                            <p class="help-block text-danger"></p>
                        </div>
                        <div class="control-group">
                            <input name="passwordCheck" type="password" class="form-control" id="passwordCheck" placeholder="Ripeti Password"
                                required="required" data-validation-required-message="Ripeti Password"/>
                            <p class="help-block text-danger"></p>
                        </div>
                        <div>
                            <input class="btn btn-primary py-2 px-4" type="submit" id="sendMessageButton" value="Registrati"/>
                        </div>
                        <a class="dropdown-item" href="LgForward">Hai già un account? Accedi</a>
                       
                    </form>
                </div>
            </div>
        </div>
   <script type="text/javascript" src="js/validatorEmail.js"></script>
    <!-- Register End -->