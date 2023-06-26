<%@page import="it.unisa.DAO.ProdottoDAO"%>
<%@page import="java.util.LinkedList"%>
<%@page import="it.unisa.bean.Prodotto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%
    	List<Prodotto> prAdvice=(List<Prodotto>)request.getAttribute("ProductAdvice");
    
    	if(prAdvice==null || prAdvice.size()<8)
    	{
    		
    		prAdvice=new LinkedList<>();
    		List<Prodotto> g=(List<Prodotto>) new ProdottoDAO().doRetrieveAllRAND();
    		for(int i=0;i<8-prAdvice.size();i++)
    			prAdvice.add(g.get(i));
    	}		
    %>
    
    <!-- Products Start -->
    <div class="container-fluid py-5">
        <div class="text-center mb-4">
            <h2 class="section-title px-5"><span class="px-2">Ti potrebbero Interessare</span></h2>
        </div>
        <div class="row px-xl-5">
            <div class="col">
                <div class="owl-carousel related-carousel">
                	<% for(int i=0;i<prAdvice.size();i++){ 
                		Prodotto ad=prAdvice.get(i);
                		
                	%>
                    <div class="card product-item border-0">
                        <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0 bordertopproduct">
                            <img class="img-fluid w-100" src=<%=ad.getSprites().get(0).getLink() %> alt="" style="height: 250px; object-fit: contain;">
                        </div>
                        <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                            <h6 class="text-truncate mb-3"><%=ad.getNome() %></h6>
                            <div class="d-flex justify-content-center">
                                <h6>$<%= ad.getPrezzo() %></h6><h6 class="text-muted ml-2"></h6>
                            </div>
                        </div>
                        <div class="card-footer d-flex justify-content-between bg-light border borderbottomproduct">
                            <a href="Detail?idProdotto=<%=ad.getIdProdotto() %>" class="btn btn-sm text-dark p-0"><i class="far mr-1"><img src="img/lente.png" alt="lente" width="25" height="25" style="padding: 10%;"></i>Dettagli</a>
                            <a href="Detail?idProdotto=<%=ad.getIdProdotto() %>" class="btn btn-sm text-dark p-0"><i class="far mr-1"><img src="img/cart.png" alt="cart" width="25" height="25"></i>Acquista</a>
                        </div>
                    </div>
                    <%} %>
                    
                </div>
            </div>
        </div>
    </div>
    <!-- Products End -->