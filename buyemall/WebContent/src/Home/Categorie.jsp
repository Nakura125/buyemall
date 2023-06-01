<%@page import="java.util.List"%>
<%@page import="it.unisa.bean.Prodotto"%>
<%@page import="it.unisa.bean.Sprites"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
    	List<Prodotto> prCategoriesSet=(List<Prodotto>)request.getAttribute("prCategoriesSet"); 
    	//System.out.println("pr:"+prCategoriesSet);
    	List<Sprites>Set=null;
    	if(prCategoriesSet!=null && prCategoriesSet.size()>0)
    		Set=prCategoriesSet.get(0).getSprites();
    	else
    		Set=Prodotto.nullProduct().getSprites();

    	//-----------------------------
    	List<Prodotto> prCategoriesCard=(List<Prodotto>)request.getAttribute("prCategoriesCard"); 
    	//System.out.println("pr:"+prCategoriesSet);
    	List<Sprites> Card=null;
    	if(prCategoriesCard!=null && prCategoriesCard.size()>0)
    	{
    		//System.out.println("ashis "+prCategoriesCard.size());
    		Card=prCategoriesCard.get(0).getSprites();
    	}
    	else
    		Card=Prodotto.nullProduct().getSprites();

    	//-----------------------------
    	List<Prodotto> prCategoriesRom=(List<Prodotto>)request.getAttribute("prCategoriesRom"); 
    	//System.out.println("pr:"+prCategoriesSet);
    	List<Sprites> Rom=null;
    	if(prCategoriesRom!=null && prCategoriesRom.size()>0)
    		Rom=prCategoriesRom.get(0).getSprites();
    	else
    		Rom=Prodotto.nullProduct().getSprites();
    	
    	
    	//-----------------------------
    	List<Prodotto> prCategoriesBox=(List<Prodotto>)request.getAttribute("prCategoriesBox"); 
    	//System.out.println("pr:"+prCategoriesSet);
    	List<Sprites> Box=null;
    	if(prCategoriesBox!=null&& prCategoriesBox.size()>0)
    		Box=prCategoriesBox.get(0).getSprites();
    	else
    		Box=Prodotto.nullProduct().getSprites();
    	
    	
    %>
<!-- Categories Start -->
    <div class="container-fluid pt-5">
        <div class="row px-1x-1 pb-3">
            <div class="col-lg-3 col-md-6 pb-1">
                <div class="cat-item d-flex flex-column border mb-4 backmacrocat bg-card" style="padding: 30px;">
                    <h5 class="font-weight-semi-bold m-0" style="text-align: center;">Carte</h5>
                        <a href="" class="cat-img position-relative overflow-hidden mb-3 text-center"> 
                        <img class="img-fluid" src=<%= Card.get(0).getLink()%> alt="" style="height: 300px; object-fit: contain;">
                    </a>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 pb-1">
                <div class="cat-item d-flex flex-column border mb-4 backmacrocat bg-card" style="padding: 30px;">
                    <h5 class="font-weight-semi-bold m-0" style="text-align: center;">Boxes</h5>
                    <a href="" class="cat-img position-relative overflow-hidden mb-3 text-center">
                        <img class="img-fluid" src=<%= Box.get(0).getLink()%> alt="" style="height: 300px; object-fit: contain;">
                        
                    </a>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 pb-1">
                <div class="cat-item d-flex flex-column border mb-4 backmacrocat bg-card" style="padding: 30px;">
                    <h5 class="font-weight-semi-bold m-0" style="text-align: center;">Sets</h5>
                    <a href="" class="cat-img position-relative overflow-hidden mb-3 text-center">
                        <img class="img-fluid" src=<%= Set.get(0).getLink()%> alt="" style="height: 300px; object-fit: contain; z-index:1;">
                    </a>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 pb-1">
                <div class="cat-item d-flex flex-column border mb-4 backmacrocat bg-card" style="padding: 30px;">
                    <h5 class="font-weight-semi-bold m-0" style="text-align: center;">Rom</h5>
                    <a href="" class="cat-img position-relative overflow-hidden mb-3 text-center">
                        <img class="img-fluid" src=<%= Rom.get(0).getLink()%> alt="" style="height: 300px; object-fit: contain;">
                    </a>
                </div>
            </div>
        </div>
    </div>
    <!-- Categories End -->