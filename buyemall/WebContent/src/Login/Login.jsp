<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    
 <!-- Login Start -->
    <div class="container-fluid pt-5">
        <div class="text-center mb-4">
            <h2 class="section-title px-5"><span class="px-2">Login</span></h2>
            <div class="contact-form">
                <div id="success" >
                <%String errMsg;
                        if((errMsg=(String) request.getAttribute("errorMsg"))!=null){ %>
                        <p style="color: red"><%=errMsg %></p>
                        <%} %>
                </div>
                <form action="Login" onsubmit="return validateLogin()">
                    <div class="control-group">
                        <input name="email" type="email" class="form-control" id="email" placeholder="Email" required="required"
                            data-validation-required-message="Inserisci la tua email" />
                        <p class="help-block text-danger"></p>
                    </div>
                    <div class="control-group">
                        <input name="password" type="password" class="form-control" id="password" placeholder="Password" required="required"
                            data-validation-required-message="Inserisci la password" />
                        <p class="help-block text-danger"></p>
                    </div>
                    <div>
                        <button class="btn btn-primary py-2 px-4" type="submit" id="submit">Login</button>
                    </div>
                    <div class="custom-control custom-checkbox d-flex flex-column align-items-center justify-content-between mb-3 text-center">
                        <input name="Ricordami" type="checkbox" class="custom-control-input" id="Ricordami">
                        <label class="custom-control-label" for="Ricordami">Ricordami</label>
                    </div>
                        <a class="dropdown-item" href="RgForward">Registrati</a>
                        <a class="dropdown-item" href="#">Password dimenticata?</a>
                </form>
            </div>
        </div>
        
        
        <script type="text/javascript" src="js/validatorEmail.js"></script>
    </div>
    <!-- Login End -->