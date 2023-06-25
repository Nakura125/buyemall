<%@page import="it.unisa.bean.Prodotto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
    	Prodotto productDetail=(Prodotto)request.getAttribute("ProductDetail");	
    %>
<!-- Shop Detail Start -->
    <div class="container-fluid py-5">
        <div class="row px-xl-5">
            <div class="col-lg-5 pb-5">
                <div id="product">
                    <div class="product border">
                        <div class="carousel-item active">
                            <img class="w-100 h-100" src=<%= productDetail.getSprites().get(0).getLink() %> alt="Image">
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-lg-7 pb-5">
                <h3 class="font-weight-semi-bold"><%=productDetail.getNome() %></h3>
               
                <h3 class="font-weight-semi-bold mb-4">$<%=productDetail.getPrezzo() %></h3>
                <p class="mb-4">Generazione:<%= productDetail.getGenerazione() %></p>
                <p class="mb-4">Quantità:<%= productDetail.getQuantita() %></p>
                
                <div class="d-flex align-items-center mb-4 pt-2">
                    
                    <form action="addCart" id="cambiocart">
                    <input type="hidden" name="idProdotto" value=<%= productDetail.getIdProdotto() %>>
                    <button class="btn btn-primary px-3 cambiocart d-flex">
                            <img class="cartb" src="img/cart black.png" alt="cart black" width="25" height="25">
                            <img class="cartw" src="img/cart white.png" alt="cart white" width="25" height="25">Acquista</button>
                        </form>      
                </div>
               
            </div>
        </div>
        <div class="row px-xl-5">
            <div class="col">
                <div class="nav nav-tabs justify-content-center border-secondary mb-4">
                    <a class="nav-item nav-link active" data-toggle="tab" href="#tab-pane-1">Descrizione</a>
                   
                </div>
                <div class="tab-content">
                    <div class="tab-pane fade show active" id="tab-pane-1">
                        <h4 class="mb-3">Descrizione del Prodotto</h4>
                        <p><%=productDetail.getDescrizione() %></p>
                        </div>

                </div>
            </div>
        </div>
    </div>
    <!-- Shop Detail End -->