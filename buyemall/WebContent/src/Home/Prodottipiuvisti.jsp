<%@page import="java.util.LinkedList"%>
<%@page import="it.unisa.bean.Prodotto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
    <%
    	List<Prodotto> visitato=(List<Prodotto>)request.getAttribute("visitato");
    
    	if(visitato==null || visitato.size()<=0)
    	{
    		visitato=new LinkedList<>();
    		for(int i=0;i<8;i++)
    			visitato.add(Prodotto.nullProduct());
    	}		
    %>
<!-- Products Start -->
    <div class="container-fluid pt-5">
        <div class="text-center mb-4">
            <h2 class="section-title px-5"><span class="px-2">Prodotti più visti</span></h2>
        </div>
        <div class="row px-xl-5 pb-3">
        	<%for(int i=0; i< 8; i++){
        		Prodotto p=visitato.get(i);
        		%>
            <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                <div class="card product-item border-0 mb-4">
                    <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0 bordertopproduct">
                        <img class="img-fluid w-100 bordertopproduct" src=<%=p.getSprites().get(0).getLink() %> alt="" style="height: 250px; object-fit: contain;">
                    </div>
                    <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                        <h6 class="text-truncate mb-3"><%=p.getNome() %></h6>
                        <div class="d-flex justify-content-center">
                            <h6>$<%=p.getPrezzo() %></h6><h6 class="text-muted ml-2"></h6>
                        </div>
                    </div>
                    <div class="card-footer d-flex justify-content-between bg-light border borderbottomproduct">
                        <a href="Detail?idProdotto=<%=p.getIdProdotto() %>" class="btn btn-sm text-dark p-0"><i class="far mr-1"><img src="img/lente.png" alt = "lente" width="25" height="25" style="padding: 10%;"></i>Dettagli</a>
                        <a href="Detail?idProdotto=<%=p.getIdProdotto() %>" class="btn btn-sm text-dark p-0"><i class="far mr-1"><img src="img/cart.png" alt = "cart" width="25" height="25"></i>Acquista</a>
                    </div>
                </div>
            </div>
            <%} %>
    </div>
    <!-- Products End -->