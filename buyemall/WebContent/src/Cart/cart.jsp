<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.LinkedList"%>
<%@page import="it.unisa.bean.Prodotto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%
	List<Prodotto> cart=(List<Prodotto>)request.getAttribute("Cart");
	System.out.println(cart.size());
	if(cart==null || cart.size()<=0)
	{
		cart=new LinkedList<>();
		cart.add(Prodotto.nullProduct());
	}	
%>

<!-- Cart Start -->
    <div class="container-fluid pt-5">
        <div class="row px-xl-5">
            <div class="col-lg-8 table-responsive mb-5">
                <table class="table table-bordered text-center mb-0">
                    <thead class="bg-secondary text-dark">
                        <tr>
                            <th>Products</th>
                            <th>Price</th>
                            <th>Remove</th>
                        </tr>
                    </thead>
                    <tbody class="align-middle">
                    <%for(int i=0; i< cart.size();i++){ %>
                        <tr>
                            <td class="align-middle"><img src=<%= cart.get(i).getSprites().get(0).getLink()%> alt="" style="width: 50px;"> 
                            <a class="text-dark px-2" href="Detail?idProdotto=<%=cart.get(i).getIdProdotto()%>"><%=cart.get(i).getNome() %></a></td>
                            <td class="align-middle"><%=cart.get(i).getPrezzo() %></td>
                            <td class="align-middle"><a href="DeleteCart?idProdotto=<%=cart.get(i).getIdProdotto() %>" class="btn btn-sm btn-primary"><i class="fa fa-times"></i></a></td>
                        </tr>
                        <%} %>
                    </tbody>
                </table>
            </div>
            <div class="col-lg-4">
                <div class="card border-secondary mb-5">
                    <div class="card-header bg-secondary border-0">
                        <h4 class="font-weight-semi-bold m-0">Cart Summary</h4>
                    </div>
                    <div class="card-body">
                        <div class="d-flex justify-content-between mb-3 pt-1">
                            <h6 class="font-weight-medium">Total</h6>
                            <h6 class="font-weight-medium"><%= new DecimalFormat("#.##").format( cart.stream().mapToDouble(Prodotto::getPrezzo).sum()) %></h6>
                        </div>
                        
                    </div>
                    <div class="card-footer border-secondary bg-transparent">
                        <div class="d-flex justify-content-between mt-2">
                            <h5 class="font-weight-bold">Total</h5>
                            <h5 class="font-weight-bold"><%= new DecimalFormat("#.##").format( cart.stream().mapToDouble(Prodotto::getPrezzo).sum()) %></h5>
                        </div>
                        <a href="Pagamento" class="btn btn-block btn-primary my-3 py-3">Proceed To Checkout</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Cart End -->
    
    
