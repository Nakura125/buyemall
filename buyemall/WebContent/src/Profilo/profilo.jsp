<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div class="container-fluid mb-5">
        <div class="row border-top px-xl-5">
            <div class="col-lg-3 d-none d-lg-block">
                <a class="btn shadow-none d-flex align-items-center justify-content-between bg-primary text-white w-100"
                    data-toggle="collapse" href="#navbar-vertical" style="height: 65px; margin-top: -1px; padding: 0 30px;">
                    <h6 class="m-0">Impostazioni</h6>
                    <i class="fa fa-angle-down text-dark"></i>
                </a>
                <nav class="collapse show navbar navbar-vertical navbar-light align-items-start p-0 border border-top-0 border-bottom-0"
                    id="navbar-vertical">
                    <div class="navbar-nav w-100 overflow-hidden">
                        <a href="#linkProfilo" class="nav-item nav-link">Profilo</a>
                        <a href="#linkOrdini" class="nav-item nav-link">Ordini</a>
                        <a href="#linkMetodoPagamento" class="nav-item nav-link">Metodi di Pagamento</a>
                    </div>
                </nav>
            </div>
            <%@ include file="profiloPart.jsp" %>
            
            <%@ include file="ordini.jsp" %>
            
            
            <%@ include file="metodipagamento.jsp" %>
       
       		<script src="js/Profilo.js"></script>
        </div>
    </div>